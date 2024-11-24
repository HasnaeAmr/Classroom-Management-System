package metier.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="journalisation")
public class Journalisation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_journalisation")
	private Long id_journalisation;
	@Column(name="description")
	private String description;
	@Column(name="date")
	private LocalDate date;
	
	public Journalisation(String description) {
		LocalDate currentDate = LocalDate.now();
		this.date=currentDate;

		this.description=description + " à " + date;
	}
	
	public String getDescription() {
		return description;
	}
	public LocalDate getDate() {
		return date;
	}
	public Journalisation() {
		// TODO Auto-generated constructor stub
	}
}
