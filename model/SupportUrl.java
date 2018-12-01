package com.mikemarelo.stels.timetableofclasses.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class SupportUrl{
    public static class CreateUrlArg {
        public static String AllArrayArg(String url, String array[][]) {
            url+='?'+array[0][0]+'='+array[0][1];
            for(int i=1;i<array.length;i++)
                url+='&'+array[i][0]+'='+array[i][1];
            return url;
        }
        public static String OneStringyArg(String url, String... arg) {
            url+='?'+arg[0];
            for(int i=1;i<arg.length;i++)
                url+='&'+arg[i];
            return url;
        }
    }
    public static boolean isNetwork(Context context) {                                              //**************************************************************************//
        ConnectivityManager cm =                                                                   //С помошью менеджера сети получаем ее состояние и делаем возврат значения**//
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);      //***Обязательно укажите с манифесте разрешение на интренет и проверку подключения
        NetworkInfo netInfo = cm.getActiveNetworkInfo();                                           //*<uses-permission android:name="android.permission.INTERNET" />************//
        if (netInfo != null && netInfo.isConnectedOrConnecting())return true;                      //*<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />//
        return false;
    }

}