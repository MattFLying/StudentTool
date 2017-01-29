package app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {	
	@RequestMapping(value={"/","index"})
	public String home(){
		return "index";
	}
	@RequestMapping(value={"/login"})
	public String login() {
		return "login";
	}
	@RequestMapping(value={"/logout"})
	public String logout(HttpSession httpSession) {
		httpSession.invalidate();
		
		return "logout";
	}
	@RequestMapping(value="/failure")
	public String failure(){
		return "failure";
	}
}