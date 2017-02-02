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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.services.CourseService;
import app.services.FieldOfStudyService;
import app.services.GradeService;
import app.services.GroupService;
import app.services.StudentService;
import app.services.TeacherService;
import app.services.UsersService;
import core.humanity.teacher.Teacher;
import core.security.certificate.Certificate;
import core.study.course.Course;
import core.study.fieldofstudy.FieldOfStudy;
import core.study.grade.Grade;
import core.study.group.Group;
import core.user.User;

/***
 * Class is the controller which allows all available for teachers operations.
 * 
 * @author Mateusz Mucha
 *
 */
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

	/***
	 * Method allows to view main page in teacher panel with all details of
	 * teacher.
	 * 
	 * @param session
	 *            - session object
	 * @param model
	 *            - model object
	 * @return index page to show
	 */
	@RequestMapping(value = "/teacher/index", method = RequestMethod.GET)
	public String teacherDetails(HttpSession session, Model model) {
		Authentication authenticationt = SecurityContextHolder.getContext().getAuthentication();

		String username = authenticationt.getName();
		session.setAttribute("username", username);

		Teacher teacher = teacherService.createTeacher(username);
		model.addAttribute("teacher", teacher);

		session.setAttribute("id", teacher.getDetails().getId());
		session.setAttribute("alias", teacher.getDetails().getTitle().getName() + " "
				+ teacher.getDetails().getFirstName() + " " + teacher.getDetails().getLastName());

		return "teacher/index";
	}

	/***
	 * Method allows to redirect to failed page after unsuccessful operations.
	 * 
	 * @return failed page to show
	 */
	@RequestMapping(value = "/teacher/failed", method = RequestMethod.GET)
	public String operationFailed() {
		return "teacher/failed";
	}

	/***
	 * Method display grade page.
	 * 
	 * @param model
	 *            - model object
	 * @return grade page
	 */
	@RequestMapping(value = "/teacher/grade", method = RequestMethod.GET)
	public String grade(Model model) {
		model.addAttribute("grade", gradeService);

		return "teacher/grade";
	}

	/***
	 * Method display change password page.
	 * 
	 * @param model
	 *            - model object
	 * @return change password page
	 */
	@RequestMapping(value = "/teacher/changepwd", method = RequestMethod.GET)
	public String changePasswordTeacher(Model model) {
		User user = new User();

		model.addAttribute("userform", user);

		return "teacher/changepwd";
	}

	/***
	 * Method displays studen details.
	 * 
	 * @param model
	 *            - model object
	 * @return student details page
	 */
	@RequestMapping(value = "/teacher/student", method = RequestMethod.GET)
	public String student(Model model) {
		model.addAttribute("student", studentService);

		return "teacher/student";
	}

	/***
	 * Method display page using to grading students.
	 * 
	 * @param model
	 *            - model object
	 * @param student
	 *            - studen identificator
	 * @param course
	 *            - course identificator
	 * @return grading page
	 */
	@RequestMapping(value = "/teacher/grading", method = RequestMethod.GET)
	public String grading(Model model, @RequestParam("student") int student, @RequestParam("course") int course) {
		Grade grade = new Grade();
		grade.getDetails().getCourse().getDetails().setId(course);
		grade.getDetails().getStudent().getDetails().setId(student);

		TempValues temp = new TempValues();

		model.addAttribute("gradeform", grade);
		model.addAttribute("temp", temp);

		return "teacher/grading";
	}

	/***
	 * Method displays edit grade panel.
	 * 
	 * @param model
	 *            - model object
	 * @param student
	 *            - student identificator
	 * @param course
	 *            - course identificator
	 * @param gradeId
	 *            - grade identificator
	 * @return edit grade page
	 */
	@RequestMapping(value = "/teacher/editgrade", method = RequestMethod.GET)
	public String editgrade(Model model, @RequestParam("student") int student, @RequestParam("course") int course,
			@RequestParam("grade") int gradeId) {
		Grade grade = gradeService.findGrade(gradeId);

		model.addAttribute("gradeform", grade);

		return "teacher/editgrade";
	}

	/***
	 * Method shows departments and fields of study to choose before grading
	 * students.
	 * 
	 * @param model
	 *            - model object
	 * @return departments and field of studies page
	 */
	@RequestMapping(value = "/teacher/depts", method = RequestMethod.GET)
	public String depts(Model model) {
		model.addAttribute("deptsfields", fieldOfStudyService.findAllFieldsForAllDepartments());
		model.addAttribute("field", new FieldOfStudy());

		return "teacher/depts";
	}

	/***
	 * Method to displays available groups on field of study to grading.
	 * 
	 * @param model
	 *            - model object
	 * @return group page
	 */
	@RequestMapping(value = "/teacher/groups", method = RequestMethod.GET)
	public String groups(Model model) {
		model.addAttribute("groupService", groupService);
		model.addAttribute("courseService", new CourseService());
		model.addAttribute("group", new Group());
		model.addAttribute("course", new Course());

		return "teacher/groups";
	}

	/***
	 * Method displays all grades for students by current teacher.
	 * 
	 * @param model
	 *            - model object
	 * @return all grades page
	 */
	@RequestMapping(value = "/teacher/grades", method = RequestMethod.GET)
	public String grades(Model model) {
		model.addAttribute("studentService", studentService);
		model.addAttribute("gradeService", gradeService);

		return "teacher/grades";
	}

	/***
	 * Method allows teacher to change password.
	 * 
	 * @param session
	 *            - session object
	 * @param user
	 *            - user object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complet operation
	 */
	@RequestMapping(value = "changepwd_teacher", method = RequestMethod.POST)
	public String changePasswordTeacher(HttpSession session, @ModelAttribute(value = "userform") User user,
			RedirectAttributes redirectAttributes) {
		try {
			user.setLogin((String) session.getAttribute("username"));

			new UsersService().changePassword(user);
			redirectAttributes.addAttribute("success", true);

			return "redirect:/teacher/changepwd";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/teacher/failed";
		}
	}

	/***
	 * Method allows to choose specific department and field of study to search
	 * students to grading.
	 * 
	 * @param field
	 *            - field of study object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complet operation
	 */
	@RequestMapping(value = "/deptsfields", method = RequestMethod.POST)
	public String deptsfields(@ModelAttribute(value = "field") FieldOfStudy field,
			RedirectAttributes redirectAttributes) {
		try {
			redirectAttributes.addAttribute("field", field.getDetails().getFieldOfStudyName());

			return "redirect:/teacher/groups";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/teacher/failed";
		}
	}

	/***
	 * Method allows to view all grades.
	 * 
	 * @param course
	 *            - course object
	 * @param group
	 *            - group object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complet operation
	 */
	@RequestMapping(value = "/grades", method = RequestMethod.POST)
	public String grades(@ModelAttribute(value = "course") Course course, @ModelAttribute(value = "group") Group group,
			RedirectAttributes redirectAttributes) {
		try {
			String[] selectNames = course.getDetails().getCourseName().split(" - ");

			course.getDetails().setCourseName(selectNames[0]);
			course.getDetails().setCourseForm(selectNames[1]);

			course = new CourseService().findByNameAndForm(course.getDetails().getCourseName(),
					course.getDetails().getCourseForm().getName());
			group = groupService.findOneByName(group.getDetails().getGroupName());

			if (group.getDetails().getFieldOfStudy().getDetails().getId() == course.getDetails().getFieldOfStudy()
					.getDetails().getId()) {

				redirectAttributes.addAttribute("course", course.getDetails().getId());
				redirectAttributes.addAttribute("group", group.getDetails().getId());

				return "redirect:/teacher/grades";
			} else {
				redirectAttributes.addAttribute("error", true);

				return "redirect:/teacher/failed";
			}
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/teacher/failed";
		}
	}

	/***
	 * Method allows to grading students by teacher using special certificate.
	 * 
	 * @param file
	 *            - certificate of teacher
	 * @param temp
	 *            - cache
	 * @param session
	 *            - session object
	 * @param grade
	 *            - grade object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complet operation
	 */
	@RequestMapping(value = "/grading", method = RequestMethod.POST)
	public String grading(@RequestParam("file") MultipartFile file, @ModelAttribute(value = "temp") TempValues temp,
			HttpSession session, @ModelAttribute(value = "gradeform") Grade grade,
			RedirectAttributes redirectAttributes) {
		try {
			grade.getDetails().getTeacher().getDetails().setId((int) session.getAttribute("id"));

			Certificate certificate = new Certificate();
			if (certificate.validateCertificate(file.getInputStream(), temp.getValue(),
					(String) session.getAttribute("alias"))) {
				gradeService.createGrade(grade);

				redirectAttributes.addAttribute("student", grade.getDetails().getStudent().getDetails().getId());
				redirectAttributes.addAttribute("course", grade.getDetails().getCourse().getDetails().getId());
				redirectAttributes.addAttribute("success", true);

				return "redirect:/teacher/grading";
			} else {
				redirectAttributes.addAttribute("error", true);

				return "redirect:/teacher/failed";
			}
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/teacher/failed";
		}
	}

	/***
	 * Method allows to edit grade by teacher.
	 * 
	 * @param session
	 *            - session object
	 * @param grade
	 *            - grade object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complet operation
	 */
	@RequestMapping(value = "/editgrade", method = RequestMethod.POST)
	public String editgrade(HttpSession session, @ModelAttribute(value = "gradeform") Grade grade,
			RedirectAttributes redirectAttributes) {
		try {
			grade.getDetails().getTeacher().getDetails().setId((int) session.getAttribute("id"));

			gradeService.updateGrade(grade);

			redirectAttributes.addAttribute("student", grade.getDetails().getStudent().getDetails().getId());
			redirectAttributes.addAttribute("course", grade.getDetails().getCourse().getDetails().getId());
			redirectAttributes.addAttribute("grade", grade.getDetails().getId());
			redirectAttributes.addAttribute("success", true);

			return "redirect:/teacher/editgrade";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/teacher/failed";
		}
	}

	/***
	 * Class represents cache values.
	 * 
	 * @author Mateusz Mucha
	 *
	 */
	private static class TempValues {
		private String value = null;

		/***
		 * Default constructor.
		 */
		public TempValues() {
		}

		/***
		 * Method to gets cache value.
		 * 
		 * @return cache value
		 */
		public String getValue() {
			return value;
		}

		/***
		 * Method to sets cache value.
		 * 
		 * @param value
		 *            - cache value
		 */
		public void setValue(String value) {
			this.value = value;
		}
	}
}