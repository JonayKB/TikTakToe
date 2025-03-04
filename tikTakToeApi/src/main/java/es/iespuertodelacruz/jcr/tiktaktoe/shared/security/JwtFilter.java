package es.iespuertodelacruz.jcr.tiktaktoe.shared.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    public static final String authHeader = "Authorization";
    public static final String authHeaderTokenPrefix = "Bearer ";

    @Autowired
    private JwtService jwtTokenManager;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // rutas permitidas sin estar autenticado
        String rutasPermitidas[] = { "/swagger-ui.html",
                "/swagger-ui/", "/v2/",
                "configuration/", "/swagger",
                "/webjars/", "/api/login",
                "/api/register", "/v3/", "/api/verify",
                "/websocket", "/index.html", "/api/v1", "/h2-console", "/api/images"
        };

        // String rutasPermitidas[] = {};
        for (String ruta : rutasPermitidas) {
            if (path.startsWith(ruta)) {
                // Permitir la solicitud sin autenticación
                filterChain.doFilter(request, response);
                return;
            }
        }

        // el token viene en un header Authorization
        String header = request.getHeader(authHeader);

        // típicamente se precede el token con bearer: Bearer token
        if (header != null && header.startsWith(authHeaderTokenPrefix)) {

            String token = header.substring(authHeaderTokenPrefix.length());
            try {
                Map<String, String> mapInfoToken = jwtTokenManager.validateAndGetClaims(token);
                final String correo = mapInfoToken.get("mail");
                final String rol = mapInfoToken.get("role");
                UserDetails userDetails = new UserDetails() {

                    String username = correo;

                    @Override
                    public Collection<? extends GrantedAuthority> getAuthorities() {
                        List<GrantedAuthority> authorities = new ArrayList<>();

                        authorities.add(new SimpleGrantedAuthority(rol));
                        return authorities;
                    }

                    @Override
                    public String getPassword() {
                        return null;
                    }

                    @Override
                    public String getUsername() {
                        return username;
                    }

                };

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);

                filterChain.doFilter(request, response);

            } catch (JWTVerificationException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"You are not authenticated. Login on host/api/login.\"}");
            return;
        }

    }
}
