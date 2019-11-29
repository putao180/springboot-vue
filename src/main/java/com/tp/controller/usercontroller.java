package com.tp.controller;

import com.tp.domain.Mail;
import com.tp.domain.User;
import com.tp.response.UserResponse;
import com.tp.respository.UserRespository;
import com.tp.service.SendSimpleMail;
import com.tp.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class usercontroller {


    @Autowired
    private UserService userService;
    @Autowired
    private SendSimpleMail sendSimpleMail;

/*
   @RequiresPermissions(value={"user_findAll"})
*/
    @RequestMapping("/findAll/{size}/{page}")
    public UserResponse findAll(@PathVariable("size")Integer size, @PathVariable("page")Integer page){
        UserResponse us= userService.findAll(page,size);
       System.out.println("这是8082的访问");


        return   userService.findAll(page,size);


    }

    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public String add() {
        return "add";
    }

    @RequestMapping(value = "/insert" ,method = RequestMethod.POST)
    public String insert(User user){
        userService.insert(user);
        return "";
    }
/*
    @RequiresPermissions(value={"user_delete"})
*/

    @RequestMapping(value = "/delete" ,method = RequestMethod.POST)
    public String delete(@RequestBody User user){
        Integer id=user.getId();
        try {
            userService.delete(id);
            return "success";
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "fail";

    }

    @RequestMapping("/findOne")
    public User findOne(@RequestBody User user){
        Integer id = user.getId();
        userService.findById(id);
        return userService.findById(id);
    }

    @RequestMapping(value = "/updateuser" ,method = RequestMethod.POST)
    public User updateuser(@RequestBody User user){

        userService.update(user);
        return userService.update(user);
    }
    @RequestMapping(value = "/log", method = RequestMethod.POST)
    public String log(@RequestBody User user) {
        String name=user.getName();
        String pass=user.getPass();
        System.out.println(name+pass);
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, pass);
            subject.login(usernamePasswordToken);

            if (subject.isAuthenticated()) {
                return "success";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "fail";
    }
    @RequestMapping(value = "/sendmail", method = RequestMethod.POST)
    public String sendmail(@RequestBody Mail mail){
        System.out.println("你是傻傻");
        String to=mail.getTo();
        String subject=mail.getSubject();
        String content=mail.getContent();
        try{
            sendSimpleMail.SendSimpleMail(to,subject,content);
            return "success";
        }catch(Exception e){
            System.out.println("forbiden");
        }

        return "fail";
    }
}
