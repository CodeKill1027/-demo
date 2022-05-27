package com.example.Springcloud.controller;


import org.apache.http.Consts;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * @author yzj
 */
@RestController
@RequestMapping("/SpringSource")
public class Upload {
    @PostMapping("/upload")   //单个文件,img 视频 音乐
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
//        String realPath = req.getSession().getServletContext().getRealPath("/uploadFile");
        // 放在static下的原因是，存放的是静态文件资源，即通过浏览器输入本地服务器地址，加文件名时是可以访问到的
//        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/";
        File targetFile = new File("file");
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream bos = null;
        try {
            String str = targetFile + "/" + file.getOriginalFilename();    //C:\Users\ASUS\Desktop\file\bg.jpg
            bos = new FileOutputStream(str);//FileOutputStream读取流的时候如果是文件夹，就会出错，无论怎么读，都拒绝访问，应该在读取的目录后面加上文件名
            bos.write(file.getBytes()); //前端文件传过来后端将文件通过流写入文件夹中

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            bos.flush();
            bos.close();
        }
        return "上传成功123";
    }

    @GetMapping("/download")  //给前端下载链接
    public String download(HttpServletResponse response) throws IOException {
        FileInputStream fis = null;
        ServletOutputStream os = null;
        try {
            String filename = "96162634a0f9ceefbc736799b1b335f2.png";
            String filePath = "file/" + filename;
            File file = new File(filePath);
            if (file.exists()) {
                //响应头的下载内容格式
                response.setContentType("application/octet-stream");
                response.setHeader("content-type", "application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename, "utf8"));
                fis = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                os = response.getOutputStream();
//                FileOutputStream os = new FileOutputStream(new File("C:\\Users\\ASUS\\Desktop\\"+filename));
                int i = fis.read(bytes);    //从文件夹中读取文件通过流写入指定文件中
                while (i != -1) {
                    os.write(bytes);
                    i = fis.read(bytes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert os != null;
            os.close();
            assert fis != null;
            fis.close();
        }
        return "下载成功123";
    }

    @GetMapping("/base")
    public String toBase64() {    //把文件或者图片以字符流传给前端
//         rebase64Str("data:image;base64,"+res.data)
            String fileAbsolutePath="file/96162634a0f9ceefbc736799b1b335f2.png";
        FileInputStream inputStream = null;
        String base64Str = "";
        try {
            Base64.Encoder encoder = Base64.getEncoder();
            inputStream = new FileInputStream(fileAbsolutePath);
            int available = inputStream.available();
            byte[] bytes = new byte[available];
            inputStream.read(bytes);
            base64Str = encoder.encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert inputStream != null;
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return base64Str;
    }



    @RequestMapping(value = "/preview2", method = RequestMethod.GET)  //向前端发送音频 音乐，视频
    @ResponseBody
    // <video controls autoPlay src="http://localhost:8892/SpringSource/preview2" />
    // <audio src="http://localhost:8892/SpringSource/preview2" controls autoPlay />
    public void getPreview2( HttpServletResponse response) {
        try {
            File file = new File("file/高枫-大中国.mp3");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader("Content-Disposition", "attachment; filename="+file.getName().replace(" ", "_"));
            InputStream iStream = new FileInputStream(file);
            IOUtils.copy(iStream, response.getOutputStream());
            response.flushBuffer();
        } catch (java.nio.file.NoSuchFileException e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping
    public String folder(MultipartFile[] files) throws IOException { //文件夹上传
        for (MultipartFile file : files) {
            //上传文件目录
            String uploadFolder = "file";
            String fileName = file.getOriginalFilename();
            File uploadFile = new File(uploadFolder,fileName);
            //判断上传文件目录是否存在，如果不存在就创建
            if (!uploadFile.getParentFile().exists()) {
                uploadFile.getParentFile().mkdirs();
            }
            file.transferTo(uploadFile);
        }
        return "上传成功!!";
    }






}



