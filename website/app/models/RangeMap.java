package models;

public class RangeMap extends CounterMap {

    public RangeMap() {
        super(RangeComparator.getInstance());
    }
}

