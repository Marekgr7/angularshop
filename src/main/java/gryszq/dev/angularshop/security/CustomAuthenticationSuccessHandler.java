package gryszq.dev.angularshop.security;



import gryszq.dev.angularshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();

        try{
            System.out.println(user.getUsername());
//            Cookie cookie = new Cookie("userId", userRepository.findByUsername(user.getUsername()).getId().toString());
            Cookie cookie = new Cookie("userId", "1");
            cookie.setMaxAge(7*24*60*60);
            httpServletResponse.addCookie(cookie);
            httpServletResponse.sendRedirect("/#!/products");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
