package app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {	
	@RequestMapping(value={"/","index"})
	public String home(){
		return "index";
	}
	@RequestMapping(value="/admin/index", method = RequestMethod.GET)
	public String admin(HttpSession session) {	
		getUserLogin(session);

		return "admin/index";
	}
	@RequestMapping(value="/student/index")
	public String student(HttpSession session){
		getUserLogin(session);
		
		return "student/index";
	}
	@RequestMapping(value="/teacher/index")
	public String teacher(HttpSession session){
		getUserLogin(session);
		
		return "teacher/index";
	}	
	@RequestMapping(value={"/login"})
	public String login() {
		return "login";
	}
	@RequestMapping(value="/failure")
	public String failure(){
		return "failure";
	}
	private void getUserLogin(HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); 
		session.setAttribute("username", username);
	}
}