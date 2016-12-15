package model.dao.interfaces;

import java.util.List;
import model.entity.Course;

public interface ICourseDao extends IGenericDao<Course, Integer> {
	Course findByName(String name);
	List<Course> findByTermAndFieldOfStudyId(Integer term, Integer fieldOfStudyId);
	List<Course> findByTermAndFieldOfStudy(Integer term, String name);
}