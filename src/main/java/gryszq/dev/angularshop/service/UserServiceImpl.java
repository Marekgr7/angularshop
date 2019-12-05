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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public User findUserById(Long id){
        return userRepository.getOne(id);
    }


    public List<User> findAllQuery(){

//        List<Object[]> queryResult = orderRepository.findAllQuery(id);
//
//        List<Order> queryList = new ArrayList<>();
//        for(Object[] obj : queryResult){
//            BigInteger queryId = (BigInteger) obj[0];
//            Boolean queryStatus = (Boolean) obj[1];
//            Double queryTotal = (Double) obj[2];
//            queryList.add(new Order(queryId.longValue(),queryTotal,queryStatus));
//        }
//        return queryList;

        List<Object[]> queryResult = userRepository.findAllQuery();

        List<User> queryList = new ArrayList<>();
        for(Object[] obj : queryResult){
            BigInteger queryId = (BigInteger) obj[0];
            String queryName = (String) obj[1];
            String queryUsername = (String) obj[2];
            BigInteger queryRole = (BigInteger) obj[3];
            queryList.add(new User(queryId.longValue(), queryName , queryUsername,queryRole.intValue()));
        }

        return queryList;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){

        Role admin = new Role("admin");
        Role normalUser = new Role("użytkownik");

        roleRepository.save(admin);
        roleRepository.save(normalUser);

        User user1 = new User("marek", "Marekgr7@gmail.com", bCryptPasswordEncoder.encode("1234"), "1234");
        User user2 = new User("paulina", "Paulina@gmail.com", bCryptPasswordEncoder.encode("1234"), "1234");

        user1.addRole(admin);
        user2.addRole(normalUser);

        userRepository.save(user1);
        userRepository.save(user2);
    }
}
