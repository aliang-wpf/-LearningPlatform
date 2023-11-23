package com.wpf.controller;

import com.wpf.pojo.Emp;
import com.wpf.pojo.Result;
import com.wpf.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UpLoadController {
            /*MultipartFile 常见方法：

        - String  getOriginalFilename();  //获取原始文件名
        - void  transferTo(File dest);     //将接收的文件转存到磁盘文件中
        - long  getSize();     //获取文件的大小，单位：字节
        - byte[]  getBytes();    //获取文件内容的字节数组
        - InputStream  getInputStream();    //获取接收到的文件内容的输入流*/

    /*@PostMapping("/upload")
    public Result upLoad(String username, Integer age, MultipartFile image) throws IOException {
        log.info("文件上传:{},{},{}",username,age,image);
        //获取原始文件名
        String oriName = image.getOriginalFilename();
        //原文件扩展名
        String exName = oriName.substring(oriName.lastIndexOf("."));
        //新文件名                  随机+扩展名
        String newName = UUID.randomUUID()+exName;
        //将文件存储在服务器的磁盘目录
        image.transferTo(new File("E:/aaa/" + newName));
        return Result.success();
    }*/
    @Autowired
        private AliOSSUtils aliOSSUtils;
    @PostMapping("/upload")
    public Result upLoad(MultipartFile image) throws IOException {
        log.info("准备上传的文件：{}",image);
        //调用阿里云OSS工具类，将上传上来的文件存入阿里云
        String url = aliOSSUtils.upload(image);
        log.info("已上传的文件：{}",url.substring(url.lastIndexOf("/")));
        return Result.success(url);
    }
}
