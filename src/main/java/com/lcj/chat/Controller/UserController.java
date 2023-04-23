package com.lcj.chat.Controller;

import com.lcj.chat.Entity.User;
import com.lcj.chat.Service.UserService;
import com.lcj.chat.Util.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/user") //这个控制器类的基路由
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("/login") //表示处理post请求，路由为/user/login
    public Result loginController(@RequestParam String uname, @RequestParam String password){
        User user = userService.loginService(uname, password);
        if(user!=null){
            return Result.success(user,"Login success!");
        }else{
            return Result.error("123","Username or password is wrong!");
        }
    }

    @PostMapping("/register")
    public Result registerController(@RequestBody User newUser){
        User user = userService.registerService(newUser);
        if(user!=null){
            return Result.success(user,"Register success!");
        }else{
            return Result.error("456","Username already exists!");
        }
    }
}
