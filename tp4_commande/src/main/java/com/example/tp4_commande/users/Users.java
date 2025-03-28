package com.example.tp4.users;

import java.util.ArrayList;
import java.util.List;

import com.example.tp4_commande.commandes.Commandes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity

public class Users {
	
	
	public Users() {
		this.commandes = new ArrayList<>();
	}


	
	@Id
	private String email;

	private String nom;
	private String prenom;
	private String motdepasse;

	@OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
	private List<Commandes> commandes;

	
	
	public Users(String nom, String prenom, String email, String motdepasse) {
		super();
	
		this.nom = nom;
		this.prenom = prenom;
		this.motdepasse = motdepasse;
		this.email = email;
		this.commandes = new ArrayList<>();
	}
	
	
	
	public List<Commandes> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commandes> commandes) {
		this.commandes = commandes;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMotdepasse() {
		return motdepasse;
	}
	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public void addCommande(Commandes commande) {
		commande.setUsers(this);
		commandes.add(commande);
    }
}
