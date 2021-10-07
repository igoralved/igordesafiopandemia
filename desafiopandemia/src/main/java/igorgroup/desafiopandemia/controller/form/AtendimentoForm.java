package igorgroup.desafiopandemia.controller.form;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sun.istack.NotNull;

import igorgroup.desafiopandemia.controller.model.Atendimento;
import igorgroup.desafiopandemia.controller.repository.AtendimentoRepository;
import igorgroup.desafiopandemia.controller.model.Etapa;
import igorgroup.desafiopandemia.controller.model.Teste;

public class AtendimentoForm {

	
	@NotNull
	private String descricao;
	private boolean relacionadoComPandemia = false;
	private boolean semPossibilidadeContagio = false;
	private LocalDate data = LocalDate.now();
	private Integer tempoAtendimento;
	
	private List<Etapa> etapas = new ArrayList<>();
	
	private List<Teste> testes = new ArrayList<>();
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public boolean eRealcionadoComPandemia() {
		return this.relacionadoComPandemia;
	}
	
	public boolean descartouPossibilidadeContagio() {
		return this.semPossibilidadeContagio;
	}
	
	public LocalDate getLocalDate() {
		return this.data;
	}
	
	public String getData() {
		return this.data.toString();
	}
	
	public Integer getTempoAtendimento() {
		return tempoAtendimento;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void relacionaComPandemia() {
		this.relacionadoComPandemia = true;
	}
	
	public void desRelacionaComPandemia() {
		this.relacionadoComPandemia = false;
	}
	
	public void descartaPossibilidadeContagio() {
		this.semPossibilidadeContagio = true;
	}
	
	public void colocaPossibilidadeContagio() {
		this.semPossibilidadeContagio = false;
	}
	
	public void setData(int anos, int meses, int dias) {
		data = LocalDate.of(anos, meses, dias);
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public void setTempoAtendimento(Integer tempo) {
		this.tempoAtendimento = tempo;
	}
	
	public void addEtapa(Etapa e) {
		etapas.add(e);
	}
	
	public void addTeste(Teste t) {
		if(testes.size() < 2) {
			testes.add(t);
		}
	}
	
	public Atendimento converter() {
		// new Atendimento(descricao, relacionadoComPandemia, semPossibilidadeContagio, tempoAtendimento);
		return new Atendimento();
	}
	
	
	
	public Atendimento atualizar(Long id, AtendimentoRepository asr) {
		List<Atendimento> a = asr.findAll();
		for(Atendimento aaux : a) {
			if(aaux.getId() == id) {
				aaux.descartaOuNaoDescarta(semPossibilidadeContagio);
				aaux.relacionaOuNao(relacionadoComPandemia);
			aaux.setDescricao(this.descricao);
			aaux.setData(this.data);
			aaux.setEtapas(this.etapas);
			aaux.setTempoAtendimento(this.tempoAtendimento);
			aaux.setTestes(this.testes);
			return aaux;
			}
		}return null;
	}
	
}
