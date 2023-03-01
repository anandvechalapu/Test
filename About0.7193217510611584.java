·       Ability to send email to NDM from default email.
·       Introduction page should display brief history of the NDM website and its goals.
·       Introduction page should display a link to the main ANMW Website.

@SpringBootApplication
public class NdmApplication {

	public static void main(String[] args) {
		SpringApplication.run(NdmApplication.class, args);
	}

}

@Controller
public class NdmController {

	@Autowired
	private NdmService ndmService;

	@GetMapping("/")
	public String getIntroduction() {
		return ndmService.getIntroduction();
	}

	@GetMapping("/goals")
	public String getGoals() {
		return ndmService.getGoals();
	}

	@PostMapping("/email")
	public void sendEmail(@RequestParam("email") String email) {
		ndmService.sendEmail(email);
	}
}

@Service
public class NdmService {

	@Autowired
	private NdmRepository ndmRepository;

	public String getIntroduction() {
		return ndmRepository.getIntroduction();
	}

	public String getGoals() {
		return ndmRepository.getGoals();
	}

	public void sendEmail(String email) {
		ndmRepository.sendEmail(email);
	}
}

@Repository
public class NdmRepository {

	public String getIntroduction() {
		// fetch introduction from database
	}

	public String getGoals() {
		// fetch goals from database
	}

	public void sendEmail(String email) {
		// send email to NDM
	}
}