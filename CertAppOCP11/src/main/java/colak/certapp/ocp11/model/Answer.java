package colak.certapp.ocp11.model;

public class Answer {

	private final String content;
	private final boolean correct;
	private boolean checked;

	public Answer(String content, boolean correct) {
		this.content = content;
		this.correct = correct;
	}

	public String getContent() {
		return content;
	}

	public boolean isCorrect() {
		return correct;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public static String getSymbol(int numAnswer) {
		String qString = "";
		switch (numAnswer) {
		case 1:
			qString = "A";
			break;
		case 2:
			qString = "B";
			break;
		case 3:
			qString = "C";
			break;
		case 4:
			qString = "D";
			break;
		case 5:
			qString = "E";
			break;
		case 6:
			qString = "F";
			break;
		case 7:
			qString = "G";
			break;
		case 8:
			qString = "H";
			break;
		}
		return qString;
	}

}