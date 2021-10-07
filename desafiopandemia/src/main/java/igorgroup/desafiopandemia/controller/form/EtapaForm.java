package igorgroup.desafiopandemia.controller.form;

import java.util.List;

import igorgroup.desafiopandemia.controller.model.Etapa;
import igorgroup.desafiopandemia.controller.repository.EtapaRepository;

public class EtapaForm {

	private int numero;
	private String descricao;
	
	public int getNumero() {
		return numero;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setNumero(int numero){
		this.numero = numero;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Etapa converter() {
		return new Etapa();
		//return new Etapa(numero, descricao);
	}
	
	
	
	public Etapa atualizar(Long id, EtapaRepository er) {
		List<Etapa> lista = er.findAll();
		for(Etapa e : lista) {
			if(e.getId() == id) {
				e.setDescricao(this.descricao);
				e.setNumero(this.numero);
				return e;
			}
		}
		return null;
	}
	
}
