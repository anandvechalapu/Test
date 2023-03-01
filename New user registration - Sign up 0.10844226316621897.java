
@SpringBootApplication
public class UserRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRegistrationApplication.class, args);
	}

}

@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void signUp(User user) {
		if(userRepository.findByEmail(user.getEmail()) != null) {
			throw new IllegalArgumentException("User with email already exists.");
		}
		
		if(user.getPassword().length() < 8) {
			throw new IllegalArgumentException("Password is too short. Must be at least 8 characters.");
		}
		
		userRepository.save(user);
	}
}

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public void signUp(@RequestBody User user) {
		userService.signUp(user);
	}

}

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	// getters and setters

}

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	
}