package igorgroup.desafiopandemia.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import igorgroup.desafiopandemia.controller.DTO.EtapaDTO;
import igorgroup.desafiopandemia.controller.form.EtapaForm;
import igorgroup.desafiopandemia.controller.model.Etapa;
import igorgroup.desafiopandemia.controller.repository.EtapaRepository;

@RestController
@RequestMapping("/etapas")
public class EtapaController {

	
	
	@Autowired
	private EtapaRepository etaparepository;
	
	//@RequestMapping("/etapastodas")
	@ResponseBody
	@GetMapping
	public List<EtapaDTO> lista(){
			List<Etapa> lista = etaparepository.findAll();
			return EtapaDTO.converter(lista);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<EtapaDTO> cadastrar(@RequestBody EtapaForm eform, 
		UriComponentsBuilder uriBuilder) {
		Etapa e = eform.converter();
		etaparepository.save(e);
		URI uri = uriBuilder.path("/etapas/{id}").buildAndExpand(e.getId()).toUri();
		return ResponseEntity.created(uri).body(new EtapaDTO(e));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EtapaDTO> detalhar(@PathVariable Long codigo) {
		Optional<Etapa> eoptional = etaparepository.findById(codigo);
		if(eoptional.isPresent()) {
			return ResponseEntity.ok(new EtapaDTO(eoptional.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EtapaDTO> atualizar(@PathVariable Long id,
			@RequestBody @Valid EtapaForm form){
		Optional<Etapa> optional = etaparepository.findById(id);
		if(optional.isPresent()) {
			Etapa e = form.atualizar(id, etaparepository);
			return ResponseEntity.ok(new EtapaDTO(e));
		}return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Etapa> optional = etaparepository.findById(id);
		if(optional.isPresent()) {
			etaparepository.deleteById(id);
			return ResponseEntity.ok().build();
		}return ResponseEntity.notFound().build();
	}
	
}
