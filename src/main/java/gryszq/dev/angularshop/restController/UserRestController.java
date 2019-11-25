package gryszq.dev.angularshop.restController;

import gryszq.dev.angularshop.model.User;
import gryszq.dev.angularshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



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

}
