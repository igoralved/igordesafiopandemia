package igorgroup.desafiopandemia.controller.form;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sun.istack.NotNull;

import igorgroup.desafiopandemia.controller.model.Atendimento;
import igorgroup.desafiopandemia.controller.model.UnidadeSaude;
import igorgroup.desafiopandemia.controller.repository.UnidadeSaudeRepository;

public class UnidadeSaudeForm {

	
	
	@NotNull
	private String nome;
	private Integer numeroPacientes;
	
	private LocalDate data;
	
	private List<Atendimento> atendimentos = new ArrayList<>();
	
	public String getNome() {
		return nome;
	}
	
	public Integer getNumeroPacientes() {
		return numeroPacientes;
	}
	
	public List<Atendimento> getAtendimentos(){
		return atendimentos;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setNumeroPacientes(Integer numeroPacientes) {
		this.numeroPacientes = numeroPacientes;
	}
	
	public String getData() {
		return data.toString();
	}
	
	public LocalDate getLocalDate() {
		return data;
	}
	
	public void setData(int ano, int meses, int dias) {
		this.data = LocalDate.of(ano, meses, dias);
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public void addAtendimento(Atendimento a) {
		atendimentos.add(a);
	}
	
	public UnidadeSaude converter() {
		return new UnidadeSaude();
	}
	
	
	
	public UnidadeSaude atualizar(Long id, UnidadeSaudeRepository usr) {
		List<UnidadeSaude> lista = usr.findAll();
		for(UnidadeSaude u : lista) {
			if(u.getId() == id) {
				u.setNome(this.nome);
				u.setNumeroPacientes(this.numeroPacientes);
				u.setData(this.data);
				u.setAtendimentos(this.atendimentos);
				return u;
			}
		}
		return null;
	}
	
}
