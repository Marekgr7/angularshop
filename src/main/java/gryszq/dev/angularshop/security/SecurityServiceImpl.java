package gryszq.dev.angularshop.security;

import gryszq.dev.angularshop.model.User;
import gryszq.dev.angularshop.service.UserServiceImpl;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class SecurityServiceImpl implements SecurityService{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    private UserServiceImpl userService;

    @Override
    public String findLoggedInUsername(){
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }
        return null;
    }

    @Override
    public void autoLogin(String username, String password){
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if(usernamePasswordAuthenticationToken.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug(String.format("Auto login %s successfully!",username));
        }

    }

    @Override
    public void registrationPasswordConfirmation(String password, String passConf){
        if (!password.equals(passConf)) {
            throw new IllegalArgumentException("hasła nie są zgodne");
        }
    }

    @Override
    public void findRegisteredUser(String username){
        try {
            User user = userService.findByUsername(username);
            if(user.getUsername().equalsIgnoreCase(username)){
                throw new IllegalArgumentException("Dany użytkownik już jest zarejestrowany");
            }
        }  catch (NullPointerException e){
            e.printStackTrace();
        }

    }
}
