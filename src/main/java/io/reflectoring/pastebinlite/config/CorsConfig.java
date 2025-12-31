package io.reflectoring.pastebinlite.config;

import org.springframework.context.annotation.Configuration;

//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * @Configuration public class CorsConfig implements WebMvcConfigurer {
 * 
 * @Override public void addCorsMappings(CorsRegistry registry) {
 * registry.addMapping("/**") .allowedOriginPatterns("*") .allowedMethods("GET",
 * "POST", "PUT", "DELETE", "OPTIONS") .allowedHeaders("*"); } }
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class CorsConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Enable CORS configuration
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            // Disable CSRF for simplicity in this case (often required for stateful sessions)
            .csrf(csrf -> csrf.disable())
            // Authorize requests
            .authorizeHttpRequests(auth -> auth
                // Allow all OPTIONS requests (preflight) without authentication
                .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
                // Allow access to your API endpoint for specific methods, or permit all if open
                .requestMatchers("/api/pastes").permitAll()
                // Require authentication for other requests (adjust as needed)
                .anyRequest().authenticated()
            );
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Set the specific origin of your Vercel frontend deployment URL here
        configuration.setAllowedOrigins(List.of("https://pastebin-lite-frontend-hkz7jopzg-rohinis-projects-c95122e8.vercel.app")); // Replace with your actual Vercel URL
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Make sure OPTIONS is included
        configuration.setAllowedHeaders(List.of("*")); // Allows all headers
        configuration.setAllowCredentials(true); // If you use cookies or authorization headers
        configuration.setMaxAge(3600L); // Cache preflight response for 1 hour

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
