package model_main;

public class Produto {

	public int prodId;
	public String nome;
	public double valor;
	public String desc;
	public int qntdEstoque;
	public int indice;
	
	public Produto(){
		super();
	}
	
	public Produto(int ProdId, String Nome, double Valor, String Desc, int QntdEstoque, int Indice) {
		this.prodId = ProdId;
		this.nome = Nome;
		this.valor = Valor;
		this.desc = Desc;
		this.qntdEstoque = QntdEstoque;
		this.indice = Indice;
	}

	@Override
	public String toString() {
		return prodId + ";" + nome + ";" + valor + ";" + desc + ";" + qntdEstoque;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setQntdEstoque(int qntdEstoque) {
		this.qntdEstoque = qntdEstoque;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}
	
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
 
	public void setNome(String nome) {
		this.nome = nome;
	}
 
	public void setValor(double valor) {
		this.valor = valor;
	}
 
	public void setDesc(String desc) {
		this.desc = desc;
	}
 
	public void setQntdEstoque(int qntdEstoque) {
		this.qntdEstoque = qntdEstoque;
	}
 
	public void setIndice(int indice) {
		this.indice = indice;
	}
	
	

}