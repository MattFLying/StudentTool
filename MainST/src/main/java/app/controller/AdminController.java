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

/***
 * Class is the controller which allows all available for system administrator
 * operations.
 * 
 * @author Mateusz Mucha
 *
 */
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

	/***
	 * Default constructor.
	 */
	public AdminController() {
	}

	/***
	 * Method allows to view main page in system administrator panel.
	 * 
	 * @param session
	 *            - session object
	 * @return index page to show
	 */
	@RequestMapping(value = "/admin/index", method = RequestMethod.GET)
	public String adminIndex(HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();

		session.setAttribute("username", username);

		return "admin/index";
	}

	/***
	 * Method allows to redirect to failed page after unsuccessful operations.
	 * 
	 * @return failed page to show
	 */
	@RequestMapping(value = "/admin/failed", method = RequestMethod.GET)
	public String operationFailed() {
		return "admin/failed";
	}

	/***
	 * Method show page to add user into database.
	 * 
	 * @param model
	 *            - model object
	 * @return add user to database page
	 */
	@RequestMapping(value = "/admin/user/adduser", method = RequestMethod.GET)
	public String addUserToDb(Model model) {
		User user = new User();

		model.addAttribute("userform", user);

		return "admin/user/adduser";
	}

	/***
	 * Method allows administrator to add user account into database.
	 * 
	 * @param user
	 *            - user object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return redirecting correct page after complete operation
	 */
	@RequestMapping(value = "/adduserdb", method = RequestMethod.POST)
	public String addUserToDb(@ModelAttribute(value = "userform") User user, RedirectAttributes redirectAttributes) {
		try {
			userService.createBasicUser(user, 1);
			redirectAttributes.addAttribute("success", true);

			return "redirect:/admin/user/adduser";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays page to add student into database.
	 * 
	 * @param model
	 *            - model object
	 * @return add student into database page
	 */
	@RequestMapping(value = { "/admin/user/addstudent" })
	public String addStudentToDb(Model model) {
		User user = new User();
		Student student = new Student();

		model.addAttribute("userform", user);
		model.addAttribute("studentform", student);
		model.addAttribute("fieldsdepts", fieldOfStudyService.findAllFieldsForAllDepartments());
		model.addAttribute("groupService", groupService);
		model.addAttribute("specializationService", specializationService);

		return "admin/user/addstudent";
	}

	/***
	 * Method allows administrator to add student into database.
	 * 
	 * @param user
	 *            - user object
	 * @param student
	 *            - student object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/addstudentdb", method = RequestMethod.POST)
	public String addStudentToDb(@ModelAttribute(value = "userform") User user,
			@ModelAttribute(value = "studentform") Student student, RedirectAttributes redirectAttributes) {
		try {
			studentService.save(student, user, 1);
			redirectAttributes.addAttribute("success", true);

			return "redirect:/admin/user/addstudent";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method to display page to create students from excel file.
	 * 
	 * @return page to create students from excel file
	 */
	@RequestMapping(value = { "/admin/user/addstudents" })
	public String addStudentsToDb() {
		return "admin/user/addstudents";
	}

	/***
	 * Method allows to add students into database from excel file.
	 * 
	 * @param file
	 *            - excel file
	 * @param model
	 *            - model object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/addstudentsdb", method = RequestMethod.POST)
	public String addStudentsToDb(@RequestParam("file") MultipartFile file, Model model,
			RedirectAttributes redirectAttributes) {
		try {
			if (file == null) {
				redirectAttributes.addAttribute("error", true);

				return "redirect:/admin/failed";
			}

			studentService.createStudentsFromExcel(file.getInputStream());
			redirectAttributes.addAttribute("success", true);

			return "redirect:/admin/user/addstudents";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays page to add teacher into database.
	 * 
	 * @param model
	 *            - model object
	 * @return add teacher into database page
	 */
	@RequestMapping(value = { "/admin/user/addteacher" })
	public String addTeacherToDb(Model model) {
		User user = new User();
		Teacher teacher = new Teacher();

		model.addAttribute("userform", user);
		model.addAttribute("teacherform", teacher);
		model.addAttribute("institutesdepts", instituteService.findAllInstitutesForAllDepartments());

		return "admin/user/addteacher";
	}

	/***
	 * Method allows administrator to add teacher into database.
	 * 
	 * @param user
	 *            - user object
	 * @param teacher
	 *            - teacher object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/addteacherdb", method = RequestMethod.POST)
	public String addTeacherToDb(@ModelAttribute(value = "userform") User user,
			@ModelAttribute(value = "teacherform") Teacher teacher, RedirectAttributes redirectAttributes) {
		try {
			teacherService.save(teacher, user, 1);
			redirectAttributes.addAttribute("success", true);

			return "redirect:/admin/user/addteacher";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays page to add department into database.
	 * 
	 * @param model
	 *            - model object
	 * @return add department into database page
	 */
	@RequestMapping(value = { "/admin/dept/adddepartment" })
	public String addDepartmentToDb(Model model) {
		Department department = new Department();

		model.addAttribute("departmentform", department);

		return "admin/dept/adddepartment";
	}

	/***
	 * Method allows administrator to add department into database.
	 * 
	 * @param department
	 *            - department object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/adddepartmentdb", method = RequestMethod.POST)
	public String addDepartmentToDb(@ModelAttribute(value = "departmentform") Department department,
			RedirectAttributes redirectAttributes) {
		try {
			departmentService.save(department);
			redirectAttributes.addAttribute("success", true);

			return "redirect:/admin/dept/adddepartment";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays page to search department to modification.
	 * 
	 * @param model
	 *            - model object
	 * @return department modification page
	 */
	@RequestMapping(value = { "/admin/dept/finddept" })
	public String findDept(Model model) {
		TempValues findDeptName = new TempValues();

		model.addAttribute("findDept", findDeptName);

		return "admin/dept/finddept";
	}

	/***
	 * Method allows administrator to find specific department to modification.
	 * 
	 * @param session
	 *            - session object
	 * @param findDeptName
	 *            - cache department value
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/departmentexist", method = RequestMethod.POST)
	public String findDept(HttpSession session, @ModelAttribute(value = "findDept") TempValues findDeptName,
			RedirectAttributes redirectAttributes) {
		try {
			Department department = departmentService.findOneByFullName(findDeptName.getName());
			if (department != null) {
				session.setAttribute("deptId", department.getDetails().getId());
				redirectAttributes.addAttribute("deptId", department.getDetails().getId());
				redirectAttributes.addAttribute("found", true);

				return "redirect:/admin/dept/editdepartment";
			} else {
				redirectAttributes.addAttribute("notfound", true);

				return "redirect:/admin/failed";
			}
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays modification page of department.
	 * 
	 * @param model
	 *            - model object
	 * @param deptId
	 *            - department identificator
	 * @return modification page of department
	 */
	@RequestMapping(value = { "/admin/dept/editdepartment" })
	public String editDepartment(Model model, @RequestParam("deptId") int deptId) {
		Department department = departmentService.findOneByDepartmentId(deptId);

		model.addAttribute("departmentform", department);

		return "admin/dept/editdepartment";
	}

	/***
	 * Method allows administrator to edit founded department.
	 * 
	 * @param session
	 *            - session object
	 * @param department
	 *            - department object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/editdepartmentdb", method = RequestMethod.POST)
	public String editDepartment(HttpSession session, @ModelAttribute(value = "departmentform") Department department,
			RedirectAttributes redirectAttributes) {
		try {
			department.getDetails().setId((int) session.getAttribute("deptId"));

			departmentService.updateDepartment(department);
			redirectAttributes.addAttribute("deptId", session.getAttribute("deptId"));
			redirectAttributes.addAttribute("edited", true);

			return "redirect:/admin/dept/editdepartment";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays page to search institute.
	 * 
	 * @param model
	 *            - model object
	 * @return search institute page
	 */
	@RequestMapping(value = { "/admin/dept/findinstitute" })
	public String findInstitute(Model model) {
		TempValues findInstituteName = new TempValues();

		model.addAttribute("findInstitute", findInstituteName);

		return "admin/dept/findinstitute";
	}

	/***
	 * Method allows administrator to search institute.
	 * 
	 * @param session
	 *            - session object
	 * @param findInstituteName
	 *            - institute cache value
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/institutexist", method = RequestMethod.POST)
	public String findInstitute(HttpSession session,
			@ModelAttribute(value = "findInstitute") TempValues findInstituteName,
			RedirectAttributes redirectAttributes) {
		try {
			Institute institute = instituteService.findByFullName(findInstituteName.getName());
			if (institute != null) {
				session.setAttribute("instituteId", institute.getDetails().getId());
				redirectAttributes.addAttribute("instituteId", institute.getDetails().getId());
				redirectAttributes.addAttribute("found", true);

				return "redirect:/admin/dept/editinstitute";
			} else {
				redirectAttributes.addAttribute("notfound", true);

				return "redirect:/admin/failed";
			}
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays page to modificate institute.
	 * 
	 * @param model
	 *            - model object
	 * @param instituteId
	 *            - institute identificator
	 * @return institute modification page
	 */
	@RequestMapping(value = { "/admin/dept/editinstitute" })
	public String editInstitute(Model model, @RequestParam("instituteId") int instituteId) {
		Institute institute = instituteService.findById(instituteId);

		model.addAttribute("instituteform", institute);
		model.addAttribute("departments", departmentService.findAll());

		return "admin/dept/editinstitute";
	}

	/***
	 * Method allows administrator to edit institute.
	 * 
	 * @param session
	 *            - session object
	 * @param institute
	 *            - institute object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/editinstitutedb", method = RequestMethod.POST)
	public String editInstitute(HttpSession session, @ModelAttribute(value = "instituteform") Institute institute,
			RedirectAttributes redirectAttributes) {
		try {
			institute.getDetails().setId((int) session.getAttribute("instituteId"));
			instituteService.updateInstitute(institute);
			redirectAttributes.addAttribute("instituteId", session.getAttribute("instituteId"));
			redirectAttributes.addAttribute("edited", true);

			return "redirect:/admin/dept/editinstitute";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays page to find specialization.
	 * 
	 * @param model
	 *            - model object
	 * @return find specialization page
	 */
	@RequestMapping(value = { "/admin/dept/findspecialization" })
	public String findSpecialization(Model model) {
		TempValues findSpecializationName = new TempValues();

		model.addAttribute("findSpecialization", findSpecializationName);

		return "admin/dept/findspecialization";
	}

	/***
	 * Method allows administrator to search specialization.
	 * 
	 * @param session
	 *            - session object
	 * @param findSpecializationName
	 *            - specialization cache value
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/specializationexist", method = RequestMethod.POST)
	public String findSpecialization(HttpSession session,
			@ModelAttribute(value = "findSpecialization") TempValues findSpecializationName,
			RedirectAttributes redirectAttributes) {
		try {
			Specialization specialization = specializationService.findOneByName(findSpecializationName.getName());
			if (specialization != null) {
				session.setAttribute("specializationId", specialization.getDetails().getId());
				redirectAttributes.addAttribute("specializationId", specialization.getDetails().getId());
				redirectAttributes.addAttribute("found", true);

				return "redirect:/admin/dept/editspecialization";
			} else {
				redirectAttributes.addAttribute("notfound", true);

				return "redirect:/admin/failed";
			}
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays specialization modification page.
	 * 
	 * @param session
	 *            - session object
	 * @param model
	 *            - model object
	 * @param specializationId
	 *            - specialization identificator
	 * @return edit specialization page
	 */
	@RequestMapping(value = { "/admin/dept/editspecialization" })
	public String editSpecialization(HttpSession session, Model model,
			@RequestParam("specializationId") int specializationId) {
		Specialization specialization = specializationService.findOneById(specializationId);

		session.setAttribute("fieldOfStudyName",
				specialization.getDetails().getFieldOfStudy().getDetails().getFieldOfStudyName());
		model.addAttribute("specializationform", specialization);
		model.addAttribute("departments", departmentService.findAll());
		model.addAttribute("fields", fieldOfStudyService.findAll());
		model.addAttribute("fieldsdepts", fieldOfStudyService.findAllFieldsForAllDepartments());

		return "admin/dept/editspecialization";
	}

	/***
	 * Method allows administrator to edit specialization.
	 * 
	 * @param session
	 *            - session object
	 * @param specialization
	 *            - specialization objec t
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/editspecializationdb", method = RequestMethod.POST)
	public String editSpecialization(HttpSession session,
			@ModelAttribute(value = "specializationform") Specialization specialization,
			RedirectAttributes redirectAttributes) {
		try {
			specialization.getDetails().setId((int) session.getAttribute("specializationId"));
			specialization.getDetails().getFieldOfStudy().getDetails()
					.setFieldOfStudyName((String) session.getAttribute("fieldOfStudyName"));

			specializationService.updateSpecialization(specialization);
			redirectAttributes.addAttribute("specializationId", session.getAttribute("specializationId"));
			redirectAttributes.addAttribute("edited", true);

			return "redirect:/admin/dept/editspecialization";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays find course page.
	 * 
	 * @param model
	 *            - model object
	 * @return find course page
	 */
	@RequestMapping(value = { "/admin/course/findcourse" })
	public String findCourse(Model model) {
		TempValues findCourseName = new TempValues();

		model.addAttribute("findCourse", findCourseName);

		return "admin/course/findcourse";
	}

	/***
	 * Method allows administrator to search course.
	 * 
	 * @param session
	 *            - session object
	 * @param findCourseName
	 *            - course cache value
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/courseexist", method = RequestMethod.POST)
	public String findCourse(HttpSession session, @ModelAttribute(value = "findCourse") TempValues findCourseName,
			RedirectAttributes redirectAttributes) {
		try {
			Course course = courseService.findByNameAndForm(findCourseName.getName(), findCourseName.getForm());

			if (course != null) {
				session.setAttribute("courseId", course.getDetails().getId());
				redirectAttributes.addAttribute("courseId", course.getDetails().getId());
				redirectAttributes.addAttribute("found", true);

				return "redirect:/admin/course/editcourse";
			} else {
				redirectAttributes.addAttribute("notfound", true);

				return "redirect:/admin/failed";
			}
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays modification page of course.
	 * 
	 * @param session
	 *            - session object
	 * @param model
	 *            - model object
	 * @param courseId
	 *            - course identificator
	 * @return modification course page
	 */
	@RequestMapping(value = { "/admin/course/editcourse" })
	public String editCourse(HttpSession session, Model model, @RequestParam("courseId") int courseId) {
		Course course = courseService.findOneById(courseId);

		model.addAttribute("courseform", course);
		model.addAttribute("fieldsdepts", fieldOfStudyService.findAllFieldsForAllDepartments());

		return "admin/course/editcourse";
	}

	/***
	 * Method allows administrator to edit course.
	 * 
	 * @param session
	 *            - session object
	 * @param course
	 *            - course object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/editcoursedb", method = RequestMethod.POST)
	public String editCourse(HttpSession session, @ModelAttribute(value = "courseform") Course course,
			RedirectAttributes redirectAttributes) {
		try {
			course.getDetails().setId((int) session.getAttribute("courseId"));
			courseService.updateCourse(course);
			redirectAttributes.addAttribute("courseId", session.getAttribute("courseId"));
			redirectAttributes.addAttribute("edited", true);

			return "redirect:/admin/course/editcourse";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays page to search field of study.
	 * 
	 * @param model
	 *            - model object
	 * @return search field of study
	 */
	@RequestMapping(value = { "/admin/field/findfield" })
	public String findField(Model model) {
		TempValues findFieldName = new TempValues();

		model.addAttribute("findField", findFieldName);

		return "admin/field/findfield";
	}

	/***
	 * Method allows administrator to find field of study.
	 * 
	 * @param session
	 *            - session object
	 * @param findFieldOfStudyName
	 *            - field of study cache value
	 * @param redirectAttributes
	 *            - redirecting cache value
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/fieldexist", method = RequestMethod.POST)
	public String findField(HttpSession session, @ModelAttribute(value = "findField") TempValues findFieldOfStudyName,
			RedirectAttributes redirectAttributes) {
		try {
			FieldOfStudy field = fieldOfStudyService.findOneByName(findFieldOfStudyName.getName());

			if (field != null) {
				session.setAttribute("fieldId", field.getDetails().getId());
				redirectAttributes.addAttribute("fieldId", field.getDetails().getId());
				redirectAttributes.addAttribute("found", true);

				return "redirect:/admin/field/editfieldofstudy";
			} else {
				redirectAttributes.addAttribute("notfound", true);

				return "redirect:/admin/failed";
			}
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays modification page for field of study.
	 * 
	 * @param session
	 *            - session object
	 * @param model
	 *            - model object
	 * @param fieldId
	 *            - field of study identificator
	 * @return field of study modification page
	 */
	@RequestMapping(value = { "/admin/field/editfieldofstudy" })
	public String editField(HttpSession session, Model model, @RequestParam("fieldId") int fieldId) {
		FieldOfStudy field = fieldOfStudyService.findOneByFieldId(fieldId);

		model.addAttribute("fieldofstudyform", field);
		model.addAttribute("departments", departmentService.findAll());

		return "admin/field/editfieldofstudy";
	}

	/***
	 * Method allows administrator to edit field of study.
	 * 
	 * @param session
	 *            - session object
	 * @param field
	 *            - field
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/editfielddb", method = RequestMethod.POST)
	public String editField(HttpSession session, @ModelAttribute(value = "courseform") FieldOfStudy field,
			RedirectAttributes redirectAttributes) {
		try {
			field.getDetails().setId((int) session.getAttribute("fieldId"));
			fieldOfStudyService.updateField(field);
			redirectAttributes.addAttribute("fieldId", session.getAttribute("fieldId"));
			redirectAttributes.addAttribute("edited", true);

			return "redirect:/admin/field/editfieldofstudy";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays page to find group.
	 * 
	 * @param model
	 *            - model object
	 * @return find group page
	 */
	@RequestMapping(value = { "/admin/field/findgroup" })
	public String findGroup(Model model) {
		TempValues findGroupName = new TempValues();

		model.addAttribute("findGroup", findGroupName);

		return "admin/field/findgroup";
	}

	/***
	 * Method allows administrator to search group.
	 * 
	 * @param session
	 *            - session object
	 * @param findGroupName
	 *            - group cache value
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/groupexist", method = RequestMethod.POST)
	public String findGroup(HttpSession session, @ModelAttribute(value = "findGroup") TempValues findGroupName,
			RedirectAttributes redirectAttributes) {
		try {
			Group group = groupService.findOneByName(findGroupName.getName());

			if (group != null) {
				session.setAttribute("groupId", group.getDetails().getId());
				redirectAttributes.addAttribute("groupId", group.getDetails().getId());
				redirectAttributes.addAttribute("found", true);

				return "redirect:/admin/field/editgroup";
			} else {
				redirectAttributes.addAttribute("notfound", true);

				return "redirect:/admin/failed";
			}
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays modification page for group.
	 * 
	 * @param session
	 *            - session object
	 * @param model
	 *            - model object
	 * @param groupId
	 *            - group identificator
	 * @return group modification page
	 */
	@RequestMapping(value = { "/admin/field/editgroup" })
	public String editGroup(HttpSession session, Model model, @RequestParam("groupId") int groupId) {
		Group group = groupService.findOneByGroupId(groupId);

		model.addAttribute("fieldofstudyform", group);
		model.addAttribute("groupform", group);
		model.addAttribute("departments", departmentService.findAll());
		model.addAttribute("fieldsdepts", fieldOfStudyService.findAllFieldsForAllDepartments());

		return "admin/field/editgroup";
	}

	/***
	 * Method allows administrator to edit group.
	 * 
	 * @param session
	 *            - session object
	 * @param group
	 *            - group object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/editgroupdb", method = RequestMethod.POST)
	public String editGroup(HttpSession session, @ModelAttribute(value = "groupform") Group group,
			RedirectAttributes redirectAttributes) {
		try {
			group.getDetails().setId((int) session.getAttribute("groupId"));
			groupService.updateGroup(group);
			redirectAttributes.addAttribute("groupId", session.getAttribute("groupId"));
			redirectAttributes.addAttribute("edited", true);

			return "redirect:/admin/field/editgroup";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays page to search student.
	 * 
	 * @param model
	 *            - model object
	 * @return search student page
	 */
	@RequestMapping(value = { "/admin/user/findstudent" })
	public String findStudent(Model model) {
		TempValues findStudentName = new TempValues();

		model.addAttribute("findStudent", findStudentName);

		return "admin/user/findstudent";
	}

	/***
	 * Method allows administrator to search student.
	 * 
	 * @param session
	 *            - session object
	 * @param findStudentName
	 *            - student cache value
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/studentexist", method = RequestMethod.POST)
	public String findStudent(HttpSession session, @ModelAttribute(value = "findStudent") TempValues findStudentName,
			RedirectAttributes redirectAttributes) {
		try {
			Student student = studentService.createStudent(findStudentName.getName());

			if (student != null) {
				session.setAttribute("studentId", student.getDetails().getId());
				session.setAttribute("album", findStudentName.getName());
				redirectAttributes.addAttribute("studentId", student.getDetails().getId());
				redirectAttributes.addAttribute("found", true);

				return "redirect:/admin/user/editstudent";
			} else {
				redirectAttributes.addAttribute("notfound", true);

				return "redirect:/admin/failed";
			}
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays modification page of student.
	 * 
	 * @param session
	 *            - session object
	 * @param model
	 *            - model object
	 * @param studentId
	 *            - student identificator
	 * @return student modification page
	 */
	@RequestMapping(value = { "/admin/user/editstudent" })
	public String editStudent(HttpSession session, Model model, @RequestParam("studentId") int studentId) {
		Student student = studentService.findById(studentId);

		model.addAttribute("studentform", student);
		model.addAttribute("fieldsdepts", fieldOfStudyService.findAllFieldsForAllDepartments());
		model.addAttribute("groupService", groupService);
		model.addAttribute("specializationService", specializationService);

		return "admin/user/editstudent";
	}

	/***
	 * Method allows administrator to edit student.
	 * 
	 * @param session
	 *            - session object
	 * @param student
	 *            - student object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/editstudentdb", method = RequestMethod.POST)
	public String editStudent(HttpSession session, @ModelAttribute(value = "studentform") Student student,
			RedirectAttributes redirectAttributes) {
		try {
			student.getDetails().setId((int) session.getAttribute("studentId"));
			studentService.updateStudent(student, (String) session.getAttribute("album"));
			redirectAttributes.addAttribute("studentId", session.getAttribute("studentId"));
			redirectAttributes.addAttribute("edited", true);

			return "redirect:/admin/user/editstudent";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays page to search teacher.
	 * 
	 * @param model
	 *            - model object
	 * @return search teacher page
	 */
	@RequestMapping(value = { "/admin/user/findteacher" })
	public String findTeacher(Model model) {
		TempValues findTeacherName = new TempValues();

		model.addAttribute("findTeacher", findTeacherName);

		return "admin/user/findteacher";
	}

	/***
	 * Method allows administrator to search teacher.
	 * 
	 * @param session
	 *            - session object
	 * @param findTeacherName
	 *            - teacher cache value
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/teacherexist", method = RequestMethod.POST)
	public String findTeacher(HttpSession session, @ModelAttribute(value = "findTeacher") TempValues findTeacherName,
			RedirectAttributes redirectAttributes) {
		try {
			Teacher teacher = teacherService.findTeacher(findTeacherName.getName(), findTeacherName.getForm());

			if (teacher != null) {
				session.setAttribute("teacherId", teacher.getDetails().getId());
				session.setAttribute("login",
						teacher.getDetails().getFirstName() + " " + teacher.getDetails().getLastName());
				redirectAttributes.addAttribute("teacherId", teacher.getDetails().getId());
				redirectAttributes.addAttribute("found", true);

				return "redirect:/admin/user/editteacher";
			} else {
				redirectAttributes.addAttribute("notfound", true);

				return "redirect:/admin/failed";
			}
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays modification page of teacher.
	 * 
	 * @param session
	 *            - session object
	 * @param model
	 *            - model object
	 * @param teacherId
	 *            - teacher identificator
	 * @return teacher modification page
	 */
	@RequestMapping(value = { "/admin/user/editteacher" })
	public String editTeacher(HttpSession session, Model model, @RequestParam("teacherId") int teacherId) {
		Teacher teacher = teacherService.findById(teacherId);

		model.addAttribute("teacherform", teacher);
		model.addAttribute("institutesdepts", instituteService.findAllInstitutesForAllDepartments());

		return "admin/user/editteacher";
	}

	/***
	 * Method allows administrator to edit teacher.
	 * 
	 * @param session
	 *            - session object
	 * @param teacher
	 *            - teacher object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/editteacherdb", method = RequestMethod.POST)
	public String editTeacher(HttpSession session, @ModelAttribute(value = "teacherform") Teacher teacher,
			RedirectAttributes redirectAttributes) {
		try {
			teacher.getDetails().setId((int) session.getAttribute("teacherId"));
			teacherService.updateTeacher(teacher, (String) session.getAttribute("login"));
			redirectAttributes.addAttribute("teacherId", session.getAttribute("teacherId"));
			redirectAttributes.addAttribute("edited", true);

			return "redirect:/admin/user/editteacher";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays page to search user.
	 * 
	 * @param model
	 *            - model object
	 * @return search user page
	 */
	@RequestMapping(value = { "/admin/user/finduser" })
	public String findUser(Model model) {
		TempValues findUserName = new TempValues();

		model.addAttribute("findUser", findUserName);

		return "admin/user/finduser";
	}

	/***
	 * Method allows administrator to search user.
	 * 
	 * @param session
	 *            - session object
	 * @param findUserName
	 *            - user cache object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/userexist", method = RequestMethod.POST)
	public String findUser(HttpSession session, @ModelAttribute(value = "findUser") TempValues findUserName,
			RedirectAttributes redirectAttributes) {
		try {
			User user = userService.findUserByLogin(findUserName.getName());

			if (user != null) {
				session.setAttribute("userId", findUserName.getName());
				redirectAttributes.addAttribute("userId", findUserName.getName());
				redirectAttributes.addAttribute("found", true);

				return "redirect:/admin/user/edituser";
			} else {
				redirectAttributes.addAttribute("notfound", true);

				return "redirect:/admin/failed";
			}
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays page to edit user.
	 * 
	 * @param session
	 *            - session object
	 * @param model
	 *            - model object
	 * @param userId
	 *            - user identificator
	 * @return edit user page
	 */
	@RequestMapping(value = { "/admin/user/edituser" })
	public String editUser(HttpSession session, Model model, @RequestParam("userId") String userId) {
		User user = userService.findUserByLogin((String) session.getAttribute("userId"));

		model.addAttribute("userform", user);

		return "admin/user/edituser";
	}

	/***
	 * Method allows administrator to edit user.
	 * 
	 * @param session
	 *            - session object
	 * @param user
	 *            - user object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/edituserdb", method = RequestMethod.POST)
	public String editUser(HttpSession session, @ModelAttribute(value = "userform") User user,
			RedirectAttributes redirectAttributes) {
		try {
			user.setLogin((String) session.getAttribute("userId"));
			userService.updateUser(user);
			redirectAttributes.addAttribute("userId", (String) session.getAttribute("userId"));
			redirectAttributes.addAttribute("edited", true);

			return "redirect:/admin/user/edituser";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays page to add institute into database.
	 * 
	 * @param model
	 *            - model object
	 * @return add institute into database page
	 */
	@RequestMapping(value = { "/admin/dept/addinstitute" })
	public String addInstituteToDb(Model model) {
		Institute institute = new Institute();

		model.addAttribute("instituteform", institute);
		model.addAttribute("departments", departmentService.findAll());

		return "admin/dept/addinstitute";
	}

	/***
	 * Method allows administrator to add institute into database.
	 * 
	 * @param institute
	 *            - institute object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/addinstitutedb", method = RequestMethod.POST)
	public String addInstituteToDb(@ModelAttribute(value = "instituteform") Institute institute,
			RedirectAttributes redirectAttributes) {
		try {
			instituteService.save(institute);
			redirectAttributes.addAttribute("success", true);

			return "redirect:/admin/dept/addinstitute";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays page to add specialization into database.
	 * 
	 * @param model
	 *            - model object
	 * @return add specialization into database page
	 */
	@RequestMapping(value = { "/admin/dept/addspecialization" })
	public String addSpecializationToDb(Model model) {
		Specialization specialization = new Specialization();

		model.addAttribute("specializationform", specialization);
		model.addAttribute("departments", departmentService.findAll());
		model.addAttribute("fields", fieldOfStudyService.findAll());
		model.addAttribute("fieldsdepts", fieldOfStudyService.findAllFieldsForAllDepartments());

		return "admin/dept/addspecialization";
	}

	/***
	 * Method allows administrator to add specialization into database.
	 * 
	 * @param specialization
	 *            - specialization object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/addspecializationdb", method = RequestMethod.POST)
	public String addSpecializationToDb(@ModelAttribute(value = "specializationform") Specialization specialization,
			RedirectAttributes redirectAttributes) {
		try {
			specializationService.save(specialization);
			redirectAttributes.addAttribute("success", true);

			return "redirect:/admin/dept/addspecialization";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays page to add field of study.
	 * 
	 * @param model
	 *            - model object
	 * @return add field of study page
	 */
	@RequestMapping(value = { "/admin/field/addfieldofstudy" })
	public String addFieldOfStudyToDb(Model model) {
		FieldOfStudy fieldOfStudy = new FieldOfStudy();

		model.addAttribute("fieldofstudyform", fieldOfStudy);
		model.addAttribute("departments", departmentService.findAll());

		return "admin/field/addfieldofstudy";
	}

	/***
	 * Method allows administrator to add field of study into database.
	 * 
	 * @param fieldOfStudy
	 *            - field of study object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/addfieldofstudydb", method = RequestMethod.POST)
	public String addFieldOfStudyToDb(@ModelAttribute(value = "fieldofstudyform") FieldOfStudy fieldOfStudy,
			RedirectAttributes redirectAttributes) {
		try {
			fieldOfStudyService.save(fieldOfStudy);
			redirectAttributes.addAttribute("success", true);

			return "redirect:/admin/field/addfieldofstudy";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays page to add group into database.
	 * 
	 * @param model
	 *            - model object
	 * @return add group page
	 */
	@RequestMapping(value = { "/admin/field/addgroup" })
	public String addGroupToDb(Model model) {
		Group group = new Group();

		model.addAttribute("groupform", group);
		model.addAttribute("departments", departmentService.findAll());
		model.addAttribute("fieldsdepts", fieldOfStudyService.findAllFieldsForAllDepartments());

		return "admin/field/addgroup";
	}

	/***
	 * Method allows administrator to add group into database.
	 * 
	 * @param group
	 *            - group object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/addgroupdb", method = RequestMethod.POST)
	public String addGroupToDb(@ModelAttribute(value = "groupform") Group group,
			RedirectAttributes redirectAttributes) {
		try {
			groupService.save(group);
			redirectAttributes.addAttribute("success", true);

			return "redirect:/admin/field/addgroup";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Method displays page to add course into database.
	 * 
	 * @param model
	 *            - model object
	 * @return add course page
	 */
	@RequestMapping(value = { "/admin/course/addcourse" })
	public String addCourseToDb(Model model) {
		Course course = new Course();

		model.addAttribute("courseform", course);
		model.addAttribute("fieldsdepts", fieldOfStudyService.findAllFieldsForAllDepartments());

		return "admin/course/addcourse";
	}

	/***
	 * Method allows administrator to add course into database.
	 * 
	 * @param course
	 *            - course object
	 * @param redirectAttributes
	 *            - redirecting attributes
	 * @return correct page after complete operation
	 */
	@RequestMapping(value = "/addcoursedb", method = RequestMethod.POST)
	public String addCourseToDb(@ModelAttribute(value = "courseform") Course course,
			RedirectAttributes redirectAttributes) {
		try {
			courseService.save(course);
			redirectAttributes.addAttribute("success", true);

			return "redirect:/admin/course/addcourse";
		} catch (Exception e) {
			redirectAttributes.addAttribute("error", true);

			return "redirect:/admin/failed";
		}
	}

	/***
	 * Class represents cache values.
	 * 
	 * @author Mateusz Mucha
	 *
	 */
	private static class TempValues {
		private String name = null;
		private String form = null;
		private int id = 0;

		/***
		 * Default constructor.
		 */
		public TempValues() {
		}

		/***
		 * Default constructor with specific name injection.
		 */
		public TempValues(String name) {
			this.name = name;
		}

		/***
		 * Method to gets cache name value.
		 * 
		 * @return cache of name
		 */
		public String getName() {
			return name;
		}

		/***
		 * Method to sets cache name value.
		 * 
		 * @param name
		 *            - cache of name
		 */
		public void setName(String name) {
			this.name = name;
		}

		/***
		 * Method to sets cache identificator.
		 * 
		 * @return identificator of cache
		 */
		public int getId() {
			return id;
		}

		/***
		 * Method to sets cache identificator.
		 * 
		 * @param id
		 *            - identificator of cache
		 */
		public void setId(int id) {
			this.id = id;
		}

		/***
		 * Method to gets form value of cache.
		 * 
		 * @return form cache value
		 */
		public String getForm() {
			return form;
		}

		/***
		 * Method to sets form value of cache.
		 * 
		 * @param form
		 *            - form value of cache
		 */
		public void setForm(String form) {
			this.form = form;
		}
	}
}