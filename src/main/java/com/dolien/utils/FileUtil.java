package com.dolien.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileUtil {

    public static Set<String> getStopWords() throws IOException {
        // 中文停用词表
        List<String> list = getText("C:\\Users\\Dolien\\Desktop\\pro\\stopwords\\cn_stopwords.txt");
        return new HashSet<>(list);
    }

    public static  List<String> getText(String src) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(src);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String word;
        while ((word = reader.readLine()) != null) {
            list.add(word);
        }
        reader.close();
        inputStream.close();
        return list;
    }
}
