package com.dolien.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordFreq {
    @ExcelProperty("词语")
    private String word;
    @ExcelProperty("频次")
    private Integer cnt;
}
