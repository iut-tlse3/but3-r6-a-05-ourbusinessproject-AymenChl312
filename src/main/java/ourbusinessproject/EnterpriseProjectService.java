package ourbusinessproject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseProjectService {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     *
     * @param entityManager
     */
    public EnterpriseProjectService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     *
     * @param title
     * @param description
     * @param enterprise
     * @return new project
     */
    public Project newProject(String title, String description, Enterprise enterprise){
        Project project = new Project();
        project.setTitle(title);
        project.setDescription(description);
        project.setEnterprise(enterprise);

        if (enterprise != null) {
            enterprise.addProject(project);
        }

        entityManager.persist(project);
        entityManager.flush();

        return project;
    }

    /**
     *
     * @param id
     * @return project by id
     */
    public Project findProjectById(Long id) {
        return entityManager.find(Project.class, id);
    }

    /**
     *
     * @param name
     * @param description
     * @param contactName
     * @param contactEmail
     * @return new enterprise
     */
    public Enterprise newEnterprise(String name, String description, String contactName, String contactEmail) {
        Enterprise enterprise = new Enterprise();
        enterprise.setName(name);
        enterprise.setDescription(description);
        enterprise.setContactName(contactName);
        enterprise.setContactEmail(contactEmail);

        entityManager.persist(enterprise);

        entityManager.flush();

        return enterprise;
    }

    /**
     *
     * @param id
     * @return enterprise by id
     */
    public Enterprise findEnterpriseById(Long id) {
        return entityManager.find(Enterprise.class, id);
    }

    /**
     *
     * @return entity manager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }
}