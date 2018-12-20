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
    public List<Menu> queryAllMainMenu() {
        List<Menu> menus = menuMapper.queryAllMainMenu();
        return menus;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Menu> querySubMenus(String parentId) {
        Menu menu = new Menu();
        menu.setParentId(Integer.parseInt(parentId));
        List<Menu> subMenus = menuMapper.select(menu);
        return subMenus;
    }
}
