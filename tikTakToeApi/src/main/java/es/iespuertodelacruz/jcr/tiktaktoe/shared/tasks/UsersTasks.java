package es.iespuertodelacruz.jcr.tiktaktoe.shared.tasks;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.ports.secondary.IGameRepository;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.ports.secondary.IUserRepository;

@Component
public class UsersTasks {

    @Autowired
    private IUserRepository userRepository;

    Logger logger = Logger.getLogger(UsersTasks.class.getName());


    @Scheduled(cron = "0 0 0 * * *")
    public void removeNotVerifiedUsers(){
        logger.info("Deleting not verified accounts");
        logger.info("Deleted for inactivity: " + userRepository.removeNotVerifiedUsers() + " users");
        return;
    }

}
