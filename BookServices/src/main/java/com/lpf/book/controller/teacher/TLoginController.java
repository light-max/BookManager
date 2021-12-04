package com.lpf.book.controller.teacher;

import com.lpf.book.constant.GlobalConstant;
import com.lpf.book.model.data.Result;
import com.lpf.book.model.entity.Account;
import com.lpf.book.service.LoginService;
import com.lpf.book.util.ump.ViewModelParameter;
import com.lpf.book.util.ump.ViewModelParameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class TLoginController {
    @Resource
    LoginService service;

    /**
     * 访问登陆页面
     */
    @GetMapping("/teacher/login")
    @ViewModelParameter(key = "role", value = "教师")
    public String login(Model model) {
        return "/login";
    }

    /**
     * 提交登陆请求
     *
     * @param session  用于保存{@link Account}已登录的管理员实例
     * @param username 用户名
     * @param pwd      密码
     */
    @PostMapping(value = "/teacher/login", produces = "text/html")
    public String login(Model model, HttpSession session, String username, String pwd) {
        Result<Account> result = service.teacher(username, pwd);
        if (result.isSuccess()) {
            session.setAttribute("teacher", result.getData());
            return "redirect:/teacher";
        } else {
            login(model);
            model.addAttribute("username", username);
            model.addAttribute("pwd", pwd);
            model.addAttribute("error", result.getMessage());
            return "/login";
        }
    }

    /**
     * @see #login(Model, HttpSession, String, String)
     */
    @PostMapping("/teacher/login")
    @ResponseBody
    public Result<Account> login(HttpSession session, String username, String pwd) {
        Result<Account> result = service.admin(username, pwd);
        if (result.isSuccess()) {
            session.setAttribute("teacher", result.getData());
        }
        return result;
    }

    /**
     * 退出登陆，并且重定向到登陆页面
     */
    @GetMapping("/teacher/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("admin");
        return "redirect:/teacher/login";
    }

    /**
     * 用户未登录的提示页面
     */
    @GetMapping(value = "/teacher/notloggedin", produces = "text/html")
    @ViewModelParameters({
            @ViewModelParameter(key = "action", value = "/teacher/login"),
            @ViewModelParameter(key = "role", value = "教师"),
            @ViewModelParameter(key = "username", value = "teacher1"),
            @ViewModelParameter(key = "pwd", value = "28905754")
    })
    public String notLogin(Model model) {
        model.addAttribute("error", GlobalConstant.noAccess.getMessage());
        return "/login";
    }

    /**
     * 返回用户未登录的错误信息
     */
    @GetMapping("/teacher/notloggedin")
    @ResponseBody
    public Result<Object> notLogin() {
        return Result.error(GlobalConstant.noAccess.getMessage());
    }
}
