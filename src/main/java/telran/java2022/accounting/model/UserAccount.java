package telran.java2022.accounting.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@EqualsAndHashCode(of = {"login"})
@Document(collection = "users")
//@Builder
//@AllArgsConstructor
public class UserAccount {
	@Id
	String login;
	@Setter
	String password;
	@Setter
	String firstName;
	@Setter
	String lastName;
	Set<String> roles;
	@Setter
	LocalDate passwordExpDate;

	public UserAccount() {
		roles = new HashSet<>();
		roles.add("USER");
	}

	public UserAccount(String login, String password, String firstName, String lastName) {
		this();
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public boolean addRole(String role) {
		return roles.add(role);
	}

	public boolean removeRole(String role) {
		return roles.remove(role);
	}

}
