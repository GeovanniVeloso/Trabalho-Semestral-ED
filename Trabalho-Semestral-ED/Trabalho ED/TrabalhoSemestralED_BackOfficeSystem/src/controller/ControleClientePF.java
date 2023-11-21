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

	// Construtor
	public ControleClientePF(JTextField textFieldCPF) {
		this.textFieldCPF = textFieldCPF;
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

		File diretorio = new File("C:\\PastaTrabalhoED");
		File arquivo = new File("C:\\PastaTrabalhoED", "ClientesPessoaFísica.csv");

		if (diretorio.isDirectory() && diretorio.exists()) {
			CriaOuAcrescentaNoArquivo(arquivo, conteudoObjeto);

		} else {
			diretorio.mkdir();
			CriaOuAcrescentaNoArquivo(arquivo, conteudoObjeto);
		}

	}

	private void Consulta() throws IOException {
		File arquivo = new File("C:\\PastaTrabalhoED", "ClientesPessoaFísica.csv");
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
		File arquivo = new File("C:\\PastaTrabalhoED", "ClientesPessoaFísica.csv");

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

}
