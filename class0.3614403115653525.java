

Controller:

@Controller
public class SignupController {
    
    @Autowired
    private SignupService signupService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        SignupResponse signupResponse = signupService.signup(signupRequest);
        if (signupResponse.isSuccess()) {
            return ResponseEntity.ok(signupResponse);
        } else {
            return ResponseEntity.badRequest().body(signupResponse);
        }
    }
   
}

Service:

@Service
public class SignupService {

    @Autowired
    private SignupRepository signupRepository;
    
    public SignupResponse signup(SignupRequest signupRequest) {
        SignupResponse signupResponse = new SignupResponse();
        if (signupRepository.findByUsername(signupRequest.getUsername()) == null) {
            User user = new User();
            user.setName(signupRequest.getName());
            user.setUsername(signupRequest.getUsername());
            user.setPassword(signupRequest.getPassword());
            signupRepository.save(user);
            signupResponse.setSuccess(true);
            signupResponse.setMessage("Signup success");
        } else {
            signupResponse.setSuccess(false);
            signupResponse.setMessage("Username already taken");
        }
        return signupResponse;
    }
}

Repository:

@Repository
public interface SignupRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}