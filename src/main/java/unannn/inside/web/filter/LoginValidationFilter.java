package unannn.inside.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class LoginValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if(HttpMethod.POST.matches( httpRequest.getMethod())){

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if(username.length() == 0 || password.length() == 0){
                request.getRequestDispatcher("/login").forward(request, response);
                return;
            }
        }

        chain.doFilter(request,response);
    }
}
