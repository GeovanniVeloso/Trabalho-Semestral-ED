
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
	private File diretorio = new File("C:\\PastaTrabalhoED");
	private File arquivo = new File("C:\\PastaTrabalhoED", "ClientesPessoaJuridica.csv");

	//construtor
	public ControleClientePJ(JTextField textFieldCnpj) {
		this.textFieldCnpj = textFieldCnpj;
		try {
			verificarBaseDados();
		} catch (Exception e) {
			String msgError = e.getMessage();
			JOptionPane.showMessageDialog(null, msgError);
		}
	}
	
	public ControleClientePJ() {
		super();
		try {
			verificarBaseDados();
		} catch (Exception e) {
			String msgError = e.getMessage();
			JOptionPane.showMessageDialog(null, msgError);
		}
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


		if (diretorio.isDirectory() && diretorio.exists()) {
			CriaOuAcrescentaNoArquivo(arquivo, conteudoObjeto);

		} else {
			diretorio.mkdir();
			CriaOuAcrescentaNoArquivo(arquivo, conteudoObjeto);
		}

	}
	
	//Consulta Cliente Por CNPJ
	public void Consulta() throws IOException {
		
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
		String[] CNPJ = {"12345678910", "12345678901", "98765432100", "13579864201", "24689753110"};
		String[] NomeFantasia = {"Empresa A", "Empresa B", "Empresa C", "Empresa D", "Empresa E"};
		String[] Logradouro = {"Avenida F", "Avenida G", "Avenida H", "Avenida I", "Avenida J"};
		String[] Complemento = {"APT 666", "APT 777", "APT 888", "APT 999", "APT 100"};
		String[] CEP = {"12345678", "87654321", "90909090", "13579975", "24686420"};
		String[] Telefone = {"123456789", "987654321", "101010101", "246846220", "135797531"};
		String[] Email = {"empA@email.com", "empB@email.com", "empC@email.com", "empD@email.com", "empE@email.com"}; 
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < 5; i++) {
			buffer.append(CNPJ[i] + ";" + NomeFantasia[i] + ";" + Logradouro[i] + ";" + (i+6) + ";" + 
						Complemento[i] + ";" + CEP[i] + ";" + Telefone[i] + ";" + Email[i] + "\r\n");
		}
		return buffer.toString();
	}	
}
