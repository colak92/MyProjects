package colak.certapp.ocp11.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import colak.certapp.ocp11.model.Question;

public class QuestionPanel extends JPanel {

	private static final long serialVersionUID = 1717362871467184002L;

	private AnswersPanel answersPanel;
	private JTextArea questionContent = new JTextArea(30, 140);

	public QuestionPanel() {
		this.setLayout(new BorderLayout());
		this.add(createQuestionPanel(), BorderLayout.CENTER);
		this.add(createAnswersPanel(), BorderLayout.SOUTH);
	}

	private JPanel createAnswersPanel() {
		JPanel wrapper = new JPanel();
		wrapper.add(answersPanel = new AnswersPanel());
		wrapper.setLayout(new FlowLayout(FlowLayout.LEFT));
		return wrapper;
	}

	private JPanel createQuestionPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEtchedBorder());
		JScrollPane scrollPane = new JScrollPane(questionContent);

		questionContent.setLineWrap(true);
		questionContent.setEditable(false);
		questionContent.setPreferredSize(new Dimension(600, 800));
		panel.add(scrollPane);

		return panel;
	}

	public void setQuestion(Question question) {
		questionContent.setText(question.getContent());
		answersPanel.setAnswers(question.getAnswers());
	}

}
