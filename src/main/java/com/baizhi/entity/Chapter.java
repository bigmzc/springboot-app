package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "charpter")
public class Chapter implements Serializable {
    @Id
    @KeySql
    private String id;
    private String title;
    private String size;
    private String duration;
    private String url;
    private Date uploadDate;
    private Integer albumId;
}
