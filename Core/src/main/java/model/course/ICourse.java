package model.course;

import model.human.details.CourseDetails;

public interface ICourse {
	CourseDetails getCourse();
	void setCourse(CourseDetails details);
}