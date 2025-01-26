package colak.certapp.ocp11.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import colak.certapp.ocp11.model.Answer;

public class AnswersPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private List<Answer> answers;
	private List<JCheckBox> checkBoxes = new ArrayList<>();
	private List<JTextField> labels = new ArrayList<>();

	public AnswersPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		for (int i = 0; i < 8; ++i) {
			JPanel rowPanel = new JPanel();
			this.add(rowPanel);

			JCheckBox box = new JCheckBox(String.valueOf((char) ('A' + i)));
			box.setVisible(true);
			box.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					for (int i = 0; i < checkBoxes.size(); ++i) {
						if (checkBoxes.get(i) == e.getSource()) {
							JCheckBox b = (JCheckBox) e.getSource();
							answers.get(i).setChecked(b.isSelected());
							return;
						}
					}
				}
			});
			
			checkBoxes.add(box);
			JTextField field = new JTextField("");
			field.setEditable(false);
			field.setBackground(Color.WHITE);
			field.setFont(new Font("MonoLisa", 2, 16));
			labels.add(field);
			rowPanel.add(box);
			rowPanel.add(field);
		}
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
		updateComponents();
	}

	void updateComponents() {
		for (int i = 0; i < answers.size(); ++i) {
			checkBoxes.get(i).setSelected(answers.get(i).isChecked());
			labels.get(i).setText(answers.get(i).getContent());
		}
		
		for (int i = 0; i < checkBoxes.size(); ++i) {
			checkBoxes.get(i).setVisible(i < answers.size());
			labels.get(i).setVisible(i < answers.size());
		}
	}

}