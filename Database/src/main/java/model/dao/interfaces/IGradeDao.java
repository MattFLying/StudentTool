package model.dao.interfaces;

import java.util.List;
import model.entity.Grade;
import model.entity.GradeId;

/***
 * Interface represents grade database object.
 * 
 * @author Mateusz Mucha
 *
 */
public interface IGradeDao extends IGenericDao<Grade, GradeId> {
	/***
	 * Method to search grade by specific identificator.
	 * 
	 * @param id
	 *            - grade identificator
	 * @return grade object
	 */
	Grade findByGradeId(Integer id);

	/***
	 * Method to search grades by teacher identificator.
	 * 
	 * @param id
	 *            - teacher identificator
	 * @return grade object
	 */
	List<Grade> findByTeacherId(Integer id);

	/***
	 * Method to search grades by student identificator.
	 * 
	 * @param id
	 *            - student identificator
	 * @return list of grades
	 */
	List<Grade> findByStudentId(Integer id);

	/***
	 * Method to search grades by type of grade.
	 * 
	 * @param type
	 *            - type of grade
	 * @return list of grades
	 */
	List<Grade> findByGradeType(String type);

	/***
	 * Method to search grades by student album number.
	 * 
	 * @param album
	 *            - album number of student
	 * @return list of grades
	 */
	List<Grade> findByStudentAlbum(String album);

	/***
	 * Method to search grades for student by course identificator and grade
	 * type.
	 * 
	 * @param student
	 *            - student identificator
	 * @param course
	 *            - course identificator
	 * @param gradeType
	 *            - type of grade
	 * @return list of grades
	 */
	List<Grade> findByStudentIdAndCourseIdAndGradeType(Integer student, Integer course, String gradeType);

	/***
	 * Method to search grades for student by course identificator, teacher
	 * identificator and grade type.
	 * 
	 * @param course
	 *            - course identificator
	 * @param student
	 *            - student identificator
	 * @param teacher
	 *            - teacher identificator
	 * @param gradeType
	 *            - type of grade
	 * @return list of grades
	 */
	List<Grade> findByStudentIdAndCourseIdAndTeacherIdAndGradeType(Integer course, Integer student, Integer teacher,
			String gradeType);
}