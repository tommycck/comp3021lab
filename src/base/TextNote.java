package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class TextNote extends Note {

	private String content;
	
	public TextNote(String title) {
		super(title);
	}
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		FileInputStream fis = null;
		InputStreamReader in = null;
		try {
			fis = new FileInputStream(absolutePath);
			in = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(in);
			while (br.readLine() != null)
				result += br.readLine();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void exportTextToFile(String pathFolder) {
		if (pathFolder == "")
			pathFolder = ".";
		FileWriter fw = null;
		try {
			File file = new File(pathFolder +  File.separator + this.getTitle().replaceAll(" ", "_") + ".txt");
			fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(this.getContent());
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
