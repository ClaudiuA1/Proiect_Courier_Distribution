package com.utcn.scdproiect.courier.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.core.userdetails.User;


import com.utcn.scdproiect.courier.Courier;
import com.utcn.scdproiect.courier.CourierRepository;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
  http
    .cors(cors -> cors.configurationSource(corsConfigurationSource()))
    .csrf(csrf -> csrf.disable())
    .authenticationProvider(authenticationProvider())
    .authorizeHttpRequests(auth -> auth
      .requestMatchers("/courier/login", "/courier/create","/auth/send-otp","/auth/verify-otp","/email/**").permitAll()
      .requestMatchers("/courier/**", "/package/**").authenticated()
      .anyRequest().permitAll()
    )
    // Activează Basic Auth (pentru debugging sau cereri simple)
    .httpBasic(Customizer.withDefaults())
    // Activează form-login ca să primeşti JSESSIONID
    .formLogin(form -> form
      // URL-ul pe care front-end-ul îl va apela
      .loginProcessingUrl("/courier/login")
      // În caz de succes, nu redirectează, doar răspunde 200
      .successHandler((req, res, auth) -> res.setStatus(HttpServletResponse.SC_OK))
      // În caz de eşec, răspunde 401
      .failureHandler((req, res, ex) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED))
      // NU defini un .defaultSuccessUrl(), nu vrei redirect
    )
    .logout(logout -> logout
        .logoutUrl("/courier/logout")                              // endpoint de logout
        .logoutSuccessHandler((req, res, auth) -> {
          res.setStatus(HttpServletResponse.SC_OK);                // răspunde 200 OK
        })
        .invalidateHttpSession(true)                              // invalidează sesiunea
        .deleteCookies("JSESSIONID")                              // șterge cookie-ul
      )
    // Sesiuni: crează doar dacă e nevoie
    .sessionManagement(sm -> sm
      .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
    );

  return http.build();
}



    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:8081"));       // origine frontend
        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE")); 
        config.setAllowedHeaders(List.of("*"));                          
        config.setAllowCredentials(true);                                

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
private CourierRepository courierRepository;

@Bean
public UserDetailsService userDetailsService() {
    return username -> {
        // aici username e ceea ce pui tu în Basic Auth (adică email-ul)
        Courier c = courierRepository.findByemail(username);
        if (c == null) {
            throw new UsernameNotFoundException("Nu există curier cu emailul: " + username);
        }
        // transformă-ți Courier-ul în UserDetails
        return User.builder()
                   .username(c.getEmail())
                   .password(c.getPassword())         // parola deja bCrypt-uită
                   .roles("COURIER")                  // sau ce roluri vrei tu
                   .build();
    };
}

@Bean
public DaoAuthenticationProvider authenticationProvider() {
  DaoAuthenticationProvider p = new DaoAuthenticationProvider();
  p.setUserDetailsService(userDetailsService());
  p.setPasswordEncoder(passwordEncoder());
  return p;
}



}
