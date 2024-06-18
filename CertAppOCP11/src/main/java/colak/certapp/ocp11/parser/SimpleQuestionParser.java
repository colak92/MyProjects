package colak.certapp.ocp11.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import colak.certapp.ocp11.model.Answer;
import colak.certapp.ocp11.model.Question;

public class SimpleQuestionParser implements QuestionParser {

	@Override
	public List<Question> parse(File f1, File f2) {

		List<Question> resultList = new ArrayList<>();
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		
		try {
			FileInputStream fis1 = new FileInputStream(f1);
			FileInputStream fis2 = new FileInputStream(f2);
			
		    InputStreamReader isr1 = new InputStreamReader(fis1, StandardCharsets.UTF_8);
		    InputStreamReader isr2 = new InputStreamReader(fis2, StandardCharsets.UTF_8);
		    
		    br1 = new BufferedReader(isr1);
		    br2 = new BufferedReader(isr2);
			
			String lineForQuestion = br1.readLine();
			String lineForAnswer = br2.readLine();
			
			do {
				// question expected
				Question question = new Question();
				StringBuilder content = new StringBuilder();
				int index = lineForQuestion.indexOf('.');
				content.append(lineForQuestion.substring(index + 1));

				lineForQuestion = br1.readLine();
				while (!lineForQuestion.startsWith("A.")) {
					content.append(System.lineSeparator());
					content.append(lineForQuestion);
					lineForQuestion = br1.readLine();
				}
				question.setContent(content.toString());
				
				// answers expected
				List<Answer> answers = new ArrayList<>();
				String correctAnswer = lineForAnswer.substring(0, lineForAnswer.indexOf(' '));
				while (isAnswer(lineForQuestion)) {
					String pom = lineForQuestion.substring(0, lineForQuestion.indexOf('.'));
					if (pom.equals(correctAnswer)) {
						answers.add(new Answer(lineForQuestion.substring(lineForQuestion.indexOf('.') + 1), true));
						lineForAnswer = lineForAnswer.substring(lineForAnswer.indexOf(' ') + 1);
						if (!lineForAnswer.isEmpty())
							correctAnswer = lineForAnswer.substring(0, lineForAnswer.indexOf(' '));
					} else {
						answers.add(new Answer(lineForQuestion.substring(lineForQuestion.indexOf('.') + 1), false));
					}
					if (!br1.ready())
						break;
					lineForQuestion = br1.readLine();
				}
				question.setAnswers(answers);
				resultList.add(question);
				if (!br2.ready())
					break;
				lineForAnswer = br2.readLine();
			} while (br1.ready());

			br1.close();
			br2.close();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return resultList;
	}

	private boolean isAnswer(String line) {
		if (line.substring(0, line.indexOf('.')).equals("A") || line.substring(0, line.indexOf('.')).equals("B")
				|| line.substring(0, line.indexOf('.')).equals("C") || line.substring(0, line.indexOf('.')).equals("D")
				|| line.substring(0, line.indexOf('.')).equals("E") || line.substring(0, line.indexOf('.')).equals("F")
				|| line.substring(0, line.indexOf('.')).equals("G") || line.substring(0, line.indexOf('.')).equals("H"))
			return true;
		return false;
	}

}
