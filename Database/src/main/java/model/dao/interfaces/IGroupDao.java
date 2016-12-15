package model.dao.interfaces;

import java.util.List;
import model.entity.Group;

public interface IGroupDao extends IGenericDao<Group, Integer> {
	Group findByName(String name);
	List<Group> findByFieldOfStudyId(Integer id); 
	List<Group> findByFieldOfStudyName(String name); 
}