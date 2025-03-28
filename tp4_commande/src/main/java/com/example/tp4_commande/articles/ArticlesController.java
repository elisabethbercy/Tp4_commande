
package com.example.tp4_commande.articles;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.tp4_commande.commandes.Commandes;
import com.example.tp2.tp4_commande.CommandesInterface;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/articles")

public class ArticlesController {

    @Autowired 
    private ArticlesInterface artService;
    @Autowired 
    private CommandesInterface comService;

    @PostMapping("/newarticle")
    public RedirectView newarticle(
        // @RequestParam Long id,
        @RequestParam String nomArticle,
        @RequestParam int qteArticle,
        @RequestParam int prixArticle,
        HttpSession session
    ){
        String user_email = (String) session.getAttribute("user_email");
        System.out.println(user_email+"  check if user_email is in session  new article");

        String nomCommande = (String) session.getAttribute("nomCommande");
        System.out.println(nomCommande+"  check if nomCommande is in session new article");
        
        

        Long idCommande = (Long) session.getAttribute("idCommande");
        System.out.println(idCommande+" <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        if (user_email == null) {
            // Redirect to home if no user 
            return new RedirectView("redirect:/store/home");
        }

        if(nomCommande == null){
            // Redirect to home if no user
            return new RedirectView("redirect:/commandes/commandes");
        }

        Commandes commandes = comService.findById(idCommande).orElseThrow(()-> new NoSuchElementException());
        session.setAttribute("idCommande", commandes.getId());

        artService.newArticle(nomArticle, qteArticle, prixArticle, commandes);

        System.out.println(" ===========> User email saved in new article from Controller newarticle "+ user_email);

        System.out.println(" ===========> Commande ID saved in new article from Controller newarticle "+ commandes.getId());

        return new RedirectView("/articles/article?idCommande="+ commandes.getId());
    }


   


    @GetMapping("/article")
    public ModelAndView article(
        HttpSession session,
        @RequestParam("idCommande") Long id
    ){
        Long idCommande = (Long) session.getAttribute("idCommande");

        if(idCommande == null){
            return new ModelAndView("redirect:/commandes/commandes");
        }

        Optional<Commandes> commandes = comService.findById(idCommande);

        if(commandes.isEmpty()){
            return new ModelAndView("redirect:/commandes/commandes");
        }


        List<Articles> listArtByCom = artService.getArticlesByCommandes(commandes.get());
        Map<String,Object> model = Map.of("articles", listArtByCom);
        // session.setAttribute("articles", );

        return new ModelAndView("/store/article",model);
    }

    @GetMapping("/deleteArticle/{idArticle}")
    public RedirectView deleteArticle(
        @PathVariable Long idArticle,
        HttpSession session
    ){
        Long idCommande = (Long) session.getAttribute("idCommande");
        System.out.println("check ======> idCommande: In deleted article " + idCommande);

        artService.deleteArticleByID(idArticle);

        return new RedirectView("/articles/article?idCommande="+ idCommande);
    }        
    
    @PostMapping("/backToCommande")
    public RedirectView backToCommande(
        HttpSession session
    ){
        Long idCommande = (Long) session.getAttribute("idCommande");
        System.out.println("check ======> idCommande: In back to commande " + idCommande);

        return new RedirectView("/commandes/commandes");
    }

    @PostMapping("/backToNewArticle")
    public RedirectView backToNewArticle(
        HttpSession session
    ){
        Long idCommande = (Long) session.getAttribute("idCommande");
        System.out.println("check ======> idCommande: In back to new article " + idCommande);

        return new RedirectView("/articles/article?idCommande=" + idCommande);
    }



    @GetMapping("/listarticlesbycommande")
    public ModelAndView listarticlesbycommande(
        @RequestParam Long id,
        HttpSession session
    ){
        Long idCommande = (Long) session.getAttribute("idCommande");
        System.out.println("check ======> idCommande: In article " + idCommande);


        Optional<Commandes> commandes = comService.findById(idCommande);

        List<Articles> listArtByCom = artService.getArticlesByCommandes(commandes.get());
        Map<String,Object> model = Map.of("articles", listArtByCom);

        return new ModelAndView("/store/article",model);
    }



    @GetMapping("/listarticles")
    public ModelAndView listarticles(){
        List<Articles> listAllArticles = artService.findAll();

        Map<String,Object> model = Map.of("articles", listAllArticles);

        System.out.println("Listes d'articles: " + listAllArticles);

        return new ModelAndView("/store/article", model);
    }



}