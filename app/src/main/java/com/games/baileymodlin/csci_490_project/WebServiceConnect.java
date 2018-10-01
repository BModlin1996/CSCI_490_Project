package com.games.baileymodlin.csci_490_project;

import java.io.BufferedInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WebServiceConnect {

    private static WebServiceConnect webServiceConnect = new WebServiceConnect();
    private URL url;
    private HttpURLConnection urlConnection;
    private final String URLString = "";

    private WebServiceConnect() {
       try {
           url = new URL(URLString);
           connect();
       } catch (MalformedURLException e) {
           e.printStackTrace();
       } finally {
           disconnect();
       }
    }

    public static WebServiceConnect getInstance(){
        return webServiceConnect;
    }

    private void connect(){
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void get(){

    }

    public void post(){

    }

    public void disconnect(){

        urlConnection.disconnect();
    }
}
