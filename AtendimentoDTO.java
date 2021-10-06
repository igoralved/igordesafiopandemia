package igorgroup.desafiopandemia.controller.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import igorgroup.desafiopandemia.controller.model.Atendimento;


public class AtendimentoDTO {

	private Long id;
		private String descricao;
		private boolean relacionadoComPandemia = false;
		private boolean semPossibilidadeContagio = false;
		private LocalDate data = LocalDate.now();
		private Integer tempoAtendimento;
		private List<EtapaDTO> etapas = new ArrayList<>();
		private List<TesteDTO> testes = new ArrayList<>();
	
	public AtendimentoDTO(Atendimento a) {
		this.id = a.getId();
		this.descricao = a.getDescricao();
		this.relacionadoComPandemia = a.getRealcionadoComPandemia();
		this.semPossibilidadeContagio = a.getSemPossibilidadeContagio();
		this.data = a.getLocalDate();
		this.tempoAtendimento = a.getTempoAtendimento();
		this.etapas.addAll(a.getEtapas().stream().map(EtapaDTO::new).collect(Collectors.toList()));
		this.testes.addAll(a.getTestes().stream().map(TesteDTO::new).collect(Collectors.toList()));
	}
	
	public Long getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public Boolean getRealcionadoComPandemia() {
		return this.relacionadoComPandemia;
	}
	
	public Boolean getSemPossibilidadeContagio() {
		return this.relacionadoComPandemia;
	}
	
	public LocalDate getLocalDate() {
		return data;
	}
	
	public String getData() {
		return data.toString();
	}
	
	public Integer getTempoAtendimento() {
		return tempoAtendimento;
	}
	
	public List<EtapaDTO> getEtapas(){
		return etapas;
	}
	
	public List<TesteDTO> getTestes(){
		return testes;
	}
	
	public String toString() {
		String result = "Descrição: " + descricao + ";/n";
		if(relacionadoComPandemia) {
			result += "Está relacionado com pandemia;/n";
		}else {
			result += "Não está relacionado com pandemia;/n";
		}
		if(semPossibilidadeContagio) {
			result += "Descartou possibilidade de contágio;/n";
		}else {
			result += "Não descartou possibilidade de contágio;/n";
		}result += "Tempo: " + tempoAtendimento;
		return result;
	}
	
	public void print() {
		System.out.println(toString());
	}
	
	public static List<AtendimentoDTO> converter(List<Atendimento> lista){
		return lista.stream().map(AtendimentoDTO::new).collect(Collectors.toList());
	}
	
}
