package igorgroup.desafiopandemia.controller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import igorgroup.desafiopandemia.controller.model.Teste;


public interface TesteRepository extends JpaRepository<Teste, Long>{

	
	@Query("SELECT t FROM Teste t")
	List<Teste> carregarTodas();
	
	
	//List<Teste> findByIdT(Long id_t);
}
