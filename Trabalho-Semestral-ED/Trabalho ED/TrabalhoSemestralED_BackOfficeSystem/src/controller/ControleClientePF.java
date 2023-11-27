package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.ListaEncadeada;
import model_main.ClientePessoaFisica;

public class ControleClientePF implements ActionListener {

	private JTextField textFieldNumero;
	private JTextField textFieldNome;
	private JTextField textFieldCelular;
	private JTextField textFieldLogradouroCPF;
	private JTextField textFieldCPF;
	private JTextField textFieldComplementoCPF;
	private JTextField textFieldCEP;
	private File diretorio = new File("C:\\PastaTrabalhoED");
	private File arquivo = new File("C:\\PastaTrabalhoED", "ClientesPessoaFísica.csv");
	
	public ControleClientePF() {
		super();
		try {
			verificarBaseDados();
		} catch (Exception e) {
			String msgError = e.getMessage();
			JOptionPane.showMessageDialog(null, msgError);
		}
	}

	// Construtor
	public ControleClientePF(JTextField textFieldCPF) {
		this.textFieldCPF = textFieldCPF;
		try {
			verificarBaseDados();
		} catch (Exception e) {
			String msgError = e.getMessage();
			JOptionPane.showMessageDialog(null, msgError);
		}
	}
	
