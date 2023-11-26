package controller;

import java.awt.event.ActionEvent;import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.Normalizer;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.ListaEncadeada;
import model_main.Produto;
import model_main.TipoProduto;

public class ControleProduto implements ActionListener {

	@SuppressWarnings("unchecked")
	public ListaEncadeada<Produto>[] hashTable = new ListaEncadeada[100];
	public ListaEncadeada<TipoProduto> listaTipoProduto = new ListaEncadeada<TipoProduto>();
	ListaEncadeada<Produto> carrinho = new ListaEncadeada<Produto>();
	private TipoProduto Tp;

	private JTextField textFieldProdId;
	private JTextField textFieldNome;
	private JTextField textFieldValor;
	private JTextArea textFieldDesc;
	private JTextField textFieldQntdEstoque;
	private JTextField textFieldTipo;
	private JTextArea textAreaCheckOut;

	public ControleProduto(JTextField TextFieldProdId, JTextField TextFieldNome, JTextField TextFieldValor,
			JTextArea TextFieldDesc, JTextField TextFieldQntdEstoque, JTextField TextFieldTipo) {
		this.textFieldProdId = TextFieldProdId;
		this.textFieldNome = TextFieldNome;
		this.textFieldValor = TextFieldValor;
		this.textFieldDesc = TextFieldDesc;
		this.textFieldQntdEstoque = TextFieldQntdEstoque;
		this.textFieldTipo = TextFieldTipo;

		int tamanho = hashTable.length;
		for (int i = 0; i < tamanho; i++) {
			hashTable[i] = new ListaEncadeada<Produto>();
		}
	}

	public ControleProduto() {
		super();
		
		int tamanho = hashTable.length;
		for (int i = 0; i < tamanho; i++) {
			hashTable[i] = new ListaEncadeada<Produto>();
		}
	}

	public ControleProduto(JTextField TextFieldProdId, JTextField textFieldTipo) {
		this.textFieldProdId = TextFieldProdId;
		this.textFieldTipo = textFieldTipo;

		int tamanho = hashTable.length;
		for (int i = 0; i < tamanho; i++) {
			hashTable[i] = new ListaEncadeada<Produto>();
		}
	}
	
	public ControleProduto(JTextField TextFieldProdId, JTextField textFieldTipo, ListaEncadeada<Produto>Carrinho) {
		this.textFieldProdId = TextFieldProdId;
		this.textFieldTipo = textFieldTipo;
		int tamanho = hashTable.length;
		for (int i = 0; i < tamanho; i++) {
			hashTable[i] = new ListaEncadeada<Produto>();
		}
	}

	public ControleProduto(JTextField TextFieldProdId) {
		this.textFieldProdId = TextFieldProdId;

		int tamanho = hashTable.length;
		for (int i = 0; i < tamanho; i++) {
			hashTable[i] = new ListaEncadeada<Produto>();
		}
	}

	public ControleProduto(JTextArea TextAreaCheckOut) {
		this.textAreaCheckOut = TextAreaCheckOut;

		int tamanho = hashTable.length;
		for (int i = 0; i < tamanho; i++) {
			hashTable[i] = new ListaEncadeada<Produto>();
		}
	}

