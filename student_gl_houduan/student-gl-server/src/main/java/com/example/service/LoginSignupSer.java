package com.example.service;


import com.example.dao.SjkMysqlDao;
import com.example.jjwtukiter.JwtUkiter;
import com.example.model.UserFanKui;
import com.example.model.UserStu;
import com.example.model.UserStuIns;
import com.example.model.UserStuSel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Component
public class LoginSignupSer {

    @Autowired
    SjkMysqlDao sjkMysqlDao;

    //登录
    public UserFanKui loginSer(UserStu userstu){
        //200/yes 409/用户不存在
        UserStu userstu1 = sjkMysqlDao.login(userstu);
        if (userstu1!=null){
            log.info("{} 登录成功",userstu1.sid);
            userstu1.token = JwtUkiter.loginToken(userstu1.sid,userstu1.sname);
            return new UserFanKui(200,userstu1);
        }
        else {
            log.info("登录失败，用户不存在");
            return new UserFanKui(409, null);
        }

    }

    //注册
    public UserFanKui signupSer(UserStu userstu){
        Integer yz=sjkMysqlDao.signupyz(userstu);
        if( yz!=null ) {
            log.info("{} 注册失败，用户已存在",userstu.sid);
            return new UserFanKui(409, userstu);//用户已存在
        }
        int st= sjkMysqlDao.signup(userstu);
        if(st==1) {
            log.info("{} 注册成功",userstu.sid);
            return new UserFanKui(200, userstu);//注册成功
        }
        else {
            log.info("{} 注册失败，服务器原因",userstu.sid);
            return new UserFanKui(500, userstu);//注册失败
        }
    }

    //学生信息添加
    public UserFanKui stuInsSer(UserStuIns userstuinto){
        int del=sjkMysqlDao.stuInsDel(userstuinto);
        if (del>0){
            int hf = sjkMysqlDao.stuDelHuiFu(userstuinto);
            if (hf>0) {
                log.info("{} 添加成功",userstuinto.sid);
                return new UserFanKui(200, null);
            }
            else {
                log.info("{} 添加失败，服务器原因",userstuinto.sid);
                return new UserFanKui(500, null);
            }
        }
        int cz=sjkMysqlDao.stuInsYz(userstuinto);
        if (cz>0) {
            log.info("{} 添加失败，信息已存在",userstuinto.sid);
            return new UserFanKui(409, null);
        }
        int into=sjkMysqlDao.stuIns(userstuinto);
        if (into>0) {
            log.info("{} 添加成功",userstuinto.sid);
            return new UserFanKui(200, null);
        }
        else {
            log.info("{} 添加失败，服务器原因",userstuinto.sid);
            return new UserFanKui(500, null);
        }
    }

    //查询
    public UserFanKui stuSelSer(UserStuSel userstusel){
        userstusel.ts=(userstusel.ts-1)*userstusel.shul;
        List<UserStuIns> userstuselects=sjkMysqlDao.stuSel(userstusel);
        if (userstuselects!=null){
            log.info("{} 分页查询成功 第{}页/每页数量{}",userstusel.sid,(userstusel.ts/userstusel.shul)+1,userstusel.shul);
            return new UserFanKui(200,userstuselects);
        }
        else{
            log.info("{} 查询失败，信息不存在",userstusel.sid);
            return new UserFanKui(409,null);
        }
    }


    //修改
    public UserFanKui stuUpSer(UserStuIns userstuinto){
        int update=sjkMysqlDao.stuUp(userstuinto);
        if (update>0) {
            log.info("{} 修改成功",userstuinto.sid);
            return new UserFanKui(200, null);
        }
        else {
            log.info("{} 修改失败，服务器原因",userstuinto.sid);
            return new UserFanKui(500, null);
        }
    }

    //逻辑删除
    public UserFanKui stuDelrSer(UserStuIns userstuinto){
        int delete=sjkMysqlDao.stuDelr(userstuinto);
        if (delete>0) {
            log.info("{} 删除成功",userstuinto.sid);
            return new UserFanKui(200, null);
        }
        else {
            log.info("{} 删除失败，服务器原因",userstuinto.sid);
            return new UserFanKui(500, null);
        }
    }
    //恢复
    public UserFanKui stuDelHuiFuSer(UserStuIns userstuinto){
        int huiFu=sjkMysqlDao.stuDelHuiFu(userstuinto);
        if (huiFu>0) {
            log.info("{} 恢复成功",userstuinto.sid);
            return new UserFanKui(200, null);
        }
        else {
            log.info("{} 恢复失败，服务器原因",userstuinto.sid);
            return new UserFanKui(500, null);
        }
    }


    //查询学生信息记录数量
    public UserFanKui stuCountSer(UserStuSel userstusel){
        int count=sjkMysqlDao.stuCount(userstusel);
        log.info("查询    sdelete= {} 的数据有 {} 条",userstusel,count);
        return new UserFanKui(200,count);
    }
}