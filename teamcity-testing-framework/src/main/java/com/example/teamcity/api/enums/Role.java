package com.example.teamcity.api.enums;

public enum Role {

    SYSTEM_ADMIN("SYSTEM_ADMIN"),
    PROJECT_ADMIN("PROJECT_ADMIN"),
    PROJECT_DEVELOPER("PROJECT_DEVELOPER"),
    PROJECT_VIEWER("PROJECT_VIEWER"),
    PROJECT_MANAGER(" PROJECT_MANAGER");

    private String text;

    Role(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

}
