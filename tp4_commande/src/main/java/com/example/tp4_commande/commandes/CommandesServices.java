package com.example.tp4_commande.commandes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tp4_commande.users.Users;


@Service
public class CommandesServices implements CommandesInterface{

    @Autowired
    private CommandesRepository cRepo;


    @Override
    public Commandes newCommande(String nomCommande, Users users) {
        Commandes commandes = new Commandes();
        commandes.setNomCommande(nomCommande);
        commandes.setUsers(users);
        return cRepo.save(commandes);
        
    }


    public List<Commandes> getCommandesByUsers(Users users){
        return cRepo.findByUsers(users);

        // check this
    }

    

    @Override
    public List<Commandes> findAll() {
        return cRepo.findAll();
    }

    public List<Commandes> findByUsers(Users  users){
        return cRepo.findByUserEmail(users.getEmail());
    }


    @Override
    public Commandes connected() {
       var usr1 = new Users("Bercy","Stephanie","bercy@gmail.com","1234");
       var com1 = new Commandes("Boissons",usr1);
      return cRepo.save(com1);

    }


    @Override
    public List<Commandes> findByNomCommande(String nomCommande) {
        return cRepo.findByNomCommande(nomCommande);
    }


    @Override
    public Optional<Commandes> findById(Long id) {
        return cRepo.findById(id);
    }


    @Override
    public void deleteCommandeById(Commandes commandes) {
        
        if (commandes != null) {
            cRepo.delete(commandes);
        }
    }
}
