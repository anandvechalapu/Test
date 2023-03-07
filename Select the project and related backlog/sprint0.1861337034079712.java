
Generated Spring Boot application with controller, service and repository classes:
Controller class: 
@RestController
@RequestMapping("/project")
public class ProjectController {
 
    @Autowired
    private ProjectService projectService;
 
    @GetMapping
    public ResponseEntity<?> getProjectsAndUserStories(){
        return ResponseEntity.ok(projectService.getProjectsAndUserStories());
    }
}

Service class:
@Service
public class ProjectService {
 
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private JiraApi jiraApi;
 
    public List<Project> getProjectsAndUserStories(){
        List<Project> projects = projectRepository.findAll();
        for (Project project : projects) {
            List<UserStory> userStories = jiraApi.getUserStoriesByProject(project);
            project.setUserStories(userStories);
        }
        return projects;
    }
}

Repository class:
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAll();
 }