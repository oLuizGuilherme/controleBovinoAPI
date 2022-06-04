package projeto.integrador.controleBovino.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.integrador.controleBovino.modelo.Bovino;

public interface BovinoRepository extends JpaRepository<Bovino, String> {
	
	public Bovino findByCodigo(String codigo);
	
	public List<Bovino> findByAbatido(boolean abatido);
	
}
