package com.example.aptify_login;

public class SignUpData {
    private String email;
    private String fullname;
    private String password;
    private String age;

    public SignUpData() {
    }

    public SignUpData(String email, String fullname, String password, String age) {
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
