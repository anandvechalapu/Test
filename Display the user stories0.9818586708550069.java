
Generated Spring Boot Application with Controller, Service and Repository Classes:

Controller Class:

@RestController
public class UserStoryController {

    private final UserStoryService userStoryService;

    public UserStoryController(UserStoryService userStoryService) {
        this.userStoryService = userStoryService;
    }

    @GetMapping("/stories")
    public List<UserStory> getUserStories(){
        return userStoryService.getUserStories();
    }

    @GetMapping("/stories/{id}")
    public UserStory getUserStory(@PathVariable Long id){
        return userStoryService.getUserStory(id);
    }
}

Service Class:

@Service
public class UserStoryService {

    private final UserStoryRepository userStoryRepository;

    public UserStoryService(UserStoryRepository userStoryRepository) {
        this.userStoryRepository = userStoryRepository;
    }

    public List<UserStory> getUserStories(){
        return userStoryRepository.findAll();
    }

    public UserStory getUserStory(Long id){
        return userStoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserStory not found with id: " + id));
    }
}

Repository Class:

@Repository
public interface UserStoryRepository extends JpaRepository<UserStory, Long> {

}