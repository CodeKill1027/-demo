package com.example.Springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletResponse;


/**
 * @author yzj
 */
@RestController
public class Foder {
    @GetMapping("/download")
    public void download(HttpServletResponse response, @RequestParam("path") String path) throws Exception {
        // 转为path
        Path folderPath = Paths.get(path);
        // 响应为二进制数据流
        response.setContentType("application/octet-stream");
        if (!Files.isDirectory(folderPath)) { // 文件下载
            File file = new File(path);
            if (!file.exists()) {
               // LOGGER.error("file not exists: " + path);
                throw new IOException("file not exists: " + path);
            }
            try (InputStream input = new FileInputStream(file);
                 OutputStream output = response.getOutputStream()) {
                // 写入数据
                int len;
                // 设置10kb缓冲区
                byte[] buffer = new byte[1024 * 10];
                // 文件设置，附件的形式打开
                response.setHeader("content-disposition", "attachment; filename=" + file.getName());
                while ((len = input.read(buffer)) > 0) {
                    output.write(buffer, 0, len);
                }
                output.flush();
            } catch (IOException e) {
               // LOGGER.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        } else { // 文件夹下载
            // 文件设置，附件形式打开
            response.setHeader("content-disposition", "attachment; filename=" + new String((folderPath.getFileName().toString() + ".zip").getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            try (ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())) {
                // 文件路径/ID
                LinkedList<String> filePath = new LinkedList<>();

                Files.walkFileTree(folderPath, new FileVisitor<Path>() {

                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        // 开始遍历目录
                        if (!dir.equals(folderPath)) {
                            filePath.addLast(dir.getFileName().toString());
                            // 写入目录
                            ZipEntry zipEntry = new ZipEntry(filePath.stream().collect(Collectors.joining("/", "", "/")));
                            try {
                                zipOutputStream.putNextEntry(zipEntry);
                                zipOutputStream.flush();
                            } catch (IOException e) {
                               // LOGGER.error(e.getMessage(), e);
                                throw new RuntimeException(e);
                            }
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        // 开始遍历文件
                        try (InputStream inputStream = Files.newInputStream(file)) {

                            // 创建一个压缩项，指定名称
                            String fileName = filePath.size() > 0
                                    ? filePath.stream().collect(Collectors.joining("/", "", "")) + "/" + file.getFileName().toString()
                                    : file.getFileName().toString();

                            ZipEntry zipEntry = new ZipEntry(fileName);
                            // 添加到压缩流
                            zipOutputStream.putNextEntry(zipEntry);
                            // 写入数据
                            int len;
                            // 设置10kb缓冲区
                            byte[] buffer = new byte[1024 * 10];
                            while ((len = inputStream.read(buffer)) > 0) {
                                zipOutputStream.write(buffer, 0, len);
                            }

                            zipOutputStream.flush();
                        } catch (IOException e) {
                          //  LOGGER.error(e.getMessage(), e);
                            throw new RuntimeException(e);
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        // 结束遍历目录
                        if (!filePath.isEmpty()) {
                            filePath.removeLast();
                        }
                        return FileVisitResult.CONTINUE;
                    }
                });
                zipOutputStream.closeEntry();
            } catch (IOException e) {
               // LOGGER.error(e.getMessage(), e);
            }
        }
    }


}
