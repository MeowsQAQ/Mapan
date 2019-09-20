package mapan.demo.controller;

import mapan.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletRequest;



@Controller
public class ProfileController {
    @GetMapping("/profile/{userId}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name="userId")Long userId,
                          Model model){
        User user =(User)request.getSession().getAttribute("user");
        if (user == null || userId!=user.getId()){
            return "redirect:/";
        }
        return "profile";
    }
}
