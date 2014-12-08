package models;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterMap extends TreeMap<String,AtomicInteger>{

    public CounterMap(){
        super();
    }

    public CounterMap(Comparator<String> comp){
        super(comp);
    }

    public void add(String value){
       if(this.containsKey(value)){
           this.get(value).incrementAndGet();
       } else {
           this.put(value, new AtomicInteger(1));
       }
    }

    public void add(String key, String value){
       if(!this.containsKey(key)){
           this.put(key, new AtomicInteger(Integer.parseInt(value)));
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
