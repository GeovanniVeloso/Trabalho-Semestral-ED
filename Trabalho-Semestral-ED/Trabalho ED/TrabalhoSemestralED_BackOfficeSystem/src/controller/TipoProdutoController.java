package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.Normalizer;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.ListaEncadeada;
import model_main.TipoProduto;

public class TipoProdutoController implements ActionListener {
	
	private JTextField tfNomeTipoProduto;
    private JTextArea taDescricaoTipoProduto;
    private JTextArea taResultadoConsultaTipoProdutoEspecifico;
    private JTextArea taResultadoConsultaTodosTiposProduto;
    private ListaEncadeada<TipoProduto> listaTipoProduto;
    private File diretorio = new File("C:\\PastaTrabalhoED");
    private File arquivo = new File(diretorio, "Cadastro_TipoProduto.csv");
	
	//Construtor Tela Excluir Tipo Produto
	public TipoProdutoController(JTextField tfNomeTipoProduto, ListaEncadeada<TipoProduto> listaTipoProduto) {
		this.tfNomeTipoProduto = tfNomeTipoProduto;
		this.listaTipoProduto = listaTipoProduto;
		try {
			verificarBaseDadosTipoProduto();
		} catch (Exception e) {
			String msgError = e.getMessage();
			JOptionPane.showMessageDialog(null, msgError);
		}
	}
	
	//Construtor Tela Cadastro Tipo Produto
	public TipoProdutoController(JTextField tfNomeTipoProduto, JTextArea taDescricaoTipoProduto, ListaEncadeada<TipoProduto> listaTipoProduto) {
		this.tfNomeTipoProduto = tfNomeTipoProduto;
		this.taDescricaoTipoProduto = taDescricaoTipoProduto;
		this.listaTipoProduto = listaTipoProduto;
		try {
			verificarBaseDadosTipoProduto();
		} catch (Exception e) {
			String msgError = e.getMessage();
			JOptionPane.showMessageDialog(null, msgError);
		}
	}

	//Construtor Tela Consulta Especifica Tipo Produto
	public TipoProdutoController(JTextArea taResultadoConsultaTipoProdutoEspecifico, JTextField tfNomeTipoProduto, ListaEncadeada<TipoProduto> listaTipoProduto) {
		this.tfNomeTipoProduto = tfNomeTipoProduto;
		this.taResultadoConsultaTipoProdutoEspecifico = taResultadoConsultaTipoProdutoEspecifico;
		this.listaTipoProduto = listaTipoProduto;
		try {
			verificarBaseDadosTipoProduto();
		} catch (Exception e) {
			String msgError = e.getMessage();
			JOptionPane.showMessageDialog(null, msgError);
		}
	}
	
