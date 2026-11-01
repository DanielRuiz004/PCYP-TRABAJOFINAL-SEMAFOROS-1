package ar.edu.unju.fi.sistemaReserva.service;

import org.springframework.stereotype.Service;
import ar.edu.unju.fi.sistemaReserva.model.Butaca;
import ar.edu.unju.fi.sistemaReserva.repository.ButacaRepository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

@Service
public class ButacaServiceImpl implements ButacaService {

	private final ButacaRepository butacaRepository;

	// mapa para guardar un semaforo por cada butaca
	private final ConcurrentHashMap<Integer, Semaphore> semaforos = new ConcurrentHashMap<>();

	public ButacaServiceImpl(ButacaRepository butacaRepository) {
		this.butacaRepository = butacaRepository;
	}

	// busca el semaforo de la butaca y si no existe lo crea con 1 permiso
	private Semaphore getSemaforo(Integer butacaId) {
		return semaforos.computeIfAbsent(butacaId, id -> new Semaphore(1));
	}

	@Override
	public List<Butaca> obtenerTodos() {
		return butacaRepository.findAll();
	}

	@Override
	public Butaca guardar(Butaca butaca) {
		return butacaRepository.save(butaca);
	}

	@Override
	public Butaca buscar(Integer id) {
		return butacaRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
	}

	@Override
	public Butaca modificar(Butaca butaca) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'modificar'");
	}

	@Override
	public boolean reservarButaca(Integer butacaId) {
		Semaphore semaforo = getSemaforo(butacaId);
		try {
			// tomar el permiso
			semaforo.acquire();
			// seccion critica
			Butaca butaca = butacaRepository.findById(butacaId).orElse(null);
			if (butaca != null && butaca.getDisponible()) {
				// reservar la butaca (disponible = false)
				butaca.setDisponible(false);
				butacaRepository.save(butaca);
				System.out.println("Se reservo la butaca " + butacaId);
				return true;
			}
			return false;
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return false;
		} finally {
			// liberacion del semaforo para que otro hilo pueda acceder a la seccion critica
			semaforo.release();
		}
	}

}
