·       Ability to select start date and end date.
·       Ability to open the calendar and enter the dates manually.
·       Ability to select the month from the drop down menu.
·       Date should display as per selection done by the user.
·       Drop down menu should be selectable to select the month.
·       Once date and year is selected automatically calendar should be closed.

Below is the code for the Spring Boot Application with Controller, Service and Repository classes for the given User Story:

Controller Class:

@RestController
public class DateSelectController {
    private DateSelectService dateSelectService;

    @Autowired
    public DateSelectController(DateSelectService dateSelectService) {
        this.dateSelectService = dateSelectService;
    }

    @PostMapping("/select-date")
    public ResponseEntity<?> selectDate(@RequestBody DateSelectRequest request) {
        DateSelectResponse response = dateSelectService.selectDate(request);
        return ResponseEntity.ok(response);
    }
}

Service Class:

@Service
public class DateSelectService {
    private DateSelectRepository dateSelectRepository;

    @Autowired
    public DateSelectService(DateSelectRepository dateSelectRepository) {
        this.dateSelectRepository = dateSelectRepository;
    }

    public DateSelectResponse selectDate(DateSelectRequest request) {
        DateSelectResponse response = dateSelectRepository.selectDate(request);
        return response;
    }
}

Repository Class:

@Repository
public class DateSelectRepository {
    public DateSelectResponse selectDate(DateSelectRequest request) {
        DateSelectResponse response = new DateSelectResponse();
        response.setStartDate(request.getStartDate());
        response.setEndDate(request.getEndDate());
        response.setMonth(request.getMonth());
        return response;
    }
}