package  igorgroup.desafiopandemia.controller.DTO;

import java.util.List;
import java.util.stream.Collectors;

import igorgroup.desafiopandemia.controller.model.Teste;

public class TesteDTO {

	private Long id;
	
	private int numero;
	private String resultado;
	
	public TesteDTO(Teste t) {
		this.id = t.getId();
		this.numero = t.getNumero();
		this.resultado = t.getResultado();
	}
	
	public Long getId() {
		return id;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public String getResultado() {
		return resultado;
	}
	
	public String toString() {
		return "Número: " + numero + "\nResultados: " + resultado;
	}
	
	public void print() {
		System.out.println(toString());
	}
	
	public static List<TesteDTO> converter(List<Teste> lista){
		return lista.stream().map(TesteDTO::new).collect(Collectors.toList());
	}
}
