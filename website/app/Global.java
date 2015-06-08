import models.Stats;
import play.*;
import play.libs.Akka;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.Props;
import actors.*;

import play.Application;
import play.GlobalSettings;
import play.libs.Akka;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.Props;
import java.util.concurrent.TimeUnit;

public class Global extends GlobalSettings {
/*
    @Override
	public void onStart(Application app) {
 		ActorRef myActor = Akka.system().actorOf(Props.create(UpdateDbActor.class));
 		Akka.system().scheduler().schedule(
			Duration.create(0, TimeUnit.MILLISECONDS), //Initial delay 0 milliseconds
			Duration.create(1440, TimeUnit.MINUTES),     //Frequency 1440 minutes <=> 24hours
			myActor,
			"hello",
			Akka.system().dispatcher(),
			null
	 	);
   }*/

    @Override
    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    }
}