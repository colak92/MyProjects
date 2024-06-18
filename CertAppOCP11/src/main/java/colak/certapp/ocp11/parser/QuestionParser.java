package colak.certapp.ocp11.parser;

import java.io.File;
import java.util.List;

import colak.certapp.ocp11.model.Question;

public interface QuestionParser {

	public List<Question> parse(File f1, File f2);
	
}
