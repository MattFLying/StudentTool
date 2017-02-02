package app.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import app.services.factory.DaoFactory;
import core.study.fieldofstudy.FieldOfStudy;
import core.study.fieldofstudy.ISpecialization;
import core.study.fieldofstudy.Specialization;
import model.dao.interfaces.ISpecializationDao;
import model.entity.Entity;

/***
 * Class as service represents Specialization. In this class contains all
 * possible operations with specialization and should be used for that.
 * 
 * @author Mateusz Mucha
 *
 */
@Service
public class SpecializationService extends DaoService<ISpecializationDao> {
	/***
	 * Default constructor sets basic fields.
	 */
	public SpecializationService() {
		super(DaoFactory.Dao.SPECIALIZATION);
	}

	@Override
	public ISpecializationDao getDao() {
		return (ISpecializationDao) dao;
	}

	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Specialization groupEntity = (model.entity.Specialization) entity;
		model.entity.SpecializationId id = new model.entity.SpecializationId();
		groupEntity.setId(id);

		ISpecialization specialization = (Specialization) base;

		model.entity.FieldOfStudy field = new FieldOfStudyService().getDao().findFieldOfStudyIdByName(
				specialization.getDetails().getFieldOfStudy().getDetails().getFieldOfStudyName());

		groupEntity.setSpecializationName(specialization.getDetails().getSpecializationName());
		groupEntity.getId().setFieldOfStudyId(field.getFieldOfStudyId());
	}

	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Specialization groupEntity = (model.entity.Specialization) entity;
		ISpecialization specialization = (Specialization) base;

		FieldOfStudy field = new FieldOfStudyService()
				.findFieldOfStudyNameById(groupEntity.getId().getFieldOfStudyId());

		specialization.getDetails().setSpecializationName(groupEntity.getSpecializationName());
		specialization.getDetails().setFieldOfStudy(field);
		specialization.getDetails().setId(groupEntity.getId().getSpecializationId());
	}

	/***
	 * Method to create specialization from entity.
	 * 
	 * @param base
	 *            - base specialization object
	 * @param entity
	 *            - specialization entity
	 * @return specialization object
	 */
	private Specialization createFromEntity(Specialization base, model.entity.Specialization entity) {
		ISpecialization specialization = new Specialization();

		createFromEntity(entity, specialization);

		return specialization.getModel();
	}

	/***
	 * Method to search specialization by name.
	 * 
	 * @param name
	 *            - name of specialization
	 * @return specialization object
	 */
	public Specialization findOneByName(String name) {
		model.entity.Specialization entity = dao().findByName(name);

		ISpecialization specialization = new Specialization();
		createFromEntity(entity, specialization);

		return specialization.getModel();
	}

	/***
	 * Method to search specialization by identificator.
	 * 
	 * @param id
	 *            - identificator of specialization
	 * @return specialization object
	 */
	public Specialization findOneById(Integer id) {
		model.entity.Specialization entity = dao().findById(id);

		ISpecialization specialization = new Specialization();
		createFromEntity(entity, specialization);

		return specialization.getModel();
	}

	/***
	 * Method to find all specializations by field of study identificator.
	 * 
	 * @param id
	 *            - identificator of field of study
	 * @return list of all specializations on field of study
	 */
	public List<Specialization> findByFieldOfStudyId(Integer id) {
		List<Specialization> list = new ArrayList<Specialization>();

		dao().findByFieldOfStudyId(id).forEach((x) -> {
			list.add(createFromEntity(new Specialization(), x));
		});

		return list;
	}

	/***
	 * Method to find all specializations by field of study name.
	 * 
	 * @param name
	 *            - name of field of study
	 * @return list of all specializations on field of study
	 */
	public List<Specialization> findByFieldOfStudyName(String name) {
		List<Specialization> list = new ArrayList<Specialization>();

		dao().findByFieldOfStudyName(name).forEach((x) -> {
			list.add(createFromEntity(new Specialization(), x));
		});

		return list;
	}

	/***
	 * Method to find all specializations by department identificator.
	 * 
	 * @param id
	 *            - department identificator
	 * @return list of all specializations on department
	 */
	public List<Specialization> findSpecsByDepartmentId(Integer id) {
		List<Specialization> list = new ArrayList<Specialization>();

		dao().findSpecsByDepartmentId(id).forEach((x) -> {
			ISpecialization specialization = new Specialization();

			specialization.getDetails().setSpecializationName(x.getSpecializationName());

			list.add(specialization.getModel());
		});

		return list;
	}

	/***
	 * Method to save specialization in database.
	 * 
	 * @param specialization
	 *            - specialization object
	 * @throws Exception
	 */
	public void save(ISpecialization specialization) throws Exception {
		model.entity.Specialization entity = new model.entity.Specialization();
		createEntity(specialization, entity);

		int success = dao().save(entity);
		if (success == 0) {
			throw new Exception();
		}
	}

	/***
	 * Method to update specialization in database.
	 * 
	 * @param specialization
	 *            - specialization object
	 * @throws Exception
	 */
	public void updateSpecialization(ISpecialization specialization) throws Exception {
		model.entity.Specialization specializationEntity = new model.entity.Specialization();

		model.entity.SpecializationId id = new model.entity.SpecializationId();
		model.entity.FieldOfStudy field = new FieldOfStudyService().getDao().findFieldOfStudyIdByName(
				specialization.getDetails().getFieldOfStudy().getDetails().getFieldOfStudyName());

		id.setFieldOfStudyId(field.getFieldOfStudyId());
		id.setSpecializationId(specialization.getDetails().getId());
		specializationEntity.setId(id);
		specializationEntity.setSpecializationName(specialization.getDetails().getSpecializationName());

		int success = dao().update(specializationEntity);
		if (success == 0) {
			throw new Exception();
		}
	}
}