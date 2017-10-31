package com.nodomain.employeeapp.develop;


import android.util.Log;


public class DevelopUtil {

    public static <T> T TODO() {
        throw new NotImplementedException();
    }

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void LogError(String message) {
        Log.e("ErrorTag", message);
    }

    public static void LogDebug(String message) {
        Log.d("DebugTag", message);
    }
}
