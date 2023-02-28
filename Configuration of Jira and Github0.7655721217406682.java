
Spring Boot Application:

Controller:

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigurationController {

    private final ConfigurationService configurationService;

    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @GetMapping
    public String getConfigurationPage() {
        return "Configuration page";
    }

    @PostMapping
    public String configure(@RequestParam String jira, @RequestParam String github) {
        if (configurationService.validateCredentials(jira, github)) {
            return "Service page";
        } else {
            return "Error message";
        }
    }
}

Service:

import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {

    public boolean validateCredentials(String jira, String github) {
        // Validate the credentials
        return true;
    }
}

Repository:

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {

}