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

import igorgroup.desafiopandemia.controller.DTO.AtendimentoDTO;

import igorgroup.desafiopandemia.controller.form.AtendimentoForm;
import igorgroup.desafiopandemia.controller.model.Atendimento;
import igorgroup.desafiopandemia.controller.repository.AtendimentoRepository;
import igorgroup.desafiopandemia.controller.repository.EtapaRepository;
import igorgroup.desafiopandemia.controller.repository.TesteRepository;

@RestController
@RequestMapping("/atendimentos")
public class AtendimentoController {

	
	
	@Autowired
	private AtendimentoRepository atendimentorepository;
	
	
	//@RequestMapping("atendimentostodos")
	@GetMapping
	@ResponseBody
	public List<AtendimentoDTO> lista(){
			List<Atendimento> lista = atendimentorepository.findAll().stream().filter(a -> a.getRealcionadoComPandemia()).toList();
			return AtendimentoDTO.converter(lista);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<AtendimentoDTO> cadastrar(@RequestBody AtendimentoForm aform, 
		UriComponentsBuilder uriBuilder) {
		Atendimento a = aform.converter();
		atendimentorepository.save(a);
		URI uri = uriBuilder.path("/atendimentos/{id}").buildAndExpand(a.getId()).toUri();
		return ResponseEntity.created(uri).body(new AtendimentoDTO(a));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AtendimentoDTO> detalhar(@PathVariable Long codigo) {
		Optional<Atendimento> aoptional = atendimentorepository.findById(codigo);
		if(aoptional.isPresent()) {
			return ResponseEntity.ok(new AtendimentoDTO(aoptional.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AtendimentoDTO> atualizar(@PathVariable Long id,
			@RequestBody @Valid AtendimentoForm form){
		Optional<Atendimento> optional = atendimentorepository.findById(id);
		if(optional.isPresent()) {
			Atendimento a = form.atualizar(id, atendimentorepository);
			return ResponseEntity.ok(new AtendimentoDTO(a));
		}return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Atendimento> optional = atendimentorepository.findById(id);
		if(optional.isPresent()) {
			atendimentorepository.deleteById(id);
			return ResponseEntity.ok().build();
		}return ResponseEntity.notFound().build();
	}
	
	
	
}
