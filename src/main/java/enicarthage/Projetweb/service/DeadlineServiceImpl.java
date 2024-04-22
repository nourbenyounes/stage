package enicarthage.Projetweb.service;


import enicarthage.Projetweb.entity.Deadline;
import enicarthage.Projetweb.entity.Etudiant;
import enicarthage.Projetweb.repository.DeadlineRepository;
import enicarthage.Projetweb.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class DeadlineServiceImpl implements DeadlineService {

    @Autowired
    private DeadlineRepository deadlineRepository;
    @Autowired
    private NotificationRepository notificationRepository;


    @Override
    public Deadline createDeadline(String description, Date date) {
        Deadline deadline = new Deadline(description, date);
        return deadlineRepository.save(deadline);
    }

    @Override
    public Deadline ajoutDeadline(Deadline deadline) {
        //yeb3ath notif mt3 ajout
        String notificationMessage = deadline.getDescription() + " \n- Nouveau deadline.";
        Notification notification = new Notification(notificationMessage, "etudiant", new Date());
        notificationRepository.save(notification);
        return deadlineRepository.save(deadline);
    }

    @Override
    public void modifierDeadline(Deadline deadline) {
        //yeb3ath notif fel mod
        String notificationMessage = deadline.getDescription() + " \n- Deadline modifiée .";
        Notification notification = new Notification(notificationMessage, "etudiant", new Date());
        notificationRepository.save(notification);
        deadlineRepository.save(deadline);
    }

    @Override
    public void deleteDeadline(Long id) {
        Optional<Deadline> existingDeadline = deadlineRepository.findById(id);
        if (existingDeadline.isPresent()) {
            deadlineRepository.deleteById(id);
        } else {
            throw new DeadlineNotFoundException("La deadline avec l'ID " + id + " n'existe pas.");
        }
    }

    @Override
    public List<Deadline> getAllDeadlines() {
        return deadlineRepository.findAll();
    }

    @Override
    public List<Deadline> getDeadlinesProches() {
        Date now = new Date();
        Date deuxJoursPlusTard = new Date(now.getTime() + 2 * 24 * 60 * 60 * 1000); // Ajoute 2 jours en millisecondes
        return deadlineRepository.findByDateBetween(now, deuxJoursPlusTard);
    }
    @Override
    public Deadline getDeadline(Long id) {
        return deadlineRepository.findById(id)
                .orElseThrow(() -> new DeadlineNotFoundException("La deadline avec l'ID " + id + " n'existe pas."));
    }
    @Override
    public void createDeadline(Deadline deadline) {
        deadlineRepository.save(deadline);
    }

}
