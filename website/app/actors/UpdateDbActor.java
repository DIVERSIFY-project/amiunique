package actors;

import akka.actor.ActorPath;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import models.*;
import java.util.*;
 
public class UpdateDbActor extends UntypedActor {

 	public UpdateDbActor() {}

	@Override
	public void onReceive(Object msg) throws Exception {
        FpDataEntityManager em = new FpDataEntityManager();
        CombinationStatsEntityManager emc = new CombinationStatsEntityManager();
        String[] fields ={"userAgentHttp","acceptHttp","connectionHttp","languageHttp","orderHttp","encodingHttp","pluginsJs","platformJs","cookiesJs","dntJs","timezoneJs","resolutionJs","localJs",
        "sessionJs","ieDataJs","canvasJs","fontsFlash","resolutionFlash","languageFlash","platformFlash","adBlock","vendorWebGljs","rendererWebGljs"};
        int nbEntries = em.getNumberOfEntries();

        for(String attribute : fields){
            HashMap<String,Integer> stats = new HashMap<String,Integer>();
            ArrayList<String> values = em.getAttribute(attribute);
            for(String combination : values){
                if(stats.get(combination) != null){
                    stats.put(combination, stats.get(combination)+1);
                }else{
                    stats.put(combination, 1);
                }
            }

            for(String key : stats.keySet()){
                int val = stats.get(key);
                //We delete the old row before adding the new one
                if(emc.updateCombinationStats(key, attribute, val, (val/((float)nbEntries))*100) == 0){
                    emc.createCombinationStats(key, attribute, val, (val/((float)nbEntries))*100);
                }
            }
        }
	}
}