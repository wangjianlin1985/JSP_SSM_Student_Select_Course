// 
// 
// 

package com.cms.controller;

import java.util.Random;
import java.awt.geom.Rectangle2D;
import java.awt.font.FontRenderContext;
import java.awt.Graphics2D;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cms.entity.Auth;
import java.util.List;
import com.cms.entity.User;
import com.alibaba.fastjson.JSON;
import com.cms.service.Login;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.cms.service.StudentService;
import com.cms.service.TeacherService;
import com.cms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import com.cms.service.AuthService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/login" })
public class LoginController
{
    @Autowired
    AuthService authService;
    @Autowired
    AdminService adminServiceImpl;
    @Autowired
    TeacherService teacherServiceImpl;
    @Autowired
    StudentService studentServiceImpl;
    
    @RequestMapping({ "/loginPage" })
    public ModelAndView toLoginPage() {
        return new ModelAndView("login");
    }
    
    @ResponseBody
    @RequestMapping({ "/doLogin" })
    public String doLogin(@RequestParam(defaultValue = "") final String username, @RequestParam(defaultValue = "") final String password, @RequestParam(defaultValue = "0") final int userType, @RequestParam(defaultValue = "") final String verifyCode, final HttpSession session) {
        final String sessionVerifyCode = (String)session.getAttribute("verifyCode");
        if (sessionVerifyCode == null || !sessionVerifyCode.equals(verifyCode.toUpperCase())) {
            return "code_error";
        }
        Login loginUser = null;
        if (userType == 1) {
            loginUser = (Login)this.adminServiceImpl;
        }
        else if (userType == 2) {
            loginUser = (Login)this.teacherServiceImpl;
        }
        else if (userType == 3) {
            loginUser = (Login)this.studentServiceImpl;
        }
        final User user = loginUser.loginValidate(username, password.toUpperCase());
        if (user != null) {
            final List<Auth> urlList = this.authService.getUrlList(user.getUserType());
            user.setUrlList(urlList);
            session.setAttribute("user", (Object)user);
            return JSON.toJSONString((Object)user);
        }
        return "false";
    }
    
    @RequestMapping({ "/out" })
    public ModelAndView loginOut(final HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/");
    }
    
    @RequestMapping({ "/getVerifyCode" })
    public void getVerifyCode(final HttpServletResponse response, final HttpSession session) {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        session.setAttribute("verifyCode", (Object)this.drawCodeImg(output));
        try {
            final ServletOutputStream out = response.getOutputStream();
            output.writeTo((OutputStream)out);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String drawCodeImg(final ByteArrayOutputStream output) {
        String code = "";
        for (int i = 0; i < 4; ++i) {
            code = String.valueOf(code) + this.randomChar();
        }
        final int width = 70;
        final int height = 36;
        final BufferedImage bImage = new BufferedImage(width, height, 5);
        final Font font = new Font("Times New Roman", 0, 20);
        final Graphics2D graphics = bImage.createGraphics();
        graphics.setFont(font);
        graphics.setColor(new Color(66, 2, 82));
        graphics.setBackground(new Color(226, 226, 240));
        graphics.clearRect(0, 0, width, height);
        final FontRenderContext context = graphics.getFontRenderContext();
        final Rectangle2D bounds = font.getStringBounds(code, context);
        final double x = (width - bounds.getWidth()) / 2.0;
        final double y = (height - bounds.getHeight()) / 2.0;
        final double ascent = bounds.getY();
        final double baseY = y - ascent;
        graphics.drawString(code, (int)x, (int)baseY);
        graphics.dispose();
        try {
            ImageIO.write(bImage, "jpg", output);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }
    
    private char randomChar() {
        final Random r = new Random();
        final String str = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
        return str.charAt(r.nextInt(str.length()));
    }
}
