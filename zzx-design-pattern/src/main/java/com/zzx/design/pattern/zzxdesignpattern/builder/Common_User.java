package com.zzx.design.pattern.zzxdesignpattern.builder;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 叮当快药科技集团有限公司</p>
 *
 * @author Daniel Zhou / zzx
 **/
public class Common_User {
    private String username;
    private String password;
    private Integer age;
    private Integer sex;

    public static Common_User.Builder builder() {
        return new Common_User.Builder();
    }

    public Common_User(String username, String password, Integer age, Integer sex) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.sex = sex;
    }

    public static class Builder {
        private String username;
        private String password;
        private Integer age;
        private Integer sex;

        public Builder() {
        }

        public Builder(String username, String password, Integer age, Integer sex) {
            this.username = username;
            this.password = password;
            this.age = age;
            this.sex = sex;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder sex(Integer sex) {
            this.sex = sex;
            return this;
        }

        public Common_User build() {
            return new Common_User(this.username, this.password, this.age, this.sex);
        }

        @Override
        public String toString() {
            return "Builder{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
        }
    }

    @Override
    public String toString() {
        return "Common_User{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", age=" + age +
            ", sex=" + sex +
            '}';
    }
}
