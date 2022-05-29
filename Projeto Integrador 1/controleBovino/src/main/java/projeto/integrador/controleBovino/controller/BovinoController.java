package projeto.integrador.controleBovino.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import projeto.integrador.controleBovino.modelo.Bovino;
import projeto.integrador.controleBovino.repository.BovinoRepository;
import projeto.integrador.controleBovino.vo.BovinoVO;

@RestController()
@RequestMapping("/bovino")
public class BovinoController {

	@Autowired
	private BovinoRepository bovinoRepository;

	@GetMapping()
	public List<Bovino> listarTodos() {
		return bovinoRepository.findAll();
	}

	@GetMapping("/relatorio")
	public ResponseEntity<List<BovinoVO>> listarAbate() {
		List<Bovino> bovinosLst = bovinoRepository.findAll();

		List<BovinoVO> bovinosAAbater = new ArrayList<BovinoVO>();

		for (Bovino bovino : bovinosLst) {
			if (bovino.getNascimento().plus(5, ChronoUnit.YEARS).isBefore(LocalDate.now())
					|| bovino.getLitrosLeite() < 40 || (bovino.getLitrosLeite() < 70 && bovino.getQuilosRacao() > 50)
					|| bovino.getPeso().compareTo(new BigDecimal(270)) > 0) {
				bovinosAAbater.add(BovinoVO.entityToVO(bovino));
			}
		}

		return ResponseEntity.ok(bovinosAAbater);
	}

	@PostMapping
	public ResponseEntity<BovinoVO> criarBovino(@RequestBody BovinoVO bovinoVO, UriComponentsBuilder uriBuilder) {

		Bovino bovino = bovinoRepository.save(new Bovino(bovinoVO.getCodigo(), bovinoVO.getLitrosLeite(),
				bovinoVO.getQuilosRacao(), bovinoVO.getPeso(), bovinoVO.getNascimento()));

		URI uri = uriBuilder.path("/bovino/{id}").buildAndExpand(bovino.getId()).toUri();
		return ResponseEntity.created(uri).body(BovinoVO.entityToVO(bovino));
	}

}
