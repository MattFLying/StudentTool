package model.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import model.dao.interfaces.GenericDao;
import model.dao.interfaces.IDepartmentDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Department;

/***
 * DAO class represents department for all operations on this type.
 * 
 * @author Mateusz Mucha
 *
 */
public class DepartmentDao extends GenericDao<Department, Integer> implements IDepartmentDao {
	private Department departmentEntity;

	/***
	 * Default construtor sets basic field using in this class.
	 */
	public DepartmentDao() {
		this.departmentEntity = null;
	}

	public Department findByShortName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;

		try {
			criteria = session.createCriteria(Department.class);
			criteria.add(Restrictions.eq("departmentName", name));
			departmentEntity = (Department) criteria.list().get(0);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}

		if (departmentEntity != null) {
			return departmentEntity;
		} else {
			return null;
		}
	}

	public Department findByFullName(String description) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;

		try {
			criteria = session.createCriteria(Department.class);
			criteria.add(Restrictions.eq("departmentDescription", description));
			departmentEntity = (Department) criteria.list().get(0);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}

		if (departmentEntity != null) {
			return departmentEntity;
		} else {
			return null;
		}
	}

	public Department findDepartmentIdByFullName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Department entity = null;

		try {
			List results = session.createCriteria(Department.class).add(Restrictions.eq("departmentDescription", name))
					.setProjection(
							Projections.projectionList().add(Projections.property("departmentId"), "departmentId"))
					.setResultTransformer(Transformers.aliasToBean(Department.class)).list();

			entity = (Department) results.get(0);
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

	public Department findDepartmentNameById(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Department entity = null;

		try {
			List results = session.createCriteria(Department.class).add(Restrictions.eq("departmentId", id))
					.setProjection(Projections.projectionList()
							.add(Projections.property("departmentDescription"), "departmentDescription")
							.add(Projections.property("departmentId"), "departmentId"))
					.setResultTransformer(Transformers.aliasToBean(Department.class)).list();

			entity = (Department) results.get(0);
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
}