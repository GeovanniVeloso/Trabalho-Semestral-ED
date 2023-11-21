
package model_main;

public class ClientePessoaJuridica {

	public String CNPJ;
	public String NomeFantasia;
	public String Logradouro;
	public int Numero;
	public String Complemento;
	public String CEP;
	public String Telefone;
	public String Email;

	public ClientePessoaJuridica() {
		super();
	}

	public ClientePessoaJuridica(String cnpj, String nomeFantasia, String logradouro, int numero, String complemento,
			String cep, String telefone, String email) {
		this.CNPJ = cnpj;
		this.NomeFantasia = nomeFantasia;
		this.Logradouro = logradouro;
		this.Numero = numero;
		this.Complemento = complemento;
		this.CEP = cep;
		this.Telefone = telefone;
		this.Email = email;
	}

	public String toString() {
		return CNPJ + ";" + NomeFantasia + ";" + Logradouro + ";" + Numero+ ";" + Complemento + ";" + CEP + ";" + Telefone + ";" + Email;
	}
}
