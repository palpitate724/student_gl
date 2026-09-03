package com.example.controller;

import com.example.model.UserFanKui;
import com.example.model.UserStu;
import com.example.model.UserStuIns;
import com.example.model.UserStuSel;
import com.example.service.LoginSignupSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class LoginSignupCon {

    @Autowired
    public  LoginSignupSer loginSignupSer;

    @RequestMapping("/login")//登录
    public UserFanKui loginCon(@RequestBody(required = false) UserStu userstu){
        return loginSignupSer.loginSer(userstu);
    }
    @RequestMapping("/signup")// 注册
    public UserFanKui signupCon(@RequestBody(required = false) UserStu userstu){
        return loginSignupSer.signupSer(userstu);
    }


    @RequestMapping("/stuins")//学生信息添加
    public UserFanKui stuInsCon(@RequestBody UserStuIns userstuinto){
        return loginSignupSer.stuInsSer(userstuinto);
    }


    @RequestMapping("/stusel")//查询
    public UserFanKui stuselCon(@RequestBody(required = false) UserStuSel userstusel){
        return loginSignupSer.stuSelSer(userstusel);
    }

    @RequestMapping("/stuup")//修改
    public UserFanKui stuUpCon(@RequestBody(required = false) UserStuIns userstuinto){
        return loginSignupSer.stuUpSer(userstuinto);
    }


    @RequestMapping("/studelr")//逻辑删除
    public UserFanKui stuDelrCon(@RequestBody(required = false) UserStuIns userstuinto){
        return loginSignupSer.stuDelrSer(userstuinto);
    }
    @RequestMapping("/studelrhuifu")//恢复
    public UserFanKui studelrhuifuCon(@RequestBody(required = false) UserStuIns userstuinto){
        return loginSignupSer.stuDelHuiFuSer(userstuinto);
    }


    @PostMapping("/stucount")//查询学生数量
    public UserFanKui stuCountCon(@RequestBody UserStuSel userstusel){
        return loginSignupSer.stuCountSer(userstusel);
    }


}
