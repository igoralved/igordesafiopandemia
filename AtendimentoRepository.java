package igorgroup.desafiopandemia.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import igorgroup.desafiopandemia.controller.model.Atendimento;

public interface AtendimentoRepository  extends JpaRepository<Atendimento, Long>{

	
	
	@Query("SELECT min(a.tempoAtendimento) FROM Atendimento a")
	Integer carregarTempoMinimo();
	
	@Query("SELECT max(a.tempoAtendimento) FROM Atendimento a")
	Integer carregarTempoMaximo();
	
	@Query("SELECT avg(a.tempoAtendimento) FROM Atendimento a")
	Double carregarTempoMedio();
	
}
