package gryszq.dev.angularshop.service;

import gryszq.dev.angularshop.model.Role;
import gryszq.dev.angularshop.model.User;
import gryszq.dev.angularshop.repository.RoleRepository;
import gryszq.dev.angularshop.repository.UserRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleRepository.findByName("użytkownik"));
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    @Override
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){

        Role admin = new Role("admin");
        Role normalUser = new Role("użytkownik");

        roleRepository.save(admin);
        roleRepository.save(normalUser);

        User user1 = new User("marek", "Marekgr7@gmail.com", bCryptPasswordEncoder.encode("1234"), "dzikdzik");
        User user2 = new User("paulina", "Paulina@gmail.com", bCryptPasswordEncoder.encode("1234"), "dzikdzik");

        user1.addRole(admin);
        user2.addRole(normalUser);

        userRepository.save(user1);
        userRepository.save(user2);
    }
}
