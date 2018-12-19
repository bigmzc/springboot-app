package com.baizhi.service;

import com.baizhi.entity.Menu;
import com.baizhi.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Menu> queryAllMenu() {
        List<Menu> menus = menuMapper.queryAllMenu();
        return menus;
    }
}
