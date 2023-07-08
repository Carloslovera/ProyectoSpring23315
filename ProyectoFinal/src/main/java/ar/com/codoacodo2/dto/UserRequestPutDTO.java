package ar.com.codoacodo2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserRequestPutDTO {
	@NotNull
	private Long id;
	@NotBlank
	private String password;
}
