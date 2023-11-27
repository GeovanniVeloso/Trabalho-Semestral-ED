package controller;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.fila.Fila;
import model.ListaEncadeada;
import model_main.ClientePessoaFisica;
import model_main.Compra;
import model_main.Produto;
import model_main.TipoProduto;
import view.Checkout;
import view.ClienteCarrinho;

public class ControleCompra {
	
	private JTextField textFieldCadastroPessoa;
	private boolean flagPessoa;
	private ListaEncadeada<Produto> produtosCarrinho;
	private JTextField textFieldIDdeCompra;
	private JTextArea textAreaResultadoConsultaHistorico;
	
	public ControleCompra(JTextField textField, boolean flagPessoa, ListaEncadeada<Produto> produtosCarrinho) {
		this.textFieldCadastroPessoa = textField;
		if(flagPessoa) {
			this.flagPessoa = true;
		} else {
			this.flagPessoa = false;
		}
	}
	
	public ControleCompra(JTextField textFieldIDdeCompra, JTextArea textAreaResultadoConsultaHistorico) {
		this.textFieldIDdeCompra = textFieldIDdeCompra;
		this.textAreaResultadoConsultaHistorico = textAreaResultadoConsultaHistorico;
	}

	public void actionPerformed(ActionEvent e) {
		String botaoSelec = e.getActionCommand();
		try {
			if (botaoSelec.equals("FINALIZAR")) {
				vincularCliente(produtosCarrinho);
			}
			if (botaoSelec.equals("consultar")) {
				exibirHistorico();
			}
		} catch (Exception erro) {
			erro.printStackTrace();
		}
	}
	
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
	
	private void vincularCliente(ListaEncadeada<Produto> produtosCarrinho) throws Exception, IOException {
		File arquivoPessoa;
		if(flagPessoa) {
			arquivoPessoa = new File("C:\\PastaTrabalhoED", "ClientesPessoaFísica.csv");
		} else {
			arquivoPessoa = new File("C:\\PastaTrabalhoED", "ClientesPessoaJuridica.csv");
		}
		boolean encontrado = false;
		try (BufferedReader bufferLeitura = new BufferedReader(new FileReader(arquivoPessoa))) {
			String linha = bufferLeitura.readLine();

			while (linha != null) {// percorre o arquivo
				String[] colunasDoCSV = linha.split(";");
				if (colunasDoCSV[0].equals(textFieldCadastroPessoa.getText())) {
					inserirClienteCSVCarrinho();
					encontrado = true;
				}

				linha = bufferLeitura.readLine();
			}
		}
		//instancias das proximas telas
		if (!encontrado) {
			JOptionPane.showMessageDialog(null, "CLIENTE NÃO ENCONTRADO");
			
			ClienteCarrinho clienteCarrinho = new ClienteCarrinho(produtosCarrinho);
			clienteCarrinho.setVisible(true);
			
		}else {
			Checkout CheckoutjFrame = new Checkout(produtosCarrinho);
			CheckoutjFrame.setVisible(true);
		}
	}
	
	private void inserirClienteCSVCarrinho() {
		try {
			File dir = new File("C:\\PastaTrabalhoED");
			File arquivo = new File(dir , "CarrinhoDeCompras.csv");
			if (dir.exists() && dir.isDirectory()) {// existe e é um diretório?
				boolean exist = false;// padrão nn existe
				if (arquivo.exists()) {// Arquivo existe?
					exist = true;// chumba que existe
				}
				FileWriter filewriter = new FileWriter(arquivo, exist);
				PrintWriter print = new PrintWriter(filewriter);// instancia a classe que escreve o conteúdo
				print.write(textFieldCadastroPessoa.getText());
				print.flush();// termina a escrita
				print.close();
				filewriter.close();
			} else {
				throw new IOException("Diretório Inválido");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	private void vincularCliente(Fila<Produto>Carrinho, ClientePessoaFisica Pessoa) throws Exception {
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
		int contador = 0;
		int tamanho = listaTipoProduto.size();
		for(int i = 0; i < tamanho; i++) {
			TipoProduto tipoProduto = listaTipoProduto.getValue(i);
			if(contador != tipoProduto.id) {
				return contador;
			}
			contador++;
		}
		return contador;
	}
	
	public void contruirCompra() {
		
	}
}
