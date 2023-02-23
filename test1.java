

Controller:

@RestController
@RequestMapping("/signup")
public class SignupController {
    private final SignupService signupService;
    
    @Autowired
    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }
    
    @PostMapping("/individual")
    public String signupIndividual(@RequestBody SignupRequest signupRequest) {
        return signupService.signupIndividual(signupRequest);
    }
}

Service:

@Service
public class SignupService {
    private final SignupRepository signupRepository;
    
    @Autowired
    public SignupService(SignupRepository signupRepository) {
        this.signupRepository = signupRepository;
    }
    
    public String signupIndividual(SignupRequest signupRequest) {
        // Validate signup request
        if (!signupRequest.isValid()) {
            return "Invalid signup request";
        }
        
        // Check if user already exists
        if (signupRepository.existsByEmail(signupRequest.getEmail())) {
            return "User already exists";
        }
        
        // Create new user
        User user = signupRepository.save(new User(signupRequest));
        
        return "User created successfully with id: " + user.getId();
    }
}

Repository:

@Repository
public interface SignupRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}