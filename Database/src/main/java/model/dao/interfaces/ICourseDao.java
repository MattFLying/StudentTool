package model.dao.interfaces;

import java.util.List;
import model.entity.Course;

/***
 * Interface represents course database object.
 * 
 * @author Mateusz Mucha
 *
 */
public interface ICourseDao extends IGenericDao<Course, Integer> {
	/***
	 * Method for search course by specific name and form of this course.
	 * 
	 * @param name
	 *            - course name
	 * @param form
	 *            - name for form of course
	 * @return course object
	 */
	Course findByNameAndForm(String name, String form);

	/***
	 * Method for search all courses form by specific name of course.
	 * 
	 * @param name
	 *            - name of course
	 * @return list courses
	 */
	List<Course> findByName(String name);

	/***
	 * Method for search all courses by specific term number and field of study
	 * identificator.
	 * 
	 * @param term
	 *            - term of course
	 * @param fieldOfStudyId
	 * @return list courses
	 */
	List<Course> findByTermAndFieldOfStudyId(Integer term, Integer fieldOfStudyId);

	/***
	 * Method for search all courses by specific term number and field of study
	 * name.
	 * 
	 * @param term
	 *            - term of course
	 * @param name
	 *            - name of course
	 * @return list courses
	 */
	List<Course> findByTermAndFieldOfStudy(Integer term, String name);

	/***
	 * Method for search all courses by specific field of study identificator.
	 * 
	 * @param fieldOfStudyId
	 *            - identificator of field of study
	 * @return list courses
	 */
	List<Course> findByFieldOfStudyId(Integer fieldOfStudyId);
}