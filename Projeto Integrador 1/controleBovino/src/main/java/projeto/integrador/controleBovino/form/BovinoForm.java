package projeto.integrador.controleBovino.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class BovinoForm {

	@NotNull (message = "Campo Código não informado.") @NotEmpty(message = "Campo Código precisa de pelo menos 1 caractere.")
	private String codigo;
	@NotNull
	private Double litrosLeite;
	@NotNull
	private Double quilosRacao;
	@NotNull
	private BigDecimal peso;
	@NotNull @DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate nascimento;
	private boolean abatido;

	public BovinoForm() {
	}

	public BovinoForm(String codigo, Double litrosLeite, Double quilosRacao, BigDecimal peso,
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

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setLitrosLeite(Double litrosLeite) {
		this.litrosLeite = litrosLeite;
	}

	public void setQuilosRacao(Double quilosRacao) {
		this.quilosRacao = quilosRacao;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}
	
	public boolean isAbatido() {
		return abatido;
	}
	
	public void setAbatido(boolean abatido) {
		this.abatido = abatido;
	}

}
