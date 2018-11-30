package com.mikemarelo.stels.timetableofclasses;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.view.menu.MenuView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import android.os.StrictMode;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends Activity {

    private static final String CHANNEL_ID = "Main" ;
    String _nowURL;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);                                                    //Указываем нашу активность в которой будем работать (используем мы ее кронечно по полной)
        
        if(isOnline(this)) {                                                               //Проверка соединнения передаем аргумент context для проведения манипуляций
                 _nowURL = "Тут какая то ссылка на сайт с таблицей";                               //Ссылка на сайт в котором будем читать первую таблицу
                 Document doc = null;                                                              //Инициализируем переменную для нашего документа (наш сайт)
                 try {
                 doc = Jsoup.connect(_nowURL).get();                                               //Инициализируем документ(наш сайт)
                 } catch (IOException e) {                                                         //Стандартная проверка(что то в стиле работает и окей)
                 e.printStackTrace();                                                              //
                 }
             if (doc == null)Toast.makeText(view.getContext(),"Эх( скорости не хватило",Toast.LENGTH_SHORT).show();

             Element table = doc.select("table").first();                                          //находим первую таблицу в документе(Так же можно найти и последнюю... но это другая исторяи)
             Elements rows = table.select("tr");                                                   //разбиваем нашу таблицу на строки по тегу
             String Pars[][] = new String[rows.size()][rows.get(0).select("td").size()];           //инициализируем массив равный размерам таблици(зачем лишний раз выделять динамическую память)
             Log.e("Size",""+rows.size());                                                         //Для проверки выводим в консоль размер таблици
                 for (int i = 0; i < rows.size(); i++) {                                           //Читаем каждую строчку
                     Element row = rows.get(i);                                                    //по номеру индекса получает строку
                     Elements cols = row.select("td");                                             // разбиваем полученную строку по тегу  на столбы
        //*****************************************************************************************//
        //********Тут можно указывать любые способы записи данных в масив, один из примеров ниже***//
        //*****************************************************************************************//
        //#########################################################################################//
        //*            if (cols.size() == 8) {                                                     //**************************************************************************//
        //*                for (int j = 0; j < cols.size(); j++) {                                 //Все строки тут отвечают за распределение полученной информации по массиву*//
        //*                    Pars[i][j] = cols.get(j).text();                                    //****"&UpT=14" это запрос который я передаю в последствии в строку*********//
        //*                    if (Pars[i][j].length() > 27)                                       //*****Размер шрифта который потом установится *****************************//
        //*                        Pars[i][j] = Pars[i][j].substring(0, 23) + "&UpT=14";           //**************************************************************************//
        //*                    else if                                                             //**************************************************************************//
        //*                            (Pars[i][j].length() > 19) Pars[i][j] += "&UpT=14";         //**************************************************************************//
        //*                    }  } else if (cols.size() == 7) {                                   //**************************************************************************//
        //*                for (int j = 0; j < cols.size(); j++) {                                 //**************************************************************************//
        //*                    Pars[i][j] = cols.get(j).text();                                    //**************************************************************************//
        //*                    if (Pars[i][j].length() > 27)                                       //**************************************************************************//
        //*                        Pars[i][j] = Pars[i][j].substring(0, 23) + "&UpT=14";           //**************************************************************************//
        //*                    else if                                                             //**************************************************************************//
        //*                            (Pars[i][j].length() > 19) Pars[i][j] += "&UpT=14";         //**************************************************************************//
        //*                    }  } else {                                                         //**************************************************************************//
        //*                for (int j = 0; j < cols.size(); j++) {                                 //**************************************************************************//
        //*                    Pars[i][j] = cols.get(j).text();                                    //**************************************************************************//
        //*                    if (Pars[i][j].length() > 27)                                       //**************************************************************************//
        //*                        Pars[i][j] = Pars[i][j].substring(0, 23) + "&UpT=14";           //**************************************************************************//
        //*                    else if                                                             //**************************************************************************//
        //*                            (Pars[i][j].length() > 19) Pars[i][j] += "&UpT=14";         //**************************************************************************//
        //*                    }  }                                                                //**************************************************************************//
        //#########################################################################################//
                 }
        }else {
            Toast.makeText(this,"Упс... Нет интернета",Toast.LENGTH_SHORT).show();                 //Выводим сообщение если нет интернета соединения
        }
    }
    public static boolean isOnline(Context context) {                                              //**************************************************************************//
        ConnectivityManager cm =                                                                   //С помошью менеджера сети получаем ее состояние и делаем возврат значения**//
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);      //***Обязательно укажите с манифесте разрешение на интренет и проверку подключения
        NetworkInfo netInfo = cm.getActiveNetworkInfo();                                           //*<uses-permission android:name="android.permission.INTERNET" />************//
        if (netInfo != null && netInfo.isConnectedOrConnecting())return true;                      //*<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />//
        return false;
    }
}
