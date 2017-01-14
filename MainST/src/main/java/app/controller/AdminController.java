package app.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	private DepartmentService departmentService = new DepartmentService();
	@Autowired
	private InstituteService instituteService = new InstituteService();
	@Autowired
	private FieldOfStudyService fieldOfStudyService = new FieldOfStudyService();
	@Autowired
	private SpecializationService specializationService = new SpecializationService();
	@Autowired
	private GroupService groupService = new GroupService();
	@Autowired
	private CourseService courseService = new CourseService();
	@Autowired
	private StudentService studentService = new StudentService();
	@Autowired
	private TeacherService teacherService = new TeacherService();
	
	@RequestMapping(value="/admin/user/adduser", method=RequestMethod.GET)
	public String addUser(Model model) {
		User user = new User();

		model.addAttribute("userform", user);
		
		return "admin/user/adduser";
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
	@RequestMapping(value={"/admin/user/addstudents"})
	public String addStudents() {
		return "admin/user/addstudents";
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
	
	@RequestMapping(value={"/admin/dept/adddepartment"})
	public String addDepartment(Model model) {
		Department department = new Department();
		
		model.addAttribute("departmentform", department);
		
		return "admin/dept/adddepartment";
	}
	@RequestMapping(value={"/admin/dept/addinstitute"})
	public String addInstitute(Model model) {
		Institute institute = new Institute();
		
		model.addAttribute("instituteform", institute);
		model.addAttribute("departments", departmentService.findAll());
		
		return "admin/dept/addinstitute";
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
	@RequestMapping(value={"/admin/field/addfieldofstudy"})
	public String addFieldofstudy(Model model) {
		FieldOfStudy fieldOfStudy = new FieldOfStudy();
		
		model.addAttribute("fieldofstudyform", fieldOfStudy);
		model.addAttribute("departments", departmentService.findAll());
		
		return "admin/field/addfieldofstudy";
	}
	@RequestMapping(value={"/admin/field/addgroup"})
	public String addGroup(Model model) {
		Group group = new Group();
		
		model.addAttribute("groupform", group);
		model.addAttribute("departments", departmentService.findAll());
		model.addAttribute("fieldsdepts", fieldOfStudyService.findAllFieldsForAllDepartments());
		
		return "admin/field/addgroup";
	}
	
	@RequestMapping(value={"/admin/course/addcourse"})
	public String addCourse(Model model) {
		Course course = new Course();

		model.addAttribute("courseform", course);
		model.addAttribute("fieldsdepts", fieldOfStudyService.findAllFieldsForAllDepartments());
		
		return "admin/course/addcourse";
	}
	@RequestMapping(value={"/admin/course/addgrade"})
	public String addGrade() {
		return "admin/course/addgrade";
	}
	
	
	
	@RequestMapping(value="/adduserdb", method=RequestMethod.POST)
	public String addUserToDb(@ModelAttribute(value="userform") User user) {
		try {
			userService.createBasicUser(user);
			
			return "redirect:/admin/user/adduser?success";
		} catch(Exception e) {
			return "redirect:/admin/user/adduser?error";
		}
	}
	@RequestMapping(value="/addstudentdb", method=RequestMethod.POST)
	public String addStudentToDb(@ModelAttribute(value="userform") User user, @ModelAttribute(value="studentform") Student student) {
		try {
			studentService.save(student, user);
			
			return "redirect:/admin/user/addstudent?success";
		} catch(Exception e) {
			e.printStackTrace();
			return "redirect:/admin/user/addstudent?error";
		}
	}
	@RequestMapping(value="/addteacherdb", method=RequestMethod.POST)
	public String addTeacherToDb(@ModelAttribute(value="userform") User user, @ModelAttribute(value="teacherform") Teacher teacher) {
		try {
			teacherService.save(teacher, user);
			
			return "redirect:/admin/user/addteacher?success";
		} catch(Exception e) {
			e.printStackTrace();
			return "redirect:/admin/user/addteacher?error";
		}
	}
	
	
	
	@RequestMapping(value="/adddepartmentdb", method=RequestMethod.POST)
	public String addDepartmentToDb(@ModelAttribute(value="departmentform") Department department) {
		try {
			departmentService.save(department);
			
			return "redirect:/admin/dept/adddepartment?success";
		} catch(Exception e) {
			return "redirect:/admin/dept/adddepartment?error";
		}
	}
	@RequestMapping(value="/addinstitutedb", method=RequestMethod.POST)
	public String addInstituteToDb(@ModelAttribute(value="instituteform") Institute institute) {
		try {
			instituteService.save(institute);
			
			return "redirect:/admin/dept/addinstitute?success";
		} catch(Exception e) {
			return "redirect:/admin/dept/addinstitute?error";
		}
	}
	@RequestMapping(value="/addfieldofstudydb", method=RequestMethod.POST)
	public String addFieldOfStudyToDb(@ModelAttribute(value="fieldofstudyform") FieldOfStudy fieldOfStudy) {
		try {
			fieldOfStudyService.save(fieldOfStudy);
			
			return "redirect:/admin/field/addfieldofstudy?success";
		} catch(Exception e) {
			return "redirect:/admin/field/addfieldofstudy?error";
		}
	}
	@RequestMapping(value="/addspecializationdb", method=RequestMethod.POST)
	public String addSpecializationToDb(@ModelAttribute(value="specializationform") Specialization specialization) {
		try {
			specializationService.save(specialization);
			
			return "redirect:/admin/dept/addspecialization?success";
		} catch(Exception e) {
			return "redirect:/admin/dept/addspecialization?error";
		}
	}
	@RequestMapping(value="/addgroupdb", method=RequestMethod.POST)
	public String addGroupToDb(@ModelAttribute(value="groupform") Group group) {
		try {
			groupService.save(group);
			
			return "redirect:/admin/field/addgroup?success";
		} catch(Exception e) {
			return "redirect:/admin/field/addgroup?error";
		}
	}
	@RequestMapping(value="/addcoursedb", method=RequestMethod.POST)
	public String addGroupToDb(@ModelAttribute(value="courseform") Course course) {
		try {
			courseService.save(course);
			
			return "redirect:/admin/course/addcourse?success";
		} catch(Exception e) {
			return "redirect:/admin/course/addcourse?error";
		}
	}
	
	
	
	
	
	
	
	
	
	
}