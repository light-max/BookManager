package com.lpf.book.controller.admin;

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

/**
 * 管理员登陆请求
 *
 * @author 李凤强
 */
@Controller
public class ALoginController {
    @Resource
    LoginService loginService;

    /**
     * 访问登陆页面
     */
    @GetMapping("/admin/login")
    @ViewModelParameter(key = "role", value = "管理员")
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
    @PostMapping(value = "/admin/login", produces = "text/html")
    public String login(Model model, HttpSession session, String username, String pwd) {
        Result<Account> result = loginService.admin(username, pwd);
        if (result.isSuccess()) {
            session.setAttribute("admin", result.getData());
            return "redirect:/admin";
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
    @PostMapping("/admin/login")
    @ResponseBody
    public Result<Account> login(HttpSession session, String username, String pwd) {
        Result<Account> result = loginService.admin(username, pwd);
        if (result.isSuccess()) {
            session.setAttribute("admin", result.getData());
        }
        return result;
    }

    /**
     * 退出登陆，并且重定向到登陆页面
     */
    @GetMapping("/admin/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("admin");
        return "redirect:/admin/login";
    }

    /**
     * 用户未登录的提示页面
     */
    @GetMapping(value = "/admin/notloggedin", produces = "text/html")
    @ViewModelParameters({
            @ViewModelParameter(key = "action", value = "/admin/login"),
            @ViewModelParameter(key = "role", value = "管理员"),
            @ViewModelParameter(key = "username", value = "admin"),
            @ViewModelParameter(key = "pwd", value = "1234")
    })
    public String notLogin(Model model) {
        model.addAttribute("error", GlobalConstant.noAccess.getMessage());
        return "/login";
    }

    /**
     * 返回用户未登录的错误信息
     */
    @GetMapping("/admin/notloggedin")
    @ResponseBody
    public Result<Object> notLogin() {
        return Result.error(GlobalConstant.noAccess.getMessage());
    }
}
