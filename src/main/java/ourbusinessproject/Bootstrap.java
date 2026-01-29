package ourbusinessproject;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap {

    // a logger for this class
    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    private final InitializationService initializationService;

    /**
     * Create a Boostrap objet with the given initialisation service
     * @param initializationService the initialisation service
     */
    public Bootstrap(InitializationService initializationService) {
        this.initializationService = initializationService;
    }

    @PostConstruct
    public void init() {
        try {
            this.initializationService.initProjects();
        } catch (RuntimeException re) {
            logger.error("Error during initialization", re);
        }
    }

    /**
     *
     * @return the initialisation service
     */
    public InitializationService getInitializationService() {
        return this.initializationService;
    }
}