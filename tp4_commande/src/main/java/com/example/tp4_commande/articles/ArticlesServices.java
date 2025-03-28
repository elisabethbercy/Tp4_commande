package com.example.tp4_commande.articles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tp4_commande.commandes.Commandes;

@Service
public class ArticlesServices implements ArticlesInterface {

    @Autowired
    private ArticlesRepository aRepo;

    @Override
    public Articles newArticle(String nomArticle, int qteArticle, int prixArticle, Commandes commandes) {
       Articles articles = new Articles();
        articles.setNomArticle(nomArticle);
        articles.setQteArticle(qteArticle);
        articles.setPrixArticle(prixArticle);
        articles.setCommandes(commandes);
        return aRepo.save(articles);
    }

    @Override
    public List<Articles> getArticlesByCommandes(Commandes commandes) {
    
        return aRepo.findByCommandes(commandes);
    }


       
    @Override
    public List<Articles> findAll() {

        return aRepo.findAll();
    }

    @Override
    public List<Articles> findByCommandes(Commandes commandes) {
        return aRepo.findByNomCommande(commandes.getNomCommande());
    }

    @Override
    public List<Articles> findByIdCommande(Commandes  commande) {
        return aRepo.findByIdCommande(commande.getId());
    }

    @Override
    public List<Articles> getArticlesByIdCommandes(Commandes commandes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArticlesByIdCommandes'");
    }

    @Override
    public void deleteArticleByID(Long id) {
        aRepo.deleteById(id);
    }

   


    
}
