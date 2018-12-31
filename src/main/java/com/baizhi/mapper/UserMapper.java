package com.baizhi.mapper;

import com.baizhi.entity.Province;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    Integer queryActiversUser(@Param("timeLength") int timeLength);
    List<Province> queryUsersDistrubution();
    List<Province> queryUsersDistrubutionBySex(@Param("sex") String sex);


}
