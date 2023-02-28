
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@Controller
public class ServiceConfigurationController {

    @Autowired
    private ServiceConfigurationService serviceConfigurationService;

    @GetMapping("/")
    public String showServiceMenu(){
        return "service_menu";
    }

    @GetMapping("/configure")
    public String showConfigurationPage(){
        return "configuration_page";
    }

    @PostMapping("/configure")
    public String configureService(@RequestParam String jiraCredentials,
                                   @RequestParam String githubCredentials) {
        if(serviceConfigurationService.validateCredentials(jiraCredentials, githubCredentials)) {
            return "service_home";
        }
        else {
            return "error_message";
        }
    }
}

@Service
public class ServiceConfigurationService {

    public boolean validateCredentials(String jiraCredentials, String githubCredentials) {
        // validate jira and github credentials
        return true;
    }
}

@Repository
public interface ServiceConfigurationRepository extends JpaRepository<ServiceConfiguration, Long> {

}