	@Override
	// Opções de botão para ativar o sistema.
	public void actionPerformed(ActionEvent e) {
		String botaoSelec = e.getActionCommand();
		try {
			if (botaoSelec.equals("Cadastrar")) {
				cadastrar();
			}
			if (botaoSelec.equals("Consultar")) {
				consultar();
			}
			if (botaoSelec.equals("Excluir")) {
				excluir();
			}
			if (botaoSelec.equals("Adicionar no carrinho")) {
				addcarinho();
			}
			if (botaoSelec.equals("Remover")) {
				removeCarrinho();
			}
			if (botaoSelec.equals("CARRINHO")) {
				checkout();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	// Adiciona o produto ao carrinho caso ele exista e tenha um número em estoque
	// igual ou superior a quantidade solicitada.
	private void addcarinho() throws Exception {
		int id = Integer.parseInt(textFieldProdId.getText());
		String Tipo = normalizarTipoProduto(textFieldTipo.getText());
		Tipo = normalizarTipoProduto(Tipo);
		int hash = hash(Tipo);
		read();
		ListaEncadeada<Produto> lista = hashTable[hash];
		int size = lista.size();
		Produto p;
		boolean teste = false;
		for(int i = 0; i < size; i++) {
			p = lista.getValue(i);
			if (p.prodId == id) {
				teste = true;
				carrinho.addFirst(p);
				JOptionPane.showMessageDialog(null, "TAMANHO: " + carrinho.size());
				i = size;
			} 
		}
		if (teste == false) {
			JOptionPane.showMessageDialog(null, "PRODUTO NAO ENCONTRADO.");
		}
	}

	// Verifica e mostra ao usuário os produtos do carrinho.
	private void checkout() throws Exception {
		String conteudo = null;
		if (!carrinho.isEmpty()) {
			int tamanho = carrinho.size();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < tamanho; i++) {
				Produto p = carrinho.getValue(i);
				buffer.append("#" + p.prodId + " #" + listaTipoProduto.getValue(p.indice) + " - " + p.nome + " - #"
						+ p.valor + " - " + p.qntdEstoque);
				buffer.append("\r\n");
			}
			conteudo = buffer.toString();
			textAreaCheckOut.setText(conteudo);
		} else {
			JOptionPane.showMessageDialog(null, "CARRINHO VAZIO.");
		}
	}

	// Remove os produtos do carrinho e atualiza o estoque.
	private void removeCarrinho() throws Exception {
		int prodId = Integer.parseInt(textFieldProdId.getText());
		int tamanho = carrinho.size();
		Produto p;
		read();
		for (int i = 0; i < tamanho; i++) {
			p = carrinho.getValue(i);
			if (p.prodId == prodId) {
				carrinho.remove(i);
				ListaEncadeada<Produto> lista = hashTable[p.indice];
				int size = lista.size();
				for (int c = 0; i < size; i++) {
					if (lista.getValue(c).prodId == prodId) {
						lista.getValue(c).qntdEstoque += p.qntdEstoque;
						c = size;
					}
				}
				i = tamanho;
			}
		}
		write();
	}

	// Adiciona o produto a hash.
	private void cadastrar() throws Exception {
		String nome = normalizarTipoProduto(textFieldNome.getText());
		String desc = normalizarTipoProduto(textFieldDesc.getText());
		int hash = hash(normalizarTipoProduto(textFieldTipo.getText()));
		Produto p = new Produto(Integer.parseInt(textFieldProdId.getText()), nome,
				Double.parseDouble(textFieldValor.getText()), desc, Integer.parseInt(textFieldQntdEstoque.getText()),
				hash);
		boolean teste = true;
		read();
		ListaEncadeada<Produto> lista = hashTable[hash];
		if (!lista.isEmpty()) {
			teste = validaProduto(p);
		}
		if (teste == true) {
			if (lista.isEmpty()) {
				lista.addFirst(p);
			} else {
				lista.addLast(p);
			}
			JOptionPane.showMessageDialog(null, "PRODUTO CADASTRADO COM SUCESSO");
			write();
		} else {
			JOptionPane.showMessageDialog(null, "CODIGO JA CADASTRADO, POR FAVOR, TENTE NOVAMENTE.");
		}
	}

	// Remove os produtos da hash.
	private void excluir() throws Exception {
		int id = Integer.parseInt(textFieldProdId.getText());
		String Tipo = normalizarTipoProduto(textFieldTipo.getText());
		int hash = hash(Tipo);
		read();
		ListaEncadeada<Produto> lista = hashTable[hash];
		int size = lista.size();
		Produto prod;
		Boolean teste = false;
		for (int i = 0; i < size; i++) {
			prod = lista.getValue(i);
			if (prod.prodId == id) {
				lista.remove(i);
				JOptionPane.showMessageDialog(null, "O PRODUTO " + prod.nome + " FOI EXCLUIDO.");
				teste = true;
				i = size;
			}
		}
		if (teste == false) {
			JOptionPane.showMessageDialog(null, "O PRODUTO NAO FOI ENCONTRADO.");
		} else {
			write();
		}
	}

	// Procura produtos na hash.
	private void consultar() throws Exception {
		int id = Integer.parseInt(textFieldProdId.getText());
		String Tipo = normalizarTipoProduto(textFieldTipo.getText());
		int hash = hash(Tipo);
		read();
		ListaEncadeada<Produto> lista = hashTable[hash];
		int size = lista.size();
		Produto prod;
		Boolean teste = false;
		for (int i = 0; i < size; i++) {
			prod = lista.getValue(i);
			if (prod.prodId == id) {
				JOptionPane.showMessageDialog(null, "ID: " + prod.prodId + "\nNOME: " + prod.nome + "\nVALOR: "
						+ prod.valor + "\nDESC: " + prod.desc + "\nQUANTIDADE EM ESTOQUE: " + prod.qntdEstoque);
				teste = true;
				i = size;
			}
		}
		if (teste == false) {
			JOptionPane.showMessageDialog(null, "O PRODUTO NAO FOI ENCONTRADO.");
		}
	}

	// Salva os dados do produto em um csv.
	private void write() throws Exception {
		File arquivo = new File("C:\\PastaTrabalhoED", "Produto.csv");
		Produto p;
		int size = hashTable.length;
		if (!arquivo.exists() && !arquivo.isFile()) {
			arquivo.createNewFile();
		}
		int c = 0;
		StringBuffer buffer = new StringBuffer();
		while (c < size) {
			ListaEncadeada<Produto> lista = hashTable[c];
			int tamanho = lista.size();
			int i = 0;
			while (i < tamanho) {
				p = lista.getValue(i);
				buffer.append(p.toString() + "\r\n");
				i = i + 1;
			}
			c = c + 1;
		}
		String thing = buffer.toString();
		FileWriter fileWriter = new FileWriter(arquivo);
		PrintWriter print = new PrintWriter(fileWriter);
		print.write(thing);
		print.flush();
		print.close();
		fileWriter.close();
	}

	// Coleta os dados do produto de um csv.
	private void read() throws Exception {
		File dir = new File("C:\\PastaTrabalhoED");
		File arq = new File(dir, "Produto.csv");
		Produto p;
		if (dir.exists() && dir.isDirectory()) {
			if (arq.exists() && arq.isFile()) {
				FileInputStream flux = new FileInputStream(arq);
				InputStreamReader reader = new InputStreamReader(flux);
				BufferedReader buffer = new BufferedReader(reader);
				String linha = buffer.readLine();
				while (linha != null) {
					String[] vet = linha.split(";");
					p = new Produto(Integer.parseInt(vet[0]), vet[1], Double.parseDouble(vet[2]), vet[3],
							Integer.parseInt(vet[4]), Integer.parseInt(vet[5]));
					ListaEncadeada<Produto> lista = hashTable[p.indice];
					if (lista.isEmpty()) {
						lista.addFirst(p);
					} else {
						lista.addLast(p);
					}
					linha = buffer.readLine();
				}
				buffer.close();
				reader.close();
				flux.close();
			}
		}
	}

	// Remove todos os produtos de um tipo específico.
	public void excluiTipo(int hash) throws Exception {
		read();
		ListaEncadeada<Produto> lista = hashTable[hash];

		while (!lista.isEmpty()) {
			lista.removeFirst();
		}
		write();

	}

	// Coleta todos os produtos em um StringBuffer e retorna como um string.
	public String prodTodos(int hash) throws Exception {
		read();
		StringBuffer buffer = new StringBuffer();
		ListaEncadeada<Produto> lista = hashTable[hash];
		int size = lista.size();
		for (int i = 0; i < size; i++) {
			Produto p = lista.getValue(i);
			buffer.append("#" + p.prodId + " NOME:" + p.nome + " R$:" + p.valor + " Descricao: " + p.desc + " ESTOQUE:" +p.qntdEstoque+ "\r\n");
		}
		String conteudo = (buffer.toString() + "\r\n");
		return conteudo;
	}

	// Normaliza Strings para comparação.
	private String normalizarTipoProduto(String texto) {
		if (texto == null) {
			return null;
		}
		String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		String textoSemAcento = pattern.matcher(textoNormalizado).replaceAll("");
		return textoSemAcento.toUpperCase();
	}

	// Procura o tipo de produto e o transforma em um indice int.
	private int hash(String Tipo) throws Exception {
		TipoProdutoController tc = new TipoProdutoController(textFieldTipo, listaTipoProduto);
		tc.verificarBaseDadosTipoProduto();
		int size = listaTipoProduto.size();
		int hash = 0;
		boolean teste = false;
		for (int i = 0; i < size; i++) {
			Tp = listaTipoProduto.getValue(i);
			if (Tp.tipo.equals(Tipo)) {
				hash = i;
				i = size;
				teste = true;
			}
		}
		if (teste != true) {
			JOptionPane.showMessageDialog(null, "TIPO DE PRODUTO NAO ACHADO", "ERRO", 0);
			throw new Exception("ERRO");
		}
		return hash;
	}

	// Verifica se um produto já existe com o mesmo código antes de cadastra - lo.
	private boolean validaProduto(Produto p) throws Exception {
		ListaEncadeada<Produto> lista = hashTable[p.indice];
		int size = lista.size();
		boolean teste = true;
		for (int i = 0; i < size; i++) {
			if (lista.getValue(i).prodId == p.prodId) {
				teste = false;
				i = size;
			}
		}
		return teste;
	}
}
