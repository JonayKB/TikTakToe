package es.iespuertodelacruz.jcr.tiktaktoe.shared.tasks;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.ports.secondary.IGameRepository;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.ports.secondary.IUserRepository;

@Component
public class GamesTasks {

    @Autowired
    private IGameRepository gameRepository;


    Logger logger = Logger.getLogger(GamesTasks.class.getName());

    @Scheduled(cron = "0 0 * * * *")
    public void finishOldGames() {
        logger.info("Finishing old games");
        logger.info("Finished for inactivity: " + gameRepository.finishOldGames() + " games");
        return;
    }


}
