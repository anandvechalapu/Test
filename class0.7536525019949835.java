

Controller Class:

@RestController
@RequestMapping("/signup")
public class SignupController {

    private final SignupService signupService;

    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping("/individual")
    public ResponseEntity<SignupResponse> signupIndividualUser(@RequestBody SignupRequest signupRequest) {
        SignupResponse signupResponse = signupService.signupIndividualUser(signupRequest);
        return ResponseEntity.ok(signupResponse);
    }
}

Service Class:

@Service
public class SignupService {

    private final SignupRepository signupRepository;

    public SignupService(SignupRepository signupRepository) {
        this.signupRepository = signupRepository;
    }

    public SignupResponse signupIndividualUser(SignupRequest signupRequest) {
        SignupResponse signupResponse = new SignupResponse();
        // Validate signupRequest
        if (validateSignupRequest(signupRequest)) {
            // Save signupRequest in database
            signupRepository.save(signupRequest);
            // Set success message in signupResponse
            signupResponse.setMessage("User signed up successfully");
        } else {
            // Set failure message in signupResponse
            signupResponse.setMessage("Invalid signup request");
        }
        return signupResponse;
    }

    private boolean validateSignupRequest(SignupRequest signupRequest) {
        // Validate signupRequest
        // Return true if valid, false otherwise
        return true;
    }
}

Repository Class:

@Repository
public interface SignupRepository extends JpaRepository<SignupRequest, Long> {

}