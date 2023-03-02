
Controller:

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/requests")
    public List<Request> getRequests() {
        return historyService.getRequests();
    }

    @GetMapping("/refresh")
    public ResponseEntity<Void> refresh() {
        historyService.refresh();
        return ResponseEntity.ok().build();
    }
}

Service:

@Service
public class HistoryService {

    private final RequestRepository requestRepository;

    public HistoryService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public List<Request> getRequests() {
        return requestRepository.findAll();
    }

    public void refresh() {
        List<Request> requests = fetchRequestsFromJavaApi();
        requestRepository.saveAll(requests);
    }

    private List<Request> fetchRequestsFromJavaApi() {
        // implementation to fetch requests from Java API
    }
}

Repository:

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

}