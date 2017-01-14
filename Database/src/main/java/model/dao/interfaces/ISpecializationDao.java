package model.dao.interfaces;

import java.util.List;
import model.entity.Specialization;
import model.entity.SpecializationId;

public interface ISpecializationDao extends IGenericDao<Specialization, SpecializationId> {
	Specialization findByName(String name);
	List<Specialization> findByFieldOfStudyId(Integer id);
	List<Specialization> findByFieldOfStudyName(String name);
	public List<Specialization> findSpecsByDepartmentId(Integer id);
}