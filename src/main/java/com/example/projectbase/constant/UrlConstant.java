package com.example.projectbase.constant;

public class UrlConstant {

  public static class Auth {
    private static final String PRE_FIX = "/auth";

    public static final String LOGIN = PRE_FIX + "/login";
    private Auth() {
    }
  }

  public static class User {
    private static final String PRE_FIX = "/user";

    public static final String GET_USERS = PRE_FIX;
    public static final String GET_USER = PRE_FIX + "/{userId}";
    public static final String GET_CURRENT_USER = PRE_FIX + "/current";

    private User() {
    }
  }

  public static class Phone {
    private static final String PRE_FIX = "/phone";
    public static final String CREATE_PHONE = PRE_FIX;
    public static final String GET_ALL_PHONE = PRE_FIX;
    public static final String GET_FILTERED_PHONE = PRE_FIX + "/filtered";
    public static final String GET_PHONE_BY_BRAND = PRE_FIX+ "/brand";
    public static final String GET_PHONE_BY_NAME = PRE_FIX+ "/name";
    public static final String GET_PHONE_BY_ID = PRE_FIX+ "/id";
    public static final String GET_PHONE_BY_PRICE_RANGE = PRE_FIX+ "/priceRange";
    public static final String UPDATE_PHONE = PRE_FIX;
    public static final String DELETE_PHONE = PRE_FIX;
    private Phone() {}
  }

}
