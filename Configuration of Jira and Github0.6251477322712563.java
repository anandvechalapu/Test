
Spring Boot Application:

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

Controller:

@RestController
public class ServiceController {

	@Autowired
	private ServiceService serviceService;

	@GetMapping("/services")
	public List<Service> getServices() {
		return serviceService.getServices();
	}

	@PostMapping("/services/configure")
	public ResponseEntity<String> configureService(@RequestBody ServiceConfiguration configuration) {
		boolean isValid = serviceService.validateCredentials(configuration);

		if (isValid) {
			return ResponseEntity.ok("Configuration successful!");
		} else {
			return ResponseEntity.badRequest().body("Invalid credentials!");
		}
	}

}

Service:

@Service
public class ServiceService {

	@Autowired
	private ServiceRepository serviceRepository;

	public List<Service> getServices() {
		return serviceRepository.findAll();
	}

	public boolean validateCredentials(ServiceConfiguration configuration) {
		// validate credentials here
		return true;
	}

}

Repository:

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

}