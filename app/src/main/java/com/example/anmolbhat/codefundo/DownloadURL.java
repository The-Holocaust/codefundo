package com.example.anmolbhat.codefundo;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadURL {

    public String ReadURL(String myurl) throws IOException {
        String data = "";
        InputStream ins = null;
        HttpURLConnection urlCon = null;

        try {
            URL url = new URL(myurl);
            urlCon = (HttpURLConnection) url.openConnection();
            urlCon.connect();

            ins = urlCon.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
                Log.d("Data", line);
                System.out.println("hello");
            }
            data = sb.toString();
            br.close();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            ins.close();
            urlCon.disconnect();
        }

        return data;
    }
}
