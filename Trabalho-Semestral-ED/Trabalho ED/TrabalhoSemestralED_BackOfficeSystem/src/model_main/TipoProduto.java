package model_main;

public class TipoProduto {

	public int id;
	public String tipo;
	public String descricao;
	
	public TipoProduto(int id, String tipo, String descricao) {
		this.id = id;
		this.tipo = tipo;
		this.descricao = descricao;
	}

	public TipoProduto() {
		super();
	}
	
	public String toString() {
		return "Tipo de produto: " + tipo + "\nDescricao: " + descricao;
	}

}
