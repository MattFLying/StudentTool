package model.dao.interfaces;

import java.util.List;
import model.entity.Specialization;
import model.entity.SpecializationId;

/***
 * Interface represents specialization database object.
 * 
 * @author Mateusz Mucha
 *
 */
public interface ISpecializationDao extends IGenericDao<Specialization, SpecializationId> {
	/***
	 * Method to search specialization by name.
	 * 
	 * @param name
	 *            - name of specialization
	 * @return specialization object
	 */
	Specialization findByName(String name);

	/***
	 * Method to search specialization by identificator.
	 * 
	 * @param id
	 *            - identificator of specialization
	 * @return specialization object
	 */
	Specialization findById(Integer id);

	/***
	 * Method to search specializations by field of study identificator.
	 * 
	 * @param id
	 *            - identificator of field of study
	 * @return specialization object
	 */
	List<Specialization> findByFieldOfStudyId(Integer id);

	/***
	 * Method to search specializations by field of study name.
	 * 
	 * @param name
	 *            - name of field of study
	 * @return list of specializations
	 */
	List<Specialization> findByFieldOfStudyName(String name);

	/***
	 * Method to search specializations by department identificator.
	 * 
	 * @param id
	 *            - identificator of department
	 * @return list of specializations
	 */
	List<Specialization> findSpecsByDepartmentId(Integer id);
}