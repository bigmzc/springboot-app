package com.baizhi.service;

public interface AppService {
    Object queryFirstPage(String uid, String type, String sub_type);

    Object detailOfWen(String id, String uid);
}
