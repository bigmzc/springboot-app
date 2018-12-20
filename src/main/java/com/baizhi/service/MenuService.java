package com.baizhi.service;

import com.baizhi.entity.Menu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface MenuService {
    List<Menu> queryAllMainMenu();

    List<Menu> querySubMenus(String parentId);
}
