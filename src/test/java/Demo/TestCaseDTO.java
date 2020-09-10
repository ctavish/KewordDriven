package Demo;

import java.util.ArrayList;
import java.util.List;

/* Represent a test case. */
public class TestCaseDTO {

	private String name;
	
	private List<TestStepDTO> stepList = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TestStepDTO> getStepList() {
		if(stepList == null)
		{
			stepList = new ArrayList<TestStepDTO>();
		}
		return stepList;
	}

	public void setStepList(List<TestStepDTO> stepList) {
		this.stepList = stepList;
	}
	
	
}
