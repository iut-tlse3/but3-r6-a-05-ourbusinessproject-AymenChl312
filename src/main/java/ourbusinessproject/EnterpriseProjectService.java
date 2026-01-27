package ourbusinessproject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EnterpriseProjectService {

    @PersistenceContext
    private EntityManager entityManager;

    public EnterpriseProjectService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Project newProject(String title, String description) {
        Project project = new Project();
        project.setTitle(title);
        project.setDescription(description);

        entityManager.persist(project);

        entityManager.flush();

        return project;
    }

    public Project findProjectById(Long id) {
        return entityManager.find(Project.class, id);
    }

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

    public Enterprise findEnterpriseById(Long id) {
        return entityManager.find(Enterprise.class, id);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}