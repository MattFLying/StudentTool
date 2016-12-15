package controller.dao;

import java.util.List;
import model.dao.TeachersCoursesDao;
import model.dao.interfaces.ITeachersCoursesDao;
import model.entity.Course;
import model.entity.TeachersCourses;

public class TeachersCoursesService extends GenericDaoService<TeachersCoursesDao, ITeachersCoursesDao> {
	private TeachersCoursesDao courses = null;
	
	
	public TeachersCoursesService() {
		this.courses = new TeachersCoursesDao();
	}


	@Override
	public TeachersCoursesDao getDao() {
		return courses;
	}
	@Override
	public ITeachersCoursesDao getDaoInterface() {
		return courses;
	}
	public List<Course> getAllByTeacherId(Integer id) {
		return courses.findByTeacherId(id);
	}
	public List<TeachersCourses> getAllByCourseId(Integer id) {
		return courses.findByCourseId(id);
	}
	public List<TeachersCourses> getAllByCourseIdAndTeacherId(Integer courseId, Integer teacherId) {
		return courses.findByCourseIdAndTeacherId(courseId, teacherId);
	}
}