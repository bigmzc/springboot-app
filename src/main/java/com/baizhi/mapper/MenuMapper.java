package com.baizhi.mapper;

import com.baizhi.entity.Menu;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MenuMapper extends Mapper<Menu> {
    List<Menu> queryAllMenu();
}
