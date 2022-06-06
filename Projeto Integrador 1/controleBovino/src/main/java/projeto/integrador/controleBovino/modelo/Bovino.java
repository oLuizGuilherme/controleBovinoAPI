package projeto.integrador.controleBovino.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import projeto.integrador.controleBovino.vo.BovinoVO;

@Entity
public class Bovino {

	@Id
	private String id;
	private String codigo;
	@Column(name = "litros_leite")
	private Double litrosLeite;
	@Column(name = "quilos_racao")
	private Double quilosRacao;
	private BigDecimal peso;
	private LocalDate nascimento;
	private Boolean abatido;

	public Bovino() {
	}

	public Bovino(String codigo, Double litrosLeite, Double quilosRacao, BigDecimal peso, LocalDate nascimento, Boolean abatido) {
		this.id = UUID.randomUUID().toString();
		this.codigo = codigo;
		this.litrosLeite = litrosLeite;
		this.quilosRacao = quilosRacao;
		this.peso = peso;
		this.nascimento = nascimento;
		this.abatido = abatido;
	}

	public String getId() {
		return id;
	}

	public String getCodigo() {
		return codigo;
	}

	public Double getLitrosLeite() {
		return litrosLeite;
	}

	public Double getQuilosRacao() {
		return quilosRacao;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}
	
	public boolean isAbatido() {
		return abatido;
	}
	
	public void atualizaRacao(BovinoVO bovinoVO) {
		this.quilosRacao = bovinoVO.getQuilosRacao();
	}
	
	public void atualizaLeite(BovinoVO bovinoVO) {
		this.litrosLeite = bovinoVO.getLitrosLeite();
	}
	
}
