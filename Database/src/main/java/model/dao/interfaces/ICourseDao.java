package model.dao.interfaces;

import java.util.List;
import model.entity.Course;

public interface ICourseDao extends IGenericDao<Course, Integer> {
	Course findByNameAndForm(String name, String form);
	List<Course> findByName(String name);
	List<Course> findByTermAndFieldOfStudyId(Integer term, Integer fieldOfStudyId);
	List<Course> findByTermAndFieldOfStudy(Integer term, String name);
	List<Course> findByFieldOfStudyId(Integer fieldOfStudyId);
}