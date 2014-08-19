package by.filippov.library.entety;

public class Book implements Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int ibsn;
	private String defects;

	public Book() {
	}

	public Book(int id, int ibsn, String defects) {
		this.id = id;
		this.ibsn = ibsn;
		this.defects = defects;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIbsn() {
		return ibsn;
	}

	public void setIbsn(int ibsn) {
		this.ibsn = ibsn;
	}

	public String getDefects() {
		return defects;
	}

	public void setDefects(String defects) {
		this.defects = defects;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((defects == null) ? 0 : defects.hashCode());
		result = prime * result + ibsn;
		result = prime * result + id;
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
		Book other = (Book) obj;
		if (defects == null) {
			if (other.defects != null)
				return false;
		} else if (!defects.equals(other.defects))
			return false;
		if (ibsn != other.ibsn)
			return false;
		if (id != other.id)
			return false;
		return true;
	}
}
