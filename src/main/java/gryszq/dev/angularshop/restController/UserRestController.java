package gryszq.dev.angularshop.restController;

import gryszq.dev.angularshop.model.User;
import gryszq.dev.angularshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/users")
public class UserRestController {


    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> findAll(){
        return userService.findAll();
    }

    @DeleteMapping
    public void deleteUser(Long id){
        userService.deleteUser(id);
    }

    @GetMapping
    public Optional<User> getUserById(Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    public void saveUser(@RequestBody User user){

        userService.save(user);
    }
}
