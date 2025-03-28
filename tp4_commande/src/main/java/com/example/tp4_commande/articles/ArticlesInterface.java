package com.example.tp4_commande.articles;

import java.util.List;

import com.example.tp4_commande.commandes.Commandes;

public interface ArticlesInterface {

    Articles newArticle(String nomArticle, int qteArticle, int prixArticle,  Commandes commandes);
    
    List<Articles> findByCommandes(Commandes commandes);

    List<Articles> findByIdCommande(Commandes commandes);

    List<Articles> getArticlesByIdCommandes(Commandes commandes);

    List<Articles> getArticlesByCommandes(Commandes commandes);

    public List<Articles>findAll();

    void deleteArticleByID(Long id);

    void validateArticleByID(Long id);

   

    
    
}
