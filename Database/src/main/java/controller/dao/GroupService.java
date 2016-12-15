package controller.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.GroupDao;
import model.dao.interfaces.IGroupDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Group;

public class GroupService extends GenericDaoService<GroupDao, IGroupDao> {
	private GroupDao group = null;
	
	
	public GroupService() {
		this.group = new GroupDao();
	}


	@Override
	public GroupDao getDao() {
		return group;
	}
	@Override
	public IGroupDao getDaoInterface() {
		return group;
	}
	public Group getByName(String name) {
		return group.findByName(name);
	}
	public List<Group> getAllByFieldOfStudyId(Integer id) {
		return group.findByFieldOfStudyId(id);
	}
	public List<Group> getAllByFieldOfStudyName(String name) {
		return group.findByFieldOfStudyName(name);
	}
	public void updateName(Integer groupId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Group entity = null;
		
		try {
			criteria = session.createCriteria(Group.class);
			criteria.add(Restrictions.eq("groupId", groupId));
			entity = (Group)criteria.list().get(0);
			
			entity.setGroupName(newValue);
			
			group.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateDescription(Integer groupId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Group entity = null;
		
		try {
			criteria = session.createCriteria(Group.class);
			criteria.add(Restrictions.eq("groupId", groupId));
			entity = (Group)criteria.list().get(0);
			
			entity.setGroupDescription(newValue);
			
			group.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
}