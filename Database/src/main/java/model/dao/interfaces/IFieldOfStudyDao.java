package model.dao.interfaces;

import java.util.HashMap;
import java.util.List;
import model.entity.Department;
import model.entity.FieldOfStudy;
import model.entity.Group;
import model.entity.Specialization;

/***
 * Interface represents field of study database object.
 * 
 * @author Mateusz Mucha
 *
 */
public interface IFieldOfStudyDao extends IGenericDao<FieldOfStudy, Integer> {
	/***
	 * Method to search field of study by full name.
	 * 
	 * @param name
	 *            - full name of field of study
	 * @return field of study object
	 */
	FieldOfStudy findByName(String name);

	/***
	 * Method to search field of study identificator by name.
	 * 
	 * @param name
	 *            - full name of field of study
	 * @return field of study object
	 */
	FieldOfStudy findFieldOfStudyIdByName(String name);

	/***
	 * Method to search field of study name by identificator.
	 * 
	 * @param id
	 * @return field of study object
	 */
	FieldOfStudy findFieldOfStudyNameById(Integer id);

	/***
	 * Method to search all fields of study by department identificator.
	 * 
	 * @param id
	 *            - identificator of field of study
	 * @return field of study object
	 */
	List<FieldOfStudy> findByDepartmentId(Integer id);

	/***
	 * Method to search all fields of study by department shortcut name.
	 * 
	 * @param name
	 *            - shortcut name of department
	 * @return list of fields of study
	 */
	List<FieldOfStudy> findByDepartmentName(String name);

	/***
	 * Method to search all fields of study by department full name.
	 * 
	 * @param name
	 *            - full name of department
	 * @return list of fields of study
	 */
	List<FieldOfStudy> findByDepartmentFullName(String name);

	/***
	 * Method to search all fields of study.
	 * 
	 * @return list of fields of study
	 */
	List<FieldOfStudy> findAllFields();

	/***
	 * Method to search all fields of study for all departments group by
	 * departments.
	 * 
	 * @return hashmap of fields of study
	 */
	HashMap<Department, List<FieldOfStudy>> findAllFieldsForAllDepartments();

	/***
	 * Method to search all fields of study for all departments and
	 * specializations.
	 * 
	 * @return hashmap of fields of study
	 */
	HashMap<Department, HashMap<FieldOfStudy, HashMap<List<Specialization>, List<Group>>>> findAllFieldsSpecsGroupsForAllDepartments();
}