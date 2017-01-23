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
import app.services.DepartmentService;
import app.services.FieldOfStudyService;
import app.services.GroupService;
import app.services.InstituteService;
import app.services.SpecializationService;
import app.services.StudentService;
import app.services.TeacherService;
import app.services.UsersService;
import core.humanity.student.Student;
import core.humanity.teacher.Teacher;
import core.study.course.Course;
import core.study.department.Department;
import core.study.department.Institute;
import core.study.fieldofstudy.FieldOfStudy;
import core.study.fieldofstudy.Specialization;
import core.study.group.Group;
import core.user.User;

@Controller
@Secured("ROLE_ADMIN")
public class AdminController {
	@Autowired
	private UsersService userService = new UsersService();
	@Autowired
	private FieldOfStudyService fieldOfStudyService = new FieldOfStudyService();
	@Autowired
	private GroupService groupService = new GroupService();
	@Autowired
	private SpecializationService specializationService = new SpecializationService();
	@Autowired
	private InstituteService instituteService = new InstituteService();
	@Autowired
	private DepartmentService departmentService = new DepartmentService();
	@Autowired
	private CourseService courseService = new CourseService();
	@Autowired
	private StudentService studentService = new StudentService();
	@Autowired
	private TeacherService teacherService = new TeacherService();
	
	
	

	@RequestMapping(value="/admin/index", method = RequestMethod.GET)
	public String admin(HttpSession session) {	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); 
		session.setAttribute("username", username);

