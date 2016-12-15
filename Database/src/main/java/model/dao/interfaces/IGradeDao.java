package model.dao.interfaces;

import java.util.List;
import model.entity.Grade;
import model.entity.GradeId;

public interface IGradeDao extends IGenericDao<Grade, GradeId> {
	List<Grade> findByTeacherId(Integer id);
	List<Grade> findByStudentId(Integer id);
	List<Grade> findByGradeType(String type);
	List<Grade> findByStudentAlbum(String album);
}