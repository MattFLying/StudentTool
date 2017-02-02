package model.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import model.dao.interfaces.GenericDao;
import model.dao.interfaces.IGroupDao;
import model.db.hib.util.HibernateUtil;
import model.entity.FieldOfStudy;
import model.entity.Group;

/***
 * DAO class represents group for all operations on this type.
 * 
 * @author Mateusz Mucha
 *
 */
public class GroupDao extends GenericDao<Group, Integer> implements IGroupDao {
	private Group groupEntity;

	/***
	 * Default construtor sets basic field using in this class.
	 */
	public GroupDao() {
		this.groupEntity = null;
	}

	public Group findByName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;

		try {
			criteria = session.createCriteria(Group.class);
			criteria.add(Restrictions.eq("groupName", name));
			groupEntity = (Group) criteria.list().get(0);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}

		if (groupEntity != null) {
			return groupEntity;
		} else {
			return null;
		}
	}

	public Group findByDescription(String description) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;

		try {
			criteria = session.createCriteria(Group.class);
			criteria.add(Restrictions.eq("groupDescription", description));
			groupEntity = (Group) criteria.list().get(0);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}

		if (groupEntity != null) {
			return groupEntity;
		} else {
			return null;
		}
	}

	public Group findGroupNameById(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Group entity = null;

		try {
			List results = session.createCriteria(Group.class).add(Restrictions.eq("groupId", id))
					.setProjection(Projections.projectionList().add(Projections.property("groupName"), "groupName"))
					.setResultTransformer(Transformers.aliasToBean(Group.class)).list();

			entity = (Group) results.get(0);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}

		if (entity != null) {
			return entity;
		} else {
			return null;
		}
	}

	public Group findGroupIdByName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Group entity = null;

		try {
			List results = session.createCriteria(Group.class).add(Restrictions.eq("groupName", name))
					.setProjection(Projections.projectionList().add(Projections.property("groupId"), "groupId"))
					.setResultTransformer(Transformers.aliasToBean(FieldOfStudy.class)).list();

			entity = (Group) results.get(0);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}

		if (entity != null) {
			return entity;
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
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			session.clear();
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
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}

		return list;
	}

	public List<Group> findGroupsByDepartmentId(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Group> list = new ArrayList<Group>();

		try {
			List<FieldOfStudy> fields = new FieldOfStudyDao().findByDepartmentId(id);

			for (FieldOfStudy field : fields) {
				criteria = session.createCriteria(Group.class);
				criteria.add(Restrictions.eq("fieldOfStudyId", field.getFieldOfStudyId()));
				List listBase = criteria.list();

				list.addAll(listBase);
			}

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}

		return list;
	}
}