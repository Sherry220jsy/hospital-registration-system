package com.sherry.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageModelDTO implements Serializable {
    //页码
    private int page;

    //每页显示记录数
    private int pageSize;

    private Long doctorId;
}
