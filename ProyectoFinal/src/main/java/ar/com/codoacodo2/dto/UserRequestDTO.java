package ar.com.codoacodo2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserRequestDTO {
	
	private String username;
	private String password;
	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}
}
