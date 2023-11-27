package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import br.edu.fateczl.fila.Fila;
import model.ListaEncadeada;
import model_main.ClientePessoaFisica;
import model_main.Compra;
import model_main.Produto;

public class ControleCompra {
	
	public void exibirCompra() {
		int id = 0;
		readCompras();
		boolean teste = false;
		ListaEncadeada<Compra>Compras
		int size = Compras.size();
		int i = 0;
		while(i < size || teste == true) {
			if(Compras.id == id) {
				
				i = size;
				teste = true;
			}
		}
		if(teste == false) {
			JOptionPane.showInputDialog("COMPRA NAO ENCONTRADA.");
		}
	}
 
	public void salvarCompra() throws Exception {
		File arquivo = new File("C:\\PastaTrabalhoED", "HistoricoCompras.csv");
		int size = hashTable.length;
		if (!arquivo.exists() && !arquivo.isFile()) {
			arquivo.createNewFile();
		}
		Compra c;
		StringBuffer buffer = new StringBuffer();
			int tamanho = Compras.size();
			int i = 0;
			while (i < tamanho) {
				c = Compras.getValue(i);
				buffer.append(c.toString + "\r\n");
				i = i + 1;
			}
		String thing = buffer.toString();
		FileWriter fileWriter = new FileWriter(arquivo);
		PrintWriter print = new PrintWriter(fileWriter);
		print.write(thing);
		print.flush();
		print.close();
		fileWriter.close();
	}
	
	public void readCompras() throws Exception {
		File arq = new File("C:\\PastaTrabalhoED", "Produto.csv");
		Compra c;
			if (arq.exists() && arq.isFile()) {
				FileInputStream flux = new FileInputStream(arq);
				InputStreamReader reader = new InputStreamReader(flux);
				BufferedReader buffer = new BufferedReader(reader);
				String linha = buffer.readLine();
				while (linha != null) {
					String[] vet = linha.split(";");
					c = new Compra(Integer.parseInt(vet[0]),vet[1]);
					Compras.addFirst(c);
					linha = buffer.readLine();
				}
				buffer.close();
				reader.close();
				flux.close();
			}
		
	}
	
	public void vincularCliente(Fila<Produto>Carrinho, ClientePessoaFisica Pessoa) {
		Fila<Produto>aux = Carrinho;
		StringBuffer buffer = new StringBuffer();
		Produto p;
		int size = Carrinho.size();
		for(int i = 0; i < size; i ++) {
			p = aux.remove();
			buffer.append(p.nome + " " + p.valor);
		}
		String conteudo = buffer.toString();
		Compra c = new Compra (geraId(), Pessoa.Nome, totalCompras(Carrinho), conteudo );
	}
 
	private double totalCompras(Fila<Produto> carrinho) throws Exception {
		double total = 0;
		Produto p;
		int size = carrinho.size();
		for(int i = 0; i < size; i ++) {
			p = carrinho.remove();
			total = total + p.valor;
		}
		return total;
	}
	
	private int geraId() {
		int id = 0;
		
		return id;
	}
}
