package controller.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.dao.GradeDao;
import model.dao.interfaces.IGradeDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Grade;

public class GradeService extends GenericDaoService<GradeDao, IGradeDao> {
	private GradeDao grade = null;
	
	
	public GradeService() {
		this.grade = new GradeDao();
	}


	@Override
	public GradeDao getDao() {
		return grade;
	}
	@Override
	public IGradeDao getDaoInterface() {
		return grade;
	}
	public List<Grade> getAllByTeacherId(Integer id) {
		return grade.findByTeacherId(id);
	}
	public List<Grade> getAllByStudentId(Integer id) {
		return grade.findByStudentId(id);
	}
	public List<Grade> getAllByStudentAlbum(String album) {
		return grade.findByStudentAlbum(album);
	}
	public List<Grade> getAllByGradeType(String type) {
		return grade.findByStudentAlbum(type);
	}
	public List<Grade> getAllByGradeTypeOfStudent(String type, Integer studentId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Grade> list = new ArrayList<Grade>();
		
		try {
			criteria = session.createCriteria(Grade.class);
			criteria.add(Restrictions.and(Restrictions.eq("id.studentId", studentId), Restrictions.eq("gradeType", type)));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	public List<Grade> getAllByGradeTypeOfTeacher(String type, Integer teacherId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Grade> list = new ArrayList<Grade>();
		
		try {
			criteria = session.createCriteria(Grade.class);
			criteria.add(Restrictions.and(Restrictions.eq("teacherId", teacherId), Restrictions.eq("gradeType", type)));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	public List<Grade> getAllForStudentByTeacher(Integer studentId, Integer teacherId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<Grade> list = new ArrayList<Grade>();
		
		try {
			criteria = session.createCriteria(Grade.class);
			criteria.add(Restrictions.and(Restrictions.eq("teacherId", teacherId), Restrictions.eq("id.studentId", studentId)));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	public void updateGradeValue(Integer gradeId, Float newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Grade entity = null;
		
		try {
			criteria = session.createCriteria(Grade.class);
			criteria.add(Restrictions.eq("id.gradeId", gradeId));
			entity = (Grade)criteria.list().get(0);
			
			entity.setGradeValue(newValue);
			
			grade.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateGradeValue(Integer gradeId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Grade entity = null;
		
		try {
			criteria = session.createCriteria(Grade.class);
			criteria.add(Restrictions.eq("id.gradeId", gradeId));
			entity = (Grade)criteria.list().get(0);
			
			entity.setGradeDescription(newValue);
			
			grade.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
	public void updateGradeType(Integer gradeId, String newValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		Grade entity = null;
		
		try {
			criteria = session.createCriteria(Grade.class);
			criteria.add(Restrictions.eq("id.gradeId", gradeId));
			entity = (Grade)criteria.list().get(0);
			
			entity.setGradeType(newValue);
			
			grade.update(entity);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.close();
		}
	}
}