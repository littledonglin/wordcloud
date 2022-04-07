package com.dolien.utils;

import com.dolien.pojo.WordFreq;
import com.huaban.analysis.jieba.JiebaSegmenter;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WrodAnalysis {

    @Test
    public void test() throws IOException {
        String src = "C:\\Users\\Dolien\\Desktop\\pro\\西游记.txt";
        List<WordFreq> analysis = analysis(src);
        System.out.println(analysis.size());
        for(WordFreq wordFreq: analysis){
            System.out.println(wordFreq);
        }
    }

    public static List<WordFreq> analysis(String src, int k) throws IOException {
        List<WordFreq> list = analysis(src);
        if(k > list.size()){
            k = list.size();
        }
        ArrayList<WordFreq> result = new ArrayList<>();
        for(int i = 0; i < k; i++){
            result.add(list.get(i));
        }
        return result;
    }

    public static List<WordFreq> analysis(String src) throws IOException {
        List<String> list = FileUtil.getText(src);
        Set<String> stopWords = FileUtil.getStopWords();

        // 分词
        JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
        ArrayList<String> result = new ArrayList<>();
        for(String s:list){
            List<String> process = jiebaSegmenter.sentenceProcess(s);
            result.addAll(process);
        }

        // 统计词频
        Map<String, Integer> map = new HashMap<>();
        for(String s: result){
            // 停用词
            if(stopWords.contains(s)) continue;
            // 标点
            if(isPunctuation(s)) continue;
            // 单个字去除
            if(s.length() == 1) continue;
            map.put(s, map.getOrDefault(s, 0)+1);
        }

        // 放入 pojo 中
        ArrayList<WordFreq> wordFreqList = new ArrayList<>();
        for(String s: map.keySet()){
            wordFreqList.add(new WordFreq(s, map.get(s)));
        }
        wordFreqList.sort((a, b) -> b.getCnt()-a.getCnt());
        return wordFreqList;
    }

    public static boolean isPunctuation(String s) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(s);
        return !m.find();
    }
}
