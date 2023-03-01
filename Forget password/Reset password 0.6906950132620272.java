
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

@RestController
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @GetMapping("/forget-password")
    public String forgetPassword(String email) {
        return passwordService.forgetPassword(email);
    }

    @PostMapping("/reset-password")
    public String resetPassword(String oldPassword, String newPassword, String confirmPassword) {
        return passwordService.resetPassword(oldPassword, newPassword, confirmPassword);
    }
}

@Service
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepository;

    public String forgetPassword(String email) {
        // Logic to send a password reset link to the user's email
        return "Password reset link sent to email";
    }

    public String resetPassword(String oldPassword, String newPassword, String confirmPassword) {
        // Logic to validate the old password, new password and confirm password
        // Logic to update the password in the repository
        return "Password reset successfully";
    }
}

@Repository
public interface PasswordRepository extends JpaRepository<User, Long> {
    // Logic to access the user from the repository
}