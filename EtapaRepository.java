package igorgroup.desafiopandemia.controller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import igorgroup.desafiopandemia.controller.model.Etapa;
import igorgroup.desafiopandemia.controller.model.Teste;


public interface EtapaRepository extends JpaRepository<Etapa, Long> {

	@Query("SELECT e FROM Etapa e WHERE (:numero is null or e.numero = :numero)")
	List<igorgroup.desafiopandemia.controller.model.Etapa> findByNumero(@Param("numero") String numero);
	
	@Query("SELECT e FROM Etapa e")
	List<Etapa> carregarTodas();
	
	//List<Etapa> findByIdE(Long id_e);
	
}
