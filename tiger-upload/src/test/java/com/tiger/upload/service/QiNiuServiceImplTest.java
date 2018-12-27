package com.tiger.upload.service;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.tiger.common.utils.JsonUtils;
import com.tiger.upload.util.QiNiuPutRet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
@Configuration
public class QiNiuServiceImplTest {
    @Autowired
    private IQiNiuService qiNiuService;
    @Autowired
    private Gson gson;

    @Test
    public void testUploadFile() {
        String fileName = "/Users/xinglongfan/Desktop/微服务.jpeg";
        File file = new File(fileName);

        Assert.assertTrue(file.exists());

        try {
            Response response = qiNiuService.uploadFile(file);
            QiNiuPutRet ret = gson.fromJson(response.bodyString(), QiNiuPutRet.class);
            String imageUrl = "http://pkbnsx71f.bkt.clouddn.com/" + ret.key;
            System.out.println("==========="+imageUrl);
            Assert.assertTrue(response.isOK());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        String key = "FvyNceBAaZF6TBh6OZpcEKlhuACG";
        try {
            Response response = qiNiuService.delete(key);
            Assert.assertTrue(response.isOK());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }

}