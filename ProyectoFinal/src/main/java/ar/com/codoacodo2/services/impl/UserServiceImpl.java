 package ar.com.codoacodo2.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.codoacodo2.domain.User;
import ar.com.codoacodo2.repository.UserRepository;
import ar.com.codoacodo2.services.UserService;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository repository;
	
	
	/*public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}*/
	
	@Override
	public void crearUser(User user) {
		this.repository.save(user);
	}

	@Override
	public User buscarUser(Long id) {
		// TODO Auto-generated method stub
		return this.repository.findById(id).get();
	}

	@Override
	public List<User> buscarTodos() {
		
		return this.repository.findAll();
	}
	
	@Override
	public User buscarUserPorUsername(String username) {
		
		return this.repository.findByUsername(username);
	}
	
	@Override
	public void eliminarUser(Long id) {
		
		this.repository.deleteById(id);
	}
	
	@Override
	public void actulizar(User user) {
		this.repository.save(user);
	}

}
