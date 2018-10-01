package com.games.baileymodlin.csci_490_project;

import java.io.BufferedInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WebServiceConnect {


    private URL url;
    private HttpURLConnection urlConnection;
    private final String URLString = "";
    private String dataIn;

    public WebServiceConnect(String command) {
        try {
            url = new URL(URLString + command);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            dataIn = readStream(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
    }

    private String readStream(InputStream in){
        try{
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            int i = in.read();
            while(i != -1){
                byteOut.write(i);
                i = in.read();
            }
            return byteOut.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public String getDataIn(){
        return dataIn;
    }
}
