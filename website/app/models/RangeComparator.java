package models;

import java.util.Comparator;

public class RangeComparator implements Comparator<String> {

    private RangeComparator(){};

    private static RangeComparator INSTANCE = new RangeComparator();

    public static RangeComparator getInstance(){
        return INSTANCE;
    }

    @Override
    public int compare(String str1, String str2){
        if (str1 == null) {
            if(str2 == null){
                return 0;
            } else {
                return 1;
            }
        } else if (str2 == null) {
            return -1;
        } else {
            String[] vals1 = str1.split("-");
            String[] vals2 = str2.split("-");
            return Integer.signum(Integer.valueOf(vals1[0]).compareTo(Integer.valueOf(vals2[0])));
        }
    }

}
