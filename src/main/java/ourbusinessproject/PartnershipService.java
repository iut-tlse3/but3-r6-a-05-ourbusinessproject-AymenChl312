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
     * @param project enterprise
     * @return partnership
     */
    public Partnership newPartnership(Project project, Enterprise enterprise) {
        Partnership partnership = new Partnership();
        partnership.setProject(project);
        partnership.setEnterprise(enterprise);
        partnership.setCreationDate(new Date());

        this.entityManager.persist(partnership);
        return partnership;
    }

    /**
     * @param id
     * @return partnership
     */
    public Partnership findPartnershipById(Long id) {
        return this.entityManager.find(Partnership.class, id);
    }

    /**
     * @param partnership
     */
    public void remove(Partnership partnership) {
        Partnership managedPartnership = this.entityManager.contains(partnership)
                ? partnership
                : this.entityManager.merge(partnership);

        this.entityManager.remove(managedPartnership);
    }
}