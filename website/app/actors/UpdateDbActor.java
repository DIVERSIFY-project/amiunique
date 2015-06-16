package actors;

import akka.actor.ActorPath;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import models.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
 
public class UpdateDbActor extends UntypedActor {

 	public UpdateDbActor() {}

	@Override
	public void onReceive(Object msg) throws Exception {
        FpDataEntityManager em = new FpDataEntityManager();
        CombinationStatsEntityManager emc = new CombinationStatsEntityManager();

        if(emc.getNumberOfEntries()<5) {

            String[] fields = {"userAgentHttp", "acceptHttp", "connectionHttp", "languageHttp", "orderHttp", "encodingHttp", "pluginsJsHashed", "platformJs", "cookiesJs", "dntJs", "timezoneJs", "resolutionJs", "localJs",
                    "sessionJs", "ieDataJs", "canvasJsHashed", "fontsFlashHashed", "resolutionFlash", "languageFlash", "platformFlash", "adBlock"};
            int nbEntries = em.getNumberOfEntries();

            ArrayList<String> plugins = em.getAttribute("pluginsJs");
            HashSet<String> pluginsAdded = new HashSet<String>();

            String patternStringPlugin = "Plugin [0-9]+: ([a-zA-Z -.]+)";
            Pattern pattern = Pattern.compile(patternStringPlugin);

            int j = 1;
            for (String rowPlugins : plugins) {
                Matcher matcher = pattern.matcher(rowPlugins);

                while (matcher.find()) {
                    String plugin = matcher.group(1);
                    if (!pluginsAdded.contains(plugin) && plugins != null) {
                        long nbSimilarPlugins = emc.getNbIdenticalPlugins(plugin);
                        if (emc.testExistingCombination(plugin)) {
                            emc.updateCombinationStats(plugin, "pluginsJs", nbSimilarPlugins);
                        } else {
                            emc.createCombinationStats(plugin, "pluginsJs", nbSimilarPlugins);
                        }
                        pluginsAdded.add(plugin);
                        j++;
                    }
                }
            }

            for (String attribute : fields) {
                List<Object[]> res = em.getStatsAttribute(attribute);

                for (Object[] resultElement : res) {
                    String combination = (String) resultElement[0];
                    if (combination == null) {
                        combination = "";
                    }
                    Long nb = (Long) resultElement[1];
                    if (emc.updateCombinationStats(combination, attribute, nb) == 0) {
                        emc.createCombinationStats(combination, attribute, nb);
                    }
                }
            }
        }
	}

}