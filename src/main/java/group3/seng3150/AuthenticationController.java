package group3.seng3150;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {

    //get method login
    @GetMapping("/login")
    public ModelAndView displayLogin() {
        ModelAndView view = new ModelAndView("login");
        return view;
    }

    //get after successful register
    public ModelAndView displayLogin(@RequestParam("message") String message) {
        ModelAndView view = new ModelAndView("login");
        view.addObject("message", message);
        return view;
    }

    //post method login
   @PostMapping("/login")
    public ModelAndView executeLogin(@RequestParam("email") String email){
        ModelAndView view = null; 

        try{
            //insert is valid user code here from database

            //test
            view = new ModelAndView("userHome");
            view.addObject("email", email);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return view;
    }

    //get method register
    @GetMapping("/register")
    public ModelAndView displayRegister() {
        ModelAndView view = new ModelAndView("register");
        return view;
    }

    //post method register
    @PostMapping("/register")
    public ModelAndView executeRegister(@RequestParam("firstName") String firstName){
        ModelAndView view = null;
        String message = "Registration successful! ";

        try{
            //insert add user to database

            //test
            view = new ModelAndView("login");

            message += firstName;
            view.addObject("message", message);


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return view;
    }




}
