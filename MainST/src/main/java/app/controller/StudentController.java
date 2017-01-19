package app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.services.GradeService;
import app.services.StudentService;
import app.services.UsersService;
import core.humanity.student.Student;
import core.user.User;

@Controller
@Secured("ROLE_STUDENT")
public class StudentController {
	@Autowired
	private StudentService studentService = new StudentService();
	@Autowired
	private GradeService gradeService = new GradeService();
	private Student student;
	
	@RequestMapping(value="/student/index", method=RequestMethod.GET)
	public String studentDetails(HttpSession session, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); 
		session.setAttribute("username", username);
		
		student = studentService.createStudent(username);
		model.addAttribute("student", student);
		
		return "student/index";
	}
	@RequestMapping(value="/student/changepwd", method=RequestMethod.GET)
	public String changePasswordStudent(Model model) {
		User user = new User();

		model.addAttribute("userform", user);
		
		return "student/changepwd";
	}
	@RequestMapping(value="/student/studentindex", method=RequestMethod.GET)
	public String studentIndex(Model model) {
		model.addAttribute("studentSemester", student.getDetails().getCurrentTermNumber());
		
		return "student/studentindex";
	}
	@RequestMapping(value="/student/grades", method=RequestMethod.GET)
	public String grades(Model model) {
		model.addAttribute("terms", studentService);
		model.addAttribute("student", student);
		model.addAttribute("grades", gradeService);
		
		return "student/grades";
	}
	@RequestMapping(value="/student/grade", method=RequestMethod.GET)
	public String grade(Model model) {
		model.addAttribute("grade", gradeService);
		
		return "student/grade";
	}
	@RequestMapping(value="changepwd", method=RequestMethod.POST)
	public String changePasswordStudent(HttpSession session, @ModelAttribute(value="userform") User user) {
		try {
			user.setLogin((String)session.getAttribute("username"));
			
			new UsersService().changePassword(user);
			
			return "redirect:/student/changepwd?success";
		} catch(Exception e) {
			e.printStackTrace();
			return "redirect:/student/changepwd?error";
		}
	}
	
	
}