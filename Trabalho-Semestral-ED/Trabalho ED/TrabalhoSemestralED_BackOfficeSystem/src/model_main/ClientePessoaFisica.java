package model_main;

public class ClientePessoaFisica {

	public String CPF;
	public String Nome;
	public String Logradouro;
	public int Numero;
	public String Complemento;
	public String CEP;
	public String Celular;

	public ClientePessoaFisica() {
		super();
	}

	public ClientePessoaFisica(String cpf, String nome, String logradouro, int numero, String complemento, String cep, String celular) {
		this.CPF = cpf;
		this.Nome = nome;
		this.Logradouro = logradouro;
		this.Numero = numero;
		this.Complemento = complemento;
		this.CEP = cep;
		this.Celular = celular;
	}
	
	public String toString() {
		return CPF + ";" + Nome+";" + Logradouro + ";" + Numero + ";" + Complemento + ";" + CEP + ";" + Celular;
	}
}
