package com.milly.springBootProject.form;

import com.milly.springBootProject.domain.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserForm {
    @NotBlank
    private String username;
    @Length(min = 6,message = "length >= 6")
    private String password;
//    @Pattern(regexp = "")
    private int phone;
    @Email
    private String email;
    @NotBlank
    private String confirmPassword;

    public UserForm() {

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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public User convertToUser() {
        User user = new UserFormConvert().convert(this);
        return user;
    }

    private class UserFormConvert implements FormConvert<UserForm, User> {
        @Override
        public User convert(UserForm userForm) {
            User user = new User();
            BeanUtils.copyProperties(userForm,user);
            return user;
        }
    }

    public boolean confirmPassword() {
        if(this.password.equals(this.confirmPassword)) {
            return true;
        }
        return false;
    }

}
