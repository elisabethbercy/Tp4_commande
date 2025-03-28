package com.example.tp2.users;

import java.util.Optional;



public interface UserInterface {
	
	void create(String nom, String prenom,String email,  String modepasse);
	public Optional<Users>findById(String email);
	boolean existById(String email);

    public Optional<Users> findByEmail(String email);



	
}
