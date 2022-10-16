package com.springsecurity.securityconfig;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.springsecurity.utilities.JwtToken;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private JwtToken jwtToken;
	@Autowired
	private SecurityUserDetails securityUserDetails;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
	String token = request.getHeader("Authorization");	
	if(token!=null)
	{
		String userName = jwtToken.getJwtTokenUserName(token);
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetails = securityUserDetails.loadUserByUsername(userName);
			boolean validateToken = jwtToken.validateToken(userDetails.getUsername(), token);
			if(validateToken)
			{
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userName, userDetails.getPassword(),userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
	}
		
	filterChain.doFilter(request, response);	
	}
}
