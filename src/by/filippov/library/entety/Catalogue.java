package by.filippov.library.entety;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Catalogue implements Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String description;
	private List<Publication> publications;

	public Catalogue(int id, String name, String description) {
		super();
		publications = new ArrayList<Publication>();
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
	}

	public Catalogue(int id, String name, String description,
			List<Publication> publications) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.publications = publications;
	}

	public Catalogue() {
		publications = new ArrayList<Publication>();
	}

	public Iterator<Publication> iterator() {
		return publications.iterator();
	}

	public List<Publication> getPublications() {
		return Collections.unmodifiableList(publications);
	}

	public boolean add(Publication e) {
		return publications.add(e);
	}

	public boolean remove(Object o) {
		return publications.remove(o);
	}

	public Publication remove(int index) {
		return publications.remove(index);
	}

	public boolean addAll(Collection<? extends Publication> c) {
		return publications.addAll(c);
	}

	public Publication get(int index) {
		return publications.get(index);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
