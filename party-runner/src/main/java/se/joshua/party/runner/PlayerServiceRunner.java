package se.joshua.party.runner;

import org.openspaces.core.GigaSpace;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.joshua.party.runner.domain.Player;
import se.joshua.party.service.PlayerService;

/**
 * @author Ali yusha {@literal <mailto:ali.yusha@so4it.com>}
 */
public class PlayerServiceRunner {

  private  PlayerService playerService;

    private GigaSpace partyGigaspace;

    public PlayerServiceRunner(GigaSpace partyGigaspace) {
        this.partyGigaspace = partyGigaspace;
    }

    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    public PlayerService getPlayerService() {
        return playerService;
    }

    public static void main(String[] args) {

        ClassPathXmlApplicationContext  context = new ClassPathXmlApplicationContext("party-in.xml");

       PlayerService playerService=(PlayerService) context.getBean("playerService");

        Player player= playerService.getPlayerByUserName("gamiiis");

        System.out.println(player);
    }

}
