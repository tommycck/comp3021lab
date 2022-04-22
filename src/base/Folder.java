package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>, Serializable{
	
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
				else
					searchKeyWords.add("OR");
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
				int searchKeyWordsSize = searchKeyWords.size();
				for (int i = 0; i < searchKeyWordsSize; i++)
				{
					if (i + 1 == searchKeyWordsSize)
					{
						if (tempTitle.contains(searchKeyWords.get(i)))
						{
							target = true;
							targetNotes.add(n);
						}
					}
					else if (i + 2 != searchKeyWordsSize)
					{
						if (searchKeyWords.get(i+1).equals("OR"))
						{
							if (tempTitle.contains(searchKeyWords.get(i)) || tempTitle.contains(searchKeyWords.get(i+2)))
							{
								if (i + 3 == searchKeyWordsSize)
								{
									target = true;
									targetNotes.add(n);
								}
								i = i + 2;

							}
						}
					}
					else
						break;

				}
				
				// check content
				if (!target)
				{
					String tempContent = ((TextNote) n).getContent().toUpperCase();
					for (int i = 0; i < searchKeyWordsSize; i++)
					{
						if (i + 1 == searchKeyWordsSize)
						{
							if (tempContent.contains(searchKeyWords.get(i)))
							{
								target = true;
								targetNotes.add(n);
							}
						}
						else if (i + 2 != searchKeyWordsSize)
						{
							if (searchKeyWords.get(i+1).equals("OR"))
							{
								if (tempContent.contains(searchKeyWords.get(i)) || tempContent.contains(searchKeyWords.get(i+2)))
								{
									if (i + 3 == searchKeyWordsSize)
									{
										target = true;
										targetNotes.add(n);
									}
									i = i + 2;

								}
							}
						}
						else
							break;

					}
				}
			}
			else
			{
				String tempTitle = n.getTitle().toUpperCase();
				int searchKeyWordsSize = searchKeyWords.size();
				for (int i = 0; i < searchKeyWordsSize; i++)
				{
					if (i + 1 == searchKeyWordsSize)
					{
						if (tempTitle.contains(searchKeyWords.get(i)))
						{
							targetNotes.add(n);
						}
					}
					else if (i + 2 != searchKeyWordsSize)
					{
						if (searchKeyWords.get(i+1).equals("OR"))
						{
							if (tempTitle.contains(searchKeyWords.get(i)) || tempTitle.contains(searchKeyWords.get(i+2)))
							{
								if (i + 3 == searchKeyWordsSize)
								{
									targetNotes.add(n);
								}
								i = i + 2;

							}
						}
					}
					else
						break;

				}
			}
		}
		return targetNotes;
	}

	public boolean removeNotes(String title) {
		// TODO Auto-generated method stub
		// Given the title of the note, delete it from the folder.
		// Return true if it is deleted successfully, otherwise return false. 

		for(Note n: notes) {
			if(n.getTitle().equals(title)) {
				notes.remove(n);
				return true;
			}
		}
		return false;
	}
	
}
