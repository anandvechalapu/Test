

/**
 * UserController
 * 
 * Handles all user signup related operations
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    /**
     * Handles signup request from user
     *
     * @param userRequest the request body containing user details
     * @return the signup response
     */
    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<SignupResponse>(userService.signup(userRequest), HttpStatus.OK);
    }
}

/**
 * UserService
 * 
 * Handles all user signup related business logic
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    /**
     * Handles signup request from user
     *
     * @param userRequest the request body containing user details
     * @return the signup response
     */
    public SignupResponse signup(UserRequest userRequest) {
        User user = userRepository.save(buildUser(userRequest));
        return buildSignupResponse(user);
    }

}

/**
 * UserRepository
 * 
 * Handles all user data related operations
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds the user by username
     *
     * @param username the username of the user
     * @return the user with the given username
     */
    User findByUsername(String username);
}