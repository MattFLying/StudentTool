package app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.services.StudentService;
import app.services.TeacherService;
import app.services.UsersService;
import core.humanity.student.Student;
import core.humanity.teacher.Teacher;
import core.user.User;

@Controller
@Secured("ROLE_TEACHER")
public class TeacherController {
	@Autowired
	private TeacherService teacherService = new TeacherService();
	
	
	
	@RequestMapping(value="/teacher/index", method=RequestMethod.GET)
	public String teacherDetails(HttpSession session, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); 
		session.setAttribute("username", username);
		
		Teacher teacher = teacherService.createTeacher(username);
		model.addAttribute("teacher", teacher);
		
		return "teacher/index";
	}
	@RequestMapping(value="/teacher/changepwd", method=RequestMethod.GET)
	public String changePasswordTeacher(Model model) {
		User user = new User();

		model.addAttribute("userform", user);
		
		return "teacher/changepwd";
	}
	@RequestMapping(value="changepwd_teacher", method=RequestMethod.POST)
	public String changePasswordTeacher(HttpSession session, @ModelAttribute(value="userform") User user) {
		try {
			user.setLogin((String)session.getAttribute("username"));
			
			new UsersService().changePassword(user);
			
			return "redirect:/teacher/changepwd?success";
		} catch(Exception e) {
			e.printStackTrace();
			return "redirect:/teacher/changepwd?error";
		}
	}
	
	
}