package ar.com.codoacodo2.services.impl;

import java.util.List;

import ar.com.codoacodo2.domain.User;

public interface UserService {
	
	public void crearUser(User user);
	public User buscarUser(Long id);
	public List<User> buscarTodos();
	public User buscarUserPorUsername(String username);
	public void eliminarUser(Long id);
	public void actualizar(User user);
}
