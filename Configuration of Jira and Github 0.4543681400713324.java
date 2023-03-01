The user should be able to access the selected service from within the application.

Spring Boot Application

Controller:

@RestController
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @PostMapping("/configure")
    public ResponseEntity<String> configureService(@RequestBody ConfigurationRequest request) {
        boolean isConfigured = configurationService.configureService(request);
        if (isConfigured) {
            return ResponseEntity.ok("Service configured successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid credentials, please try again");
        }
    }
}

Service:

@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    public boolean configureService(ConfigurationRequest request) {
        JiraCredentials jiraCredentials = request.getJiraCredentials();
        GitHubCredentials gitHubCredentials = request.getGitHubCredentials();
        //Validate the credentials
        if (configurationRepository.validateCredentials(jiraCredentials, gitHubCredentials)) {
            //Save the credentials
            configurationRepository.saveCredentials(jiraCredentials, gitHubCredentials);
            return true;
        }
        return false;
    }

}

Repository:

@Repository
public class ConfigurationRepository {

    //Validate the credentials
    public boolean validateCredentials(JiraCredentials jiraCredentials, GitHubCredentials gitHubCredentials) {
        // Logic to validate the credentials
        return true;
    }

    //Save the credentials
    public void saveCredentials(JiraCredentials jiraCredentials, GitHubCredentials gitHubCredentials) {
        // Logic to save the credentials
    }
}