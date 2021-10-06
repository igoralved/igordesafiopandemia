package  igorgroup.desafiopandemia.controller.DTO;

import java.util.List;
import java.util.stream.Collectors;

import igorgroup.desafiopandemia.controller.model.Etapa;

public class EtapaDTO {

	private Long id;
	
	private int numero;
	private String descricao;
	
	public EtapaDTO(Etapa e) {
		this.id = e.getId();
		this.numero = e.getNumero();
		this.descricao = e.getDescricao();
	}
	
	public Long getId() {
		return id;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static List<EtapaDTO> converter(List<Etapa> lista){
		return lista.stream().map(EtapaDTO::new).collect(Collectors.toList());
	}
	
	public String toString() {
		return "Número: " + numero + ";/n" +
	"Descrição: " + descricao + ";";
	}
	
	public void print() {
		System.out.println(toString());
	}
	
}
