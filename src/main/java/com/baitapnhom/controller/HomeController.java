package com.baitapnhom.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.baitapnhom.entity.User;
import com.baitapnhom.service.EmailService;
import com.baitapnhom.service.UserService;

@Controller
@RequestMapping("")
public class HomeController {
	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	
	@Autowired
    private EmailService emailService;
	@GetMapping({ "/home", "" })
	public String listStudents(Model model,
			@CookieValue(value = "isNameCookie", defaultValue = "defaultCookieValue") String cookieValue) {
	    
		System.out.println("\nSession trong home " + session.getAttribute("isNameSession"));
		if(session.getAttribute("isNameSession") ==null)
		System.out.println("Cookie trong home " + cookieValue);
		if((session.getAttribute("isNameSession") != null)&&(cookieValue.equals("defaultCookieValue"))) {
			model.addAttribute("isNameSession", session.getAttribute("isNameSession"));
			model.addAttribute("isNameCookie", cookieValue);
			System.out.println("125410");
			return "/home/home";
		}
		if (!(cookieValue.equals("defaultCookieValue")&&(session.getAttribute("isNameSession") == null))) {
			model.addAttribute("isNameCookie", cookieValue);
			model.addAttribute("isNameSession", session.getAttribute("isNameSession"));
			return "/home/home";
		}
		if ((cookieValue.equals("defaultCookieValue")&&(session.getAttribute("isNameSession") == null))) {
			model.addAttribute("isNameCookie", cookieValue);
			model.addAttribute("isNameSession", session.getAttribute("isNameSession"));
			System.out.println("01221255");
			return "/home/home";
		}
		return "/Login-Register/login";
	}

	@GetMapping("/login")
	public String login(Model model,
			@CookieValue(value = "isNameCookie", defaultValue = "defaultCookieValue") String cookieValue) {
		System.out.println("\nSession trong login" + session.getAttribute("isNameSession"));
		System.out.println("Cookie trong login" + cookieValue);
		if((session.getAttribute("isNameSession") != null)&&(cookieValue.equals("defaultCookieValue"))) {
			model.addAttribute("isNameSession", session.getAttribute("isNameSession"));
			model.addAttribute("isNameCookie", cookieValue);
			return "forward:/home";
		}
		if (!(cookieValue.equals("defaultCookieValue")&&(session.getAttribute("isNameSession") == null))) {
			model.addAttribute("isNameSession", session.getAttribute("isNameSession"));
			model.addAttribute("isNameCookie", cookieValue);
			return "forward:/home";
		}
		if(session.getAttribute("isNameSession")==null){
			model.addAttribute("isNameSession", session.getAttribute("isNameSession"));
			model.addAttribute("isNameCookie", cookieValue);
			return "/Login-Register/login";
		}
				
		if ((cookieValue.equals("defaultCookieValue"))) {
			model.addAttribute("isNameSession", session.getAttribute("isNameSession"));
			model.addAttribute("isNameCookie", cookieValue);
			return "/Login-Register/login";
		}
		return "";

	}

	@PostMapping("/login")
	public String login(Model model,
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {
		if (userService.checkLogin(username, password)) {
			model.addAttribute("ok", "Bạn đã đăng nhập thành công");

			var luumatkhau = request.getParameter("luuMatKhauu");
			if (luumatkhau != null && luumatkhau.equals("luuMatkhau")) {
				System.out.println("cookies login post" + session.getAttribute("isNameCookie"));
				// Cookie[] cookies = request.getCookies(); Mảng cookie
				addCookie("isNameCookie", username);
				model.addAttribute("isNameCookie", username);
				session.removeAttribute("isNameSession");
			} else {
				session.setAttribute("isNameSession", username);
				System.out.println("session login post" + session.getAttribute("isName"));
				removeCookie("isNameCookie", "n");
				model.addAttribute("isName", session.getAttribute("isName"));
			}
		} else {
			model.addAttribute("!ok", "Bạn đã đăng nhập thất bại");
		}
		return "redirect:/sanpham";
	}

	public void addCookie(String name, String valuee) {
		Cookie newCookie = new Cookie(name, valuee);
		newCookie.setMaxAge(24 * 24 * 24);
		newCookie.setPath("/");
		response.addCookie(newCookie);
	}

	public void removeCookie(String name, String valuee) {
		Cookie newCookie = new Cookie(name, valuee);
		newCookie.setMaxAge(0);
		newCookie.setPath("/");
		response.addCookie(newCookie);
	}

	@GetMapping("/register")
	public String dangky(Model model) {
		model.addAttribute("user", new User());
		return "Login-Register/register";
	}
	  

	@PostMapping("/dangky")
	public String saveUser(@RequestParam(name = "password2") String password2,
			@ModelAttribute("user") User user, Model model) {
		boolean isFix = false;

		if (!password2.equals(user.getPassword())) {
			model.addAttribute("khongtrungmatkhau", new String("Lỗi nhập không trùng mật khẩu!"));
			isFix = true;
			model.addAttribute("isfix", isFix);
			return "Login-Register/register";
		}
		user.setRole("User");
		user.setImage("image.png");
		user.setStatus(false);
		userService.save(user);
		int code = (int) Math.floor(((Math.random() * 10000)));
     ;
		emailService.sendSimpleEmail(user.getEmail(),
                
                "Xác thực email",
                "Xin chào quý khách rất vui được phu mụ quý khách . Mã xác nhận :"+code);
        session.setAttribute("code", code);  
        session.setAttribute("email",user.getEmail());  
        model.addAttribute("gmail", user.getEmail());
        return "Login-Register/xacnhan";
		
	}
	 @GetMapping("/xacnhan")
     public String guilai(Model model) {
	     
	        int code = (int) Math.floor(((Math.random() * 10000)));
	     ;
	        emailService.sendSimpleEmail(session.getAttribute("email").toString(),
	                
	                "Xác thực email",
	                "Xin chào quý khách rất vui được phu mụ quý khách . Mã xác nhận :"+code);
	        session.setAttribute("code", code);  
	  session.setAttribute("email",session.getAttribute("email").toString());  
       System.out.println(session.getAttribute("email").toString());
       model.addAttribute("gmail", session.getAttribute("email").toString());
	     return "Login-Register/xacnhan";
     }
	@PostMapping("/xacnhan")
    public String xacnhan(Model model, @RequestParam(name = "code") int code) {
       if (code==session.getAttribute("code").hashCode())
        return "redirect:/login";
       return "Login-Register/xacnhan";
    }

	@GetMapping("/logout")
	public String logout(Model model, HttpSession session,@CookieValue(value = "isNameCookie", defaultValue = "defaultCookieValue") String cookieValue) {
		session.removeAttribute("isNameSession");
		removeCookie("isNameCookie", "defaultCookieValue");
		model.addAttribute("isNameCookie", cookieValue);
        model.addAttribute("isNameSession", session.getAttribute("isNameSession"));
		return "Login-Register/login";
	}

	@GetMapping("/profile")
	public String profile(Model model, HttpSession session,@CookieValue(value = "isNameCookie", defaultValue = "defaultCookieValue") String cookieValue){
		model.addAttribute("isNameCookie", cookieValue);
        model.addAttribute("isNameSession", session.getAttribute("isNameSession"));
		return "/home/profile";
	}
}
