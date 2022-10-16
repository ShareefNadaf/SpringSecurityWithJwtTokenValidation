package com.springsecurity.utilities;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtToken {

	@Value("${secreatkey}")
	private String secreatKey;
	
	public boolean validateToken(String userName,String token)
	{
		String tokenUserName = getJwtTokenUserName(token);
		return (userName.equals(tokenUserName) && !isTokenExpired(token));
	}
	
	public boolean isTokenExpired(String token)
	{
		return getJwtTokenExpirationDate(token).before(new Date(System.currentTimeMillis()));
	}
	
	public Date getJwtTokenExpirationDate(String token)
	{
		return getJwtTokenDetails(token).getExpiration();
	}
	
	public String getJwtTokenUserName(String token)
	{
		return getJwtTokenDetails(token).getSubject();
	}
	
	public Claims getJwtTokenDetails(String token)
	{
		return Jwts.parser().setSigningKey(secreatKey.getBytes())
				.parseClaimsJws(token)
				.getBody();
	}
	
	public String generateJwtToken(String subject)
	{
		return Jwts.builder().setSubject(subject)
				.setIssuer("Shareef").setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)))
				.signWith(SignatureAlgorithm.HS512, secreatKey.getBytes()).compact();
	}
}
