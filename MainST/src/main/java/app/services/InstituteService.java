package app.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import app.services.factory.DaoFactory;
import core.study.department.Department;
import core.study.department.IInstitute;
import core.study.department.Institute;
import model.dao.interfaces.IInstituteDao;
import model.entity.Entity;

/***
 * Class as service represents Institute. In this class contains all possible
 * operations with institute and should be used for that.
 * 
 * @author Mateusz Mucha
 *
 */
@Service
public class InstituteService extends DaoService<IInstituteDao> {
	/***
	 * Default constructor sets basic fields.
	 */
	public InstituteService() {
		super(DaoFactory.Dao.INSTITUTE);
	}

	@Override
	public IInstituteDao getDao() {
		return (IInstituteDao) dao;
	}

	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Institute instituteEntity = (model.entity.Institute) entity;
		IInstitute institute = (Institute) base;

		model.entity.Department dept = new DepartmentService().getDao().findDepartmentIdByFullName(
				institute.getDetails().getDepartment().getDetails().getDepartmentFullName());

		instituteEntity.setInstituteDescription(institute.getDetails().getInstituteFullName());
		instituteEntity.setInstituteName(institute.getDetails().getInstituteShortName());
		instituteEntity.setDepartmentId(dept.getDepartmentId());
	}

	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Institute instituteEntity = (model.entity.Institute) entity;
		IInstitute institute = (Institute) base;

		Department dept = new DepartmentService().findDepartmentNameById(instituteEntity.getDepartmentId());

		institute.getDetails().setInstituteFullName(instituteEntity.getInstituteDescription());
		institute.getDetails().setInstituteShortName(instituteEntity.getInstituteName());
		institute.getDetails().setDepartment(dept);
		institute.getDetails().setId(instituteEntity.getInstituteId());
	}

	/***
	 * Method to search institute by identificator.
	 * 
	 * @param id
	 *            - institute identificator
	 * @return institute object
	 */
	public Institute findById(Integer id) {
		model.entity.Institute entity = dao().findById(id);

		IInstitute institute = new Institute();
		createFromEntity(entity, institute);

		return institute.getModel();
	}

	/***
	 * Method to find institute by full name.
	 * 
	 * @param name
	 *            - full name of institute
	 * @return institute object
	 */
	public Institute findByFullName(String name) {
		model.entity.Institute entity = dao().findByFullName(name);

		IInstitute institute = new Institute();
		createFromEntity(entity, institute);

		return institute.getModel();
	}

	/***
	 * Method to search all institutes by department identificator.
	 * 
	 * @param id
	 *            - department identificator
	 * @return list of all institutes on department
	 */
	public List<Institute> findAllByDepartmentId(int id) {
		List<Institute> list = new ArrayList<Institute>();

		dao().findByDepartmentId(id).forEach((x) -> {
			list.add(createFromEntity(new Institute(), x));
		});

		return list;
	}

	/***
	 * Method to find institute full name by identificator.
	 * 
	 * @param id
	 *            - institute identificator
	 * @return institute object
	 */
	public Institute findInstituteNameById(int id) {
		model.entity.Institute entity = dao().findInstituteNameById(id);

		IInstitute institute = new Institute();
		institute.getDetails().setInstituteFullName(entity.getInstituteDescription());

		return institute.getModel();
	}

	/***
	 * Method to find institute identificator by full name.
	 * 
	 * @param name
	 *            - institute full name
	 * @return institute object
	 */
	public Institute findInstituteIdByName(String name) {
		model.entity.Institute entity = dao().findInstituteIdByName(name);

		IInstitute institute = new Institute();
		institute.getDetails().setId(entity.getInstituteId());

		return institute.getModel();
	}

	/***
	 * Method to find all institutes grouped by departments.
	 * 
	 * @return list of institutes grouped by departments
	 */
	public HashMap<Department, List<Institute>> findAllInstitutesForAllDepartments() {
		HashMap<model.entity.Department, List<model.entity.Institute>> entities = dao()
				.findAllInstitutesForAllDepartments();
		HashMap<Department, List<Institute>> list = new HashMap<Department, List<Institute>>();

		for (Map.Entry<model.entity.Department, List<model.entity.Institute>> entry : entities.entrySet()) {
			Department dept = new Department();
			dept.getDetails().setDepartmentFullName(entry.getKey().getDepartmentDescription());
			dept.getDetails().setId(entry.getKey().getDepartmentId());

			List<Institute> fields = new ArrayList<Institute>();
			for (model.entity.Institute i : entry.getValue()) {
				IInstitute institute = new Institute();
				institute.getDetails().setInstituteFullName(i.getInstituteDescription());

				institute.getDetails().setDepartment(dept);

				fields.add(institute.getModel());
			}

			list.put(dept, fields);
		}

		return list;
	}

	/***
	 * Method to update institute in database.
	 * 
	 * @param institute
	 *            - institute object
	 * @throws Exception
	 */
	public void updateInstitute(Institute institute) throws Exception {
		model.entity.Institute entity = new model.entity.Institute();

		model.entity.Department dept = new DepartmentService().getDao().findDepartmentIdByFullName(
				institute.getDetails().getDepartment().getDetails().getDepartmentFullName());

		entity.setInstituteDescription(institute.getDetails().getInstituteFullName());
		entity.setInstituteName(institute.getDetails().getInstituteShortName());
		entity.setDepartmentId(dept.getDepartmentId());
		entity.setInstituteId(institute.getDetails().getId());

		int success = dao().update(entity);
		if (success == 0) {
			throw new Exception();
		}
	}

	/***
	 * Method to create institute from entity.
	 * 
	 * @param base
	 *            - base institute object
	 * @param entity
	 *            - institute entity
	 * @return institute object
	 */
	private Institute createFromEntity(IInstitute base, model.entity.Institute entity) {
		IInstitute institute = new Institute();

		createFromEntity(entity, institute);

		return institute.getModel();
	}

	/***
	 * Method to save institute in database.
	 * 
	 * @param institute
	 *            - institute object
	 * @throws Exception
	 */
	public void save(IInstitute institute) throws Exception {
		model.entity.Institute entity = new model.entity.Institute();
		createEntity(institute, entity);

		int success = dao().save(entity);
		if (success == 0) {
			throw new Exception();
		}
	}
}