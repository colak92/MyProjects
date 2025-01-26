package colak.certapp.ocp11.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import colak.certapp.ocp11.model.Question;

public class QuestionPanel extends JPanel {

	private static final long serialVersionUID = 1717362871467184002L;

	private AnswersPanel answersPanel;
	private JTextArea questionContent = new JTextArea(23, 105);

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
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		questionContent.setLineWrap(true);
		questionContent.setEditable(false);
		questionContent.setPreferredSize(new Dimension(600, 600));
		questionContent.setFont(new Font("MonoLisa", 2, 18));
		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(scrollPane);

		return panel;
	}

	public void setQuestion(Question question) {
		questionContent.setText(question.getContent());
		answersPanel.setAnswers(question.getAnswers());
	}

}