		return "admin/index";
	}	
	@RequestMapping(value="/admin/user/adduser", method=RequestMethod.GET)
	public String addUser(Model model) {
		User user = new User();
		model.addAttribute("userform", user);
		
		return "admin/user/adduser";
	}
	@RequestMapping(value="/adduserdb", method=RequestMethod.POST)
	public String addUserToDb(@ModelAttribute(value="userform") User user, RedirectAttributes redirectAttributes) {
		try {
			userService.createBasicUser(user);	
			redirectAttributes.addAttribute("success", true);
			
			return "redirect:/admin/user/adduser";
		} catch(Exception e) {
			redirectAttributes.addAttribute("error", true);
			
			return "redirect:/admin/user/adduser";
		}
	}
	@RequestMapping(value={"/admin/user/addstudent"})
	public String addStudent(Model model) {
		User user = new User();
		Student student = new Student();
		
		model.addAttribute("userform", user);
		model.addAttribute("studentform", student);
		model.addAttribute("fieldsdepts", fieldOfStudyService.findAllFieldsForAllDepartments());		
		model.addAttribute("groupService", groupService);
		model.addAttribute("specializationService", specializationService);
		
		return "admin/user/addstudent";
	}
	@RequestMapping(value="/addstudentdb", method=RequestMethod.POST)
	public String addStudentToDb(@ModelAttribute(value="userform") User user, @ModelAttribute(value="studentform") Student student, RedirectAttributes redirectAttributes) {
		try {
			studentService.save(student, user);			
			redirectAttributes.addAttribute("success", true);
			
			return "redirect:/admin/user/addstudent";
		} catch(Exception e) {
			redirectAttributes.addAttribute("error", true);
			
			return "redirect:/admin/user/addstudent";
		}
	}
	@RequestMapping(value={"/admin/user/addstudents"})
	public String addStudents() {
		return "admin/user/addstudents";
	}
	@RequestMapping(value="/addstudentsdb", method=RequestMethod.POST)
	public String addStudentsToDb(@RequestParam("file") MultipartFile file, Model model, RedirectAttributes redirectAttributes) {
		try {
			studentService.createStudentsFromExcel(file.getInputStream());
			redirectAttributes.addAttribute("success", true);
			
			return "redirect:/admin/user/addstudents";
		} catch(Exception e) {
			redirectAttributes.addAttribute("error", true);
			
			return "redirect:/admin/user/addstudents";
		}
	}
	@RequestMapping(value={"/admin/user/addteacher"})
	public String addTeacher(Model model) {
		User user = new User();
		Teacher teacher = new Teacher();
		
		model.addAttribute("userform", user);
		model.addAttribute("teacherform", teacher);
		model.addAttribute("institutesdepts", instituteService.findAllInstitutesForAllDepartments());
		
		return "admin/user/addteacher";
	}
	@RequestMapping(value="/addteacherdb", method=RequestMethod.POST)
	public String addTeacherToDb(@ModelAttribute(value="userform") User user, @ModelAttribute(value="teacherform") Teacher teacher, RedirectAttributes redirectAttributes) {
		try {
			teacherService.save(teacher, user);
			redirectAttributes.addAttribute("success", true);
			
			return "redirect:/admin/user/addteacher";
		} catch(Exception e) {
			redirectAttributes.addAttribute("error", true);
			
			return "redirect:/admin/user/addteacher";
		}
	}
	@RequestMapping(value={"/admin/dept/adddepartment"})
	public String addDepartment(Model model) {
		Department department = new Department();
		
		model.addAttribute("departmentform", department);
		
		return "admin/dept/adddepartment";
	}
	@RequestMapping(value="/adddepartmentdb", method=RequestMethod.POST)
	public String addDepartmentToDb(@ModelAttribute(value="departmentform") Department department, RedirectAttributes redirectAttributes) {
		try {
			departmentService.save(department);
			redirectAttributes.addAttribute("success", true);
			
			return "redirect:/admin/dept/adddepartment";
		} catch(Exception e) {
			redirectAttributes.addAttribute("error", true);
			
			return "redirect:/admin/dept/adddepartment";
		}
	}
	@RequestMapping(value={"/admin/dept/addinstitute"})
	public String addInstitute(Model model) {
		Institute institute = new Institute();
		
		model.addAttribute("instituteform", institute);
		model.addAttribute("departments", departmentService.findAll());
		
		return "admin/dept/addinstitute";
	}
	@RequestMapping(value="/addinstitutedb", method=RequestMethod.POST)
	public String addInstituteToDb(@ModelAttribute(value="instituteform") Institute institute, RedirectAttributes redirectAttributes) {
		try {
			instituteService.save(institute);
			redirectAttributes.addAttribute("success", true);
			
			return "redirect:/admin/dept/addinstitute";
		} catch(Exception e) {
			redirectAttributes.addAttribute("error", true);
			
			return "redirect:/admin/dept/addinstitute";
		}
	}
	@RequestMapping(value={"/admin/dept/addspecialization"})
	public String addSpecialization(Model model) {
		Specialization specialization = new Specialization();
		
		model.addAttribute("specializationform", specialization);
		model.addAttribute("departments", departmentService.findAll());
		model.addAttribute("fields", fieldOfStudyService.findAll());
		model.addAttribute("fieldsdepts", fieldOfStudyService.findAllFieldsForAllDepartments());
		
		return "admin/dept/addspecialization";
	}
	@RequestMapping(value="/addspecializationdb", method=RequestMethod.POST)
	public String addSpecializationToDb(@ModelAttribute(value="specializationform") Specialization specialization, RedirectAttributes redirectAttributes) {
		try {
			specializationService.save(specialization);
			redirectAttributes.addAttribute("success", true);
			
			return "redirect:/admin/dept/addspecialization";
		} catch(Exception e) {
			redirectAttributes.addAttribute("error", true);
			
			return "redirect:/admin/dept/addspecialization";
		}
	}
	@RequestMapping(value={"/admin/field/addfieldofstudy"})
	public String addFieldofstudy(Model model) {
		FieldOfStudy fieldOfStudy = new FieldOfStudy();
		
		model.addAttribute("fieldofstudyform", fieldOfStudy);
		model.addAttribute("departments", departmentService.findAll());
		
		return "admin/field/addfieldofstudy";
	}
	@RequestMapping(value="/addfieldofstudydb", method=RequestMethod.POST)
	public String addFieldOfStudyToDb(@ModelAttribute(value="fieldofstudyform") FieldOfStudy fieldOfStudy, RedirectAttributes redirectAttributes) {
		try {
			fieldOfStudyService.save(fieldOfStudy);
			redirectAttributes.addAttribute("success", true);
			
			return "redirect:/admin/field/addfieldofstudy";
		} catch(Exception e) {
			redirectAttributes.addAttribute("error", true);
			
			return "redirect:/admin/field/addfieldofstudy";
		}
	}
	@RequestMapping(value={"/admin/field/addgroup"})
	public String addGroup(Model model) {
		Group group = new Group();
		
		model.addAttribute("groupform", group);
		model.addAttribute("departments", departmentService.findAll());
		model.addAttribute("fieldsdepts", fieldOfStudyService.findAllFieldsForAllDepartments());
		
		return "admin/field/addgroup";
	}
	@RequestMapping(value="/addgroupdb", method=RequestMethod.POST)
	public String addGroupToDb(@ModelAttribute(value="groupform") Group group, RedirectAttributes redirectAttributes) {
		try {
			groupService.save(group);
			redirectAttributes.addAttribute("success", true);
			
			return "redirect:/admin/field/addgroup";
		} catch(Exception e) {
			redirectAttributes.addAttribute("error", true);
			
			return "redirect:/admin/field/addgroup";
		}
	}
	@RequestMapping(value={"/admin/course/addcourse"})
	public String addCourse(Model model) {
		Course course = new Course();

		model.addAttribute("courseform", course);
		model.addAttribute("fieldsdepts", fieldOfStudyService.findAllFieldsForAllDepartments());
		
		return "admin/course/addcourse";
	}
	@RequestMapping(value="/addcoursedb", method=RequestMethod.POST)
	public String addCourseToDb(@ModelAttribute(value="courseform") Course course, RedirectAttributes redirectAttributes) {
		try {
			courseService.save(course);
			redirectAttributes.addAttribute("success", true);
			
			return "redirect:/admin/course/addcourse";
		} catch(Exception e) {
			redirectAttributes.addAttribute("error", true);
			
			return "redirect:/admin/course/addcourse";
		}
	}
}