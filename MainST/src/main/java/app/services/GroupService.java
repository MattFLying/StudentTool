package app.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import app.services.factory.DaoFactory;
import core.study.fieldofstudy.FieldOfStudy;
import core.study.group.Group;
import core.study.group.IGroup;
import model.dao.interfaces.IGroupDao;
import model.entity.Entity;

/***
 * Class as service represents Group. In this class contains all possible
 * operations with group and should be used for that.
 * 
 * @author Mateusz Mucha
 *
 */
@Service
public class GroupService extends DaoService<IGroupDao> {
	/***
	 * Default constructor sets basic fields.
	 */
	public GroupService() {
		super(DaoFactory.Dao.GROUP);
	}

	@Override
	public IGroupDao getDao() {
		return (IGroupDao) dao;
	}

	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Group groupEntity = (model.entity.Group) entity;
		IGroup group = (Group) base;

		model.entity.FieldOfStudy field = new FieldOfStudyService().getDao()
				.findFieldOfStudyIdByName(group.getDetails().getFieldOfStudy().getDetails().getFieldOfStudyName());

		groupEntity.setGroupDescription(group.getDetails().getDescription());
		groupEntity.setGroupYear(group.getDetails().getYear());
		groupEntity.setGroupName(group.getDetails().getGroupName());
		groupEntity.setFieldOfStudyId(field.getFieldOfStudyId());
	}

	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Group groupEntity = (model.entity.Group) entity;
		IGroup group = (Group) base;

		FieldOfStudy field = new FieldOfStudyService().findFieldOfStudyNameById(groupEntity.getFieldOfStudyId());

		group.getDetails().setDescription(groupEntity.getGroupDescription());
		group.getDetails().setYear(groupEntity.getGroupYear());
		group.getDetails().setGroupName(groupEntity.getGroupName());
		group.getDetails().setFieldOfStudy(field);
		group.getDetails().setId(groupEntity.getGroupId());
	}

	/***
	 * Method to create group from entity.
	 * 
	 * @param base
	 *            - base group object
	 * @param entity
	 *            - group entity
	 * @return group object
	 */
	private Group createFromEntity(Group base, model.entity.Group entity) {
		IGroup group = new Group();

		createFromEntity(entity, group);

		return group.getModel();
	}

	/***
	 * Method to search group name by identificator.
	 * 
	 * @param id
	 *            - group identificator
	 * @return group object
	 */
	public Group findGroupNameById(int id) {
		model.entity.Group entity = dao().findGroupNameById(id);

		IGroup group = new Group();
		group.getDetails().setGroupName(entity.getGroupName());

		return group.getModel();
	}

	/***
	 * Method to search group identificator by name.
	 * 
	 * @param name
	 *            - name of group
	 * @return group object
	 */
	public Group findGroupIdByName(String name) {
		model.entity.Group entity = dao().findGroupIdByName(name);

		IGroup group = new Group();
		group.getDetails().setId(entity.getFieldOfStudyId());

		return group.getModel();
	}

	/***
	 * Method to find group by identificator.
	 * 
	 * @param id
	 *            - group identificator
	 * @return group object
	 */
	public Group findOneByGroupId(int id) {
		model.entity.Group entity = dao().findById(id);

		IGroup group = new Group();
		createFromEntity(entity, group);

		return group.getModel();
	}

	/***
	 * Method to find group by name.
	 * 
	 * @param name
	 *            - group name
	 * @return group object
	 */
	public Group findOneByName(String name) {
		model.entity.Group entity = dao().findByName(name);

		IGroup group = new Group();
		createFromEntity(entity, group);

		return group.getModel();
	}

	/***
	 * Method to find all groups by field of study identificator.
	 * 
	 * @param id
	 *            - field of study identificator
	 * @return group object
	 */
	public List<Group> findByFieldOfStudyId(Integer id) {
		List<Group> list = new ArrayList<Group>();

		dao().findByFieldOfStudyId(id).forEach((x) -> {
			list.add(createFromEntity(new Group(), x));
		});

		return list;
	}

	/***
	 * Method to find all groups by field of study name.
	 * 
	 * @param name
	 *            - field of study name
	 * @return group object
	 */
	public List<Group> findByFieldOfStudyName(String name) {
		List<Group> list = new ArrayList<Group>();

		dao().findByFieldOfStudyName(name).forEach((x) -> {
			list.add(createFromEntity(new Group(), x));
		});

		return list;
	}

	/***
	 * Method to find all groups by department identificator.
	 * 
	 * @param id
	 *            - department identificator
	 * @return group object
	 */
	public List<Group> findGroupsByDepartmentId(Integer id) {
		List<Group> list = new ArrayList<Group>();

		dao().findGroupsByDepartmentId(id).forEach((x) -> {
			IGroup group = new Group();

			group.getDetails().setGroupName(x.getGroupName());
			group.getDetails().setId(x.getGroupId());

			list.add(group.getModel());
		});

		return list;
	}

	/***
	 * Method to save group in database.
	 * 
	 * @param group
	 *            - group object
	 * @throws Exception
	 */
	public void save(IGroup group) throws Exception {
		model.entity.Group entity = new model.entity.Group();
		createEntity(group, entity);

		int success = dao().save(entity);
		if (success == 0) {
			throw new Exception();
		}
	}

	/***
	 * Method to update group in database.
	 * 
	 * @param group
	 *            - group object
	 * @throws Exception
	 */
	public void updateGroup(IGroup group) throws Exception {
		model.entity.Group entity = new model.entity.Group();

		model.entity.FieldOfStudy field = new FieldOfStudyService().getDao()
				.findFieldOfStudyIdByName(group.getDetails().getFieldOfStudy().getDetails().getFieldOfStudyName());

		entity.setGroupId(group.getDetails().getId());
		entity.setGroupDescription(group.getDetails().getDescription());
		entity.setGroupYear(group.getDetails().getYear());
		entity.setGroupName(group.getDetails().getGroupName());
		entity.setFieldOfStudyId(field.getFieldOfStudyId());

		int success = dao().update(entity);
		if (success == 0) {
			throw new Exception();
		}
	}
}