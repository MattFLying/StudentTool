package model.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.interfaces.GenericDao;
import model.dao.interfaces.IGradeDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Grade;
import model.entity.GradeId;
import model.entity.Student;

public class GradeDao extends GenericDao<Grade, GradeId> implements IGradeDao {
	private Grade gradeEntity;
	
	
	public GradeDao() {
		this.gradeEntity = null;
	}


	public Grade findByGradeId(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(Grade.class);
			criteria.add(Restrictions.eq("id.gradeId", id));
			gradeEntity = (Grade)criteria.list().get(0);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		
		if(gradeEntity != null) {
			return gradeEntity;
		} else {
			return null;
		}
	}
	public List<Grade> findByTeacherId(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Grade> list = new ArrayList<Grade>();
		
		try {
			criteria = session.createCriteria(Grade.class);
			criteria.add(Restrictions.eq("teacherId", id));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		
		return list;
	}
	public List<Grade> findByStudentId(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Grade> list = new ArrayList<Grade>();
		
		try {
			criteria = session.createCriteria(Grade.class);
			criteria.add(Restrictions.eq("id.studentId", id));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		
		return list;
	}
	public List<Grade> findByStudentIdAndCourseId(Integer student, Integer course) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Grade> list = new ArrayList<Grade>();
		
		try {
			criteria = session.createCriteria(Grade.class);
			criteria.add(Restrictions.and(Restrictions.eq("id.studentId", student), Restrictions.eq("id.courseId", course)));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		
		return list;
	}
	public List<Grade> findByGradeType(String type) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Grade> list = new ArrayList<Grade>();
		
		try {
			criteria = session.createCriteria(Grade.class);
			criteria.add(Restrictions.eq("gradeType", type));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		
		return list;
	}
	public List<Grade> findByStudentAlbum(String album) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Grade> list = new ArrayList<Grade>();
		
		try {
			Student student = new StudentDao().findByAlbum(album);
			
			criteria = session.createCriteria(Grade.class);
			criteria.add(Restrictions.eq("id.studentId", student.getId().getStudentId()));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		
		return list;
	}
}