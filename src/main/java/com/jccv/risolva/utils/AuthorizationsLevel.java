package com.jccv.risolva.utils;

public class AuthorizationsLevel {

    public static final String MAX_LEVEL = "hasAuthority('SUPERADMIN')";
    public static final String ADMIN_LEVEL = "hasAuthority('SUPERADMIN') or hasAuthority('ADMIN')";
    public static final String USER_LEVEL = "hasAuthority('SUPERADMIN') or hasAuthority('ADMIN') or hasAuthority('USER')";


}