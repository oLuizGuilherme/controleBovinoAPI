package projeto.integrador.controleBovino.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.integrador.controleBovino.modelo.Bovino;

public interface BovinoRepository extends JpaRepository<Bovino, String> {

}
