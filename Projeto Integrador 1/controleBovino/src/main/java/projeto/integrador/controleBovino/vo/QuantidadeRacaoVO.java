package projeto.integrador.controleBovino.vo;

import java.math.BigDecimal;

public class QuantidadeRacaoVO {

	private int quantidadeBovinos;
	private BigDecimal quantidadeRacao;

	public QuantidadeRacaoVO() {
	}

	public QuantidadeRacaoVO(int quantidadeBovinos, BigDecimal quantidadeRacao) {
		this.quantidadeBovinos = quantidadeBovinos;
		this.quantidadeRacao = quantidadeRacao;

	}

	public int getQuantidadeBovinos() {
		return quantidadeBovinos;
	}

	public BigDecimal getQuantidadeRacao() {
		return quantidadeRacao;
	}

	public void setQuantidadeBovinos(int quantidadeBovinos) {
		this.quantidadeBovinos = quantidadeBovinos;
	}

	public void setQuantidadeRacao(BigDecimal quantidadeRacao) {
		this.quantidadeRacao = quantidadeRacao;
	}
}
