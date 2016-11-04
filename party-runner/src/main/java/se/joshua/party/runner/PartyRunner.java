package se.joshua.party.runner;

import com.j_spaces.core.IJSpace;
import com.so4it.test.gs.runner.GigaSpaceClusterRunner;
import com.so4it.test.gs.runner.GigaSpaceClusterRunnerImpl;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;
import se.joshua.party.runner.entity.PlayerEntity;
import se.joshua.party.service.PlayerService;

import java.time.LocalDate;

/**
 * @author Ali Yusha {@literal <mailto:Ali.yusha@so4it.com/>}
 */
public class PartyRunner {

    public static GigaSpace partyGigaspace;

    private static final int NUMBER_OF_PRIMARIES = 1;
    private static final int NUMBER_OF_BACKUPS = 0;
    public static final String LOOKUP_GROUP_NAME = "test";
    public static final String LOCAL_HOST = "127.0.0.1";


    public GigaSpace getPartyGigaspace() {
        return partyGigaspace;
    }

    public void setPartyGigaspace(GigaSpace partyGigaspace) {
        this.partyGigaspace=partyGigaspace;
    }

    private static final String[] PUS = {
            "classpath*:pu-party-component.xml"
    };



    public  static void main(String[] args)throws Exception {
        for (String pu : PUS) {
            GigaSpaceClusterRunner partySpaceClusterRunner = GigaSpaceClusterRunnerImpl.builder()
                    .withConfigLocation(pu)
                    .withLookupLocator(LOCAL_HOST)
              //      .withLookupGroup(LOOKUP_GROUP_NAME)
                    .withNumberOfPrimaries(NUMBER_OF_PRIMARIES)
                    .withNumberOfBackups(NUMBER_OF_BACKUPS)
                    .shouldStartEmbeddedLookupService(false)
                    .build();
            partySpaceClusterRunner.run();

            // move this to playerServiceRunner class
            IJSpace space = new UrlSpaceConfigurer("jini://*/*/party?locators=127.0.0.1").space();
            partyGigaspace = new GigaSpaceConfigurer(space).gigaSpace();

            partyGigaspace.write(PlayerEntity.builder()
                            .withEmail("ali@joshua.com")
                            .withFirstName("ali")
                            .withLastName("joshua")
                            .withUserName("gamiiis")
                            .withStartDate(LocalDate.now())
                            .withEndDate(LocalDate.now())
                            .build());
        }


    }



}
