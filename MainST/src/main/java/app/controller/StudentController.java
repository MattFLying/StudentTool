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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import app.services.GradeService;
import app.services.StudentService;
import app.services.TeacherService;
import app.services.UsersService;
import core.humanity.student.Student;
import core.user.User;

/***
 * Class is the controller which allows all available for students operations.
 * 
 * @author Mateusz Mucha
 *
 */
@Controller
@Secured("ROLE_STUDENT")
public class StudentController {
	@Autowired
	private StudentService studentService = new StudentService();
	@Autowired
	private TeacherService teacherService = new TeacherService();
	@Autowired
	private GradeService gradeService = new GradeService();
	private Student student;

	/***
	 * Method allows to view main page in student panel with all details of
	 * student.
	 * 
	 * @param session
	 *            - session object
	 * @param model
	 *            - model object
	 * @return index page to show
	 */
	@RequestMapping(value = "/student/index", method = RequestMethod.GET)
	public String studentDetails(HttpSession session, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();
		session.setAttribute("username", username);

		student = studentService.createStudent(username);
		model.addAttribute("student", student);

		return "student/index";
	}

	/***
	 * Method allows to redirect to failed page after unsuccessful operations.
	 * 
	 * @return failed page to show
	 */
	@RequestMapping(value = "/student/failed", method = RequestMethod.GET)
	public String operationFailed() {
		return "student/failed";
	}

	/***
	 * Method show change password page for student.
	 * 
	 * @param model
	 *            - model object
	 * @return change password page to show
	 */
	@RequestMapping(value = "/student/changepwd", method = RequestMethod.GET)
	public String changePasswordStudent(Model model) {
		User user = new User();

		model.addAttribute("userform", user);

		return "student/changepwd";
	}

	/***
	 * Method shows all terms for students.
	 * 
	 * @param model
	 *            - model object
	 * @return available terms page
	 */
	@RequestMapping(value = "/student/studentindex", method = RequestMethod.GET)
	public String studentIndex(Model model) {
		model.addAttribute("studentSemester", student.getDetails().getCurrentTermNumber());

		return "student/studentindex";
	}

	/***
	 * Method to show all grades for students.
	 * 
	 * @param model
	 *            - model object
	 * @return grades page
	 */
	@RequestMapping(value = "/student/grades", method = RequestMethod.GET)
	public String grades(Model model) {
		model.addAttribute("terms", studentService);
		model.addAttribute("student", student);
		model.addAttribute("grades", gradeService);

		return "student/grades";
	}

	/***
	 * Method to show details about grade
	 * 
	 * @param model
	 *            - model object
	 * @return grade details page
	 */
	@RequestMapping(value = "/student/grade", method = RequestMethod.GET)
	public String grade(Model model) {
		model.addAttribute("grade", gradeService);

		return "student/grade";
	}

	/***
	 * Method to show details about teacher
	 * 
	 * @param model
	 *            - model object
	 * @return teacher details page
	 */
	@RequestMapping(value = "/student/teacher", method = RequestMethod.GET)
	public String teacher(Model model) {
		model.addAttribute("teacher", teacherService);

		return "student/teacher";
	}

	/***
	 * Method allows student to change user password.
	 * 
	 * @param session
	 *            - session object
	 * @param user
	 *            - student user object
	 * @param redirectAttributes
	 *            - redirecting attributes after completing operation
	 * @return redirecting correct page after completing oepration
	 */
	@RequestMapping(value = "changepwd", method = RequestMethod.POST)
	public String changePasswordStudent(HttpSession session, @ModelAttribute(value = "userform") User user,
			RedirectAttributes redirectAttributes) {
		try {
			user.setLogin((String) session.getAttribute("username"));

			new UsersService().changePassword(user);
			redirectAttributes.addAttribute("success", true);

			return "redirect:/student/changepwd";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/student/failed";
		}
	}
}