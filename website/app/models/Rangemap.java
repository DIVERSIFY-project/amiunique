package models;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Rangemap extends TreeMap<String,AtomicInteger> {

    public Rangemap() {
        super(new RangeComparator());
    }

    public void add(String value){
        if(this.containsKey(value)){
            this.get(value).incrementAndGet();
        } else {
            this.put(value,new AtomicInteger(1));
        }
    }
}

class RangeComparator implements Comparator<String> {

    @Override
    public int compare(String str1, String str2){
        String[] vals1 = str1.split("-");
        String[] vals2 = str2.split("-");
        return Integer.signum(Integer.valueOf(vals1[0]).compareTo(Integer.valueOf(vals2[0])));
    }

}
