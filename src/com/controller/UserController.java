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
     * �û���¼
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
            //��¼�ɹ�
            List<User> lists = userService.selectUserList();
            model.addAttribute("userLists", lists);//�����û���Ϣ
            model.addAttribute("currentUser", userresult.getUsername());    
            return "redirect:main";
        }
        return "error";
    }
    @RequestMapping("/main")
    public String  main(@RequestParam(value="currentPage",defaultValue="1",required=false)int currentPage,Model model){
        model.addAttribute("pagemsg", userService.findByPage(currentPage));//���Է�ҳ����
        return "main";
    }
    /**
     * �����༭ҳ��
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
     * �����û�����
     * @return
     */
    @RequestMapping("/save")
    public String save(User user){
        System.out.println(user.toString());
        if(user.getId()==null){
            //idΪnull�Ǳ���
            userService.insertSelective(user);
        }else{
            //��idֵΪ�޸�
            userService.updateByPrimaryKeySelective(user);
        }   
        return "redirect:main";
    }
    /**
     * ɾ���û�����
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam("id") int id){
        userService.deleteByPrimaryKey(id);
        return "redirect:main";
    }
    /**
     * ���һ���û�����
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
