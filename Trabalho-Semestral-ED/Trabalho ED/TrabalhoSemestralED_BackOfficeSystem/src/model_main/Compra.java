package model_main;

public class Compra {

	public int id;
	public String cliente;
	public String itens;
	public double valorTotal;

	public Compra() {
		super();
	}

	public Compra(int id, String cliente, String itens, double valorTotal) {
		this.id = id;
		this.cliente = cliente;
		this.itens = itens;
		this.valorTotal = valorTotal;
	}

	public String toString() {
		return "#" + id + "\tCliente: " + cliente + "\t$" + valorTotal + "\t" + itens;
	}

}
