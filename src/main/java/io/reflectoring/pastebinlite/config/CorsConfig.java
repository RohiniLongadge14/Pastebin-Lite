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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // 🔥 VERY IMPORTANT
        config.setAllowCredentials(false);

        // ✅ Allow your Vercel frontend
        config.setAllowedOrigins(List.of(
                "https://pastebin-lite-frontend-ssbhcxudh-rohinis-projects-c95122e8.vercel.app",
                "https://pastebin-lite-frontend-82j0i7l6e-rohinis-projects-c95122e8.vercel.app"
        ));

        // ✅ Required methods
        config.setAllowedMethods(List.of(
                "GET", "POST", "PUT", "DELETE", "OPTIONS"
        ));

        // ✅ Required headers
        config.setAllowedHeaders(List.of(
                "Content-Type", "Authorization"
        ));

        // ✅ Expose JSON
        config.setExposedHeaders(List.of(
                "Content-Type"
        ));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}