package com.example.model;

import lombok.Data;

@Data
public class UserStuSel {
    public String sid;
    public String sname;
    public Integer sage;
    public String ssex;
    public String ssion;
    public Integer sdelete;
    public Integer ts=1; //分页页码
    public Integer shul=10; //每页数量
}
