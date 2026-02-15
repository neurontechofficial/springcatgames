package site.meowcat.springcatgames.Service;

import org.springframework.stereotype.Service;
import site.meowcat.springcatgames.Models.User;
import tools.jackson.databind.ext.javatime.ser.DurationSerializer;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
    private final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();
    public boolean register(String username, String password) {
        if(users.containsKey(username)) return false;
        users.put(username, new User(username, password));
        return true;
    }
    public boolean login(String username, String password) {
        User u = users.get(username);
        return u != null && u.getPassword().equals(password);
    }
}
