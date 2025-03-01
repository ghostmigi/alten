package alten.alten;

import alten.alten.user.model.Role;
import alten.alten.user.model.User;
import alten.alten.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
public class AltenApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AltenApplication.class, args);
	}

	public void run(String... args) {
		List<User> adminAccounts = userRepository.findByRole(Role.ADMIN);

		if (adminAccounts.isEmpty()) {
			User user = new User();
			user.setFirstName("admin");
			user.setLastName("admin");
			user.setEmail("admin@admin.com");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("qqqqqqqq"));
			userRepository.save(user);
		} else {
			System.out.println("Admin accounts already exist.");
		}
	}
}
