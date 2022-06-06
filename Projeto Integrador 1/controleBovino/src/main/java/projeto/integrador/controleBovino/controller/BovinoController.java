package projeto.integrador.controleBovino.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import projeto.integrador.controleBovino.form.BovinoForm;
import projeto.integrador.controleBovino.modelo.Bovino;
import projeto.integrador.controleBovino.repository.BovinoRepository;
import projeto.integrador.controleBovino.vo.BovinoVO;
import projeto.integrador.controleBovino.vo.QuantidadeLeiteVO;
import projeto.integrador.controleBovino.vo.QuantidadeRacaoVO;

@RestController()
@RequestMapping("/bovino")
public class BovinoController {

	@Autowired
	private BovinoRepository bovinoRepository;

	@GetMapping()
	public List<Bovino> listarTodos() {
		return bovinoRepository.findAll();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<BovinoVO> bovinoByCodigo(@PathVariable String codigo) {
		return ResponseEntity.ok(BovinoVO.entityToVO(bovinoRepository.findByCodigo(codigo)));
	}

	@GetMapping("/abater")
	public ResponseEntity<List<BovinoVO>> bovinosAAbater() {
		List<Bovino> bovinosLst = bovinoRepository.findByAbatido(false);

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
	public ResponseEntity<BovinoVO> criarBovino(@RequestBody @Valid BovinoForm bovinoForm,
			UriComponentsBuilder uriBuilder) {

		Bovino bovino = bovinoRepository.save(new Bovino(bovinoForm.getCodigo(), bovinoForm.getLitrosLeite(),
				bovinoForm.getQuilosRacao(), bovinoForm.getPeso(), bovinoForm.getNascimento(), bovinoForm.isAbatido()));

		URI uri = uriBuilder.path("/bovino/{id}").buildAndExpand(bovino.getId()).toUri();
		return ResponseEntity.created(uri).body(BovinoVO.entityToVO(bovino));
	}
	
	@GetMapping("/leitesemanal")
	public ResponseEntity<QuantidadeLeiteVO> somarLeite() {
		List<Bovino> bovinosLst = bovinoRepository.findByAbatido(false);
		
		Double producaoLeiteSemanal = 0.0;
		
		for (Bovino bovino : bovinosLst) {
			producaoLeiteSemanal = producaoLeiteSemanal + bovino.getLitrosLeite();
		}
		
		QuantidadeLeiteVO quantidadeLeiteVO = new QuantidadeLeiteVO(producaoLeiteSemanal, bovinosLst.size());
		
		return ResponseEntity.ok(quantidadeLeiteVO);
		
	}
	

	@GetMapping("/racaosemanal")
	public ResponseEntity<QuantidadeRacaoVO> somaRacao() {

		List<Bovino> bovinosLst = bovinoRepository.findByAbatido(false);
		
		int somaBovino = 0;
		BigDecimal somaRacao = new BigDecimal(0);

		for (Bovino bovino : bovinosLst) {
			if (!bovino.isAbatido()) {
				somaBovino++;
				somaRacao = somaRacao.add(new BigDecimal(bovino.getQuilosRacao()));
			}
		}

		QuantidadeRacaoVO quantidadeRacaoVO = new QuantidadeRacaoVO(somaBovino, somaRacao);
		return ResponseEntity.ok(quantidadeRacaoVO);

	}
	
	@GetMapping("/abatidos")
	public ResponseEntity<List<BovinoVO>> retornarAbatidos(){
		List<Bovino> abatidos = bovinoRepository.findByAbatido(true);
		
		List<BovinoVO> abatidosVO = abatidos.stream().map(bovino -> BovinoVO.entityToVO(bovino)).collect(Collectors.toList());
		
		return ResponseEntity.ok(abatidosVO);
	}
	
	@PutMapping("/racao/{codigo}/{quilosRacao}")
	public ResponseEntity<BovinoVO> atualizaAlimentacao(@PathVariable String codigo, @PathVariable double quilosRacao) {
		Bovino bovino = bovinoRepository.findByCodigo(codigo);
		BovinoVO bovinoVO = BovinoVO.entityToVO(bovino);
		bovinoVO.setQuilosRacao(quilosRacao);
		bovino.atualizaRacao(bovinoVO);
		
		bovinoRepository.save(bovino);
		
		return ResponseEntity.ok(bovinoVO);
	}
	
	@PutMapping("/leite/{codigo}/{litrosLeite}")
	public ResponseEntity<BovinoVO> atualizaLitrosLeite(@PathVariable String codigo, @PathVariable double litrosLeite) {
		Bovino bovino = bovinoRepository.findByCodigo(codigo);
		BovinoVO bovinoVO = BovinoVO.entityToVO(bovino);
		bovinoVO.setLitrosLeite(litrosLeite);
		bovino.atualizaLeite(bovinoVO);
		
		bovinoRepository.save(bovino);
		
		return ResponseEntity.ok(bovinoVO);
	}
	

}
