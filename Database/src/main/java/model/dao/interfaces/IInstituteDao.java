package model.dao.interfaces;

import java.util.HashMap;
import java.util.List;
import model.entity.Department;
import model.entity.Institute;

/***
 * Interface represents institute database object.
 * 
 * @author Mateusz Mucha
 *
 */
public interface IInstituteDao extends IGenericDao<Institute, Integer> {
	/***
	 * Method to search institute by identificator.
	 * 
	 * @param id
	 *            - identificator of institute
	 * @return institute object
	 */
	Institute findById(Integer id);

	/***
	 * Method to search institute by shortcut name.
	 * 
	 * @param name
	 *            - shortcut name of institute
	 * @return institute object
	 */
	Institute findByName(String name);

	/***
	 * Method to search institute by full name.
	 * 
	 * @param name
	 *            - full name of institute
	 * @return institute object
	 */
	Institute findByFullName(String name);

	/***
	 * Method to search institute full name by identificator.
	 * 
	 * @param id
	 *            - identificator of institute
	 * @return institute object
	 */
	Institute findInstituteNameById(Integer id);

	/***
	 * Method to search institute identificator by full name.
	 * 
	 * @param name
	 *            - full name of institute
	 * @return institute object
	 */
	Institute findInstituteIdByName(String name);

	/***
	 * Method to search institutes by department identificator.
	 * 
	 * @param id
	 *            - identificator of department
	 * @return list of institutes
	 */
	List<Institute> findByDepartmentId(Integer id);

	/***
	 * Method to search institutes by department full name..
	 * 
	 * @param name
	 *            - full name of department
	 * @return list of institutes
	 */
	List<Institute> findByDepartmentName(String name);

	/***
	 * Method to search institutes for departments.
	 * 
	 * @return hashmap of institutes with departments
	 */
	HashMap<Department, List<Institute>> findAllInstitutesForAllDepartments();
}