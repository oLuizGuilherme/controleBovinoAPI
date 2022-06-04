package projeto.integrador.controleBovino.vo;

public class QuantidadeLeiteVO {

	private Double quantidadeLeiteSemanal;
	private Integer quantidadeBovinos;
	
	public QuantidadeLeiteVO() {
	}
	
	public QuantidadeLeiteVO(Double quantidadeLeiteSemanal, Integer quantidadeBovinos) {
		this.quantidadeLeiteSemanal = quantidadeLeiteSemanal;
		this.quantidadeBovinos = quantidadeBovinos;
	}

	public Double getQuantidadeLeiteSemanal() {
		return quantidadeLeiteSemanal;
	}

	public void setQuantidadeLeiteSemanal(Double quantidadeLeiteSemanal) {
		this.quantidadeLeiteSemanal = quantidadeLeiteSemanal;
	}

	public Integer getQuantidadeBovinos() {
		return quantidadeBovinos;
	}

	public void setQuantidadeBovinos(Integer quantidadeBovinos) {
		this.quantidadeBovinos = quantidadeBovinos;
	}

}
