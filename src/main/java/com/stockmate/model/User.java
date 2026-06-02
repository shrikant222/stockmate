package com.stockmate.model;

import com.stockmate.enums.AccountStatus;

public class User extends BaseEntity{

    private String name;

    private String password;

    private String email;

    private String phone;

    private AccountStatus accountStatus;
}
