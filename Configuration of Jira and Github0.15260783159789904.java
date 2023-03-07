
@RestController
public class ConfigurationController {
	
	private ConfigurationService configurationService;
	
	public ConfigurationController(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	
	@GetMapping("/configure")
	public ResponseEntity<String> configure(String service, String jiraUsername, String jiraPassword, String githubUsername, String githubPassword) {
		if (configurationService.configure(service, jiraUsername, jiraPassword, githubUsername, githubPassword)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}

@Service
public class ConfigurationService {
	
	private ConfigurationRepository configurationRepository;
	
	public ConfigurationService(ConfigurationRepository configurationRepository) {
		this.configurationRepository = configurationRepository;
	}
	
	public boolean configure(String service, String jiraUsername, String jiraPassword, String githubUsername, String githubPassword) {
		Configuration configuration = new Configuration(service, jiraUsername, jiraPassword, githubUsername, githubPassword);
		return configurationRepository.save(configuration) != null;
	}
}

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
	
}

@Entity
public class Configuration {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String service;
	private String jiraUsername;
	private String jiraPassword;
	private String githubUsername;
	private String githubPassword;
	
	public Configuration() {
		
	}
	
	public Configuration(String service, String jiraUsername, String jiraPassword, String githubUsername, String githubPassword) {
		this.service = service;
		this.jiraUsername = jiraUsername;
		this.jiraPassword = jiraPassword;
		this.githubUsername = githubUsername;
		this.githubPassword = githubPassword;
	}
	
	// getters and setters
}