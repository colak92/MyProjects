package colak.certapp.ocp11.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import colak.certapp.ocp11.model.Question;

public class JavaCert {

	public static List<Question> allQuestions;
	private ArrayList<Question> examQuestions;

	private int counter;
	public static final int NUM_OF_QUESTIONS = 495; // Max number of questions

	public void loadQuestions() {
		SimpleQuestionParser parser = new SimpleQuestionParser();
		allQuestions = parser.parse(new File("src/main/resources/allQuestions.txt"), new File("src/main/resources/allAnswers.txt"));
	}

	public JavaCert() {
		this(NUM_OF_QUESTIONS);
	}

	public JavaCert(Integer count) {
		loadQuestions();
		this.setExamQuestions(new ArrayList<Question>());
		createNewExam(count);
	}

	private void createNewExam(Integer count) {
		counter = 0;

		while (counter < count) {
			int randomNum = (int) (Math.random() * 495); // Number of questions in file
			if (examQuestions.contains(allQuestions.get(randomNum)) == false) {
				counter++;
				examQuestions.add(allQuestions.get(randomNum));
			}
		}
		for (Question q : examQuestions) {
			Collections.shuffle(q.getAnswers());
		}
	}

	public ArrayList<Question> getExamQuestions() {
		return examQuestions;
	}
	
	public void setExamQuestions(ArrayList<Question> examQuestions) {
		this.examQuestions = examQuestions;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public JavaCertResult evaluateAnswers() {
		JavaCertResult res = new JavaCertResult();

		for (int i = 0; i < getExamQuestions().size(); ++i)
			if (!getExamQuestions().get(i).isCorrectlyAnswered())
				res.incorrectAnswers.add("" + (i + 1) + " " + " " + getExamQuestions().get(i).getCorrectAnswers());

		res.numberOfCorrectAnswers = getExamQuestions().size() - res.incorrectAnswers.size();
		res.totalNumberOfQuestions = getExamQuestions().size();
		res.correctPercent = res.numberOfCorrectAnswers * 100.0 / res.totalNumberOfQuestions;
		return res;
	}

}
