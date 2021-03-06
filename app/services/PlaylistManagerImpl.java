package services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import models.Task;
import java.util.List;

@Service
public class PlaylistManagerImpl implements PlaylistManager {
    private static final Logger log = LoggerFactory.getLogger(PlaylistManagerImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void addTask(Task theTask) {
        if(em == null) {
            log.error("Entity Manger is null when adding task");
        }
        log.info("EntityManager: " + em);
        em.persist(theTask);
    }

    @Transactional
    public void removeTask(int theTaskId) {
        List resultList = em.createQuery("SELECT T from Task AS T WHERE T.id = " + theTaskId).getResultList();
        log.info(resultList.toString());
        log.info("To Delete: id: " + theTaskId);

        if(!resultList.isEmpty()) {
            log.info("Deleting: " + resultList.get(0));
            em.remove(resultList.get(0));
        }
    }

    public List<Task> getAllTasks() {
        List<Task> list = em.createQuery("from Task", Task.class).getResultList();
        log.info(list.toString());
        return list;
    }
}
