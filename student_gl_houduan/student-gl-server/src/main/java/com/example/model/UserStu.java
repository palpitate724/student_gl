package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserStu {
    public String sid;
    public String sname;

    @JsonIgnore
    public String spassword;

    public Integer sjiaose;
    public String token;
}
