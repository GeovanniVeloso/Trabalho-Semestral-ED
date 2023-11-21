
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
import model_main.ClientePessoaJuridica;

public class ControleClientePJ implements ActionListener {

	private JTextField textFieldNomeFantasia;
	private JTextField textFieldTelefone;
	private JTextField textFieldNumero;
	private JTextField textFieldLogradouro;
	private JTextField textFieldCnpj;
	private JTextField textFieldComplemento;
	private JTextField textFieldCEP;
	private JTextField textFieldEmail;

	//construtor
	public ControleClientePJ(JTextField textFieldCnpj) {
		this.textFieldCnpj = textFieldCnpj;
	}

	//construtor
	public ControleClientePJ(JTextField textFieldCnpj, JTextField textFieldNomeFantasia, JTextField textFieldLogradouro,
			JTextField textFieldNumero, JTextField textFieldComplemento, JTextField textFieldCEP,
			JTextField textFieldTelefone, JTextField textFieldEmail) {

		this.textFieldCnpj = textFieldCnpj;
		this.textFieldNomeFantasia = textFieldNomeFantasia;
		this.textFieldLogradouro = textFieldLogradouro;
		this.textFieldNumero = textFieldNumero;
		this.textFieldComplemento = textFieldComplemento;
		this.textFieldCEP = textFieldCEP;
		this.textFieldTelefone = textFieldTelefone;
		this.textFieldEmail = textFieldEmail;
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

	//Cadastra Cliente Por CNPJ
	private void cadastrar() throws IOException {
		ClientePessoaJuridica pessoaJuridica = new ClientePessoaJuridica();

		pessoaJuridica.CNPJ = textFieldCnpj.getText();
		pessoaJuridica.NomeFantasia = textFieldNomeFantasia.getText();
		pessoaJuridica.Logradouro = textFieldLogradouro.getText();
		pessoaJuridica.Numero = Integer.parseInt(textFieldNumero.getText());
		pessoaJuridica.Complemento = textFieldComplemento.getText();
		pessoaJuridica.CEP = textFieldCEP.getText();
		pessoaJuridica.Telefone = textFieldTelefone.getText();
		pessoaJuridica.Email = textFieldEmail.getText();

		//System.out.println(pessoaJuridica);
		String conteudoObjeto = pessoaJuridica.toString();

		File diretorio = new File("C:\\PastaTrabalhoED");
		File arquivo = new File("C:\\PastaTrabalhoED", "ClientesPessoaJuridica.csv");

		if (diretorio.isDirectory() && diretorio.exists()) {
			CriaOuAcrescentaNoArquivo(arquivo, conteudoObjeto);

		} else {
			diretorio.mkdir();
			CriaOuAcrescentaNoArquivo(arquivo, conteudoObjeto);
		}

	}
	
	//Consulta Cliente Por CNPJ
	public void Consulta() throws IOException {
		
		 File arquivo = new File("C:\\PastaTrabalhoED", "ClientesPessoaJuridica.csv");
		 boolean encontrado = false;
		 try (BufferedReader bufferLeitura = new BufferedReader(new FileReader(arquivo))) {
	            String linha = bufferLeitura.readLine();

	            while (linha != null) {// percorre o arquivo
	            	String[] colunasDoCSV = linha.split(";");
	            	if(colunasDoCSV[0].equals(textFieldCnpj.getText())) {
	            		JOptionPane.showMessageDialog(null,"CNPJ: "+colunasDoCSV[0]+
								            			   "\nNome Fantasia: "+colunasDoCSV[1]+
								            			   "\nLogradouro: "+colunasDoCSV[2]+
								            			   "\nNúmero: "+colunasDoCSV[3]+
								            			   "\nComplemento: "+colunasDoCSV[4]+
								            			   "\nCEP: "+colunasDoCSV[5]+
								            			   "\nTelefone: "+colunasDoCSV[6]+
								            			   "\nEmail: "+colunasDoCSV[7],
								            			   "Consulta",JOptionPane.PLAIN_MESSAGE);
	            		encontrado = true;
	            	}

	                linha = bufferLeitura.readLine();
	            }
	        }
		 if (!encontrado) {
	            JOptionPane.showMessageDialog(null, "CNPJ NÃO ENCONTRADO");
	        }
	}

	//Exclui Cliente por CNPJ
	private void excluir() throws Exception {
	    ListaEncadeada<String> linhasParaManter = new ListaEncadeada<>();
	    File arquivo = new File("C:\\PastaTrabalhoED", "ClientesPessoaJuridica.csv");

	    if (arquivo.exists() && arquivo.isFile()) {
	        boolean encontrado = false;
	        try (BufferedReader bufferLeitura = new BufferedReader(new FileReader(arquivo))) {
	            String linha = bufferLeitura.readLine();

	            while (linha != null) {// percorre o arquivo
	                String[] colunasDoCSV = linha.split(";");

	                if (!colunasDoCSV[0].equals(textFieldCnpj.getText())) {
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
	            JOptionPane.showMessageDialog(null, "CNPJ NÃO ENCONTRADO");
	        } else {
	            JOptionPane.showMessageDialog(null, "O CLIENTE DE CNPJ: " + textFieldCnpj.getText() + " FOI EXCLUIDO");
	        }
	    } else {
	        throw new IOException("Arquivo Inválido");
	    }
	}
	
	

	//Função auxiliar Para Cadastro de PJ
	private void CriaOuAcrescentaNoArquivo(File arquivo, String conteudo) throws IOException {

		if (arquivo.exists() && arquivo.isFile()) {

			String content = conteudo;
			FileWriter filewriter = new FileWriter(arquivo, true);// Abre arquivo
			PrintWriter print = new PrintWriter(filewriter);// instancia a classe que escreve o conteúdo
			print.write(content+"\n");// escreve o conteúdo
			print.flush();// termina a escrita
			print.close();
			filewriter.close();

		} else {

			String content = conteudo;
			FileWriter filewriter = new FileWriter(arquivo);// Abre arquivo
			PrintWriter print = new PrintWriter(filewriter);// instancia a classe que escreve o conteúdo
			print.write(content+"\n");// escreve o conteúdo
			print.flush();// termina a escrita
			print.close();
			filewriter.close();

		}

	}
}
