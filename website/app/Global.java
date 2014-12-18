import models.Stats;
import play.*;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        Logger.info("Application has started");
        Stats.getInstance();
    }

    @Override
    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    }
}