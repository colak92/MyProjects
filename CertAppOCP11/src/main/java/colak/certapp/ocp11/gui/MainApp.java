package colak.certapp.ocp11.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import colak.certapp.ocp11.parser.JavaCert;
import colak.certapp.ocp11.parser.JavaCertResult;

public class MainApp {
	
	JFrame frame;
	JMenuItem startExamMenuItem;
	QuestionPanel questionPanel;
	
	JavaCert javaCert;
	int currentQuestionIndex;
	
	ButtonGroup btnGroup = new ButtonGroup();
	JLabel indexLabel = new JLabel();
	JButton previous = new JButton("Previous");
	JButton next = new JButton("Next");
	JButton finish = new JButton("Finish");
	
	static JRadioButtonMenuItem mniNimbus = new JRadioButtonMenuItem("Nimbus");
	static JRadioButtonMenuItem mniMetal = new JRadioButtonMenuItem("Metal");
	static JRadioButtonMenuItem mniAcryl = new JRadioButtonMenuItem("Acryl");
	static JRadioButtonMenuItem mniAero = new JRadioButtonMenuItem("Aero");
	static JRadioButtonMenuItem mniAluminium = new JRadioButtonMenuItem("Aluminium");
	static JRadioButtonMenuItem mniFast = new JRadioButtonMenuItem("Fast");
    static JRadioButtonMenuItem mniGraphite = new JRadioButtonMenuItem("Graphite");
    static JRadioButtonMenuItem mniLuna = new JRadioButtonMenuItem("Luna");
    static JRadioButtonMenuItem mniMint = new JRadioButtonMenuItem("Mint");
    static JRadioButtonMenuItem mniTexture = new JRadioButtonMenuItem("Texture");
    
    public MainApp() {
		
		previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	displayQuestion(--currentQuestionIndex);
            }
        });
		
		next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	displayQuestion(++currentQuestionIndex);
            }
        });
		
		finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	onFinish();
            }
        });
	}
	
	public static void main(String... args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MainApp().buildGui();
			}
		});
	}

	protected void buildGui() {
		frame = new JFrame("Java Certification OCP 11");
		frame.setPreferredSize(new Dimension(1600, 1024));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		buildMenu();
	}

	private void buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		btnGroup.add(mniNimbus);
		btnGroup.add(mniMetal);
		btnGroup.add(mniAcryl);
		btnGroup.add(mniAero);
		btnGroup.add(mniAluminium);
		btnGroup.add(mniFast);
		btnGroup.add(mniGraphite);
		btnGroup.add(mniLuna);
		btnGroup.add(mniMint);
		btnGroup.add(mniTexture);
		
		JMenu themeMenu = new JMenu("Theme");
		themeMenu.add(mniNimbus);
		themeMenu.add(mniMetal);
		themeMenu.add(mniAcryl);
		themeMenu.add(mniAero);
		themeMenu.add(mniAluminium);
		themeMenu.add(mniFast);
		themeMenu.add(mniGraphite);
		themeMenu.add(mniLuna);
		themeMenu.add(mniMint);
		themeMenu.add(mniTexture);
		menuBar.add(themeMenu);
		
		JMenu examMenu = new JMenu("Exam");
		menuBar.add(examMenu);

		startExamMenuItem = new JMenuItem("Start new exam");
		startExamMenuItem.addActionListener(e -> startNewExam());
		examMenu.add(startExamMenuItem);
		examMenu.addSeparator();

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(e -> System.exit(0));
		examMenu.add(exitItem);
		
		mniNimbus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLaf(frame, "nimbus");
            }
        });
		
		mniMetal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLaf(frame, "metal");
            }
        });
		
		mniAcryl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLaf(frame, "acryl");
            }
        });
		mniAero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLaf(frame, "aero");
            }
        });
		mniAluminium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLaf(frame, "aluminium");
            }
        });
		mniFast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLaf(frame, "fast");
            }
        });
		mniGraphite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLaf(frame, "graphite");
            }
        });
		mniLuna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLaf(frame, "luna");
            }
        });
		mniMint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLaf(frame, "mint");
            }
        });
		mniTexture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLaf(frame, "texture");
            }
        });
	}
	
	private void startNewExam() {
		frame.setContentPane(createExamPanel());
		String result = JOptionPane.showInputDialog(frame, "Chose number of questions (max 495)", "New exam", JOptionPane.QUESTION_MESSAGE);
		if (result != null) {
			
			int resultToInt = Integer.valueOf(result);
			if (resultToInt > 495) {	// TODO (Create limit for max number of questions is 495)
				System.exit(0);
			}
			
			Integer count = Integer.valueOf(result);
			javaCert = new JavaCert(count);
			currentQuestionIndex = 0;
			displayQuestion(0);
			frame.revalidate();
		}
	}

	private JPanel createExamPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(indexLabel = new JLabel(""), BorderLayout.NORTH);
		panel.add(questionPanel = new QuestionPanel(), BorderLayout.CENTER);
		panel.add(createButtonsPanel(), BorderLayout.SOUTH);

		return panel;
	}
	
	private JPanel createButtonsPanel() {
		JPanel panel = new JPanel();
		panel.add(previous);
		panel.add(next);
		panel.add(finish);
		
		return panel;
	}
	
	private void onFinish() {
		JavaCertResult res = javaCert.evaluateAnswers();
		StringBuilder str = new StringBuilder();
		str.append("Result: ").append(res.numberOfCorrectAnswers).append("/").append(res.totalNumberOfQuestions).append(" (").append(res.correctPercent).append("% )\n");
		str.append("Incorect answers: \n");
		for (String s : res.incorrectAnswers)
			str.append(s).append("\n");
		
		JTextArea textArea = new JTextArea(str.toString());
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		scrollPane.setPreferredSize( new Dimension( 300, 300 ) );
		JOptionPane.showMessageDialog(frame, scrollPane, "Exam result", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void displayQuestion(int i) {
		indexLabel.setText("Question " + (currentQuestionIndex + 1) + " / " + javaCert.getExamQuestions().size());
		currentQuestionIndex = i;
		questionPanel.setQuestion(javaCert.getExamQuestions().get(i));
		previous.setEnabled(currentQuestionIndex > 0);
		next.setEnabled(currentQuestionIndex < javaCert.getExamQuestions().size() - 1);
		frame.revalidate();
	}
	
	public static void changeLaf(JFrame frame, String laf) {
		
		if (laf.equals("nimbus")) {
        	mniNimbus.setSelected(true);
            try {
            	UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		if (laf.equals("metal")) {
        	mniMetal.setSelected(true);
            try {
            	UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (laf.equals("acryl")) {
        	mniAcryl.setSelected(true);
            try {
                UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (laf.equals("aero")) {
        	mniAero.setSelected(true);
            try {
                UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (laf.equals("aluminium")) {
        	mniAluminium.setSelected(true);
            try {
                UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (laf.equals("fast")) {
        	mniFast.setSelected(true);
            try {
                UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		if (laf.equals("graphite")) {
        	mniGraphite.setSelected(true);
            try {
                UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (laf.equals("luna")) {
        	mniLuna.setSelected(true);
            try {
                UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (laf.equals("mint")) {
        	mniMint.setSelected(true);
            try {
                UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (laf.equals("texture")) {
        	mniTexture.setSelected(true);
            try {
                UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SwingUtilities.updateComponentTreeUI(frame);
    }

}
