package app.services;

import core.humanity.details.Address;
import core.study.fieldofstudy.FieldOfStudy;
import core.study.group.Group;

public class Test {

	public static void main(String[] args) {
		GroupService s = new GroupService();
		
		
		Group f = s.findGroupNameById(1);
		
		
		System.out.println(s.findByFieldOfStudyId(1).get(0).getDetails().getGroupName());
	}

}
