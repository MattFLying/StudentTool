package app.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import app.services.factory.DaoFactory;
import core.study.department.Department;
import core.study.department.IDepartment;
import model.dao.interfaces.IDepartmentDao;
import model.entity.Entity;

/***
 * Class as service represents Department. In this class contains all possible
 * operations with department and should be used for that.
 * 
 * @author Mateusz Mucha
 *
 */
@Service
public class DepartmentService extends DaoService<IDepartmentDao> {
	/***
	 * Default constructor sets basic fields.
	 */
	public DepartmentService() {
		super(DaoFactory.Dao.DEPARTMENT);
	}

	@Override
	public IDepartmentDao getDao() {
		return (IDepartmentDao) dao;
	}

	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Department departmentEntity = (model.entity.Department) entity;
		IDepartment department = (Department) base;

		departmentEntity.setDepartmentBuilding(department.getDetails().getDepartmentBuilding());
		departmentEntity.setDepartmentDescription(department.getDetails().getDepartmentFullName());
		departmentEntity.setDepartmentName(department.getDetails().getDepartmentShortName());
	}

	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Department departmentEntity = (model.entity.Department) entity;
		IDepartment department = (Department) base;

		department.getDetails().setDepartmentBuilding(departmentEntity.getDepartmentBuilding());
		department.getDetails().setDepartmentFullName(departmentEntity.getDepartmentDescription());
		department.getDetails().setDepartmentShortName(departmentEntity.getDepartmentName());
		department.getDetails().setId(departmentEntity.getDepartmentId());
	}

	/***
	 * Method for creating and returning department object from entity.
	 * 
	 * @param base
	 *            - base department object
	 * @param entity
	 *            - department entity
	 * @return department object converted from entity
	 */
	private Department createFromEntity(Department base, model.entity.Department entity) {
		IDepartment department = new Department();

		createFromEntity(entity, department);

		return department.getModel();
	}

	/***
	 * Method to search department full name by identificator.
	 * 
	 * @param id
	 *            - department identificator
	 * @return department
	 */
	public Department findDepartmentNameById(int id) {
		model.entity.Department entity = dao().findDepartmentNameById(id);

		IDepartment department = new Department();
		createFromEntity(entity, department);

		return department.getModel();
	}

	/***
	 * Method to search department identificator by full name.
	 * 
	 * @param name
	 *            - full name of department
	 * @return department
	 */
	public Department findDepartmentIdByFullName(String name) {
		model.entity.Department entity = dao().findDepartmentIdByFullName(name);

		IDepartment department = new Department();
		createFromEntity(entity, department);

		return department.getModel();
	}

	/***
	 * Method to search department by identificator.
	 * 
	 * @param id
	 *            - department identificator
	 * @return department
	 */
	public Department findOneByDepartmentId(int id) {
		model.entity.Department entity = dao().findById(id);

		IDepartment department = new Department();
		createFromEntity(entity, department);

		return department.getModel();
	}

	/***
	 * Method to search department by shorcut name.
	 * 
	 * @param name
	 *            - shortcut name of department
	 * @return department
	 */
	public Department findOneByShortName(String name) {
		model.entity.Department entity = dao().findByShortName(name);

		IDepartment department = new Department();
		createFromEntity(entity, department);

		return department.getModel();
	}

	/***
	 * Method to search department by full name.
	 * 
	 * @param name
	 *            - full name of department
	 * @return department
	 */
	public Department findOneByFullName(String name) {
		model.entity.Department entity = dao().findByFullName(name);

		IDepartment department = new Department();
		createFromEntity(entity, department);

		return department.getModel();
	}

	/***
	 * Method to save department into database.
	 * 
	 * @param department
	 *            - department object type
	 * @throws Exception
	 */
	public void save(IDepartment department) throws Exception {
		model.entity.Department entity = new model.entity.Department();
		createEntity(department, entity);

		int success = dao().save(entity);
		if (success == 0) {
			throw new Exception();
		}
	}

	/***
	 * Method to update department in database.
	 * 
	 * @param department
	 *            - department object type
	 * @throws Exception
	 */
	public void updateDepartment(IDepartment department) throws Exception {
		model.entity.Department departmentEntity = new model.entity.Department();

		departmentEntity.setDepartmentBuilding(department.getDetails().getDepartmentBuilding());
		departmentEntity.setDepartmentDescription(department.getDetails().getDepartmentFullName());
		departmentEntity.setDepartmentName(department.getDetails().getDepartmentShortName());
		departmentEntity.setDepartmentId(department.getDetails().getId());

		int success = dao().update(departmentEntity);
		if (success == 0) {
			throw new Exception();
		}
	}

	/***
	 * Method to find all departments.
	 * 
	 * @return list of departments
	 */
	public List<Department> findAll() {
		List<Department> list = new ArrayList<Department>();

		dao().findAll(model.entity.Department.class).forEach((x) -> {
			list.add(createFromEntity(new Department(), x));
		});

		return list;
	}
}