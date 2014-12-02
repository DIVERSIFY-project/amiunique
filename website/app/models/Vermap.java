package models;

import scala.xml.Atom;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Vermap extends TreeMap<String,AtomicInteger> {

    public Vermap() {
        super(new VersionComparator());
    }

    public void add(String value){
        if(this.containsKey(value)){
            this.get(value).incrementAndGet();
        } else {
            this.put(value,new AtomicInteger(1));
        }
    }

    public double getCounter(){
        double counter = 0;
        for(AtomicInteger value : this.values()){
            counter += value.doubleValue();
        }
        return counter;
    }

}

class VersionComparator implements Comparator<String> {

    private static Double doubleValue(String s) {
        try {
            return Double.valueOf(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public int compareVersion(String str1, String str2){
        String[] vals1 = str1.split("\\.");
        String[] vals2 = str2.split("\\.");
        int i = 0;
        while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])){
          i++;
        }

        if (i < vals1.length && i < vals2.length){
            int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
            return Integer.signum(diff);
        } else {
            return Integer.signum(vals1.length - vals2.length);
        }
    }


    @Override
    public int compare(String s1, String s2) {
        Double d1 = doubleValue(s1);
        Double d2 = doubleValue(s2);
        if (d1 == null && d2 == null) {
            return s1.compareTo(s2);
        } else if (d1 == null) {
            return 1;
        } else if (d2 == null) {
            return -1;
        } else {
            return compareVersion(s1,s2);
        }
    }
}
