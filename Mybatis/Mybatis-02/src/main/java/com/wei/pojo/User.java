package com.wei.pojo;

public class User {
    private int Id;
    private String Name;
    private String pwd;

    public User(){

    }

    public User(int Id, String Name, String pwd){
        this.Id=Id;
        this.Name=Name;
        this.pwd=pwd;
    }
//fn+command+in
    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
