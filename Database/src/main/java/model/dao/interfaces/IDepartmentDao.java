package model.dao.interfaces;

import model.entity.Department;

/***
 * Interface represents department database object.
 * 
 * @author Mateusz Mucha
 *
 */
public interface IDepartmentDao extends IGenericDao<Department, Integer> {
	/***
	 * Method to search department by shortcut.
	 * 
	 * @param name
	 *            - shortcut name of department
	 * @return department object
	 */
	Department findByShortName(String name);

	/***
	 * Method to search department by full department name.
	 * 
	 * @param description
	 *            - full name of department
	 * @return department object
	 */
	Department findByFullName(String description);

	/***
	 * Method to search department identificator by department name.
	 * 
	 * @param name
	 *            - full name of department
	 * @return department object
	 */
	Department findDepartmentIdByFullName(String name);

	/***
	 * Method to search department name by department identificator.
	 * 
	 * @param id
	 *            - identificator of department
	 * @return department object
	 */
	Department findDepartmentNameById(Integer id);
}