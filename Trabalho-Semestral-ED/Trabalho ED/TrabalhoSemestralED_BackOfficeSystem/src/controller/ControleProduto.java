package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.Normalizer;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fatec.zl.stack.Stack;
import br.edu.fateczl.fila.Fila;
import model.ListaEncadeada;
import model_main.ClientePessoaFisica;
import model_main.Compra;
import model_main.Produto;
import model_main.TipoProduto;

public class ControleProduto implements ActionListener {

	private static final String Finally = null;
	@SuppressWarnings("unchecked")
	public ListaEncadeada<Produto>[] hashTable = new ListaEncadeada[100];
	public ListaEncadeada<TipoProduto> listaTipoProduto = new ListaEncadeada<TipoProduto>();
	ListaEncadeada<Produto> carrinho = new ListaEncadeada<Produto>();
	private TipoProduto Tp;
	Fila<Produto> fila = new Fila<Produto>();

	private JTextField textFieldProdId;
	private JTextField textFieldNome;
	private JTextField textFieldValor;
	private JTextArea textFieldDesc;
	private JTextField textFieldQntdEstoque;
	private JTextField textFieldTipo;
	private JTextArea textAreaCarrinho;
	private JTextField textFieldIndiceProduto;

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
		try {
			verificarBaseDados();
			read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ControleProduto() {
		super();

		int tamanho = hashTable.length;
		for (int i = 0; i < tamanho; i++) {
			hashTable[i] = new ListaEncadeada<Produto>();
		}
		try {
			verificarBaseDados();
			read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ControleProduto(JTextField TextFieldProdId, JTextField TextFieldTipo, JTextField TextFieldIndiceProduto) {
		this.textFieldProdId = TextFieldProdId;
		this.textFieldTipo = TextFieldTipo;
		this.textFieldIndiceProduto = TextFieldIndiceProduto;
 
		int tamanho = hashTable.length;
		for (int i = 0; i < tamanho; i++) {
			hashTable[i] = new ListaEncadeada<Produto>();
		}
		try {
			verificarBaseDados();
			read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ControleProduto(JTextField TextFieldProdId, JTextField textFieldTipo, ListaEncadeada<Produto> Carrinho) {
		this.textFieldProdId = TextFieldProdId;
		this.textFieldTipo = textFieldTipo;
		int tamanho = hashTable.length;
		for (int i = 0; i < tamanho; i++) {
			hashTable[i] = new ListaEncadeada<Produto>();
		}
		try {
			verificarBaseDados();
			read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ControleProduto(JTextField TextFieldProdId) {
		this.textFieldProdId = TextFieldProdId;

		int tamanho = hashTable.length;
		for (int i = 0; i < tamanho; i++) {
			hashTable[i] = new ListaEncadeada<Produto>();
		}
		try {
			verificarBaseDados();
			read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ControleProduto(JTextArea textAreaCarrinho) {
		this.textAreaCarrinho = textAreaCarrinho;
		int tamanho = hashTable.length;
		for (int i = 0; i < tamanho; i++) {
			hashTable[i] = new ListaEncadeada<Produto>();
		}
		try {
			verificarBaseDados();
			read();
		} catch (Exception e) {
			e.printStackTrace();
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
 
			/* Operacoes de carrinho */
			if (botaoSelec.equals("Adicionar no carrinho")) {
				addcarinho();
 
			}
			if (botaoSelec.equals("Remover")) {
				removeCarrinho();
			}
			if (botaoSelec.equals("CARRINHO")) {
//				Fila = checkout();
//				return Lista;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
 
	}
 
	private void criarCSV(ListaEncadeada<Produto> carrinho) {
		try {
			File dir = new File("C:\\PastaTrabalhoED");
			File arquivo = new File(dir , "CarrinhoDeCompras.csv");
			if (dir.exists() && dir.isDirectory()) {// existe e é um diretório?
				Produto p;
				StringBuffer buffer = new StringBuffer();
				int tamanho = carrinho.size() - 1;
				while (tamanho >= 0) {
					p = carrinho.getValue(tamanho);
					buffer.append(p.toString() + "\r\n");
					tamanho -= 1;
				}
				String thing = buffer.toString();
				FileWriter fileWriter = new FileWriter(arquivo);
				PrintWriter print = new PrintWriter(fileWriter);
				print.write(thing);
				print.flush();
				print.close();
				fileWriter.close();
			} else {
				throw new IOException("Diretório Inválido");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Abre arquivo
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	// Adiciona o produto ao carrinho caso ele exista e tenha um número em estoque
	// igual ou superior a quantidade solicitada.
	private void addcarinho() throws Exception {
		while(!carrinho.isEmpty()) {
			carrinho.removeFirst();
		}
		readCarrinho();
		int id = Integer.parseInt(textFieldProdId.getText());
		int hash = hash(normalizarTipoProduto(textFieldTipo.getText()));
		ListaEncadeada<Produto> lista = hashTable[hash];
		int size = lista.size();
		Produto p;
		boolean teste = false;
		for (int i = 0; i < size; i++) {
			p = lista.getValue(i);
			if (p.prodId == id) {
				if (lista.getValue(i).qntdEstoque >= 1) {
					carrinho.addFirst(p);
					int novoEstoque = lista.getValue(i).qntdEstoque - 1;
					lista.getValue(i).setQntdEstoque(novoEstoque);
					teste = true;
				} else {
					JOptionPane.showMessageDialog(null, "NÃO HA QUANTIDADE SUFICENTE EM ESTOQUE.");
				}
			}
		}
		if (teste == false) {
			JOptionPane.showMessageDialog(null, "PRODUTO NÃO ENCONTRADO.");
		} else {
			write();
			criarCSV(carrinho);
			JOptionPane.showMessageDialog(null, "Adicionado no carrinho com Sucesso", "Sucesso!",
					JOptionPane.PLAIN_MESSAGE);
		}
	}

	private void readCarrinho() throws IOException {
		File dir = new File("C:\\PastaTrabalhoED");
		File arq = new File(dir, "CarrinhoDeCompras.csv");
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
							Integer.parseInt(vet[4]), 0);
					carrinho.addFirst(p);
					linha = buffer.readLine();
				}
				buffer.close();
				reader.close();
				flux.close();
			}
		}
	}


	// Verifica e mostra ao usuário os produtos do carrinho.
	private Fila<Produto> checkout() throws Exception {
		int size = carrinho.size();
		for (int i = 0; i < size; i++) {
			fila.insert(carrinho.getValue(i));
		}
		return fila;
	}

	private void removeCarrinho() throws Exception {
		int id = Integer.parseInt(textFieldIndiceProduto.getText());
		File dir = new File("C:\\PastaTrabalhoED");
		File arq = new File(dir, "CarrinhoDeCompras.csv");
		ListaEncadeada<Produto> Carrinho = new ListaEncadeada<Produto>();
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
							Integer.parseInt(vet[4]), 0);
					Carrinho.addFirst(p);
					linha = buffer.readLine();
				}
				buffer.close();
				reader.close();
				flux.close();
			}
		}
		int size = Carrinho.size();
		for (int i = 0; i < size; i++) {
			if (Carrinho.getValue(i).prodId == id) {
				Carrinho.remove(i);
				i = size;
			}
		}
		criarCSV(Carrinho);
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
		read();
		int id = Integer.parseInt(textFieldProdId.getText());
		String Tipo = normalizarTipoProduto(textFieldTipo.getText());
		int hash = hash(Tipo);
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
				buffer.append(p.toString() + ";" + p.indice + "\r\n");
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
		int size = hashTable.length;
		for(int i = 0; i < size; i ++) {
			ListaEncadeada<Produto>erase = hashTable[i];
			if(!erase.isEmpty()) {
				while(!erase.isEmpty()) {
					erase.removeFirst();
				}
			}
		}
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
	
	public void destruirCarrinho() throws Exception {
		File dir = new File("C:\\PastaTrabalhoED");
		File arquivo = new File(dir , "CarrinhoDeCompras.csv");
		write();
		try {
			arquivo.delete();
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Nao foi possivel destruir o carrinho de compras.", 
					"Erro ao destruir carrinho", JOptionPane.ERROR_MESSAGE);
		} 
	}

	public void verificarBaseDados() throws Exception {
		File diretorio = new File("C:\\PastaTrabalhoED");
		File arquivo = new File(diretorio, "Produto.csv");
		if (!diretorio.exists() || !diretorio.isDirectory()) {
			diretorio.mkdirs(); 
		}
		if(!arquivo.exists() || !arquivo.isFile()) {
			String conteudo = gerarDadosOriginais();
			FileWriter fileWriter = new FileWriter(arquivo);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(conteudo);
			print.flush();
			print.close();
			fileWriter.close();
		}			
	}
	
	private String gerarDadosOriginais() {
		String[] Nome = {
			    "SMARTPHONE", "TABLET", "LIVRO DE FICCAO", "NOTEBOOK", "PERFUME MALBEC",
			    "MAQUINA DE IMPRESSAO", "EQUIPAMENTO DE SOLDAGEM", "MOTOR ELETRICO", "TORNO MECANICO",
			    "BOMBA HIDRAULICA", "SABONETE", "PILHA", "REFRIGERANTE", "HOT POCKET",
			    "DETERGENTE", "CHOCOLATE", "QUADROS", "PULSEIRA", "BRINQUEDO", "FONE DE OUVIDO",
			    "KIT DE PRIMEIRO SOCORROS", "EXTINTORES DE INCENDIO", "LANTERNA", "ALIMENTO ENLATADO",
			    "COBERTOR TERMICO", "SMART TV", "CARRO", "MAQUINA DE LAVAR ROUPA", "COMPUTADOR",
			    "CAMERA FOTOGRAFICA", "PIANO", "EQUIPAMENTO DE MERGULHO", "JOIAS", "VINHOS", "JARROS",
			    "FRUTA", "LEITE", "CARNE", "PAO", "BOLO", "GELADEIRA", "MAQUINA DE LAVAR LOUCA",
			    "MOVEIS DE MADEIRA", "PARAFUSADEIRA", "BICICLETA", "PAPEL HIGIENICO", "GUARDANAPO",
			    "LAMPADA", "ISQUEIRO", "SACOLA", "EQUIPAMENTO DE CONSTRUCAO PESADA", "SERVIDORES",
			    "PLACAS FOTOVOLTAICAS", "EQUIPAMENTO DE FABRICACAO DE SEMI-CONDUTORES", "TICS",
			    "CAMBIO DE CARRO", "CHAPA DE ACO", "CIRCUITO INTEGRADO", "TECIDO DE FABRICACAO DE ROUPA",
			    "PARAFUSO", "CONSULTORIA EMPRESARIAL", "SUPPLY CHAIN", "CONTABILIDADE EMPRESARIAL",
			    "LOGISTICA", "TREINAMENTO CORPORATIVO", "PETROLEO", "MINERIO DE FERRO", "MADEIRA",
			    "TRIGO", "GAS NATURAL", "PECAS PLASTICAS", "COMPONTENTES ELETRONICOS", "LINGOTES",
			    "PRODUTOS QUIMICOS", "CELULOSE"
			};
		double[] Valor = {
	            2000.00, 1000.00, 30.00, 3000.00, 150.00, 50000.00, 10000.00, 2500.00, 20000.00, 8000.00,
	            3.00, 2.00, 5.00, 4.00, 1.00, 15.00, 20.00, 10.00, 40.00, 50.00, 25.00, 50.00, 15.00,
	            3.00, 10.00, 2500.00, 50000.00, 1200.00, 3000.00, 5000.00, 3500.00, 800.00, 200.00, 150.00,
	            600.00, 2.50, 4.00, 30.00, 3.00, 10.00, 2000.00, 1500.00, 800.00, 150.00, 1000.00, 3.00,
	            1.50, 5.00, 1.00, 0.50, 100000.00, 30000.00, 50000.00, 50000.00, 20000.00, 250.00, 5.00,
	            50.00, 8.00, 1.00, 50000.00, 20000.00, 3000.00, 8000.00, 2500.00, 80.00, 150.00, 20.00,
	            4.00, 60.00, 2.00, 5.00, 10.00, 8.00, 16.00
	        };
		String[] Descricao = {
			    "CELULARES", "IPADS", "LIVROS SOBRE FICCAO CIENTIFICA", "NOTEBOOK GAMER", "MALBEC GOLD",
			    "IMPRESSAO DE ROTULOS", "SOLDAGEM MIG", "MOTOR DE ALTA EFICIENCIA ENERGETICA", "PARA PRODUCAO INDUSTRIAL",
			    "ALTO FLUXO DE BOMBAMENTO", "MARCA REXONA", "PILHA DURACELL", "BEBIDAS PEPSICO", "HAMBURGUERS CONGELADOS",
			    "DETERGENTE NEUTRO", "CACAU-SHOW", "QUADROS GEEKS", "PULSEIRAS DA AMIZADE", "BRINQUEDOS INFANTIS",
			    "FONE DE OUVIDO BLUETOOTH", "BANDEIDES E ATADURAS", "PARA CONTROLE DE FOGO", "UTILIZA BATERIAS",
			    "ALTAS CALORIAS", "PARA CASOS DE EXTREMO FRIO", "ALEXA INTEGRADA", "FORD KA", "SECADORA INCLUSA",
			    "EXCELENTE PARA ESCRITORIO", "ALTA RESOLUCAO", "PARA PROFISSIONAIS", "SNORKEL", "OURO E PRATA",
			    "CHILENOS E FRANCESES", "FEITOS DE BARRO", "FRUTAS FRESCAS", "LEITE DESNATADO", "CARNE BOVINA",
			    "PAO FRANCES", "BOLO DE CHOCOLATE", "BRASTEMP", "SECADORA INCLUSA", "MADEIRA MACICA",
			    "VARIOS TIPOS DE PARAFUSO", "BICICLETA COM MARCHA", "FOLHA DUPLA", "GUARDANAPO DE PAPEL",
			    "LAMPADA INCADESCENTE", "ISQUEIRO BIC", "SACOLA DE PLASTICO", "PARA CONSTRUCAO DE GRANDES OBRAS",
			    "SERVIDORES DE NAS", "PAINEL SOLAR", "SILICIO E GERMANIO", "TECNOLOGIAS DA INFORMACAO E DA COMUNICACAO",
			    "CAMBIO MANUAL", "5 MM DE ESPESSURA", "CIS PARA PLACAS DE MICROELETRONICOS", "TECIDO DE LA",
			    "PARAFUSO INDUSTRIAIS", "SERVICOS DE CONSULTORIA EMPRESARIAL", "SISTEMA DE CADEIA DE SUPRIMENTOS",
			    "SISTEMA DE CONTABILIDADE EMPRESARIAL", "SERVICOS DE LOGISTICA", "SERVICOS DE TREINAMENTO CORPORATIVO",
			    "BARRIL DE PETROLEO BRUTO", "BAUXITA", "EUCALIPTO", "PARA CEVADA", "UTILIZADO EM TERMO-ELETRICAS",
			    "FEITAS POR INJECAO", "PARA GADGETS", "PECAS METALICAS", "PARA PRODUCAO INDUSTRIAL", "PAPEL"
			};
		StringBuffer buffer = new StringBuffer();
		for(int indice = 0; indice < 15; indice++) {
			for(int id = 0; id < 5; id++) {
				buffer.append(id + ";" + Nome[(indice*5+id)] + ";" + Valor[(indice*5+id)] + ";" + 
							Descricao[(indice*5+id)] + ";" + "10" + ";" + indice + "\r\n");
			}
		}
		return buffer.toString();
	}

	// Remove todos os produtos de um tipo específico.
	public void excluiTipo(int hash) throws Exception {
		ListaEncadeada<Produto> lista = hashTable[hash];
		while (!lista.isEmpty()) {
			lista.removeFirst();
		}
		write();

	}

	// Coleta todos os produtos em um StringBuffer e retorna como um string.
	public String prodTodos(int hash) {
		StringBuffer buffer = new StringBuffer();
		ListaEncadeada<Produto> lista = hashTable[hash];
		String conteudo = "";
		// if (hash == 2) {
		// System.out.println("Tipo 2.");
		// } else {
		if (!lista.isEmpty()) {
			int size = lista.size();
			for (int i = 0; i < size; i++) {
				Produto p;
				try {
					p = lista.getValue(i);
					buffer.append("#" + p.prodId + " NOME:" + p.nome + " R$:" + p.valor + " Descricao: " + p.desc
							+ " ESTOQUE:" + p.qntdEstoque + "\r\n");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			conteudo = (buffer.toString() + "\r\n");
		}
		// }
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
	
	//***************************************************************************************************
	//Método que exibe o carrinho de compras na tela
	//***************************************************************************************************
	public void exibirCarrinho() throws Exception {
		Stack<Produto> carrinho = new Stack<Produto>();
		StringBuffer buffer = new StringBuffer();
		carrinho = lerCSV(carrinho);
		if(carrinho.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O carrinho de compras esta vazio.", "Carrinho vazio",
											JOptionPane.PLAIN_MESSAGE);
		} else {
//			carrinho = inverterPilha(carrinho);
			while(!carrinho.isEmpty()) {
				Produto produto = carrinho.pop();
				buffer.append("#" + produto.prodId + "\t" + produto.nome + "\t$" + produto.valor + "\r\n");
			}
			String conteudo = buffer.toString();
			textAreaCarrinho.setText(conteudo);
		}
	}
	

	//***************************************************************************************************
	//Método que lê o arquivo CSV e transfere o conteúdo para uma pilha
	//***************************************************************************************************
	private Stack lerCSV(Stack<Produto> carrinho) throws Exception {
		File arquivo = new File("C:\\PastaTrabalhoED", "CarrinhoDeCompras.csv");
		if(arquivo.exists() && arquivo.isFile()) {
			FileInputStream fluxo = new FileInputStream(arquivo);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {	
				String[] conteudo = linha.split(";");
				Produto produto = new Produto(Integer.parseInt(conteudo[0]), conteudo[1], Double.parseDouble(conteudo[2]), null, 0, 0);
				carrinho.push(produto);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {	
			JOptionPane.showMessageDialog(null, "Nao foi possivel encontrar a base de dados do sistema", 
												"Erro na busca do arquivo CSV", JOptionPane.ERROR_MESSAGE);
		}	
		return carrinho;
	}
	
	//***************************************************************************************************
	//Método que inverte a pilha carrinho para exibição em ordem
	//***************************************************************************************************
	private Stack inverterPilha(Stack<Produto> carrinho) throws Exception {
		Stack<Produto> pilhaAuxiliar = new Stack<Produto>();
		while(!carrinho.isEmpty()) {
			pilhaAuxiliar.push(carrinho.pop());
		}
		return pilhaAuxiliar;
	}
}