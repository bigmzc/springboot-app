package com.baizhi.controller;

import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/queryAll")
    public String getAllMenu(Model model) {
        List<Menu> menus = menuService.queryAllMenu();
        System.out.println(menus);
        model.addAttribute("list", menus);
        return "forward:/main/main.jsp";
    }

}
