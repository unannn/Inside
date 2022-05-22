package unannn.inside.config.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import unannn.inside.web.dto.LoginDto;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoginFailedHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
//        request.setAttribute("loginDto", new LoginDto(username, password));
        request.getParameterMap().entrySet().forEach(o1 -> System.out.println(o1.getKey() +  " = " + o1.getValue()[0]));
//        request.setAttribute("loginDto",new LoginDto(username,password));
        request.getRequestDispatcher("/login").forward(request, response);
    }
}
