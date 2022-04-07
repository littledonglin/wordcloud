package com.dolien.utils;

import com.alibaba.excel.EasyExcel;
import com.dolien.pojo.WordFreq;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class DownLoadExcel {

    @Test
    public void test() throws IOException {
        String src = "C:\\Users\\Dolien\\Desktop\\pro\\水浒传.txt";
        writeExcel(src);
    }

    public void writeExcel(String src) throws IOException {
        List<WordFreq> list = WrodAnalysis.analysis(src, 100);
        EasyExcel.write("分词分析.xlsx", WordFreq.class).sheet("分词").doWrite(list);
    }
}
