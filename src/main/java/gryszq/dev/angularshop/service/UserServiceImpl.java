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
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
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
        save(new User("marek", "Marekgr7@gmail.com", "dzikdzik", "dzikdzik"));
        save(new User("paulina", "Paulina@gmail.com", "dzikdzik", "dzikdzik"));
    }
}
