package com.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.entity.User;
import com.service.UserService;

@SessionAttributes("currentUser")
@Controller
public class UserController {

    @Resource
    private UserService userService;
    /**
     * 用户登录
     * @param user
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public String login(@RequestParam("userName")String userName,
            @RequestParam("password")String password,Model model) throws Exception{
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        User userresult = userService.loginByUserNameAndPassword(user);
        if(userresult!=null){
            //登录成功
            List<User> lists = userService.selectUserList();
            model.addAttribute("userLists", lists);//回显用户信息
            model.addAttribute("currentUser", userresult.getUsername());    
            return "redirect:main";
        }
        return "error";
    }
    @RequestMapping("/main")
    public String  main(@RequestParam(value="currentPage",defaultValue="1",required=false)int currentPage,Model model){
        model.addAttribute("pagemsg", userService.findByPage(currentPage));//回显分页数据
        return "main";
    }
    /**
     * 跳到编辑页面
     * @param currentPage
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String editpage(@RequestParam("id") int id,
            Model model){
        User user =userService.selectByPrimaryKey(id);
        model.addAttribute("returnUser", user);
        return "edit";
    }
    /**
     * 保存用户数据
     * @return
     */
    @RequestMapping("/save")
    public String save(User user){
        System.out.println(user.toString());
        if(user.getId()==null){
            //id为null是保存
            userService.insertSelective(user);
        }else{
            //有id值为修改
            userService.updateByPrimaryKeySelective(user);
        }   
        return "redirect:main";
    }
    /**
     * 删除用户数据
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam("id") int id){
        userService.deleteByPrimaryKey(id);
        return "redirect:main";
    }
    /**
     * 添加一个用户数据
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model){
        model.addAttribute("returnUser", new User());
        return "edit";
    }
    
    @RequestMapping("/index")
    public String index( ){
        return "index";
    }
}
