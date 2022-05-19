package com.example.demo.utils;

import com.example.demo.entity.Filetext;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @author yzj
 */
@Service
public class Fileio {
    /**
     * 重写
     *
     * @param filetext filetext
     */
    public void rewrite(Filetext filetext) {
        File file = new File(filetext.getFileName());//我们在该类的位置创建一个新文件
        if (file.exists()) {
            FileWriter f = null;//创建文件写入对象
            BufferedWriter f1 = null;//创建字符流写入对象
            try {
                //这里把文件写入对象和字符流写入对象分开写了
                f = new FileWriter(filetext.getFileName());//创建一个名为cc.txt的文件
                f1 = new BufferedWriter(f);
                    f1.write(filetext.getText());//把String中的字符写入文件

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            } finally {//如果没有catch 异常，程序最终会执行到这里
                try {
                    assert f1 != null;
                    f1.close();
                    f.close();//关闭文件
                } catch (Exception e2) {
                    // TODO: handle exception
                }
            }
        } else {
            FileWriter f = null;//创建文件写入对象
            BufferedWriter f1 = null;//创建字符流写入对象
            try {
                boolean newFile = file.createNewFile();
                if (newFile){
                    try {
                        //这里把文件写入对象和字符流写入对象分开写了
                        f = new FileWriter(filetext.getFileName());//创建一个名为cc.txt的文件
                        f1 = new BufferedWriter(f);
                        //通过循环遍历上面的String 数组中的元素
                            f1.write(filetext.getText());//把String中的字符写入文件
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    assert f1 != null;
                    f1.close();
                    f.close();//关闭文件
                } catch (Exception e2) {
                    // TODO: handle exception
                    e2.printStackTrace();
                }
            }
        }
    }



    public void read( String FileName){
        File file=new File(FileName);
        FileReader f=null;//文件读取对象
        BufferedReader f1=null;//字符流对象
        if (file.exists()){
            try {
                f=new FileReader(file);
                f1=new BufferedReader(f);
                //循环打印cc文件中的每行数据
                String str=null;
                while((str=f1.readLine())!=null) {
                    System.out.println(str);
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }finally {
                try {
                    assert f1 != null;
                    f1.close();
                    f.close();
                } catch (Exception e2) {
                    // TODO: handle exception
                    e2.printStackTrace();
                }
            }
        }
        else {
            Object absolutePath = file.getAbsolutePath();
            System.out.println("该路径下:"+absolutePath+"  无"+file.getName()+"文件");
        }
    }



}
