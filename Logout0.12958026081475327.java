·       User should be able to log out of their account without any issue.

Controller:
@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(){
        return "login";
    }

}

Service:
@Service
public class LogoutService {

    public void logout(){
        //logic to logout
    }

}

Repository:
@Repository
public class LogoutRepository {

    public void logout(){
        //logic to logout
    }

}