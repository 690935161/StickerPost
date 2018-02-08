package org.hfly.springbootdemo.controller;

import org.hfly.springbootdemo.entity.User;
import org.hfly.springbootdemo.mapper.StickerMapper;
import org.hfly.springbootdemo.mapper.UserMapper;
import org.hfly.springbootdemo.service.StickerService;
import org.hfly.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {
	@Resource
	UserMapper userMapper;

	@Resource
	StickerMapper stickerMapper;

	@Autowired
	UserService userService;

	@Autowired
	StickerService stickerService;

	@RequestMapping("/doLogin")
	public String doLogin(HttpSession session, RedirectAttributes attrs, @RequestParam String username, @RequestParam String password) {
		User user = userService.checkUserPassword(username, password);
		if (user != null) {
			session.setAttribute("user", user);
			return "redirect:/";
		}
		attrs.addFlashAttribute("msg", "Username or password not match.");
		return "redirect:/login";
	}

	@RequestMapping("/doRegister")
	public String doRegister(RedirectAttributes attrs, @RequestParam String username, @RequestParam String password,
							 @RequestParam String nickname, @RequestParam String description) {
		if (userService.registerUser(username, password, nickname, description)) {
			attrs.addFlashAttribute("msg", "Register success.");
			return "redirect:/login";
		}
		attrs.addFlashAttribute("msg", "Register fail.");
		return "redirect:/register";
	}

	@RequestMapping("checkUserUsing")
	@ResponseBody
	public String checkUserUsing(@RequestParam String username) {
		if (userService.checkUsernameExist(username)) {
			return "false";
		}
		return "true";
	}

	@RequestMapping("/doLogout")
	public String doLogout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/login";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/register")
	public String register() {
		return "register";
	}

	@RequestMapping("/")
	public String main(HttpServletRequest request, HttpSession session, @RequestParam(defaultValue = "0", required = false) Integer page) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			request.setAttribute("hello", "Login for User: " + user.getNickname());
			request.setAttribute("desc", user.getDescription());
			request.setAttribute("stickers", stickerService.getIndexStickerTextByTimeAndPage(page));
			return "index";
		}
		return "redirect:/login";
	}

	@RequestMapping("/postSticker")
	public String postSticker(HttpSession session, RedirectAttributes attrs, @RequestParam String postText) {
		User user = (User) session.getAttribute("user");
		if (stickerService.postSticker(user.getUserId(), postText)) {
			attrs.addFlashAttribute("msg", "Post Success");
			return "redirect:/";
		}
		attrs.addFlashAttribute("msg", "Post Fail");
		return "redirect:/";
	}
}
