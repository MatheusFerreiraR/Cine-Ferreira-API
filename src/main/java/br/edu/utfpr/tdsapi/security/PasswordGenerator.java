package br.edu.utfpr.tdsapi.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("m0bil3"));
		System.out.println(encoder.encode("senha123"));
	}
}
