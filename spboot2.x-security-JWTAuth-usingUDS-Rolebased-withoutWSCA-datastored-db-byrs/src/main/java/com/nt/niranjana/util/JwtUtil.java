package com.nt.niranjana.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Value("${token.secret}")
	private String secret;
	
	
	private String generateToken(Map<String, Object> claims, String subject) 
	{
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)  //subject means "username"
				.setIssuer("EIDIKO") ////token provider name
				.setIssuedAt(new Date(System.currentTimeMillis()))//token issue time
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)))//token expire time 10 minutes
				.signWith(SignatureAlgorithm.HS256, secret.getBytes())//see the algorithm and secret
				.compact();
	}
	
	//here using subject/username, token will generate then later token has claimed
	public String generateToken(String subject) 
	{
		Map<String, Object> claims = new HashMap<>();
		return generateToken(claims, subject);
	}
	
	//To know the token is valid or not, then we need to Claim the token and get the details from token
	private Claims getClaims(String token)
	{
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
	}
	
	//read only username from token
	public String getUsername(String token) 
	{
		return getClaims(token).getSubject();
	}

	//get token Details
	public  Claims getTokenDetails(String token)
	{
		
		Claims c =	Jwts.parser()   //parser means details
				.setSigningKey(secret.getBytes())
				.parseClaimsJws(token).getBody();
				
				System.out.println("Token Username :"+c.getSubject());
				System.out.println("Token Provided by: "+c.getIssuer());
				System.out.println("Token Issue on: "+c.getIssuedAt());
				System.out.println("Token ID: "+c.getId());
				System.out.println("Token Expire on: "+c.getExpiration());
		return c;
	}
}
