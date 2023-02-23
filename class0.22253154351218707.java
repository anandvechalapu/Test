

Controller:

@RestController
public class SignupController {
  
  @Autowired
  private SignupService signupService;

  @PostMapping("/signup")
  public ResponseEntity<String> signupIndividual(@RequestBody SignupRequest signupRequest) {
    signupService.signupIndividual(signupRequest);
    return ResponseEntity.ok("User successfully signed up");
  }
}

Service:

@Service
public class SignupService {
  
  @Autowired
  private SignupRepository signupRepository;
  
  public void signupIndividual(SignupRequest signupRequest) {
    signupRepository.save(signupRequest);
  }
}

Repository:

@Repository
public interface SignupRepository extends JpaRepository<SignupRequest, Long> {

}