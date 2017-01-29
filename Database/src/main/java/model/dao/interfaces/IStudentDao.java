package model.dao.interfaces;

import java.util.List;
import model.entity.Student;
import model.entity.StudentId;

/***
 * Interface represents address database object.
 * 
 * @author Mateusz Mucha
 *
 */
public interface IStudentDao extends IGenericDao<Student, StudentId> {
	/***
	 * Method to search students by group identificator.
	 * 
	 * @param id
	 *            - identificator of group
	 * @return list of students
	 */
	List<Student> findByGroupId(Integer id);

	/***
	 * Method to search students by field of study identificator.
	 * 
	 * @param id
	 *            - identificator of field of study
	 * @return list of students
	 */
	List<Student> findByFieldOfStudyId(Integer id);

	/***
	 * Method to search students by specialization identificator.
	 * 
	 * @param id
	 *            - identificator of specialization
	 * @return list of students
	 */
	List<Student> findBySpecializationId(Integer id);

	/***
	 * Method to search student by album number.
	 * 
	 * @param album
	 *            - album number of student
	 * @return student object
	 */
	Student findByAlbum(String album);

	/***
	 * Method to search student by first name and last name.
	 * 
	 * @param firstName
	 *            - first name of student
	 * @param lastName
	 *            - last name of student
	 * @return student object
	 */
	Student findByName(String firstName, String lastName);

	/***
	 * Method to search students by group name.
	 * 
	 * @param name
	 *            - name of group
	 * @return list of students
	 */
	List<Student> findByGroupName(String name);

	/***
	 * Method to search students by field of study name.
	 * 
	 * @param name
	 *            - name of field of study
	 * @return list of students
	 */
	List<Student> findByFieldOfStudyName(String name);

	/***
	 * Method to search students by specialization name.
	 * 
	 * @param name
	 *            - name of specialization
	 * @return list of students
	 */
	List<Student> findBySpecializationName(String name);

	/***
	 * Method to search student by user login identificator.
	 * 
	 * @param id
	 *            - identificator of user login
	 * @return student object
	 */
	Student findByUserId(Integer id);

	/***
	 * Method to search student by identificator.
	 * 
	 * @param id
	 *            - identificator of student
	 * @return student object
	 */
	Student findById(Integer id);
}