package com.nt.niranjana.main;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class PasswordEncode
{

	public static void main(String[] args) throws Exception
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pwd = encoder.encode("sam1");
		System.out.println("sisu as encrypted: "+pwd);
		
	}
}
