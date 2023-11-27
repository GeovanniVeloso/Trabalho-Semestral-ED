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
	private File diretorio = new File("C:\\PastaTrabalhoED");
	private File arquivo = new File(diretorio, "HistoricoCompras.csv");
	private String nomeCliente;
	private String totalCompra;
	private String itens;
	
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

	public ControleCompra(String nomeCliente, String totalCompra, String itens) {
		this.nomeCliente = nomeCliente;
		this.totalCompra = totalCompra;
		this.itens = itens;
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
			if (botaoSelec.equals("Finalizar Compra")) {
				criarHistorico();
			}
		} catch (Exception erro) {
			erro.printStackTrace();
		}
	}

	private void criarHistorico() throws Exception {
		if (diretorio.exists() && diretorio.isDirectory()) {// existe e é um diretório?
		    boolean exist = false;// padrão nn existe
		    if (arquivo.exists()) {// Arquivo existe?
		       exist = true;// chumba que existe
		    }
		    int id = gerarId();
		    String content = id + ";" +nomeCliente+ ";" +itens+ ";"+ totalCompra;
		    System.out.println(content);
		    FileWriter filewriter = new FileWriter(arquivo, exist);// Abre arquivo
		    PrintWriter print = new PrintWriter(filewriter);// instancia a classe que escreve o conteúdo
		    print.write(content);// escreve o conteúdo
		    print.flush();// termina a escrita
		    print.close();
		    filewriter.close();
		} else {
			throw new IOException("Diretório Inválido");
		}	
	}

	private void exibirHistorico() throws Exception {
		Compra compraProcurada = new Compra();
		compraProcurada.id = Integer.parseInt(textFieldIDdeCompra.getText());
		boolean compraExiste = false;
		ListaEncadeada<Compra> historico = new ListaEncadeada<>();
		historico = pegarHistorico(historico);
		int tamanho = historico.size();
		for(int i = 0; i < tamanho; i++) {
			Compra compra = historico.getValue(i);
			if(compra.id == compraProcurada.id) {
				compraExiste = true;
				textAreaResultadoConsultaHistorico.setText(compra.toString());
				JOptionPane.showMessageDialog(null, "Consulta Realizada com Sucesso", "Sucesso!", JOptionPane.PLAIN_MESSAGE);
				break;
			}	
		}	
		if (!compraExiste) {
			JOptionPane.showMessageDialog(null, "ID de compra invalido. Nao possivel encontrar a compra.",
					"Compra nao identificada", JOptionPane.PLAIN_MESSAGE);
		}	
	}

	private ListaEncadeada<Compra> pegarHistorico(ListaEncadeada<Compra> historico) throws Exception {
		File arquivo = new File("C:\\PastaTrabalhoED", "HistoricoCompras.csv");
		if(arquivo.exists() && arquivo.isFile()) {
			FileInputStream fluxo = new FileInputStream(arquivo);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {	
				String[] conteudo = linha.split(";");
				Compra compra = new Compra(Integer.parseInt(conteudo[0]), conteudo[1], conteudo[2], Double.parseDouble(conteudo[3]));
				historico.addLast(compra);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} 
		return historico;
	}

	
	private void vincularCliente(ListaEncadeada<Produto> produtosCarrinho) throws Exception, IOException {
		File arquivoPessoa;
		String nomeCliente = null;
		if(flagPessoa) {// PF ou PJ
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
					nomeCliente = colunasDoCSV[1];
//					inserirClienteCSVCarrinho();
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
			ListaEncadeada<Produto> carrinhoCheio = new ListaEncadeada<>();
			carrinhoCheio = preencherCarrinhoCheio(carrinhoCheio);
			
			//passa cliente
			double total = totalCompras(carrinhoCheio);		
			String totalCompra = String.valueOf(total);
			Checkout CheckoutjFrame = new Checkout(carrinhoCheio,nomeCliente, totalCompra);
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
 
	private double totalCompras(ListaEncadeada<Produto> carrinho) throws Exception {
		double total = 0;
		int size = carrinho.size();
		for(int i = 0; i < size; i ++) {
			Produto p = carrinho.getValue(i);
			total = total + p.valor;
		}
		return total;
	}
	
	private int gerarId() throws Exception {
		ListaEncadeada<Compra> historico = new ListaEncadeada<>();
		historico = pegarHistorico(historico);
		int contador = 0;
		int tamanho = historico.size();
		for(int i = 0; i < tamanho; i++) {
			Compra compra = historico.getValue(i);
			if(contador != compra.id) {
				return contador;
			}
			contador++;
		}
		return contador;
	}
	
	private ListaEncadeada<Produto> preencherCarrinhoCheio(ListaEncadeada<Produto> carrinhoCheio) throws Exception {
		File arquivo = new File("C:\\PastaTrabalhoED", "CarrinhoDeCompras.csv");
		if(arquivo.exists() && arquivo.isFile()) {
			FileInputStream fluxo = new FileInputStream(arquivo);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {	
				String[] conteudo = linha.split(";");
				Produto produto = new Produto(Integer.parseInt(conteudo[0]), conteudo[1], Double.parseDouble(conteudo[2]), null, 0, 0);
				carrinhoCheio.addLast(produto);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {	
			JOptionPane.showMessageDialog(null, "Nao foi possivel encontrar a base de dados do sistema", 
												"Erro na busca do arquivo CSV", JOptionPane.ERROR_MESSAGE);
		}	
		return carrinhoCheio;
	
	}

}
