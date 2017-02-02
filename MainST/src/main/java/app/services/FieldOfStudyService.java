package app.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import app.services.factory.DaoFactory;
import core.study.department.Department;
import core.study.fieldofstudy.FieldOfStudy;
import core.study.fieldofstudy.IFieldOfStudy;
import model.dao.interfaces.IFieldOfStudyDao;
import model.entity.Entity;

/***
 * Class as service represents Field Of Study. In this class contains all
 * possible operations with fields of study and should be used for that.
 * 
 * @author Mateusz Mucha
 *
 */
@Service
public class FieldOfStudyService extends DaoService<IFieldOfStudyDao> {
	/***
	 * Default constructor sets basic fields.
	 */
	public FieldOfStudyService() {
		super(DaoFactory.Dao.FIELD_OF_STUDY);
	}

	@Override
	public IFieldOfStudyDao getDao() {
		return (IFieldOfStudyDao) dao;
	}

	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.FieldOfStudy fieldEntity = (model.entity.FieldOfStudy) entity;
		IFieldOfStudy field = (FieldOfStudy) base;

		model.entity.Department dept = new DepartmentService().getDao()
				.findDepartmentIdByFullName(field.getDetails().getDepartment().getDetails().getDepartmentFullName());

		fieldEntity.setFieldOfStudyName(field.getDetails().getFieldOfStudyName());
		fieldEntity.setDepartmentId(dept.getDepartmentId());
	}

	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.FieldOfStudy fieldEntity = (model.entity.FieldOfStudy) entity;
		IFieldOfStudy field = (FieldOfStudy) base;

		Department dept = new DepartmentService().findDepartmentNameById(fieldEntity.getDepartmentId());

		field.getDetails().setFieldOfStudyName(fieldEntity.getFieldOfStudyName());
		field.getDetails().setDepartment(dept);
		field.getDetails().setId(fieldEntity.getFieldOfStudyId());
	}

	/***
	 * Method to create field of study object from entity.
	 * 
	 * @param base
	 *            - base field of study object
	 * @param entity
	 *            - entity
	 * @return field of study
	 */
	private FieldOfStudy createFromEntity(FieldOfStudy base, model.entity.FieldOfStudy entity) {
		IFieldOfStudy field = new FieldOfStudy();

		createFromEntity(entity, field);

		return field.getModel();
	}

	/***
	 * Method to search field of study by identificator.
	 * 
	 * @param id
	 *            - identificator of field of study
	 * @return field of study
	 */
	public FieldOfStudy findOneByFieldId(int id) {
		model.entity.FieldOfStudy entity = dao().findById(id);

		IFieldOfStudy field = new FieldOfStudy();
		createFromEntity(entity, field);

		return field.getModel();
	}

	/***
	 * Method to search field of study name by identificator.
	 * 
	 * @param id
	 *            - identificator of field of study
	 * @return field of study
	 */
	public FieldOfStudy findFieldOfStudyNameById(int id) {
		model.entity.FieldOfStudy entity = dao().findFieldOfStudyNameById(id);

		IFieldOfStudy field = new FieldOfStudy();
		field.getDetails().setFieldOfStudyName(entity.getFieldOfStudyName());
		field.getDetails().setId(entity.getFieldOfStudyId());

		return field.getModel();
	}

	/***
	 * Method to search field of study by name.
	 * 
	 * @param name
	 *            - field of study name
	 * @return field of study
	 */
	public FieldOfStudy findOneByName(String name) {
		model.entity.FieldOfStudy entity = dao().findByName(name);

		IFieldOfStudy field = new FieldOfStudy();
		createFromEntity(entity, field);

		return field.getModel();
	}

	/***
	 * Method to find all fields of study for specific department full name.
	 * 
	 * @param name
	 *            - full name of department
	 * @return list of fields of study
	 */
	public List<FieldOfStudy> findAllByDepartmentFullName(String name) {
		List<FieldOfStudy> list = new ArrayList<FieldOfStudy>();

		dao().findByDepartmentFullName(name).forEach((x) -> {
			list.add(createFromEntity(new FieldOfStudy(), x));
		});

		return list;
	}

	/***
	 * Method to find all fields of study for specific department short name.
	 * 
	 * @param name
	 *            - short name of department
	 * @return list of fields of study
	 */
	public List<FieldOfStudy> findAllByDepartmentShortName(String name) {
		List<FieldOfStudy> list = new ArrayList<FieldOfStudy>();

		dao().findByDepartmentName(name).forEach((x) -> {
			list.add(createFromEntity(new FieldOfStudy(), x));
		});

		return list;
	}

	/***
	 * Method to save field of study in database.
	 * 
	 * @param field
	 *            - field of study object
	 * @throws Exception
	 */
	public void save(IFieldOfStudy field) throws Exception {
		model.entity.FieldOfStudy entity = new model.entity.FieldOfStudy();
		createEntity(field, entity);

		int success = dao().save(entity);
		if (success == 0) {
			throw new Exception();
		}
	}

	/***
	 * Method to update field of study in database.
	 * 
	 * @param field
	 *            - field of study object
	 * @throws Exception
	 */
	public void updateField(IFieldOfStudy field) throws Exception {
		model.entity.FieldOfStudy entity = new model.entity.FieldOfStudy();

		model.entity.Department dept = new DepartmentService().getDao()
				.findDepartmentIdByFullName(field.getDetails().getDepartment().getDetails().getDepartmentFullName());

		entity.setFieldOfStudyName(field.getDetails().getFieldOfStudyName());
		entity.setDepartmentId(dept.getDepartmentId());
		entity.setFieldOfStudyId(field.getDetails().getId());

		int success = dao().update(entity);
		if (success == 0) {
			throw new Exception();
		}
	}

	/***
	 * Method to find all fields of study.
	 * 
	 * @return list with all fields of study
	 */
	public List<FieldOfStudy> findAll() {
		List<FieldOfStudy> list = new ArrayList<FieldOfStudy>();

		dao().findAll(model.entity.FieldOfStudy.class).forEach((x) -> {
			IFieldOfStudy field = new FieldOfStudy();
			Department dept = new Department();

			field.getDetails().setFieldOfStudyName(x.getFieldOfStudyName());
			dept.getDetails().setId(x.getDepartmentId());
			field.getDetails().setDepartment(dept);

			list.add(field.getModel());
		});

		return list;
	}

	/***
	 * Method to find all fields of grupy and group them by departments.
	 * 
	 * @return hashmap with grouped fields of study by departments
	 */
	public HashMap<Department, List<FieldOfStudy>> findAllFieldsForAllDepartments() {
		HashMap<model.entity.Department, List<model.entity.FieldOfStudy>> entities = dao()
				.findAllFieldsForAllDepartments();
		HashMap<Department, List<FieldOfStudy>> list = new HashMap<Department, List<FieldOfStudy>>();

		for (Map.Entry<model.entity.Department, List<model.entity.FieldOfStudy>> entry : entities.entrySet()) {
			Department dept = new Department();
			dept.getDetails().setDepartmentFullName(entry.getKey().getDepartmentDescription());
			dept.getDetails().setId(entry.getKey().getDepartmentId());

			List<FieldOfStudy> fields = new ArrayList<FieldOfStudy>();
			for (model.entity.FieldOfStudy f : entry.getValue()) {
				IFieldOfStudy field = new FieldOfStudy();
				field.getDetails().setFieldOfStudyName(f.getFieldOfStudyName());

				field.getDetails().setDepartment(dept);

				fields.add(field.getModel());
			}

			list.put(dept, fields);
		}

		return list;
	}
}