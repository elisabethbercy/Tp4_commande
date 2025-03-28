package com.example.tp4_commande.commandes;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.tp4_commande.users.Users;

public interface CommandesRepository extends CrudRepository<Commandes, Long>{
    
    Optional<Commandes>findById(Long id);

    public List<Commandes>findAll();
    
    List<Commandes> findByUsers(Users users);

    @Query("SELECT c FROM Commandes c WHERE c.users.email = :email")
    List<Commandes> findByUserEmail(@Param("email") String email);

    List<Commandes> findByNomCommande(String nomCommande);

    void delete(Commandes commandes);




    
}
