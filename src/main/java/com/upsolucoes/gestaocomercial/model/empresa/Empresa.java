package com.upsolucoes.gestaocomercial.model.empresa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nome;

	private String cnpjouCPF;

	private String telefone;

	private String email;

	private String endereco;

	public Empresa() {

	}

	public Empresa(String nome, String cnpjouCPF, String telefone, String email, String endereco) {
		super();
		this.nome = nome;
		setCnpjouCPF(cnpjouCPF);
		setTelefone(telefone);
		this.email = email;
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpjouCPF() {
		return cnpjouCPF;
	}

	public void setCnpjouCPF(String cnpjouCPF) {
		String digits = cnpjouCPF.replaceAll("\\D", "");

		if (digits.length() == 11) {

			this.cnpjouCPF = String.format("%s.%s.%s-%s", digits.substring(0, 3), digits.substring(3, 6),
					digits.substring(6, 9), digits.substring(9));

		} else if (digits.length() == 14) {

			this.cnpjouCPF = String.format("%s.%s.%s/%s-%s", digits.substring(0, 2), digits.substring(2, 5),
					digits.substring(5, 8), digits.substring(8, 12), digits.substring(12));

		} else {
			this.cnpjouCPF = cnpjouCPF;
		}
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		String digits = telefone.replaceAll("\\D", "");

		if (digits.length() == 11) {
			this.telefone = String.format("(%s) %s-%s", digits.substring(0, 2), digits.substring(2, 7),
					digits.substring(7));
		} else if (digits.length() == 10) {
			this.telefone = String.format("(%s) %s-%s", digits.substring(0, 2), digits.substring(2, 6),
					digits.substring(6));
		} else {
			this.telefone = telefone;
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
