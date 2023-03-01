
Spring Boot Application

Controller Class

@RestController
public class PasswordResetController {

	@Autowired
	private PasswordResetService passwordResetService;
	
	@RequestMapping("/user/resetPassword")
	public ResponseEntity<String> resetPassword(String emailAddress) {
		if (passwordResetService.isAccountActive(emailAddress)) {
			return passwordResetService.resetPassword(emailAddress);
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not found");
	}
	
	@RequestMapping("/user/verifyPasswordResetLink")
	public ResponseEntity<String> verifyPasswordResetLink(String link) {
		if (passwordResetService.isLinkValid(link)) {
			return ResponseEntity.status(HttpStatus.OK).body("Link is valid");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Link is invalid");
	}
	
	@RequestMapping("/user/updatePassword")
	public ResponseEntity<String> updatePassword(String oldPassword, String newPassword, String confirmPassword) {
		if (passwordResetService.isPasswordValid(oldPassword, newPassword, confirmPassword)) {
			return passwordResetService.updatePassword(oldPassword, newPassword);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password is invalid");
	}
	
	@RequestMapping("/user/login")
	public ResponseEntity<String> login(String newPassword) {
		if (passwordResetService.isPasswordValid(newPassword)) {
			return ResponseEntity.status(HttpStatus.OK).body("Successfully logged in");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid password");
	}

}

Service Class

@Service
public class PasswordResetService {

	@Autowired
	private PasswordResetRepository passwordResetRepository;
	
	public boolean isAccountActive(String emailAddress) {
		return passwordResetRepository.isAccountActive(emailAddress);
	}
	
	public ResponseEntity<String> resetPassword(String emailAddress) {
		boolean isSuccess = passwordResetRepository.resetPassword(emailAddress);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body("Password reset link sent to email");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending password reset link");
	}
	
	public boolean isLinkValid(String link) {
		return passwordResetRepository.isLinkValid(link);
	}
	
	public boolean isPasswordValid(String oldPassword, String newPassword, String confirmPassword) {
		return passwordResetRepository.isPasswordValid(oldPassword, newPassword, confirmPassword);
	}
	
	public ResponseEntity<String> updatePassword(String oldPassword, String newPassword) {
		boolean isSuccess = passwordResetRepository.updatePassword(oldPassword, newPassword);
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body("Password updated successfully");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating password");
	}
	
	public boolean isPasswordValid(