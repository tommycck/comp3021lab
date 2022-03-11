package base;

import java.util.Date;

public class Note implements Comparable<Note>{
	private Date date;
	private String title;
	
	public Note(String title) {
		this.title = title;
		this.date = new Date(System.currentTimeMillis());
	}
	
	public String getTitle() {
		return title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		// check if the title is equal, then return true
		Note other = (Note) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	// lab 03
	public String toString() {
		return date.toString() + "\t" + title;
	}
	
	// Note --> compare creation date
	// more recently --> smaller
	// return 1 = this > o
	// return -1 = this < o
	// return 0 = this = o
	@Override
	public int compareTo(Note o) {
		// TODO Auto-generated method stub
		if (this.date.equals(o.date))
			return 0;
		else if (this.date.before(o.date))
			return 1;
		else
			return -1;
		
	}
	
}
