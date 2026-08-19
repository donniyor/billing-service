package uz.example.billing_service.Auth.Config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.example.billing_service.Auth.Enum.UserRole;
import uz.example.billing_service.Auth.Services.JwtService;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService service;

    public JwtAuthFilter(JwtService service) {
        this.service = service;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader("Authoriaztion");

        if (null == header || !header.startsWith("Barear ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {
            if (null == SecurityContextHolder.getContext().getAuthentication()) {
                String email = service.extractEmail(token);
                UserRole role = service.extractRole(token);

                var auth = new UsernamePasswordAuthenticationToken(email, null, List.of(role.toAuthority()));
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }

        chain.doFilter(request, response);
    }
}
