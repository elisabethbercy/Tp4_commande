package com.example.tp4_commande.commandes;


import java.util.ArrayList;
import java.util.List;

import com.example.tp4_commande.articles.Articles;
import com.example.tp4_commande.users.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Commandes {


    public Commandes(){
        
    }
    
    @Id
    @GeneratedValue
    private Long id;

    private String nomCommande;

    // joining users email column to Commandes table
    @ManyToOne
    @JoinColumn(name = "email")
    private Users users;

    // joining nomCommande column to article table
    @OneToMany(mappedBy = "commandes", fetch = FetchType.EAGER)
    private List<Articles> articles;

    public Commandes(String nomCommande, Users users) {
       
        this.nomCommande = nomCommande;
        this.users =  users;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomCommande() {
        return nomCommande;
    }

    public void setNomCommande(String nomCommande) {
        this.nomCommande = nomCommande;
    }

    public void addArticle(Articles article) {
        article.setCommandes(this);
        articles.add(article);
    }
    

}
