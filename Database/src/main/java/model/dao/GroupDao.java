package model.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.interfaces.GenericDao;
import model.dao.interfaces.IGroupDao;
import model.db.hib.util.HibernateUtil;
import model.entity.FieldOfStudy;
import model.entity.Group;

public class GroupDao extends GenericDao<Group, Integer> implements IGroupDao {
	private Group groupEntity;
	
	
	public GroupDao() {
		this.groupEntity = null;
	}
	

	public Group findByName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(Group.class);
			criteria.add(Restrictions.eq("groupName", name));
			groupEntity = (Group)criteria.list().get(0);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}

		if(groupEntity != null) {
			return groupEntity;
		} else {
			return null;
		}
	}
	public List<Group> findByFieldOfStudyId(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Group> list = new ArrayList<Group>();
		
		try {
			criteria = session.createCriteria(Group.class);
			criteria.add(Restrictions.eq("fieldOfStudyId", id));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	public List<Group> findByFieldOfStudyName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Group> list = new ArrayList<Group>();
		
		try {
			FieldOfStudy field = new FieldOfStudyDao().findByName(name);
			
			criteria = session.createCriteria(Group.class);
			criteria.add(Restrictions.eq("fieldOfStudyId", field.getFieldOfStudyId()));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
}
