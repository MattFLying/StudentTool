package app.controller;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.services.CourseService;
import app.services.DepartmentService;
import app.services.FieldOfStudyService;
import app.services.GradeService;
import app.services.GroupService;
import app.services.StudentService;
import app.services.TeacherService;
import app.services.UsersService;
import core.humanity.student.Student;
import core.humanity.teacher.Teacher;
import core.study.course.Course;
import core.study.department.Department;
import core.study.fieldofstudy.FieldOfStudy;
import core.study.grade.Grade;
import core.study.group.Group;
import core.user.User;

@Controller
@Secured("ROLE_TEACHER")
public class TeacherController {
	@Autowired
	private TeacherService teacherService = new TeacherService();
	@Autowired
	private FieldOfStudyService fieldOfStudyService = new FieldOfStudyService();
	@Autowired
	private GroupService groupService = new GroupService();
	@Autowired
	private GradeService gradeService = new GradeService();
	@Autowired
	private StudentService studentService = new StudentService();
	
	
	
	@RequestMapping(value="/teacher/index", method=RequestMethod.GET)
	public String teacherDetails(HttpSession session, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); 
		session.setAttribute("username", username);
		
		Teacher teacher = teacherService.createTeacher(username);
		model.addAttribute("teacher", teacher);
		session.setAttribute("id", teacher.getDetails().getId());
		
		return "teacher/index";
	}
	@RequestMapping(value="/teacher/grade", method=RequestMethod.GET)
	public String grade(Model model) {
		model.addAttribute("grade", gradeService);
		
		return "teacher/grade";
	}
	@RequestMapping(value="/teacher/changepwd", method=RequestMethod.GET)
	public String changePasswordTeacher(Model model) {
		User user = new User();

		model.addAttribute("userform", user);
		
		return "teacher/changepwd";
	}
	@RequestMapping(value="/teacher/student", method=RequestMethod.GET)
	public String student(Model model) {
		model.addAttribute("student", studentService);
		
		return "teacher/student";
	}
	
	@RequestMapping(value="/teacher/grading", method=RequestMethod.GET)
	public String grading(Model model, @RequestParam("student") int student, @RequestParam("course") int course) {
		Grade grade = new Grade();
		grade.getDetails().getCourse().getDetails().setId(course);
		grade.getDetails().getStudent().getDetails().setId(student);
		
		model.addAttribute("gradeform", grade);
		
		return "teacher/grading";
	}
	@RequestMapping(value="/teacher/editgrade", method=RequestMethod.GET)
	public String editgrade(Model model, @RequestParam("student") int student, @RequestParam("course") int course, @RequestParam("grade") int gradeId) {
		Grade grade = gradeService.findGrade(gradeId);
		
		model.addAttribute("gradeform", grade);
		
		return "teacher/editgrade";
	}
	@RequestMapping(value="/teacher/depts", method=RequestMethod.GET)
	public String depts(Model model) {
		model.addAttribute("deptsfields", fieldOfStudyService.findAllFieldsForAllDepartments());
		model.addAttribute("field", new FieldOfStudy());
		
		return "teacher/depts";
	}
	@RequestMapping(value="/teacher/groups", method=RequestMethod.GET)
	public String groups(Model model) {
		model.addAttribute("groupService", groupService);
		model.addAttribute("courseService", new CourseService());
		model.addAttribute("group", new Group());
		model.addAttribute("course", new Course());
		
		return "teacher/groups";
	}
	@RequestMapping(value="/teacher/grades", method=RequestMethod.GET)
	public String grades(Model model) {
		model.addAttribute("studentService", studentService);
		model.addAttribute("gradeService", gradeService);
		
		return "teacher/grades";
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
	@RequestMapping(value="/deptsfields", method=RequestMethod.POST)
	public String deptsfields(@ModelAttribute(value="field") FieldOfStudy field, RedirectAttributes redirectAttributes) {
		try {
			redirectAttributes.addAttribute("field", field.getDetails().getFieldOfStudyName());
			
			return "redirect:/teacher/groups";
		} catch(Exception e) {
			e.printStackTrace();
			return "redirect:/teacher/depts?error";
		}
	}
	@RequestMapping(value="/grades", method=RequestMethod.POST)
	public String grades(@ModelAttribute(value="course") Course course, @ModelAttribute(value="group") Group group, RedirectAttributes redirectAttributes) {
		try {
			String[] selectNames = course.getDetails().getCourseName().split(" - ");
			
			course.getDetails().setCourseName(selectNames[0]);
			course.getDetails().setCourseForm(selectNames[1]); 
			
			course = new CourseService().findByNameAndForm(course.getDetails().getCourseName(), course.getDetails().getCourseForm().getName());
			group = groupService.findOneByName(group.getDetails().getGroupName());
			
			if(group.getDetails().getFieldOfStudy().getDetails().getId() ==
					course.getDetails().getFieldOfStudy().getDetails().getId()) {
				System.out.println("trololololo");
				
				redirectAttributes.addAttribute("course", course.getDetails().getId());
				redirectAttributes.addAttribute("group", group.getDetails().getId());
				
				return "redirect:/teacher/grades";
			} else {
				return "redirect:/teacher/grades?error";
			}
		} catch(Exception e) {
			e.printStackTrace();
			return "redirect:/teacher/grades?error";
		}
	}
	@RequestMapping(value="/grading", method=RequestMethod.POST)
	public String grading(HttpSession session, @ModelAttribute(value="gradeform") Grade grade, RedirectAttributes redirectAttributes) {
		try {
			grade.getDetails().getTeacher().getDetails().setId((int)session.getAttribute("id"));
			
			gradeService.createGrade(grade);
			
			redirectAttributes.addAttribute("student", grade.getDetails().getStudent().getDetails().getId());
			redirectAttributes.addAttribute("course", grade.getDetails().getCourse().getDetails().getId());
			redirectAttributes.addAttribute("success", true);
			
			return "redirect:/teacher/grading";
		} catch(Exception e) {
			e.printStackTrace();
			redirectAttributes.addAttribute("student", grade.getDetails().getStudent().getDetails().getId());
			redirectAttributes.addAttribute("course", grade.getDetails().getCourse().getDetails().getId());
			redirectAttributes.addAttribute("error", true);
			
			return "redirect:/teacher/grading";
		}
	}
	@RequestMapping(value="/editgrade", method=RequestMethod.POST)
	public String editgrade(HttpSession session, @ModelAttribute(value="gradeform") Grade grade, RedirectAttributes redirectAttributes) {
		try {
			grade.getDetails().getTeacher().getDetails().setId((int)session.getAttribute("id"));
			
			gradeService.updateGrade(grade);
			
			redirectAttributes.addAttribute("student", grade.getDetails().getStudent().getDetails().getId());
			redirectAttributes.addAttribute("course", grade.getDetails().getCourse().getDetails().getId());
			redirectAttributes.addAttribute("success", true);
			
			return "redirect:/teacher/grading";
		} catch(Exception e) {
			e.printStackTrace();
			redirectAttributes.addAttribute("student", grade.getDetails().getStudent().getDetails().getId());
			redirectAttributes.addAttribute("course", grade.getDetails().getCourse().getDetails().getId());
			redirectAttributes.addAttribute("error", true);
			
			return "redirect:/teacher/grading";
		}
	}
	
	
	
}