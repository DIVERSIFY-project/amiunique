import play.Logger;
import play.libs.Akka;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.Props;
import actors.*;

import play.Application;
import play.GlobalSettings;
import java.util.concurrent.TimeUnit;

public class Global extends GlobalSettings {

    @Override
	public void onStart(Application app) {
 		ActorRef myActor = Akka.system().actorOf(Props.create(UpdateDbActor.class));
 		Akka.system().scheduler().scheduleOnce(
			Duration.create(0, TimeUnit.MILLISECONDS), //Initial delay 0 milliseconds
			myActor,
			"hello",
			Akka.system().dispatcher(),
			null
	 	);
   }

    @Override
    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    }
}