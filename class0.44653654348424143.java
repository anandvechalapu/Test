

/**
 * This class is used to sign up a new user
 */
@Controller
public class SignupController {
   
   /**
    * Service class to interact with repository layer
    */
   @Autowired
   SignupService signupService;

   /**
    * Method to handle sign up request
    */
   @RequestMapping("/signup")
   public String signup(@RequestParam("name") String name,
                        @RequestParam("email") String email,
                        @RequestParam("phone") String phone) {
   
       // Validate the inputs
       if (StringUtils.isEmpty(name) || StringUtils.isEmpty(phone) || StringUtils.isEmpty(email)) {
           return "redirect:/signup-failure";
       }
   
       // Create signup object
       Signup signup = new Signup();
       signup.setName(name);
       signup.setPhone(phone);
       signup.setEmail(email);
   
       // Save the signup object
       signupService.save(signup);
   
       return "redirect:/signup-success";
   }
}

/**
 * This service class is used to interact with repository layer
 */
@Service
public class SignupService {

   /**
    * Repository class to interact with database
    */
   @Autowired
   SignupRepository signupRepository;

   /**
    * Method to save signup object
    */
   public void save(Signup signup) {
       signupRepository.save(signup);
   }
   
}

/**
 * This repository class is used to interact with database
 */
@Repository
public interface SignupRepository extends JpaRepository<Signup, Long> {

}