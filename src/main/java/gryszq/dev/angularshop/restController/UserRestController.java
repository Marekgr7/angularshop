package gryszq.dev.angularshop.restController;

import gryszq.dev.angularshop.model.User;
import gryszq.dev.angularshop.security.SecurityServiceImpl;
import gryszq.dev.angularshop.security.UserValidator;
import gryszq.dev.angularshop.service.UserService;
import gryszq.dev.angularshop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/users")
public class UserRestController {

    private UserService userService;

    private UserServiceImpl userServiceimpl;

    private UserValidator userValidator;

    private SecurityServiceImpl securityServiceImpl;

    @Autowired
    public UserRestController(UserService userService, UserServiceImpl userServiceimpl,UserValidator userValidator,SecurityServiceImpl securityServiceImpl) {
        this.userService = userService;
        this.userServiceimpl = userServiceimpl;
        this.userValidator = userValidator;
        this.securityServiceImpl = securityServiceImpl;
    }

    @GetMapping("/all")
    public List<User> findAll(){
        return userServiceimpl.findAllQuery();
    }

    @DeleteMapping
    public void deleteUser(Long id){
        userService.deleteUser(id);
    }

    @GetMapping
    public Optional<User> getUserById(Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/name")
    public User getUserByName(String username){
        return userService.findByUsername(username);
    }

    @PostMapping
    public void saveUser(@Valid @RequestBody User user){

        System.out.println(user.getUsername());

        securityServiceImpl.registrationPasswordConfirmation(user.getPassword(),user.getPasswordConfirm());

        securityServiceImpl.findRegisteredUser(user.getUsername());

        userService.save(user);
    }

//    @PostMapping
//    public String registration(@RequestBody User userForm, BindingResult bindingResult) {
//        userValidator.validate(userForm, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//
//        userService.save(userForm);
//
//        return "redirect:/#!/products";
//    }
}
