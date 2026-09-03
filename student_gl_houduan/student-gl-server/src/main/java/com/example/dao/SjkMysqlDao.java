package com.example.dao;

import com.example.model.UserStu;
import com.example.model.UserStuIns;
import com.example.model.UserStuSel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SjkMysqlDao {
    //登录查询
    UserStu login(@Param("userstu") UserStu userstu);


    //注册_数据写入
    int signup(@Param("userstu") UserStu userstu);
    //注册_数据验证
    Integer signupyz(@Param("userstu") UserStu userstu);


    //学生信息添加
    int stuIns(@Param("userstuinto") UserStuIns userstuinto);
    //学生信息添加_软删除验证
    int stuInsDel(@Param("userstuinto") UserStuIns userstuinto);
    //查询学生信息_存在验证
    int stuInsYz(@Param("userstuinto") UserStuIns userstuinto);


    //查询学生信息
    List<UserStuIns> stuSel(@Param("userstusel") UserStuSel userstusel);

    //学生信息修改
    int stuUp(@Param("userstuinto") UserStuIns userstuinto);
    //学生信息删除
    int stuDelr(@Param("userstuinto") UserStuIns userstuinto);
    //学生信息恢复
    int stuDelHuiFu(@Param("userstuinto") UserStuIns userstuinto);


    //查询学生信息记录数量
    int stuCount(@Param("userstusel") UserStuSel userstusel);

}
