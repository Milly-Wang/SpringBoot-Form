package com.milly.springBootProject.web;

import com.milly.springBootProject.domain.User;
import com.milly.springBootProject.domain.UserRepository;
import com.milly.springBootProject.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/register")
    public String registerPost(@Valid UserForm userForm, BindingResult result){
//        User user = new UserFormConvert().convert(userForm);
        if(result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            for(FieldError error : fieldErrors) {
                System.out.println(error.getField() + error.getDefaultMessage() + error.getCode());
            }
            return "register";
        }
        User user = userForm.convertToUser();
        userRepository.save(user);
        return "redirect:/login";
    }

//    public User convertFor(UserForm userForm) {
//        User user = new User();
//        BeanUtils.copyProperties(userForm,user);
//        return user;
//    }
//    public String registerPost(User user){
//        userRepository.save(user);
//        return "redirect:/login";
//    }
//    public String registerPost(@RequestParam String username,
//                               @RequestParam String password,
//                               @RequestParam String email,
//                               @RequestParam int phone){
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setEmail(email);
//        user.setPhone(phone);
//        userRepository.save(user);
//        return "redirect:/login";
//    }
}
