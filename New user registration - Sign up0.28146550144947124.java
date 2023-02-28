
@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signUpUser(@RequestBody User user) {
        if (signUpService.isValidSignUp(user)) {
            signUpService.saveUser(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}

@Service
public class SignUpService {

    @Autowired
    private UserRepository userRepository;

    public boolean isValidSignUp(User user) {
        return isValidName(user.getName()) &&
                isValidEmail(user.getEmail()) &&
                isValidPassword(user.getPassword());
    }

    private boolean isValidName(String name) {
        return name != null && !name.isEmpty();
    }

    private boolean isValidEmail(String email) {
        return email != null && !email.isEmpty() && !userRepository.existsByEmail(email);
    }

    private boolean isValidPassword(String password) {
        return password != null && !password.isEmpty() && password.length() >= 8;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}