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
    //全件取得
    @RequestMapping(value = "/userControl/", method = RequestMethod.GET)
    public String users(Model model) {
        List<UserDto> users = userService.getUserAll();
        model.addAttribute("message", "ユーザー管理画面");
        model.addAttribute("users", users);
        return "userControl";
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
		model.addAttribute("userForm", form);
		return "userUpdate";
	}
	@RequestMapping(value = "/userUpdate/update/input/{id}/", method = RequestMethod.POST)
	public String userUpdate(Model model, @ModelAttribute UserForm form) {
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(form, dto);
		userService.updateUser(dto);
		Logger.getLogger(UserControlController.class).log(Level.INFO, "ユーザー情報を更新しました");
		return "redirect:/userControl/";
	}
}