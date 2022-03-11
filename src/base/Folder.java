package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>{
	
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note n) {
		notes.add(n);
	}

	public String getName() {
		return name;
	}
	
	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		for (Note n : notes){
			if (n instanceof TextNote)
				nText++;
			else
				nImage++;
		}
		return name + ":" + nText + ":" + nImage;
	}

	// lab03
	// compare name
	@Override
	public int compareTo(Folder o) {
		// TODO Auto-generated method stub
		return this.name.compareTo(o.name);
	}
	
	public void sortNotes() {
		// List<Note> notes = new ArrayList<Note>();
		Collections.sort(notes);
	}
	
	public List<Note> searchNotes(String keywords) {
		List<Note> targetNotes = new ArrayList<Note>();
		ArrayList<String> searchKeyWords = new ArrayList<String>();
		String temp = keywords.toUpperCase();
		int keywordLen = temp.length();
		
		while (keywordLen != 0)
		{
			int findSpaceIndex = temp.indexOf(" ");
			if (findSpaceIndex != -1)
			{
				if (!temp.substring(0,findSpaceIndex).equals("OR"))
					searchKeyWords.add(temp.substring(0,findSpaceIndex));
				temp = temp.substring(findSpaceIndex+1,keywordLen);
				keywordLen = keywordLen - findSpaceIndex - 1;
			}
			else
			{
				searchKeyWords.add(temp.substring(0,keywordLen));
				keywordLen = 0;
			}
		}
		
		for (Note n : notes)
		{
			if(n instanceof TextNote){
				//check title
				boolean target = false;
				String tempTitle = n.getTitle().toUpperCase();
				if (tempTitle.contains(searchKeyWords.get(0)) || tempTitle.contains(searchKeyWords.get(1)))
				{
					if (tempTitle.contains(searchKeyWords.get(2)) || tempTitle.contains(searchKeyWords.get(3)))
					{
						target = true;
						targetNotes.add(n);
					}
				}
				
				// check content
				if (!target)
				{
					String tempContent = ((TextNote) n).getContent().toUpperCase();
					if (tempContent.contains(searchKeyWords.get(0)) || tempContent.contains(searchKeyWords.get(1)))
					{
						if (tempContent.contains(searchKeyWords.get(2)) || tempContent.contains(searchKeyWords.get(3)))
						{
							target = true;
							targetNotes.add(n);
						}
					}
				}
			}
			else
			{
				String tempTitle = n.getTitle().toUpperCase();
				if (tempTitle.contains(searchKeyWords.get(0)) || tempTitle.contains(searchKeyWords.get(1)))
				{
					if (tempTitle.contains(searchKeyWords.get(2)) || tempTitle.contains(searchKeyWords.get(3)))
					{
						targetNotes.add(n);
					}
				}
			}
		}
		return targetNotes;
	}
	
}
