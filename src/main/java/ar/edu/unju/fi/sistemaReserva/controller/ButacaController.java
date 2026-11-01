package ar.edu.unju.fi.sistemaReserva.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unju.fi.sistemaReserva.model.Butaca;
import ar.edu.unju.fi.sistemaReserva.service.ButacaService;

@RestController
@RequestMapping("/api/butacas")
@CrossOrigin(origins = { "*", "http://localhost:5174" })
public class ButacaController {

    private final ButacaService butacaService;

    public ButacaController(ButacaService butacaService) {
        this.butacaService = butacaService;
    }

    // GET /api/butacas - obtener todas las butacas
    @GetMapping
    public ResponseEntity<List<Butaca>> obtenerTodas() {
        return ResponseEntity.ok(butacaService.obtenerTodos());
    }

    // GET /api/butacas/{id} - obtener una butaca por ID
    @GetMapping("/{id}")
    public ResponseEntity<Butaca> obtenerPorId(@PathVariable Integer id) {
        Butaca butaca = butacaService.buscar(id);
        if (butaca != null) {
            return ResponseEntity.ok(butaca);
        }
        return ResponseEntity.notFound().build();
    }

    // POST /api/butacas - crear una butaca nueva
    @PostMapping
    public ResponseEntity<Butaca> crear(@RequestBody Butaca butaca) {
        return ResponseEntity.ok(butacaService.guardar(butaca));
    }

    // POST /api/butacas/{id}/reservar - reservar una butaca (usa el semaforo)
    @PostMapping("/{id}/reservar")
    public ResponseEntity<String> reservar(@PathVariable Integer id) {
        boolean resultado = butacaService.reservarButaca(id);
        if (resultado) {
            return ResponseEntity.ok("Butaca " + id + " reservada exitosamente");
        }
        return ResponseEntity.badRequest()
                .body("No se pudo reservar la butaca " + id + " (no existe o ya esta reservada)");
    }

}
