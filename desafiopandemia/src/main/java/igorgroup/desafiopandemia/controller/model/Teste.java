package igorgroup.desafiopandemia.controller.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Teste")
public class Teste {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer numero;
	private String resultado;
	
	public Teste() {
		
	}
	
	public Teste(int numero, String resultado) {
		this.numero = numero;
		this.resultado = resultado;
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
		}Teste outra = (Teste) obj;
		if(id == null) {
			if(outra.getId() != null) {
				return false;
			}
		}else if(!getId().equals(outra.getId())) {
			return false;
		}return true;
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
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	public String toString() {
		return "Número: " + numero + "\nResultados: " + resultado;
	}
	
	public void print() {
		System.out.println(toString());
	}
	
}
