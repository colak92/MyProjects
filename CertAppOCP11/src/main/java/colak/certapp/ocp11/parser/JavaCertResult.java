package colak.certapp.ocp11.parser;

import java.util.ArrayList;
import java.util.List;

public class JavaCertResult {
	
	public long numberOfCorrectAnswers;

	public long totalNumberOfQuestions;

	public double correctPercent;

	public List<String> incorrectAnswers = new ArrayList<>();

}
