package liga.shop.shop.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("OWNER")) {
            response.sendRedirect("/owner");
        }
        else {
            if (roles.contains("ADMIN")) {
                response.sendRedirect("/admin");
            }
            else {
                if (roles.contains("USER")) {
                    response.sendRedirect("/user");
                }
            }
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}