package fr.esaip;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnexionJpa {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //2eme tp
        Livre livre = new Livre();
        livre = em.find(Livre.class, 2);
        if (livre != null) {
            System.out.println(livre);
        }
        //1ère requête
        Emprunt emprunt = em.find(Emprunt.class, 1);

        if (emprunt != null) {
            System.out.println("Emprunt ID : " + emprunt.getId());
            for (Livre l : emprunt.getLivres()) {
                System.out.println("Livre : " + l.getTitre() + " - " + l.getAuteur());
            }
        } else {
            System.out.println("Aucun emprunt trouvé pour cet ID.");
        }
        //2eme requete
        Client client = em.find(Client.class, 1);

        if (client != null) {
            System.out.println("Emprunts de " + client.getNom() + " " + client.getPrenom() + " :");
            for (Emprunt e : client.getEmprunts()) {
                System.out.println("Emprunt ID: " + e.getId() + " - Début: " + e.getDateDebut());
            }
        } else {
            System.out.println("Client non trouvé.");
        }
        em.getTransaction().commit();
        em.close();
        emf.close();

    }
}
