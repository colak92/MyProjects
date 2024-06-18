package colak.certapp.ocp11.model;

import java.util.List;

public class Question {

	private String content;
	private List<Answer> answers;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public String toString() {
		String s = content;
		int numAnswer = 1;
		for (Answer a : answers) {
			s += "\n";
			s += Answer.getSymbol(numAnswer++) + "." + a.getContent();
		}
		return s;
	}

	public boolean isCorrectlyAnswered() {
		for (Answer a : answers)
			if (a.isCorrect() != a.isChecked())
				return false;
		return true;
	}

	public String getCorrectAnswers() {
		String s = "";
		for (int i = 0; i < answers.size(); ++i) {
			if (answers.get(i).isCorrect()) {
				s += Answer.getSymbol(i + 1) + " ";
			}

		}
		return s;
	}

}