package com.lgx.springbootredis.controller;

import com.github.pagehelper.PageInfo;
import com.lgx.springbootredis.listener.MyHttpSessionListener;
import com.lgx.springbootredis.pojo.User;
import com.lgx.springbootredis.pojo.query.UserQuery;
import com.lgx.springbootredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author lgx
 * @version 1.0
 * @date 2022/9/18 12:50
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/tologin")
    public ModelAndView loginto(User user, HttpServletRequest request){
        System.out.println(user);
        ModelAndView mv=new ModelAndView();
        Boolean flag = userService.isUser(user);
        if(flag){
            HttpSession session = request.getSession();
            session.setAttribute("loginuser",user);
            mv.setViewName("redirect:/login");
        }else{
            mv.setViewName("login");
        }
        return mv;
    }
    @GetMapping("/adminlogin")
    public ModelAndView toLogin(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @GetMapping("/login")
    public ModelAndView index(ModelAndView modelAndView, UserQuery  userQuery) {
        PageInfo<User> userPageInfo = userService.listUserByName(userQuery);
        modelAndView.setViewName("index");
        modelAndView.addObject("page",userPageInfo);
        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView indexByName(ModelAndView modelAndView, UserQuery  userQuery) {
        PageInfo<User> userPageInfo = userService.listUserByName(userQuery);
        modelAndView.setViewName("index");
        modelAndView.addObject("page",userPageInfo);
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView deleteUser( ModelAndView modelAndView,RedirectAttributes redirectAttributes,@PathVariable Integer id){
        Boolean flag = userService.deleteUserById(id);
        if(flag){
            redirectAttributes.addFlashAttribute("msg","删除用户成功");
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }else{
            redirectAttributes.addFlashAttribute("msg","删除用户失败");
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
    }
    @GetMapping("/edit/{id}")
    public ModelAndView queryUserById(@PathVariable("id") Integer id){
        User user = userService.queryUserById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editUser");
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    //更新用户信息
    @PostMapping("/edit")
    public ModelAndView updateUser(User user,RedirectAttributes redirectAttributes,ModelAndView modelAndView){
        Integer id = user.getId();
        if(id ==null){//新增
            boolean flag = userService.addUser(user);
            if(flag){
                redirectAttributes.addFlashAttribute("msg","新增用户成功");
                modelAndView.setViewName("redirect:/");
                return modelAndView;
            }else{
                redirectAttributes.addFlashAttribute("msg","新增用户成功");
                redirectAttributes.addFlashAttribute("msg","新增用户失败");
                modelAndView.setViewName("redirect:/");
                return modelAndView;
            }
        }else{//更新用户信息
            Boolean flag = userService.updateUser(user);
            if(flag){
                redirectAttributes.addFlashAttribute("msg","更新用户成功");
                modelAndView.setViewName("redirect:/");
                return modelAndView;
            }else{
                redirectAttributes.addFlashAttribute("msg","更新用户失败");
                modelAndView.setViewName("redirect:/");
                return modelAndView;
            }
        }

    }
    @GetMapping("/update")
    public ModelAndView toAddUser(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("editUser");
        mv.addObject(new User());
        return mv;
    }

    @RequestMapping("/online")
    @ResponseBody
    public Object online(HttpServletRequest httpServletRequest) {
        Object count=httpServletRequest.getServletContext().getAttribute("count");
        return  "当前在线人数：" +count+ "人";
    }
}
