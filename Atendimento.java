package igorgroup.desafiopandemia.controller.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name = "Atendimento")
public class Atendimento {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private Boolean relacionado_com_pandemia = false;
	private Boolean sem_possibilidade_contagio = false;
	private Integer tempoAtendimento;

	private LocalDate data = LocalDate.now();
	
	@OneToMany
	@JoinColumn(name = "id_atendimento")
	private List<Etapa> etapa = new ArrayList<Etapa>();
	@OneToMany
	@JoinColumn(name = "id_atendimento")
	private List<Teste> teste = new ArrayList<Teste>();
	
	public Atendimento() {
		
	}
	
	public Atendimento(String desc, boolean rcp, boolean spc, Integer ta) {
		this.descricao = desc;
		this.relacionado_com_pandemia = rcp;
		this.sem_possibilidade_contagio = spc;
		this.tempoAtendimento = ta;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}if(obj == null) {
			return false;
		}if(getClass() != obj.getClass()) {
			return false;
		}Atendimento outra = (Atendimento) obj;
		if(id == null) {
			if(outra.getId() != null) {
				return false;
			}
		}else if(!getId().equals(outra.getId())) {
			return false;
		}return true;
	}
	
	public Long getId() {
		return this.id;
	}
	
	
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public Boolean getRealcionadoComPandemia() {
		return this.relacionado_com_pandemia;
	}
	
	public Boolean getSemPossibilidadeContagio() {
		return this.sem_possibilidade_contagio;
	}
	
	public LocalDate getLocalDate() {
		return this.data;
	}
	
	public String getData() {
		return data.toString();
	}
	
	public Integer getTempoAtendimento() {
		return this.tempoAtendimento;
	}
	
	//@OneToMany
	public List<Etapa> getEtapas(){
		return etapa;
	}
	
	//@OneToMany
	public List<Teste> getTestes(){
		return  teste;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void relacionaComPandemia() {
		this.relacionado_com_pandemia = true;
	}
	
	public void desRelacionaComPandemia() {
		this.relacionado_com_pandemia = false;
	}
	
	public void descartaPossibilidadeContagio() {
		this.sem_possibilidade_contagio = true;
	}
	
	public void colocaPossibilidadeContagio() {
		this.sem_possibilidade_contagio = false;
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
		etapa.add(e);
	}
	
	public void addTeste(Teste t) {
		if(teste.size() < 2) {
			teste.add(t);
		}
	}
	
	public void setEtapas(List<Etapa> lista) {
		this.etapa = lista;
	}
	
	public void setTestes(List<Teste> lista) {
		this.teste = lista;
	}
	
	public void descartaOuNaoDescarta(boolean simounao) {
		this.sem_possibilidade_contagio = simounao;
	}
	
	public void relacionaOuNao(boolean simounao) {
		this.relacionado_com_pandemia = simounao;
	}
	
	public String toString() {
		String result = "Descrição: " + descricao + ";/n";
		if(relacionado_com_pandemia) {
			result += "Está relacionado com pandemia;/n";
		}else {
			result += "Não está relacionado com pandemia;/n";
		}
		if(sem_possibilidade_contagio) {
			result += "Descartou possibilidade de contágio;/n";
		}else {
			result += "Não descartou possibilidade de contágio;/n";
		}result += "Tempo: " + tempoAtendimento;
		return result;
	}
	
	public void print() {
		System.out.println(toString());
	}
	
}
