package model.dao.interfaces;

import java.util.List;
import model.entity.Group;

/***
 * Interface represents group database object.
 * 
 * @author Mateusz Mucha
 *
 */
public interface IGroupDao extends IGenericDao<Group, Integer> {
	/***
	 * Method to search group by group name.
	 * 
	 * @param name
	 *            - name of group
	 * @return group object
	 */
	Group findByName(String name);

	/***
	 * Method to search group by group description.
	 * 
	 * @param description
	 *            - description of group
	 * @return group object
	 */
	Group findByDescription(String description);

	/***
	 * Method to search group name by identificator.
	 * 
	 * @param id
	 *            - identificator of group
	 * @return group object
	 */
	Group findGroupNameById(Integer id);

	/***
	 * Method to search group identificator by name.
	 * 
	 * @param name
	 *            - name of group
	 * @return group object
	 */
	Group findGroupIdByName(String name);

	/***
	 * Method to search groups by field of study identificator.
	 * 
	 * @param id
	 *            - identificator of field of study
	 * @return list of groups
	 */
	List<Group> findByFieldOfStudyId(Integer id);

	/***
	 * Method to search groups by field of study name.
	 * 
	 * @param name
	 *            - name of field of study
	 * @return list of groups
	 */
	List<Group> findByFieldOfStudyName(String name);

	/***
	 * Method to search groups by department identificator.
	 * 
	 * @param id
	 *            - identificator of department
	 * @return list of groups
	 */
	List<Group> findGroupsByDepartmentId(Integer id);
}