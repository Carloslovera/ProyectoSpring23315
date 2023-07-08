package ar.com.codoacodo2.domain;



import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "usuario")
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder

public class User {
		
		@Id
		@GeneratedValue(strategy =GenerationType.IDENTITY)
		private Long id;
		
		
		@Column(name="username",length = 50, unique = true)
		private String username;
		
		
		@Column(name = "password",length = 50)
		private String password;
		
		/*@Column(name = "alias")
		private String alias;*/
		
		@ManyToMany(fetch = FetchType.EAGER)
		@JoinTable(
				name = "usuario_roles",
				joinColumns = @JoinColumn(name="usuario_id"),
				inverseJoinColumns = @JoinColumn(name="role_id")
		)
		
		private Set<Role> roles;
		
		
		
		
}
