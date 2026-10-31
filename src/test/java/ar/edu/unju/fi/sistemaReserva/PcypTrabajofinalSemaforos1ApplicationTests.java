package ar.edu.unju.fi.sistemaReserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import ar.edu.unju.fi.sistemaReserva.service.ButacaService;
import ar.edu.unju.fi.sistemaReserva.model.Butaca;

@SpringBootTest
class PcypTrabajofinalSemaforos1ApplicationTests {
	@Autowired
	ButacaService butacaService;
	Butaca butaca1, butaca2, butaca3, butaca4;

	@BeforeEach
	void setUp() {
		butaca1 = new Butaca();
		butaca1.setFila(1);
		butaca1.setColumna(1);
		butaca1.setDisponible(true);
		butaca1.setPrecio(1200);
	}

	@Test
	void testAgregarButaca() {
		Butaca butaca = new Butaca();
		butaca.setFila(1);
		butaca.setColumna(2);
		butaca.setDisponible(true);
		butaca.setPrecio(3000);
		butacaService.guardar(butaca);
		assertEquals(1, butacaService.obtenerTodos().size());
	}

	@Test
	void buscarButaca() {
		butacaService.guardar(butaca1);
		Butaca butaca = butacaService.buscar(1);
		assertEquals(1, butaca.getFila());
		assertEquals(1, butaca.getColumna());
		assertEquals(true, butaca.getDisponible());
		assertEquals(1200, butaca.getPrecio());
		System.out.println("########## ID ############: " + butaca.getId());
	}

	@Test
	void modificarButaca() {
		butacaService.guardar(butaca1);
		Butaca butaca5 = butacaService.buscar(1);
		assertEquals(true, butaca5.getDisponible());
		butaca5.setDisponible(false);
		butacaService.guardar(butaca5);
		Butaca butaca6 = butacaService.buscar(1);
		assertEquals(false, butaca6.getDisponible());
	}
}
