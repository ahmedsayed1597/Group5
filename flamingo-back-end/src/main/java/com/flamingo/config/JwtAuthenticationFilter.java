package com.flamingo.config;




import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
          @NonNull HttpServletRequest request,
          @NonNull  HttpServletResponse response,
          @NonNull  FilterChain filterChain
  ) throws ServletException, IOException {
//        get the Jwt(Bearer) token  from header called Authorization -> should start with "Bearer "
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String username;

    if (authHeader == null || !authHeader.startsWith("Bearer ")){

//          go to next filter and pass request and response to the next one
      filterChain.doFilter(request,response);
      return;
    }
//       extract jwt token "Bearer "-> 7 chars
    jwt = authHeader.substring(7);
//        extract username from JWT token
    username = jwtService.extractUsername(jwt);
   
//        check if the user not auth yet
    if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
      if (jwtService.isTokenValid(jwt , userDetails)){

//                needed by security context holder to update security context
        UsernamePasswordAuthenticationToken authToken = new  UsernamePasswordAuthenticationToken(
                userDetails ,
                null,
                userDetails.getAuthorities()
                
        );
        System.out.println(authToken.getAuthorities());
        authToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request,response);
  }

// @Override
// protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//     String AutherizationHeader =request.getHeader("Authorization");
//     String userName = null;
//     String token = null;
//     if (AutherizationHeader != null && AutherizationHeader.startsWith("Bearer ")){
//         token=AutherizationHeader.substring(7);
//         userName=jwtService.extractUsername(token);
//     }
//     if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
//         UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
//         if (jwtService.isTokenValid(token,userDetails)){
//             UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                     new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//             usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//             SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//         }
//     }
//     filterChain.doFilter(request,response);
// }
}