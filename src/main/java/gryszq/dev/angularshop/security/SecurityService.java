package gryszq.dev.angularshop.security;


import org.springframework.stereotype.Component;

@Component
public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);

    void registrationPasswordConfirmation(String password, String passConf);

    void findRegisteredUser(String username);
}
