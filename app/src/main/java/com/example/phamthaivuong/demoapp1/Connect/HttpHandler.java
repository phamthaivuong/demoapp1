package com.example.phamthaivuong.demoapp1.Connect;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpHandler {
    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler() {
    }
    public String makeServiceCall (String reqUrl){
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            // đọc phản hồi
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);

        } catch (MalformedURLException e) {
            Log.e(TAG,"MalformedURLException (Nhận thức sai ) : " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG,"ProtocolException (Giao thức ngoại lệ ) : " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG,"IOException (Ngoại lệ ) : " + e.getMessage());
        } catch (Exception e){
            Log.e(TAG,"Exception (Ngoại lệ ) : " + e.getMessage());
        }

        return response;
    }

    private String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try{
            while ((line = reader.readLine()) != null){
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
