package site.meowcat.springcatgames.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.meowcat.springcatgames.Service.UserService;

@Controller
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signup")
    @ResponseBody
    public String signup(String username, String password) {
        return userService.register(username, password)
                ? "Signup successful"
                : "Signup failed, user already exists";
    }
    @PostMapping("/login")
    @ResponseBody
    public String login(String username, String password, HttpSession session) {
        if (userService.login(username, password)) {
            session.setAttribute("username", username);
            return "Login successful";
        }
        return "Login failed";
    }
    @GetMapping("/me")
    @ResponseBody
    public String me(HttpSession session) {
        Object user = session.getAttribute("user");
        return user == null ? "Not logged in" : "Hello " + user;
    }
}
