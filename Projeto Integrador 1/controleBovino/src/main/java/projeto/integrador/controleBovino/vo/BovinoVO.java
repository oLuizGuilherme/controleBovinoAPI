package projeto.integrador.controleBovino.vo;

import java.math.BigDecimal;
import java.time.LocalDate;

import projeto.integrador.controleBovino.modelo.Bovino;

public class BovinoVO {

	private String codigo;
	private Double litrosLeite;
	private Double quilosRacao;
	private BigDecimal peso;
	private LocalDate nascimento;

	public BovinoVO() {
	}

	public BovinoVO(String codigo, Double litrosLeite, Double quilosRacao, BigDecimal peso,
			LocalDate nascimento) {
		this.codigo = codigo;
		this.litrosLeite = litrosLeite;
		this.quilosRacao = quilosRacao;
		this.peso = peso;
		this.nascimento = nascimento;
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

	public static BovinoVO entityToVO(Bovino bovino) {
		return new BovinoVO(bovino.getCodigo(), bovino.getLitrosLeite(), bovino.getQuilosRacao(), bovino.getPeso(), bovino.getNascimento());
	}

}
