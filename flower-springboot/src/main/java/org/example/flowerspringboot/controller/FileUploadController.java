package org.example.flowerspringboot.controller;


import org.example.flowerspringboot.utils.AliOssUtil;
import org.example.flowerspringboot.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@RestController
public class FileUploadController {

    @PostMapping("upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        String originalFilename=file.getOriginalFilename();

        String fileType=file.getContentType().substring(file.getContentType().indexOf('/')+1);
        System.out.println(fileType);
//        if(fileType.equals("jpeg")){}
//                else if(fileType=="png"){}
//                else if (fileType=="jpg"){}
//                else {return Result.error("文件格式错误");}
//

       if(!(fileType.equals("jpeg")||fileType.equals("png")||fileType.equals("jpg")||fileType.equals("webp"))){
           return Result.error("请上传图片文件");
       }
        String fileName= UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        String url= AliOssUtil.uploadFile(fileName,file.getInputStream());
        return Result.success(url);
    }
}
