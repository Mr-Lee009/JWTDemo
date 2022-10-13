package com.mta.jwt.demo.model;

import org.springframework.beans.BeanUtils;

public class UserEntity {
    private long id;
    private String username;
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static void main(String[] args) {
        UserEntity u1 = new UserEntity();
        u1.id = 1000L;
        u1.password = "1111111";
        u1.username = "cao cao";
        System.out.println(u1);

        Human u2 = new Human();
        BeanUtils.copyProperties(u1,u2);
        System.out.println(u2);
    }

    static class Human{
        public String username;
        public String password;
        public String uuid;
        public String classZ;

        public Human(){

        }
        public Human(String name, String password, String uuid, String classZ) {
            this.username = name;
            this.password = password;
            this.uuid = uuid;
            this.classZ = classZ;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getClassZ() {
            return classZ;
        }

        public void setClassZ(String classZ) {
            this.classZ = classZ;
        }

        @Override
        public String toString() {
            return "Human{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", uuid='" + uuid + '\'' +
                    ", classZ='" + classZ + '\'' +
                    '}';
        }
    }
}