	// Contrutor
	public ControleClientePF(JTextField textFieldCPF, JTextField textFieldNome, JTextField textFieldCelular,
			JTextField textFieldLogradouroCPF, JTextField textFieldNumero, JTextField textFieldComplementoCPF,
			JTextField textFieldCEP) {

		this.textFieldCPF = textFieldCPF;
		this.textFieldNome = textFieldNome;
		this.textFieldCelular = textFieldCelular;
		this.textFieldLogradouroCPF = textFieldLogradouroCPF;
		this.textFieldNumero = textFieldNumero;
		this.textFieldComplementoCPF = textFieldComplementoCPF;
		this.textFieldCEP = textFieldCEP;
		try {
			verificarBaseDados();
		} catch (Exception e) {
			String msgError = e.getMessage();
			JOptionPane.showMessageDialog(null, msgError);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String botaoSelecionadoNaMain = e.getActionCommand();

		try {

			if (botaoSelecionadoNaMain.equals("Cadastrar")) {
				cadastrar();
			}

			if (botaoSelecionadoNaMain.equals("Excluir")) {
				excluir();
			}
			if (botaoSelecionadoNaMain.equals("Consultar")) {
				Consulta();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}

	private void cadastrar() throws IOException {
		ClientePessoaFisica pessoaFisica = new ClientePessoaFisica();

		pessoaFisica.CPF = textFieldCPF.getText();
		pessoaFisica.Nome = textFieldNome.getText();
		pessoaFisica.Logradouro = textFieldLogradouroCPF.getText();
		pessoaFisica.Numero = Integer.parseInt(textFieldNumero.getText());
		pessoaFisica.Complemento = textFieldComplementoCPF.getText();
		pessoaFisica.CEP = textFieldCEP.getText();
		pessoaFisica.Celular = textFieldCelular.getText();

		// System.out.println(pessoaFisica);
		String conteudoObjeto = pessoaFisica.toString();


		if (diretorio.isDirectory() && diretorio.exists()) {
			CriaOuAcrescentaNoArquivo(arquivo, conteudoObjeto);

		} else {
			diretorio.mkdir();
			CriaOuAcrescentaNoArquivo(arquivo, conteudoObjeto);
		}

	}

	private void Consulta() throws IOException {
		boolean encontrado = false;
		try (BufferedReader bufferLeitura = new BufferedReader(new FileReader(arquivo))) {
			String linha = bufferLeitura.readLine();

			while (linha != null) {// percorre o arquivo
				String[] colunasDoCSV = linha.split(";");
				if (colunasDoCSV[0].equals(textFieldCPF.getText())) {
					JOptionPane.showMessageDialog(null,
							"CPF: " + colunasDoCSV[0] + "\nNome: " + colunasDoCSV[1] + "\nLogradouro: "
									+ colunasDoCSV[2] + "\nNúmero: " + colunasDoCSV[3] + "\nComplemento: "
									+ colunasDoCSV[4] + "\nCEP: " + colunasDoCSV[5] + "\nCelular: " + colunasDoCSV[6],
							"Consulta", JOptionPane.PLAIN_MESSAGE);
					encontrado = true;
				}

				linha = bufferLeitura.readLine();
			}
		}
		if (!encontrado) {
			JOptionPane.showMessageDialog(null, "CPF NÃO ENCONTRADO");
		}

	}

	private void excluir() throws Exception {
		ListaEncadeada<String> linhasParaManter = new ListaEncadeada<>();

		if (arquivo.exists() && arquivo.isFile()) {
			boolean encontrado = false;
			try (BufferedReader bufferLeitura = new BufferedReader(new FileReader(arquivo))) {
				String linha = bufferLeitura.readLine();

				while (linha != null) {// percorre o arquivo
					String[] colunasDoCSV = linha.split(";");

					if (!colunasDoCSV[0].equals(textFieldCPF.getText())) {
						linhasParaManter.addLast(linha);
					} else {
						encontrado = true;
					}

					linha = bufferLeitura.readLine();
				}
			}

			try (BufferedWriter bufferEscrita = new BufferedWriter(new FileWriter(arquivo, false))) {
				for (int ctd = 0; ctd < linhasParaManter.size(); ctd++) {
					String lane = linhasParaManter.getValue(ctd);
					bufferEscrita.write(lane);
					bufferEscrita.newLine();
				}
			}

			if (!encontrado) {
				JOptionPane.showMessageDialog(null, "CPF NÃO ENCONTRADO");
			} else {
				JOptionPane.showMessageDialog(null, "O CLIENTE DE CPF: " + textFieldCPF.getText() + " FOI EXCLUIDO");
			}
		} else {
			throw new IOException("Arquivo Inválido");
		}

	}

	// Função auxiliar Para Cadastro de PF
	private void CriaOuAcrescentaNoArquivo(File arquivo, String conteudo) throws IOException {

		if (arquivo.exists() && arquivo.isFile()) {

			String content = conteudo;
			FileWriter filewriter = new FileWriter(arquivo, true);// Abre arquivo
			PrintWriter print = new PrintWriter(filewriter);// instancia a classe que escreve o conteúdo
			print.write(content + "\n");// escreve o conteúdo
			print.flush();// termina a escrita
			print.close();
			filewriter.close();

		} else {

			String content = conteudo;
			FileWriter filewriter = new FileWriter(arquivo);// Abre arquivo
			PrintWriter print = new PrintWriter(filewriter);// instancia a classe que escreve o conteúdo
			print.write(content + "\n");// escreve o conteúdo
			print.flush();// termina a escrita
			print.close();
			filewriter.close();

		}

	}
	
	public void verificarBaseDados() throws Exception {
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
		String[] CPF = {"12345678910", "12345678901", "98765432100", "13579864201", "24689753110"};
		String[] Nome = {"JOAO", "MARIA", "JOSE", "RICARDO", "ANA"};
		String[] Logradouro = {"RUA A", "RUA B", "RUA C", "RUA D", "RUA E"};
		String[] Complemento = {"APT 101", "APT 202", "APT 303", "APT 404", "APT 505"};
		String[] CEP = {"12345678", "87654321", "90909090", "13579975", "24686420"};
		String[] Celular = {"123456789", "987654321", "101010101", "246846220", "135797531"};
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < 5; i++) {
			buffer.append(CPF[i] + ";" + Nome[i] + ";" + Logradouro[i] + ";" + (i+1) + ";" + 
						Complemento[i] + ";" + CEP[i] + ";" + Celular[i] + "\r\n");
		}
		return buffer.toString();
	}	
	
}


