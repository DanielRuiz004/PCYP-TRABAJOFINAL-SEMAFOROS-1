package ar.edu.unju.fi.sistemaReserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.sistemaReserva.model.Butaca;

@Repository
public interface ButacaRepository extends JpaRepository<Butaca, Integer> {
}
