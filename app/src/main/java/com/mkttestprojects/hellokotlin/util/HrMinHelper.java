package com.mkttestprojects.hellokotlin.util;

/**
 * Created by kaungkhantsoe on 2020-02-29.
 **/
public class HrMinHelper {

    public static String getHrMinString(int timeInMins) {
        try {
            int hr, min;
            if (timeInMins >= 60) {
                hr = timeInMins/60;
                min = timeInMins%60;

                return hr + "H " + min + "M";
            }else {
                return timeInMins + "M";
            }
        }catch (Exception ex) {
            return timeInMins + "M";
        }
    }
}
