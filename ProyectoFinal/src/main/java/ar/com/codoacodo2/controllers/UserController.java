package ar.com.codoacodo2.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.codoacodo2.domain.User;
import ar.com.codoacodo2.dto.UserDTO;
import ar.com.codoacodo2.dto.UserRequestDTO;
import ar.com.codoacodo2.dto.UserRequestPutDTO;
import ar.com.codoacodo2.dto.UserResponseDTO;
import ar.com.codoacodo2.services.UserService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Builder
public class UserController {

	
	private final UserService userService;
	
	//GET
	@GetMapping("/{id}")	
	public ResponseEntity<UserDTO> m1(
			@PathVariable("id") Long id
			) {
		
		User user = this.userService.buscarUser(id);
				
		UserDTO dto =UserDTO.builder()
				.id(user.getId())
				.username(user.getUsername())
				.roles(user.getRoles()
						.stream()
						.map(x -> x.getRol())
						.collect(Collectors.toSet())
				).build();
				

		
		
		return ResponseEntity.ok(dto);
	}
	
	//GET
	@GetMapping()	
	public ResponseEntity<List<User>> findAll() {
		
		List<User> user = this.userService.buscarTodos();
		
		
		return ResponseEntity.ok(user);
	}
	
	
	@PostMapping()
	@PreAuthorize(value= "hasRole('ROLE_ADMIN')")
	public ResponseEntity<UserResponseDTO> createUser(
			@RequestBody UserRequestDTO request
		)  {
		
		
		User user = this.userService.buscarUserPorUsername(request.getUsername());
		if(user != null) {
			UserResponseDTO response = UserResponseDTO.builder()
				.username(user.getUsername())
				.build();
			
			return ResponseEntity.ok(response);
		}
		
		
		User newUser = User.builder()
				.username(request.getUsername())
				.password(request.getPassword())
				.build();
				
		this.userService.crearUser(newUser);
		
		UserResponseDTO response = UserResponseDTO.builder()
			.username(newUser.getUsername())
			.build();
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> actualizar(
			@PathVariable("id") Long id			
		) {
		
		this.userService.eliminarUser(id);
		
		return ResponseEntity.ok().build();
	}
	
	 
	@PutMapping("/{id}")
	public ResponseEntity<UserRequestPutDTO> actualizarPorPut(
			@PathVariable(name="id", required = true)Long id,
			@Validated @RequestBody UserRequestPutDTO request
			
			){
		User user = this.userService.buscarUser(id);
		if(!user.getId().equals(request.getId())) {
			return ResponseEntity.badRequest().body(request);
		}
		
		user.setPassword(request.getPassword());
		
		this.userService.actulizar(user);
		
		return ResponseEntity.ok().build();
	}
}