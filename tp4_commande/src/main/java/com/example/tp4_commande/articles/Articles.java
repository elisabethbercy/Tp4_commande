package com.example.tp4_commande.articles;

import com.example.tp4_commande.commandes.Commandes;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Articles {

    @Id
    @GeneratedValue
    private long idArticle;
  

    private String nomArticle;
    private int qteArticle;
    private int prixArticle;

    

    // joining commande  nomCommande column to article table
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id")
    private Commandes commandes;    

    public Articles(){

    }

    public Articles(String nomArticle, int qteArticle, int prixArticle, Commandes commandes) {
        this.nomArticle = nomArticle;
        this.qteArticle = qteArticle;
        this.prixArticle = prixArticle;
        this.commandes = commandes;
    }

    public long getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(long idArticle) {
        this.idArticle = idArticle;
    }



    
    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public int getQteArticle() {
        return qteArticle;
    }

    public void setQteArticle(int qteArticle) {
        this.qteArticle = qteArticle;
    }

    public int getPrixArticle() {
        return prixArticle;
    }

    public void setPrixArticle(int prixArticle) {
        this.prixArticle = prixArticle;
    }
  

    public Commandes getCommandes() {
        return commandes;
    }

    public void setCommandes(Commandes commandes) {
        this.commandes = commandes;
    }

}
