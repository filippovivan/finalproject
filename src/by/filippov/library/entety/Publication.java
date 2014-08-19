package by.filippov.library.entety;

public class Publication implements Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int isbn;
	private String title;
	private String author;
	private int publishYear;
	private String genre;
	private String format;
	private String annotation;

	public Publication() {
	}

	public Publication(int ibsn, String title, int publishYear, String author,
			String genre, String format, String annotation) {
		super();
		this.isbn = ibsn;
		this.title = title;
		this.publishYear = publishYear;
		this.author = author;
		this.genre = genre;
		this.format = format;
		this.setAnnotation(annotation);
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int ibsn) {
		this.isbn = ibsn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + isbn;
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
		Publication other = (Publication) obj;
		if (isbn != other.isbn)
			return false;
		return true;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

}
