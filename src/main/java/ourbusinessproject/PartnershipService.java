package ourbusinessproject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PartnershipService {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Crée un nouveau partenariat entre un projet et une entreprise.
     * @param project Le projet concerné
     * @param enterprise L'entreprise partenaire
     * @return Le partenariat créé et persisté
     */
    public Partnership newPartnership(Project project, Enterprise enterprise) {
        Partnership partnership = new Partnership();
        partnership.setProject(project);
        partnership.setEnterprise(enterprise);
        // Le test vérifie "assertNotNull(fetchedPartnership.getCreationDate())",
        // il faut donc initialiser la date ici.
        partnership.setCreationDate(new Date());

        this.entityManager.persist(partnership);
        return partnership;
    }

    /**
     * Recherche un partenariat par son identifiant.
     * @param id L'identifiant du partenariat
     * @return Le partenariat trouvé ou null
     */
    public Partnership findPartnershipById(Long id) {
        return this.entityManager.find(Partnership.class, id);
    }

    /**
     * Supprime un partenariat existant.
     * @param partnership Le partenariat à supprimer
     */
    public void remove(Partnership partnership) {
        // On s'assure que l'entité est gérée par l'EntityManager avant la suppression
        // (bien que dans le contexte transactionnel du test, elle le soit déjà).
        Partnership managedPartnership = this.entityManager.contains(partnership)
                ? partnership
                : this.entityManager.merge(partnership);

        this.entityManager.remove(managedPartnership);
    }
}