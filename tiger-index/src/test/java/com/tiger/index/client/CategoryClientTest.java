package com.tiger.index.client;

import com.tiger.item.pojo.LitemallCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryClientTest {
    @Autowired
    private CategoryClient categoryClient;

    @Test
    public void testQueryCategory() {
        List<LitemallCategory> list = categoryClient.queryCategoryByL1();
        System.out.println("--------------"+list.size());
        for (int i = 0;i<list.size();i++){
            System.out.println(list.get(i).getName());
        }
    }

}