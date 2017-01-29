package app.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import app.services.factory.DaoFactory;
import core.study.fieldofstudy.FieldOfStudy;
import core.study.group.Group;
import model.dao.interfaces.IGroupDao;
import model.entity.Entity;

@Service
public class GroupService extends DaoService<IGroupDao> {
	public GroupService() {
		super(DaoFactory.Dao.GROUP);
	}
	
	
	@Override
	public IGroupDao getDao() {
		return (IGroupDao)dao;
	}
	@Override
	protected void createEntity(Object base, Entity entity) {
		model.entity.Group groupEntity = (model.entity.Group)entity;
		Group group = (Group)base;
		
		model.entity.FieldOfStudy field = new FieldOfStudyService().getDao().findFieldOfStudyIdByName(group.getDetails().getFieldOfStudy().getDetails().getFieldOfStudyName());
		
		groupEntity.setGroupDescription(group.getDetails().getDescription());
		groupEntity.setGroupYear(group.getDetails().getYear());
		groupEntity.setGroupName(group.getDetails().getGroupName());
		groupEntity.setFieldOfStudyId(field.getFieldOfStudyId());
	}
	@Override
	protected void createFromEntity(Entity entity, Object base) {
		model.entity.Group groupEntity = (model.entity.Group)entity;
		Group group = (Group)base;
		
		FieldOfStudy field = new FieldOfStudyService().findFieldOfStudyNameById(groupEntity.getFieldOfStudyId());
		
		group.getDetails().setDescription(groupEntity.getGroupDescription());
		group.getDetails().setYear(groupEntity.getGroupYear());
		group.getDetails().setGroupName(groupEntity.getGroupName());
		group.getDetails().setFieldOfStudy(field);
		group.getDetails().setId(groupEntity.getGroupId());
	}
	private Group createFromEntity(Group base, model.entity.Group entity) {
		Group field = new Group();
		
		createFromEntity(entity, field);
		
		return field;
	}
	public Group findGroupNameById(int id) {
		model.entity.Group entity = dao().findGroupNameById(id);
		
		Group group = new Group();
		group.getDetails().setGroupName(entity.getGroupName());
		
		return group;
	}
	public Group findGroupIdByName(String name) {
		model.entity.Group entity = dao().findGroupIdByName(name);
		
		Group group = new Group();
		group.getDetails().setId(entity.getFieldOfStudyId());
		
		return group;
	}
	public Group findOneByGroupId(int id) {
		model.entity.Group entity = dao().findById(id);
		
		Group group = new Group();
		createFromEntity(entity, group);
		
		return group;
	}
	public Group findOneByName(String name) {
		model.entity.Group entity = dao().findByName(name);
		
		Group group = new Group();
		createFromEntity(entity, group);
		
		return group;
	}
	public Group findOneByDescription(String description) {
		model.entity.Group entity = dao().findByDescription(description);
		
		Group group = new Group();
		createFromEntity(entity, group);
		
		return group;
	}
	public List<Group> findByFieldOfStudyId(Integer id) {
		List<Group> list = new ArrayList<Group>();
		
		dao().findByFieldOfStudyId(id).forEach( (x) -> {
			list.add(createFromEntity(new Group(), x));
		});
		
		return list;
	}
	public List<Group> findByFieldOfStudyName(String name) {
		List<Group> list = new ArrayList<Group>();
		
		dao().findByFieldOfStudyName(name).forEach( (x) -> {
			list.add(createFromEntity(new Group(), x));
		});
		
		return list;
	}
	public List<Group> findGroupsByDepartmentId(Integer id) {
		List<Group> list = new ArrayList<Group>();		
		
		dao().findGroupsByDepartmentId(id).forEach( (x) -> {
			Group group = new Group();
			
			group.getDetails().setGroupName(x.getGroupName());
			group.getDetails().setId(x.getGroupId());
			
			list.add(group);
		});
		
		return list;
	}
	public void save(Group group) throws Exception {
		model.entity.Group entity = new model.entity.Group();	
		createEntity(group, entity);
		
		int success = dao().save(entity);
		if(success == 0) {
			throw new Exception();
		}
	}
	public void update(Group group) throws Exception {
		model.entity.Group entity = new model.entity.Group();	
		createEntity(group, entity);
		
		int success = dao().update(entity);
		if(success == 0) {
			throw new Exception();
		}
	}
	public void updateGroup(Group group) throws Exception {
		model.entity.Group entity = new model.entity.Group();	
		
		model.entity.FieldOfStudy field = new FieldOfStudyService().getDao().findFieldOfStudyIdByName(group.getDetails().getFieldOfStudy().getDetails().getFieldOfStudyName());
		
		entity.setGroupId(group.getDetails().getId());
		entity.setGroupDescription(group.getDetails().getDescription());
		entity.setGroupYear(group.getDetails().getYear());
		entity.setGroupName(group.getDetails().getGroupName());
		entity.setFieldOfStudyId(field.getFieldOfStudyId());
		
		int success = dao().update(entity);
		if(success == 0) {
			throw new Exception();
		}
	}
	public void delete(Group group) {
		model.entity.Group entity = new model.entity.Group();	
		createEntity(group, entity);
		
		dao().delete(entity);
	}
}