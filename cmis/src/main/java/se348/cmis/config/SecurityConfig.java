package se348.cmis.config;

import java.io.PrintWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import se348.cmis.dto.GeneralMessage;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  UserDetailsService getAuthorization() {
    return new UserDetailService();
  }

  @Autowired
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(getAuthorization()).passwordEncoder(new BCryptPasswordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
        .cors().and().csrf().disable()
        .authorizeRequests()
        .antMatchers("*").permitAll()
        .antMatchers("/bank/**").hasAuthority("BANK")
        .antMatchers("/customer/**").hasAuthority("CUSTOMER")
        .and()
        .formLogin()
        .loginPage("/without_log")
        .successHandler((request, response, authentication) -> {
          /* Login success */
          String username = authentication.getName();
          response.setContentType("application/json;charset=utf-8");
          PrintWriter out = response.getWriter();
          GeneralMessage loginMessage = new GeneralMessage();
          loginMessage.setState(200);
          loginMessage.setMessage(authentication.getAuthorities().toString());
          loginMessage.setSucceeded(true);
          out.write(loginMessage.toString());
          out.flush();
          out.close();
        })
        .failureHandler((httpServletRequest, httpServletResponse, e) -> {
          /* Login failure */
          httpServletResponse.setContentType("application/json;charset=utf-8");
          PrintWriter out = httpServletResponse.getWriter();
          GeneralMessage loginMessage = new GeneralMessage();
          loginMessage.setState(401);
          loginMessage.setMessage(e.getMessage());
          loginMessage.setSucceeded(false);
          out.write(loginMessage.toString());
          out.flush();
          out.close();
        })
        .loginProcessingUrl("/login")
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessHandler((request, response, authentication) -> {
          /* Logout */
          response.setContentType("application/json;charset=utf-8");
          PrintWriter out = response.getWriter();
          request.getSession().invalidate();
          GeneralMessage loginMessage = new GeneralMessage();
          loginMessage.setState(200);
          loginMessage.setMessage("logout");
          loginMessage.setSucceeded(true);
          out.write(loginMessage.toString());
          out.flush();
          out.close();
        })
        .clearAuthentication(true)
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID");
  }
}
