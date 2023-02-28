
@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = loginService.login(loginRequest);
        if (loginResponse.getSuccess()) {
            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponse);
        }
    }
}

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        if (loginRepository.validateLogin(loginRequest)) {
            return LoginResponse.builder()
                    .success(true)
                    .message("Login successful")
                    .build();
        } else {
            return LoginResponse.builder()
                    .success(false)
                    .message("Invalid username or password")
                    .build();
        }
    }
}

@Repository
public class LoginRepository {

    public boolean validateLogin(LoginRequest loginRequest) {
        // code to query the database and validate the login credentials
    }
}