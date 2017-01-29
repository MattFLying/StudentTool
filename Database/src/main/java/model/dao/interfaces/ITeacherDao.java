package model.dao.interfaces;

import java.util.List;
import model.entity.Teacher;
import model.entity.TeacherId;

/***
 * Interface represents address database object.
 * 
 * @author Mateusz Mucha
 *
 */
public interface ITeacherDao extends IGenericDao<Teacher, TeacherId> {
	/***
	 * Method to search teachers by department identificator.
	 * 
	 * @param id
	 *            - identificator of department
	 * @return list of teachers
	 */
	List<Teacher> findByDepartmentId(Integer id);

	/***
	 * Method to search teachers by institute identificator.
	 * 
	 * @param id
	 *            - identificator of institute
	 * @return list of teachers
	 */
	List<Teacher> findByInstituteId(Integer id);

	/***
	 * Method to search teacher by first name and last name.
	 * 
	 * @param firstName
	 *            - first name of teacher
	 * @param lastName
	 *            - last name of teacher
	 * @return teacher object
	 */
	Teacher findByName(String firstName, String lastName);

	/***
	 * Method to search teacher by user identificator.
	 * 
	 * @param id
	 *            - identificator of user
	 * @return teacher object
	 */
	Teacher findByUserId(Integer id);

	/***
	 * Method to search teacher by user login.
	 * 
	 * @param userLogin
	 *            - login of teacher user
	 * @return teacher object
	 */
	Teacher findByLogin(String userLogin);

	/***
	 * Method to search teacher by identificator.
	 * 
	 * @param id
	 *            - identificator of teacher
	 * @return teacher object
	 */
	Teacher findById(Integer id);
}