package model_main;

public class Compra {

	public int id;
	public String cliente;
	public double valorTotal;
	public String itens;

	public Compra() {
		super();
	}

	public Compra(int id, String cliente, double valorTotal, String itens) {
		this.id = id;
		this.cliente = cliente;
		this.valorTotal = valorTotal;
		this.itens = itens;
	}

	public String toString() {
		return "#" + id + "\tCliente: " + cliente + "\t$" + valorTotal + "\t" + itens;
	}

}
