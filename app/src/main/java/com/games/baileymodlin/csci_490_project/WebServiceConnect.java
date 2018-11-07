/**
 * @author Bailey Modlin
 * @version 1.0
 * Created - 9/31/2018
 * Last updated - 10/01/2018
 */
package com.games.baileymodlin.csci_490_project;

import java.io.BufferedInputStream;
<<<<<<< HEAD
=======

import java.io.BufferedOutputStream;
>>>>>>> origin/master
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
<<<<<<< HEAD
=======
import javax.net.ssl.HttpsURLConnection;

import java.io.OutputStream;
>>>>>>> origin/master
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;




public class WebServiceConnect {

    private static WebServiceConnect webServiceConnect = new WebServiceConnect();
    private String dataIn;
    private static final String TAG = WebServiceConnect.class.getSimpleName();

    /**
     * WebServiceConnect(String) - Makes a connection to the webservice and retrieves the queried information
     *
     */
    private WebServiceConnect() {

    }

    public static WebServiceConnect getInstance(){
        return webServiceConnect;
    }

    public String getData(String link) {


        try {
            //The URL to the webserver
            URL url = new URL(link);
            //Make a connection to the webserver
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            try {
                //Get the input stream
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                dataIn = readStream(in);
                System.out.print("Connected");
            } finally {
                //After the data is transacted disconnect from the webserver
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataIn;
    }

    /**
     * readStream - takes the provided input stream and converts it in to a usable string
     * @param in - The buffered input stream download from the webservice
     * @return - The translated input stream
     */
    private String readStream(InputStream in){
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        try{
            while((line = reader.readLine()) != null){
                stringBuilder.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }

    /**
     * getDataIn() - Gets the value of dataIn
     * @return - The string value of dataIn
     */
    public String getDataIn(){
        return dataIn;
    }
}
