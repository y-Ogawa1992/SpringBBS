package jp.co.bbs.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.bbs.dto.user.UserDto;
import jp.co.bbs.form.UserForm;
import jp.co.bbs.service.UserService;

@Controller
public class UserControlController {

    @Autowired
    private UserService userService;

    //top表示
    @RequestMapping(value = "/bbs/", method = RequestMethod.GET)
    public String top(Model model) {
        model.addAttribute("message", "トップ");
        return "bbs";
    }

    //ログイン画面
    @RequestMapping(value = "/bbs/login/", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("message", "ログイン");
        return "login";
    }
    @RequestMapping(value = "/bbs/login/", method = RequestMethod.POST)
    public String login(@ModelAttribute UserForm form, Model model) {
    	userService.login(form.getLoginId(), form.getPassword());
    	return "redirect:/user/";
    }

    //全件取得
    @RequestMapping(value = "/bbs/userControl/", method = RequestMethod.GET)
    public String users(Model model) {
        List<UserDto> users = userService.getUserAll();
        model.addAttribute("message", "ユーザー管理画面");
        model.addAttribute("users", users);
        return "userControl";
    }

    //新規登録
    @RequestMapping(value = "/bbs/signUp/", method = RequestMethod.GET)
    public String userInsert(Model model) {
    	UserForm form = new UserForm();
    	model.addAttribute("userForm", form);
    	model.addAttribute("message", "ユーザー新規登録画面");
    	return "signUp";
    }
    @RequestMapping(value = "/bbs/signUp/", method = RequestMethod.POST)
    public String userInsert(@ModelAttribute UserForm form, Model model) {
    	int count = userService.signUp(form.getName());

    	return "redirect:/userControl/";
     }

    //編集
	@RequestMapping(value = "/userUpdate/update/input/{id}/", method = RequestMethod.GET)
	public String userUpdate(Model model, @PathVariable int id) {
		UserDto user = userService.getUser(id);
		model.addAttribute("message", "ユーザー編集画面");
		model.addAttribute("user", user);
		UserForm form = new UserForm();
		form.setId(user.getId());
		form.setName(user.getName());
		form.setLoginId(user.getLoginId());
		form.setBranchId(user.getBranchId());
		form.setDepartmentId(user.getDepartmentId());
		model.addAttribute("userForm", form);
		return "userUpdate";
	}
	@RequestMapping(value = "/userUpdate/update/input/{id}/", method = RequestMethod.POST)
	public String userUpdate(Model model, @ModelAttribute UserForm form) {
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(form, dto);
		userService.userUpdate(dto);
		Logger.getLogger(UserControlController.class).log(Level.INFO, "ユーザー情報を更新しました");
		return "redirect:/userControl/";
	}

	//新規投稿
	@RequestMapping(value = "/bbs/newMessage/", method = RequestMethod.GET)
	public String newMessage(Model model) {
    	UserForm form = new UserForm();
    	model.addAttribute("message", "ユーザー新規登録画面");
    	return "newMessage";
	}

}