	//Construtor Tela Consulta Todos Tipos Produto
	public TipoProdutoController(JTextArea taResultadoConsultaTodosTiposProduto, ListaEncadeada<TipoProduto> listaTipoProduto) {
		this.taResultadoConsultaTodosTiposProduto = taResultadoConsultaTodosTiposProduto;
		this.listaTipoProduto = listaTipoProduto;
		try {
			verificarBaseDadosTipoProduto();
		} catch (Exception e) {
			String msgError = e.getMessage();
			JOptionPane.showMessageDialog(null, msgError);
		}
	}
	
	
	//******************************************************************************************************************
	//Metodo utilizado para verificar existencia de base de dados e adiconar dados na lista encadeada de tipo de produto
	//******************************************************************************************************************
	public void verificarBaseDadosTipoProduto() throws Exception  {
		//Verifica se o diretorio existe, se nao existir, cria
		verificarDiretorio();
		//Verifico se o arquivo existe, se nao existir, cria
		verificarArquivo();
		//Preencher a lista de tipo de produto com os dados do cadastro csv
		listaTipoProduto = preencherListaTipoProduto();
		//Verifica se lista foi preenchida com informacoes de cadastro
		if(listaTipoProduto.isEmpty()) {
			//Se lista nao foi preenchida, o sistema encerra
			JOptionPane.showMessageDialog(null, "Lista de tipo de produto nao foi preenchida. O programa sera encerrado.", 
											"Erro no preenchimento da estrutura de dados", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}	//fim if
	}	//fim metodo
	

	//***********************************************************************************************************************************************
	//Metodo que verifica se o diretorio ja existe no computador, caso nao exista ele cria um novo, chamada no metodo verificarBaseDadosTipoProduto()
	//***********************************************************************************************************************************************
	private void verificarDiretorio() {
		//Verifica se diretorio existe
		if (!diretorio.exists() || !diretorio.isDirectory()) {
			diretorio.mkdirs(); // Cria o diretorio
		}	//fim if
	}	//fim metodo
	
	
	//***********************************************************************************************************************************************
	//Metodo que verifica se o arquivo CSV de tipo de produto ja existe no computador, caso nao exista ele cria um novo, chamada no metodo verificarBaseDadosTipoProduto()
	//***********************************************************************************************************************************************
	private void verificarArquivo() throws IOException  {
		// Verifica se arquivo existe
		if(!arquivo.exists() || !arquivo.isFile()) {
			//Gera os tipos de produto e suas descricoes originais
			String conteudo = gerarDadosOriginais();
			//abre o arquivo e define operacao(write ou append)
			FileWriter fileWriter = new FileWriter(arquivo);
			//escreve o conteudo no arquivo
			PrintWriter print = new PrintWriter(fileWriter);
			//abre o arquivo e escreve o conteudo
			print.write(conteudo);
			//finaliza a escrita
			print.flush();
			//fecha o arquivo
			print.close();
			//fecha o arquivo
			fileWriter.close();
		}	//fim if
	}	//fim metodo
	
	
	//******************************************************************************************************************
	//Metodo que gera os tipos de produto e suas descricoes originais, chamada no metodo verificarBaseDadosTipoProduto()
	//******************************************************************************************************************
	private String gerarDadosOriginais() {
		//vetor com tipos de produtos originais
		String[] tipo = {
			    			"PRODUTOS AO CONSUMIDOR", "PRODUTOS INDUSTRIAIS", "BENS DE CONVENIENCIA", "BENS DE IMPULSO",
			    			"BENS DE EMERGENCIA", "BENS DE COMPRA COMPARADA", "BENS DE ESPECIALIDADE", "BENS PERECIVEIS",
			    			"BENS DURAVEIS", "BENS NAO-DURAVEIS", "BENS DE CAPITAL", "PARTES E MATERIAIS", "ABASTECIMENTO E SERVICOS",
			    			"COMMODITIES", "PRODUTOS INTERMEDIARIOS"
						};
		//vetor com a descricao dos tipos de produtos originais
		String[] descricao = {
			    "USADOS POR USUARIOS-FINAIS", "USADOS NA PRODUCAO DE OUTROS BENS", "ADQUIRIDOS FREQUENTEMENTE E COM UM ESFORCO MINIMO",
			    "COMPRA POR ESTIMULOS SENSORIAIS IMEDIATOS", "BENS NECESSARIOS IMEDIATAMENTE", "ALGUMA COMPARACAO COM OUTROS BENS COMO CARROS E TVs. "
			    + "OU SEJA, SAO PRODUTOS QUE EXIGEM UM ALTO ESFORCO DO CONSUMIDOR PARA COMPARAR OS REQUISITOS E FAZER UMA ESCOLHA QUE ATENDA AS SUAS NECESSIDADES",
			    "COMPARACAO EXTENSIVA COM OUTROS BENS DE UMA LONGA BUSCA POR INFORMACOES", "BENS QUE SE DETERIORARAO RAPIDAMENTE MESMO SEM USO",
			    "BENS QUE SOBREVIVEM A OCASIOES DE MULTILO USO", "BENS QUE SERAO CONSUMIDOS EM UMA UNICA OPORTUNIDADE", "INSTALACOES, EQUIPAMENTOS E CONSTRUCOES",
			    "BENS QUE SAO AGREGADOS A UM PRODUTO FINAL", "BENS QUE FACILITAM A PRODUCAO", "BENS INDIFERENCIAVEIS", "RESULTA DA FABRICACAO DE OUTRO PRODUTO"
			};
		//Cria um buffer para armazenar o conteudo
		StringBuffer buffer = new StringBuffer();
		//Itera sobre os vetores para criar linhas no formato csv (tipo;descricao) e adiciona ao buffer
		for(int id = 0; id < 15; id++) {
			buffer.append(id + ";" + tipo[id] + ";" + descricao[id] + "\r\n");
		}	//fim for
		//Converte conteudo do buffer para string e retorna ao metodo verificarBaseDadosTipoProduto()
		return buffer.toString();
	}	//fim metodo
	
	
	//******************************************************************************************************************************
	//Metodo que preenche a lista de tipo de produto com os dados do cadastro csv, chamada no metodo verificarBaseDadosTipoProduto()
	//******************************************************************************************************************************
	private ListaEncadeada<TipoProduto> preencherListaTipoProduto() throws Exception {
		//Verificar se o arquivo existe
		if(arquivo.exists() && arquivo.isFile()) {
			//abre arquivo
			FileInputStream fluxo = new FileInputStream(arquivo);
			//le arquivo
			InputStreamReader leitor = new InputStreamReader(fluxo);
			//converte arquivo
			BufferedReader buffer = new BufferedReader(leitor);
			//coloca o conteudo no buffer
			String linha = buffer.readLine();
			while(linha != null) {	//procura EOF(end of file)
				//separa o conteudo da linha pelo ponto e virgula e coloca em um vetor
				String[] conteudo = linha.split(";");
				//Cria instancia do Tipo Produto
				TipoProduto tipoProduto = new TipoProduto(Integer.parseInt(conteudo[0]), conteudo[1], conteudo[2]);
				//Adiciona a lista
				listaTipoProduto.addLast(tipoProduto);
				//le proxima linha
				linha = buffer.readLine();
			}
			//fecha arquivo
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {	//Caso arquivo nao seja encontrado
			//Informa usuario que nao foi possivel encontrar base de dados do sistema
			JOptionPane.showMessageDialog(null, "Nao foi possivel encontrar a base de dados do sistema", 
												"Erro na busca do arquivo CSV", JOptionPane.ERROR_MESSAGE);
		}	//fim else
		//Retorna lista ao metodo verificarBaseDadosTipoProduto()
		return listaTipoProduto;
	}	//fim metodo


	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando.equals("Cadastrar")) {
			try {
				cadastrarTipoProduto();
			} catch (Exception erroCadastroTipoProduto) {
				String mensagemErroCadastroTipoProduto = erroCadastroTipoProduto.getMessage();
				JOptionPane.showMessageDialog(null, mensagemErroCadastroTipoProduto);
			}
		}
		if(comando.equals("consultar")) {
			try {
				consultarTipoProdutoEspecifico();
			} catch (Exception erroConsultaTipoProdutoEspecifico) {
				String mensagemErroConsultaTipoProdutoEspecifico = erroConsultaTipoProdutoEspecifico.getMessage();
				JOptionPane.showMessageDialog(null, mensagemErroConsultaTipoProdutoEspecifico);
			}
		}
		if(comando.equals("Consultar")) {
			try {
				consultarTodosTiposProduto();
			} catch (Exception erroConsultaTodosTiposProduto) {
				String mensagemErroConsultaTodosTiposProduto = erroConsultaTodosTiposProduto.getMessage();
				JOptionPane.showMessageDialog(null, mensagemErroConsultaTodosTiposProduto);
			}
		}
		if(comando.equals("Excluir")) {
			try {
				excluirTipoProduto();
			} catch (Exception erroExcluirTipoProduto) {
				String mensagemErroExcluirTipoProduto = erroExcluirTipoProduto.getMessage();
				JOptionPane.showMessageDialog(null, mensagemErroExcluirTipoProduto);
			}
		}
	}
	
	
	//***************************************************************
	//Metodo utilizado para cadastrar novo tipo de produto no sistema
	//***************************************************************
	private void cadastrarTipoProduto() throws IOException, Exception  {
		//Cria uma instancia de tipo de produto
		TipoProduto tipoProduto = new TipoProduto();
		//Pega o conteudo de um campo na tela, inserido pelo usuario
		tipoProduto.tipo = tfNomeTipoProduto.getText();
		tipoProduto.descricao = taDescricaoTipoProduto.getText();
		//Normaliza os dados, para nao dar erro no cadastro csv
		tipoProduto.tipo = normalizarTipoProduto(tipoProduto.tipo);
		tipoProduto.descricao = normalizarTipoProduto(tipoProduto.descricao);
		boolean tipoProdutoExiste = verificarTipoProduto(tipoProduto.tipo);
		if(!tipoProdutoExiste) {
			tipoProduto.id = gerarId();
			//Adicona novo tipo de produto a lista e ao cadastro csv
			listaTipoProduto.add(tipoProduto, tipoProduto.id);
			//Informa usuario que novo tipo de produto foi adicionado com sucesso ao sistema
			JOptionPane.showMessageDialog(null, "Cadastro Realizado com Sucesso" + "\n" + tipoProduto.tipo + " foi adicionado como tipo de produto.",
											"Sucesso!", JOptionPane.PLAIN_MESSAGE);
		} else {
			//Informa usuario que o tipo de produto inserido ja existe
			JOptionPane.showMessageDialog(null, "Nao foi possivel realizar o cadastro.\n" + 
										"Pois o tipo de produto " + tipoProduto.tipo + " ja existe na base de dados.", "Falha no cadastro de tipo de produto",
										JOptionPane.PLAIN_MESSAGE);
		}
		//Chamada de metodo para salvar as informacoes no CSV
		salvarTipoProdutoCSV();
	}	//fim metodo
	
	
	//******************************************************************************************************************
	//Metodo utilizado para consultar um tipo de produto especifico, exibindo-se sua descricao
	//******************************************************************************************************************
	private void consultarTipoProdutoEspecifico() throws Exception  {
		ControleProduto opProduto = new ControleProduto();
		//Cria uma instancia de tipo de produto
		TipoProduto tipoProdutoProcurado = new TipoProduto();
		//Pega o conteudo de um campo na tela, inserido pelo usuario
		tipoProdutoProcurado.tipo = tfNomeTipoProduto.getText();
		//Normaliza os dados inseridos pelos usuarios
		tipoProdutoProcurado.tipo = normalizarTipoProduto(tipoProdutoProcurado.tipo);
		//flag que verifica se o tipo de produto procurado foi encontrado
		boolean tipoProdutoExiste = false;
		//Percorre a lista em busca do tipo de produto procurado 
		int tamanho = listaTipoProduto.size();
		for(int i = 0; i < tamanho; i++) {
			//Cria uma instancia e pega o tipo de produto da lista
			TipoProduto tipoProduto = listaTipoProduto.getValue(i);
			//Verifica se e o mesmo tipo de produto procurado
			if(tipoProduto.tipo.equals(tipoProdutoProcurado.tipo)) {
				//Produto encontrado
				tipoProdutoExiste = true;
				//Informa usuario sobre este tipo de produto
				String produto = opProduto.prodTodos(i);
				taResultadoConsultaTipoProdutoEspecifico.setText(tipoProduto.toString() + "\nProduto(s): " + produto);
				JOptionPane.showMessageDialog(null, "Consulta Realizada com Sucesso", "Sucesso!", JOptionPane.PLAIN_MESSAGE);
				break;
			}	//fim if
		}	//fim for
		//Caso nao exista este tipo de produto
		if (!tipoProdutoExiste) {
			//Informa usuario que o produto procurado nao foi encontrado
			JOptionPane.showMessageDialog(null, "Tipo de produto " + tipoProdutoProcurado.tipo + " nao foi encontrado.",
					"Falha na busca de tipo de produto", JOptionPane.PLAIN_MESSAGE);
		}	//fim if
	}	//fim metodo
	
	
	//*****************************************************************************************************
	//Metodo utilizado para exibir ao usuario os tipos de produtos existentes no sistema
	//*****************************************************************************************************
	public void consultarTodosTiposProduto() throws IOException, Exception {
		ControleProduto opProduto = new ControleProduto();
		StringBuffer buffer = new StringBuffer();
		if(listaTipoProduto.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nao ha nenhum tipo de produto na base de dados no momento",
					"Nenhum tipo de produto", JOptionPane.PLAIN_MESSAGE);
		} else {
			//Percorre a lista em busca do tipo de produto procurado 
			int tamanho = listaTipoProduto.size();
			for(int i = 0; i < tamanho; i++) {
				//Cria uma instancia e pega o tipo de produto da lista
				TipoProduto tipoProduto = listaTipoProduto.getValue(i);
				String produto = opProduto.prodTodos(i);
				buffer.append(tipoProduto.toString() + "\nProduto(s): " + produto + "\r\n");
			}	//fim for
			String conteudo = buffer.toString();
			//Informa usuario sobre este tipo de produto
			taResultadoConsultaTodosTiposProduto.setText(conteudo);
			JOptionPane.showMessageDialog(null, "Consulta Realizada com Sucesso", "Sucesso!", JOptionPane.PLAIN_MESSAGE);
		}
	}	//fim metodo
	
	//****************************************************************************************************************************
	//Metodo utilizado para excluir uma categoria de produtos
	//****************************************************************************************************************************
	public void excluirTipoProduto() throws IOException, Exception {
		//Cria uma instancia de tipo de produto
		TipoProduto tipoProdutoProcurado = new TipoProduto();
		//Pega o conteudo de um campo na tela, inserido pelo usuario
		tipoProdutoProcurado.tipo = tfNomeTipoProduto.getText();
		//Normaliza os dados inseridos pelos usuarios
		tipoProdutoProcurado.tipo = normalizarTipoProduto(tipoProdutoProcurado.tipo);
		//flag que verifica se o tipo de produto procurado foi encontrado
		boolean tipoProdutoExiste = false;
		//Percorre a lista em busca do tipo de produto procurado 
		int tamanho = listaTipoProduto.size();
		for(int i = 0; i < tamanho; i++) {
			//Cria uma instancia e pega o tipo de produto da lista
			TipoProduto tipoProdutoExistente = listaTipoProduto.getValue(i);
			//Verifica se e o mesmo tipo de produto procurado
			if(tipoProdutoExistente.tipo.equals(tipoProdutoProcurado.tipo)) {
				//Produto encontrado
				tipoProdutoExiste = true;
				//Informa usuario a remocao deste tipo de produto
				JOptionPane.showMessageDialog(null, "Exclusão Realizada com Sucesso.\n" + 
													tipoProdutoProcurado.tipo + " foi removido do sistema.", 
													"Sucesso!", JOptionPane.PLAIN_MESSAGE);
				//Remove tipo de produto do sistema
				listaTipoProduto.remove(i);
				break;
			}	//fim if
		}	//fim for
		//Caso nao exista este tipo de produto
		if(!tipoProdutoExiste) {
			//Informa usuario que o produto procurado nao foi encontrado
			JOptionPane.showMessageDialog(null, "Tipo de produto " + tipoProdutoProcurado.tipo + " nao foi encontrado.",
											"Falha na busca de tipo de produto", JOptionPane.PLAIN_MESSAGE);
		}	//fim if
		//Chamada de metodo para salvar as informacoes no CSV
		salvarTipoProdutoCSV();
	}	//fim metodo
	
	
	//*************************************************************************************************************
	//Metodo utilizado para verificar se o tipo de produto que esta sendo cadastrado ja nao existe na base de dados
	//*************************************************************************************************************
	private boolean verificarTipoProduto(String novoNomeTipoProduto) throws Exception {
		//Percorre a lista de tipo de produto 
		int tamanho = listaTipoProduto.size();
		for(int i = 0; i < tamanho; i++) {
			//Cria uma instancia e pega o tipo de produto da lista
			TipoProduto tipoProduto = listaTipoProduto.getValue(i);
			//Verifica se e o mesmo tipo de produto
			if(tipoProduto.tipo.equals(novoNomeTipoProduto)) {
				//Produto existe
				return true;
			}	//fim if
		}	//fim for
		return false;
	}
	
	
	//***********************************************************************
	//Metodo utilizado para gerar um novo id para o tipo de produto
	//***********************************************************************
	private int gerarId() throws Exception {
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
	
	
	//*******************************************************************************************************************
	//Metodo que normaliza os dados inseridos pelos usuarios, para nao dar erro no cadastro csv, chamado nos metodos CRUD
	//*******************************************************************************************************************
	private String normalizarTipoProduto(String texto) {
		//Se o texto estiver vazio
		if (texto == null) {
            return null;
        }	//fim if
        // Normaliza para decompor os acentos
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        // Usa uma expressao regular para remover os caracteres acentuados
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        // Retorna string normalizada aos metodos
        String textoSemAcento = pattern.matcher(textoNormalizado).replaceAll("");
        //Converte o texto para maisculo e retorna normalizado aos metodos
        return textoSemAcento.toUpperCase();
	}	//fim if
	
	
	//********************************************************************************************
	//Metodo responsavel por salvar as informacoes geradas e alteradas pelo usuario em arquivo CSV
	//********************************************************************************************
	private void salvarTipoProdutoCSV() throws IOException, Exception {
		//Verifico se diretorio existe
		if(diretorio.exists() && diretorio.isDirectory()) {
			//Cria um buffer para armazenar o conteudo
			StringBuffer buffer = new StringBuffer();
			//Itera sobre a lista de tipo de produtos
			int tamanho = listaTipoProduto.size();
			for(int i = 0; i < tamanho; i++) {
				//Cria uma instancia e pega o tipo de produto da lista
				TipoProduto tipoProduto = listaTipoProduto.getValue(i);
				//Cria linhas no formato csv (tipo;descricao) e adiciona ao buffer
				buffer.append(tipoProduto.id + ";" + tipoProduto.tipo + ";" + tipoProduto.descricao + "\r\n");
			}	//fim for
			//Converte conteudo do buffer para string
			String conteudo = buffer.toString();
			//abre o arquivo e define operacao(write ou append)
			FileWriter fileWriter = new FileWriter(arquivo, false);
			//escreve o conteudo no arquivo
			PrintWriter print = new PrintWriter(fileWriter);
			//abre o arquivo e escreve o conteudo
			print.write(conteudo);
			//finaliza a escrita
			print.flush();
			//fecha o arquivo
			print.close();
			//fecha o arquivo
			fileWriter.close();
		} else {
			//Informa usuario que nao foi possivel encontrar base de dados do sistema
			JOptionPane.showMessageDialog(null, "Nao foi possivel encontrar a base de dados do sistema", 
												"Erro na busca de diretorio", JOptionPane.ERROR_MESSAGE);
		}	//fim else
	}	//fim metodo               	
     
}
