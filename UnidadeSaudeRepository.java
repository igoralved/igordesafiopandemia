package igorgroup.desafiopandemia.controller.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import igorgroup.desafiopandemia.controller.model.UnidadeSaude;


public interface UnidadeSaudeRepository extends JpaRepository<UnidadeSaude, Long>{
	
	List<UnidadeSaude> findByData(LocalDate data);
	
	@Query("SELECT u FROM UnidadeSaude u WHERE u.data BETWEEN :begin AND :end")
	List<UnidadeSaude> findByWeek(@Param("begin") LocalDate begin, @Param("end") LocalDate end);
	
}
