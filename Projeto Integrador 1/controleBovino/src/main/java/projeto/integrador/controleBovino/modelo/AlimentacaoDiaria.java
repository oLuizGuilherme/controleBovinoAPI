package projeto.integrador.controleBovino.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "alimentacao_diaria")
public class AlimentacaoDiaria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private LocalDate data;
	@JoinColumn(name = "id_bovino")
	@ManyToOne
	private Bovino bovino;

	public AlimentacaoDiaria() {
	}

	public String getId() {
		return id;
	}

	public LocalDate getData() {
		return data;
	}

	public Bovino getBovino() {
		return bovino;
	}

}
