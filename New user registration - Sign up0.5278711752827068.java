
Controller:

@RestController
public class SignUpController {

    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        if(signUpService.isEmailRegistered(signUpRequest)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already registered");
        }

        if(!signUpService.isPasswordValid(signUpRequest)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password is too weak");
        }

        if(!signUpService.isEmailValid(signUpRequest)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is not valid");
        }

        signUpService.createUser(signUpRequest);
        return ResponseEntity.status(HttpStatus.OK).body("User created successfully");
    }  
}

Service:

@Service
public class SignUpService {

    private final SignUpRepository signUpRepository;

    public SignUpService(SignUpRepository signUpRepository) {
        this.signUpRepository = signUpRepository;
    }

    public boolean isEmailRegistered(SignUpRequest signUpRequest) {
        return signUpRepository.existsByEmail(signUpRequest.getEmail());
    }

    public boolean isPasswordValid(SignUpRequest signUpRequest) {
        String password = signUpRequest.getPassword();
        return (password.length() >= 8 && !password.equals(password.toLowerCase()));
    }

    public boolean isEmailValid(SignUpRequest signUpRequest) {
        String email = signUpRequest.getEmail();
        return (email.contains("@"));
    }

    public void createUser(SignUpRequest signUpRequest) {
        signUpRepository.save(new User(signUpRequest.getName(), signUpRequest.getEmail(), signUpRequest.getPassword()));
    }
}

Repository:

@Repository
public interface SignUpRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}