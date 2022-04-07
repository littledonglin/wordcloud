package com.dolien.service;

import com.dolien.pojo.WordFreq;
import com.dolien.utils.WrodAnalysis;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class WordCloudService {

    public List<WordFreq> wordCloud() throws IOException {
        String src = "C:\\Users\\Dolien\\Desktop\\pro\\水浒传.txt";
        return WrodAnalysis.analysis(src,1000);
    }
}
