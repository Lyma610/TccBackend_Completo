package br.itb.projeto.seuprojeto.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.itb.projeto.seuprojeto.model.entity.Reacao;
import br.itb.projeto.seuprojeto.service.ReacaoService;

@RestController
@RequestMapping("/reacao")
public class ReacaoController {

	private ReacaoService reacaoService;

	// Source -> Generate Constructor using Fields...
	public ReacaoController(ReacaoService reacaoService) {
		super();
		this.reacaoService = reacaoService;
	}

	@GetMapping("/test")
	public String getTest() {
		return "Olá, Reacao!";
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Reacao> findById(@PathVariable long id) {
		Reacao reacao = reacaoService.findById(id);
		return new ResponseEntity<Reacao>(reacao, HttpStatus.OK);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Reacao>> findAll(){
		List<Reacao> reacaos = reacaoService.findAll();
		return new ResponseEntity<List<Reacao>>(reacaos, HttpStatus.OK);
	}

	@GetMapping("/findAllAtivos")
	public ResponseEntity<List<Reacao>> findAllAtivos(){
		List<Reacao> reacaos = reacaoService.findAllAtivos();
		return new ResponseEntity<List<Reacao>>(reacaos, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Reacao> create(@RequestBody Reacao reacao) {
		try {
			Reacao novaReacao = reacaoService.create(reacao);
			return new ResponseEntity<Reacao>(novaReacao, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/inativar/{id}")
	public ResponseEntity<Reacao> inativar(@PathVariable long id) {
		try {
			Reacao reacao = reacaoService.inativar(id);
			if (reacao != null) {
				return new ResponseEntity<Reacao>(reacao, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
