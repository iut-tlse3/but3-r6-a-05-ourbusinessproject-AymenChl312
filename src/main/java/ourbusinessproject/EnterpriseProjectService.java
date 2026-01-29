package ourbusinessproject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseProjectService {

    @PersistenceContext
    private final EntityManager entityManager;

    /**
     * Create a new EnterpriseProjectService initialized with an entity manager
     * @param entityManager the entity manager
     */
    public EnterpriseProjectService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    /**
     * Create a new project
     * @param aTitle the title of the new project
     * @param aDescription the description of the new project
     * @return the created project
     */
    public Project newProject(String aTitle, String aDescription, Enterprise enterprise) {
        Project project = new Project();
        project.setTitle(aTitle);
        project.setDescription(aDescription);
        project.setEnterprise(enterprise);
        this.entityManager.persist(project);
        this.entityManager.flush();
        enterprise.addProject(project);
        return project;
    }

    /**
     * Find the project for the given id
     * @param projectId the id of the searched project
     * @return the project with corresponding id or null
     */
    public Project findProjectById(Long projectId) {
        return this.entityManager.find(Project.class, projectId);
    }

    /**
     * Create a new enterprise
     * @param aName the name of the enterprise
     * @param aDescription the description of the enterprise
     * @param aContactName the contact name of the enterprise
     * @param mail the mail of the enterprise
     * @return the created enterprise
     */
    public Enterprise newEnterprise(
            String aName,
            String aDescription,
            String aContactName,
            String mail
    ) {
        Enterprise enterprise = new Enterprise();
        enterprise.setName(aName);
        enterprise.setDescription(aDescription);
        enterprise.setContactEmail(mail);
        enterprise.setContactName(aContactName);
        this.entityManager.persist(enterprise);
        this.entityManager.flush();
        return enterprise;
    }

    /**
     * Find the enterprise for the given id
     * @param enterpriseId the id of the searched enterprise
     * @return the enterprise if it exists else null
     */
    public Enterprise findEnterpriseById(Long enterpriseId) {
        return this.entityManager.find(Enterprise.class, enterpriseId);
    }

    /**
     *
     * @return the list of all projects order by title
     */
    public List<Project> findAllProjects() {
        String queryAsJpql = "select p from Project p order by p.title";
        TypedQuery<Project> query = this.entityManager.createQuery(
                queryAsJpql,
                Project.class
        );
        return query.getResultList();
    }
}