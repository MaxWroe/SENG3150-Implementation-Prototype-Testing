package group3.seng3150;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    //get method
    @GetMapping("/login")
    public ModelAndView displayLogin() {
        ModelAndView view = new ModelAndView("login");
        return view;
    }

    //post method
   @PostMapping("/login")
    public ModelAndView executeLogin(@RequestParam("username") String username){
        ModelAndView view = null; 

        try{
            //insert is valid user code here from database

            //test
            view = new ModelAndView("userHome");
            view.addObject("username", username);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return view;
    }
}
