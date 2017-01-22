package core.study.details;

public enum Grades {
	_2(2f), _2_5(2.5f), _3(3f), _3_5(3.5f), _4(4f), _4_5(4.5f), _5(5f);
	
	
	private float value;
	
	Grades(float value) {
		this.value = value;
	}
	
	public float getValue() {
		return value;
	}
}