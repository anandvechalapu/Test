The feature has been deployed to production.

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@Controller
public class CodeGenerationController {
    @Autowired
    private CodeGenerationService codeGenerationService;

    @GetMapping("/codeGeneration")
    public String codeGeneration(Model model) {
        // Prompt user to enter code generation for Java/.Net/Python/Node
        model.addAttribute("languageOptions", codeGenerationService.getLanguageOptions());
        return "codeGeneration";
    }

    @PostMapping("/codeGeneration")
    public String codeGeneration(@RequestParam("language") String language, Model model) {
        // Prompt user to select patterns from a list of available options
        model.addAttribute("patternOptions", codeGenerationService.getPatternOptions(language));
        // Prompt user to select a design
        model.addAttribute("designOptions", codeGenerationService.getDesignOptions(language));
        return "codeGeneration";
    }

    @PostMapping("/codeGeneration/jira")
    public String codeGenerationJira(@RequestParam("language") String language,
                                    @RequestParam("pattern") String pattern,
                                    @RequestParam("design") String design,
                                    Model model) {
        // Prompt user to select a Jira project and provide details
        model.addAttribute("jiraOptions", codeGenerationService.getJiraOptions(language, pattern, design));
        return "codeGeneration";
    }

    @PostMapping("/codeGeneration/github")
    public String codeGenerationGithub(@RequestParam("language") String language,
                                      @RequestParam("pattern") String pattern,
                                      @RequestParam("design") String design,
                                      @RequestParam("jira") String jira,
                                      Model model) {
        // Prompt user to select a GitHub repository and configure settings for publishing
        model.addAttribute("githubOptions", codeGenerationService.getGithubOptions(language, pattern, design, jira));
        return "codeGeneration";
    }

    @PostMapping("/codeGeneration/generate")
    public String codeGenerationGenerate(@RequestParam("language") String language,
                                        @RequestParam("pattern") String pattern,
                                        @RequestParam("design") String design,
                                        @RequestParam("jira") String jira,
                                        @RequestParam("github") String github,
                                        Model model) {
        // Generate code based on user selections
        codeGenerationService.generateCode(language, pattern, design, jira, github);
        return "codeGeneration";
    }
}

@Service
public class CodeGenerationService {
    @Autowired
    private CodeGenerationRepository codeGenerationRepository;

    public List<String> getLanguageOptions() {
        // Get list of available language options
        return codeGenerationRepository.getLanguageOptions();
    }

    public List<String> getPatternOptions(String language) {
        // Get list of available pattern options for the selected language
        return codeGenerationRepository.getPatternOptions(language);
    }

    public List<String> getDesignOptions(String language) {
        // Get list of available design options for the selected language
        return codeGenerationRepository.getDesignOptions(language);
    }

    public List<String> getJiraOptions(String language, String pattern, String design) {
        // Get list of