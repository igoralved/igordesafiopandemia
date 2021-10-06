package igorgroup.desafiopandemia.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import igorgroup.desafiopandemia.controller.DTO.TesteDTO;
import igorgroup.desafiopandemia.controller.form.TesteForm;
import igorgroup.desafiopandemia.controller.model.Teste;
import igorgroup.desafiopandemia.controller.repository.TesteRepository;

@RestController
@RequestMapping("/testes")
public class TesteController {

	
	
	@Autowired
	private TesteRepository testerepository;
	
	
	
	//@RequestMapping("/testes")
	@GetMapping
	@ResponseBody
	public List<TesteDTO> lista(){
			List<Teste> lista = testerepository.findAll();
			return TesteDTO.converter(lista);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TesteDTO> cadastrar(@RequestBody TesteForm eform, 
		UriComponentsBuilder uriBuilder) {
		Teste t = eform.converter();
		testerepository.save(t);
		URI uri = uriBuilder.path("/testes/{id}").buildAndExpand(t.getId()).toUri();
		return ResponseEntity.created(uri).body(new TesteDTO(t));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TesteDTO> detalhar(@PathVariable Long codigo) {
		Optional<Teste> eoptional = testerepository.findById(codigo);
		if(eoptional.isPresent()) {
			return ResponseEntity.ok(new TesteDTO(eoptional.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TesteDTO> atualizar(@PathVariable Long id,
			@RequestBody @Valid TesteForm form){
		Optional<Teste> optional = testerepository.findById(id);
		if(optional.isPresent()) {
			Teste t = form.atualizar(id, testerepository);
			return ResponseEntity.ok(new TesteDTO(t));
		}return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Teste> optional = testerepository.findById(id);
		if(optional.isPresent()) {
			testerepository.deleteById(id);
			return ResponseEntity.ok().build();
		}return ResponseEntity.notFound().build();
	}
	
}
