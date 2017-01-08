package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {	
	@RequestMapping(value={"/","index"})
	public String home(){
		return "index";
	}
	@RequestMapping(value="/admin/index")
	public String admin(){
		return "admin/index";
	}
	@RequestMapping(value="/student/index")
	public String student(){
		return "student/index";
	}
	@RequestMapping(value="/teacher/index")
	public String teacher(){
		return "teacher/index";
	}	
	@RequestMapping(value={"/login"})
	public String login(){
		return "login";
	}
	@RequestMapping(value="/failure")
	public String failure(){
		return "failure";
	}
}