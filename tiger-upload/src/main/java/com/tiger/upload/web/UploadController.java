package com.tiger.upload.web;

import com.tiger.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author
 * @create 2018-12-25 18:28
 **/
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

//    @PostMapping("image")
//    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file){
//        String url = uploadService.uploadImage(file);
//        return ""
//    }
}
