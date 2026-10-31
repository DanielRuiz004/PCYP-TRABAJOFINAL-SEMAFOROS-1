package ar.edu.unju.fi.sistemaReserva.service;

import java.util.List;
import ar.edu.unju.fi.sistemaReserva.model.Butaca;

public interface ButacaService {
	public List<Butaca> obtenerTodos();

	public Butaca guardar(Butaca butaca);

	public Butaca buscar(Integer id);

	public void eliminar(Integer id);

	public Butaca modificar(Butaca butaca);

	public boolean reservarButaca(Integer butacaId);

}
