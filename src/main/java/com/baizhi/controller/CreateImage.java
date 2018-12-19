package com.baizhi.controller;

import com.baizhi.util.CreateValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/img")
public class CreateImage {

    @RequestMapping("/createImg")
    public void createImage(HttpSession session, HttpServletResponse response)
            throws IOException {
        CreateValidateCode cvc = new CreateValidateCode(120, 30, 4, 20);
        String code = cvc.getCode();
        session.setAttribute("vcode", code);
        //输出图片
        cvc.write(response.getOutputStream());
    }
}
