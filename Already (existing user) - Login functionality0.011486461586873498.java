

@RestController
public class LoginController {
 
    private final LoginService loginService;
 
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
 
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginForm loginForm) {
        User user = loginService.login(loginForm);
        if (user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

@Service
public class LoginService {
 
    private final UserRepository userRepository;
 
    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
 
    public User login(LoginForm loginForm) {
        User user = userRepository.findByUsernameAndPassword(loginForm.getUsername(), loginForm.getPassword());
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }
}

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 
    User findByUsernameAndPassword(String username, String password);
}