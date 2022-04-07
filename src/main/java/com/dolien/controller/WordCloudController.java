package com.dolien.controller;

import com.alibaba.fastjson.JSON;
import com.dolien.pojo.WordFreq;
import com.dolien.service.WordCloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@RestController
public class WordCloudController {

    @Autowired
    private WordCloudService wordCloudService;

    @GetMapping("/hello")
    public String hello() throws IOException {
        List<WordFreq> wordFreqs = wordCloudService.wordCloud();
        return JSON.toJSONString(wordFreqs);
    }

    @RequestMapping(value="/download")
    public String downloads(HttpServletResponse response , HttpServletRequest request) throws Exception{
        //要下载的图片地址
        String  path = request.getServletContext().getRealPath("D:\\Codefield\\Code_Java\\jieba");
        String  fileName = "分词分析.xlsx";

        //1、设置response 响应头
        response.reset(); //设置页面不缓存,清空buffer
        response.setCharacterEncoding("UTF-8"); //字符编码
        response.setContentType("multipart/form-data"); //二进制传输数据
        //设置响应头
        response.setHeader("Content-Disposition",
                "attachment;fileName="+ URLEncoder.encode(fileName, "UTF-8"));

        File file = new File(path,fileName);
        //2、 读取文件--输入流
        InputStream input=new FileInputStream(file);
        //3、 写出文件--输出流
        OutputStream out = response.getOutputStream();

        byte[] buff =new byte[1024];
        int index=0;
        //4、执行 写出操作
        while((index= input.read(buff))!= -1){
            out.write(buff, 0, index);
        }
        out.flush();
        out.close();
        input.close();
        return null;
    }
}
