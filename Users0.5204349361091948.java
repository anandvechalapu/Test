W- 
Wholesaler.
B- 
Branch

Below is a Spring Boot application with controller, service, and repository classes for the given user story:

Controller:

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
    
    @PostMapping("/users/add")
    public void addUser(@RequestBody User user) {
        if (user.getUserName() == null || user.getUserName().isEmpty()) {
            throw new BadRequestException("USER USERNAME BLANK");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new BadRequestException("USER PASSWORD BLANK");
        }
        if (user.getLevel() == null || user.getLevel().isEmpty()) {
            throw new BadRequestException("USER LEVEL BLANK");
        }
        if (user.getParentType() == null || user.getParentType().isEmpty()) {
            throw new BadRequestException("USER PARENTTYPE BLANK");
        }
        if ((user.getParentType().equals("W") && (user.getWholesaler() == null || user.getWholesaler().isEmpty()))) {
            throw new BadRequestException("USER WHOLESALER BLANK");
        }
        if ((user.getParentType().equals("B") && (user.getBranch() == null || user.getBranch().isEmpty()))) {
            throw new BadRequestException("USER BRANCH BLANK");
        }
        if (user.getParentType().equals("U")) {
            if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
                throw new BadRequestException("USER FIRSTNAME BLANK");
            }
            if (user.getLastName() == null || user.getLastName().isEmpty()) {
                throw new BadRequestException("USER LASTNAME BLANK");
            }
            if (user.getCompany() == null || user.getCompany().isEmpty()) {
                throw new BadRequestException("USER COMPANY BLANK");
            }
            if (user.getPostcode() == null || user.getPostcode().isEmpty()) {
                throw new BadRequestException("USER POSTCODE BLANK");
            }
            if (user.getTelephoneNo() == null || user.getTelephoneNo().isEmpty()) {
                throw new BadRequestException("USER TELEPHONENO BLANK");
            }
            if (user.getEmailAddress() == null || user.getEmailAddress().isEmpty()) {
                throw new BadRequestException("USER EMAIL ADDRESS BLANK");
            }
            if (!user.getEmailAddress().matches("[A-Za-z0-9]+@[A-Za-z0-9]+.[A-Za-z0-9]+")) {
                throw new BadRequestException("USER EMAIL ADDRESS INVALID");
            }
            try {
                InetAddress.getByName(user.getEmailAddress().split("@")[1]);
            } catch (UnknownHostException e) {
                throw new BadRequestException("USER EMAIL HOSTNAME DOES NOT RESOLVE");
            }
        }
        userService.addUser(user);
    }
    
    @PostMapping("/users/edit")
    public void editUser