package models;

public class VersionMap extends CounterMap {

    public VersionMap() {
        super(VersionComparator.getInstance());
    }

}

