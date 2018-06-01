package id.co.infotech.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Kitchen {

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("@dmin#123"));
	}

}
