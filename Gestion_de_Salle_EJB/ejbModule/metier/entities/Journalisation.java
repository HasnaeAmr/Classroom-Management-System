package metier.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="journalisation")
public class Journalisation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int  id_journalisation;
	private String Description;
	private Date Date;
	public Journalisation() {
		// TODO Auto-generated constructor stub
	}
	public Journalisation(int id_journalisation, String description, Date date) {
		super();
		this.id_journalisation = id_journalisation;
		Description = description;
		Date = date;
	}
	public int getId_journalisation() {
		return id_journalisation;
	}
	public void setId_journalisation(int id_journalisation) {
		this.id_journalisation = id_journalisation;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}

}
