package model.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import model.entity.Specialization;
import model.dao.interfaces.GenericDao;
import model.dao.interfaces.IFieldOfStudyDao;
import model.db.hib.util.HibernateUtil;
import model.entity.Department;
import model.entity.FieldOfStudy;
import model.entity.Group;

public class FieldOfStudyDao extends GenericDao<FieldOfStudy, Integer> implements IFieldOfStudyDao {
	private FieldOfStudy fieldEntity;
	
	
	public FieldOfStudyDao() {
		this.fieldEntity = null;
	}
	

	public FieldOfStudy findByName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		
		try {
			criteria = session.createCriteria(FieldOfStudy.class);
			criteria.add(Restrictions.eq("fieldOfStudyName", name));
			fieldEntity = (FieldOfStudy)criteria.list().get(0);
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}

		if(fieldEntity != null) {
			return fieldEntity;
		} else {
			return null;
		}
	}
	public HashMap<Department, List<FieldOfStudy>> findAllFieldsForAllDepartments() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteriaDept, criteriaField = null;
		List<Department> departments = new ArrayList<Department>();
		List<FieldOfStudy> fields = new ArrayList<FieldOfStudy>();
		HashMap<Department, List<FieldOfStudy>> list = new HashMap<Department, List<FieldOfStudy>>();
		
		try {
			criteriaDept = session.createCriteria(Department.class);
			departments = criteriaDept.list();	
			Collections.sort(departments, (a, b) -> a.getDepartmentName().compareToIgnoreCase(b.getDepartmentName()));
			
			for(Department dept : departments) {
				criteriaField = session.createCriteria(FieldOfStudy.class).add(Restrictions.eq("departmentId", dept.getDepartmentId()));
				fields = criteriaField.list();
				
				Collections.sort(fields, (a, b) -> a.getFieldOfStudyName().compareToIgnoreCase(b.getFieldOfStudyName()));
				list.put(dept, fields);
			}
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		
		return list;
	}
	public HashMap<Department, HashMap<FieldOfStudy, HashMap<List<Specialization>, List<Group>>>> findAllFieldsSpecsGroupsForAllDepartments() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteriaDept, criteriaField, criteriaSpec, criteriaGroup = null;
		
		List<Department> departments = new ArrayList<Department>();
		List<Group> groups = new ArrayList<Group>();
		List<Specialization> specs = new ArrayList<Specialization>();
		List<FieldOfStudy> fields = new ArrayList<FieldOfStudy>();
		HashMap<List<Specialization>, List<Group>> specsGroups = new HashMap<List<Specialization>, List<Group>>();
		HashMap<FieldOfStudy, HashMap<List<Specialization>, List<Group>>> specsGroupsForFields = new HashMap<FieldOfStudy, HashMap<List<Specialization>, List<Group>>>();
		HashMap<Department, HashMap<FieldOfStudy, HashMap<List<Specialization>, List<Group>>>> all = new HashMap<Department, HashMap<FieldOfStudy, HashMap<List<Specialization>, List<Group>>>>();
		
		
		try {
			criteriaDept = session.createCriteria(Department.class);
			departments = criteriaDept.list();
			
			for(Department dept : departments) {
				criteriaField = session.createCriteria(FieldOfStudy.class).add(Restrictions.eq("departmentId", dept.getDepartmentId()));
				fields = criteriaField.list();
				
				specs = new ArrayList<Specialization>();
				groups = new ArrayList<Group>();
				//System.out.println(dept.getDepartmentDescription());
				for(FieldOfStudy field : fields) {
					criteriaSpec = session.createCriteria(Specialization.class).add(Restrictions.eq("id.fieldOfStudyId", field.getFieldOfStudyId()));
					criteriaGroup = session.createCriteria(Group.class).add(Restrictions.eq("fieldOfStudyId", field.getFieldOfStudyId()));
					
					specs.addAll(criteriaSpec.list());
					groups.addAll(criteriaGroup.list());
					
					specsGroups.put(specs, groups);
					specsGroupsForFields.put(field, specsGroups);
					
					//System.out.println("\n"+ field.getFieldOfStudyName() + " grupy: " + groups.size() + " spece: " + specs.size() +"\n");
				}
				all.put(dept, specsGroupsForFields);
			}
			
			
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		
		return all;
	}
	
	
	
	
	
	public static HashMap<Department, HashMap<FieldOfStudy, HashMap<List<Specialization>, List<Group>>>> ffindAllFieldsSpecsGroupsForAllDepartments() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteriaDept, criteriaField, criteriaSpec, criteriaGroup = null;
		
		List<Department> departments = new ArrayList<Department>();
		List<Group> groups = new ArrayList<Group>();
		List<Specialization> specs = new ArrayList<Specialization>();
		List<FieldOfStudy> fields = new ArrayList<FieldOfStudy>();
		HashMap<List<Specialization>, List<Group>> specsGroups = new HashMap<List<Specialization>, List<Group>>();
		HashMap<FieldOfStudy, HashMap<List<Specialization>, List<Group>>> specsGroupsForFields = new HashMap<FieldOfStudy, HashMap<List<Specialization>, List<Group>>>();
		HashMap<Department, HashMap<FieldOfStudy, HashMap<List<Specialization>, List<Group>>>> all = new HashMap<Department, HashMap<FieldOfStudy, HashMap<List<Specialization>, List<Group>>>>();
		
		
		try {
			criteriaDept = session.createCriteria(Department.class);
			departments = criteriaDept.list();
			
			for(Department dept : departments) {
				criteriaField = session.createCriteria(FieldOfStudy.class).add(Restrictions.eq("departmentId", dept.getDepartmentId()));
				fields = criteriaField.list();
				
				specs = new ArrayList<Specialization>();
				groups = new ArrayList<Group>();
				System.out.println(dept.getDepartmentDescription());
				for(FieldOfStudy field : fields) {
					criteriaSpec = session.createCriteria(Specialization.class).add(Restrictions.eq("id.fieldOfStudyId", field.getFieldOfStudyId()));
					criteriaGroup = session.createCriteria(Group.class).add(Restrictions.eq("fieldOfStudyId", field.getFieldOfStudyId()));
					
					specs.addAll(criteriaSpec.list());
					groups.addAll(criteriaGroup.list());
					
					specsGroups.put(specs, groups);
					specsGroupsForFields.put(field, specsGroups);
					
					System.out.println("\n"+ field.getFieldOfStudyName() + " grupy: " + groups.size() + " spece: " + specs.size() +"\n");
				}
				all.put(dept, specsGroupsForFields);
			}
			
			
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		
		return all;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public FieldOfStudy findFieldOfStudyNameById(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		FieldOfStudy entity = null;
		
		try {
			List results = session.createCriteria(FieldOfStudy.class)
					.add(Restrictions.eq("fieldOfStudyId", id))
					.setProjection( Projections.projectionList()
					.add( Projections.property("fieldOfStudyName"), "fieldOfStudyName")
					.add( Projections.property("fieldOfStudyId"), "fieldOfStudyId") )
					.setResultTransformer(Transformers.aliasToBean(FieldOfStudy.class))
					.list();

			entity = (FieldOfStudy)results.get(0);			
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}

		if(entity != null) {
			return entity;
		} else {
			return null;
		}
	}
	public FieldOfStudy findFieldOfStudyIdByName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		FieldOfStudy entity = null;
		
		try {
			List results = session.createCriteria(FieldOfStudy.class)
					.add(Restrictions.eq("fieldOfStudyName", name))
					.setProjection( Projections.projectionList()
					.add( Projections.property("fieldOfStudyId"), "fieldOfStudyId") )
					.setResultTransformer(Transformers.aliasToBean(FieldOfStudy.class))
					.list();

			entity = (FieldOfStudy)results.get(0);			
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}

		if(entity != null) {
			return entity;
		} else {
			return null;
		}
	}
	public List<FieldOfStudy> findByDepartmentId(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<FieldOfStudy> list = new ArrayList<FieldOfStudy>();
		
		try {
			criteria = session.createCriteria(FieldOfStudy.class);
			criteria.add(Restrictions.eq("departmentId", id));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		
		return list;
	}
	public List<FieldOfStudy> findByDepartmentName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<FieldOfStudy> list = new ArrayList<FieldOfStudy>();
		
		try {
			Department department = new DepartmentDao().findByName(name);
			
			criteria = session.createCriteria(FieldOfStudy.class);
			criteria.add(Restrictions.eq("departmentId", department.getDepartmentId()));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		
		return list;
	}
	public List<FieldOfStudy> findByDepartmentFullName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = null;
		List<FieldOfStudy> list = new ArrayList<FieldOfStudy>();
		
		try {
			Department department = new DepartmentDao().findByFullName(name);
			
			criteria = session.createCriteria(FieldOfStudy.class);
			criteria.add(Restrictions.eq("departmentId", department.getDepartmentId()));
			list = criteria.list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		
		return list;
	}
	public List<FieldOfStudy> findAllFields() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<FieldOfStudy> list = new ArrayList<FieldOfStudy>();
		
		try {
			list = session.createCriteria(FieldOfStudy.class).list();
		} catch( Exception e ) {
			e.getStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		
		return list;
	}
}