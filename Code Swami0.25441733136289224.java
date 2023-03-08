
Controller

@RestController
@RequestMapping("/codeSwami")
public class CodeSwamiController {
    
    @Autowired
    private CodeSwamiService codeSwamiService;
    
    @PostMapping("/generateCode")
    public ResponseEntity<Void> generateCode(@RequestBody CodeSwami codeSwami) {
        codeSwamiService.generateCode(codeSwami);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/getUserStories")
    public ResponseEntity<List<UserStory>> getUserStories() {
        List<UserStory> userStories = codeSwamiService.getUserStories();
        return new ResponseEntity<>(userStories, HttpStatus.OK);
    }
    
    @GetMapping("/getLogs")
    public ResponseEntity<List<Log>> getLogs() {
        List<Log> logs = codeSwamiService.getLogs();
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }
    
    @GetMapping("/getGitHubLinks")
    public ResponseEntity<List<GitHubLink>> getGitHubLinks() {
        List<GitHubLink> gitHubLinks = codeSwamiService.getGitHubLinks();
        return new ResponseEntity<>(gitHubLinks, HttpStatus.OK);
    }
}

Service

@Service
public class CodeSwamiService {
    
    @Autowired
    private CodeSwamiRepository codeSwamiRepository;
    
    public void generateCode(CodeSwami codeSwami) {
        // code to generate code
    }
    
    public List<UserStory> getUserStories() {
        return codeSwamiRepository.findAllUserStories();
    }
    
    public List<Log> getLogs() {
        return codeSwamiRepository.findAllLogs();
    }
    
    public List<GitHubLink> getGitHubLinks() {
        return codeSwamiRepository.findAllGitHubLinks();
    }
}

Repository

@Repository
public interface CodeSwamiRepository extends JpaRepository<CodeSwami, Long> {

    List<UserStory> findAllUserStories();
    
    List<Log> findAllLogs();
    
    List<GitHubLink> findAllGitHubLinks();
    
}