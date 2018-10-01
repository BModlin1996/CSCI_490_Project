/**
 * @author Bailey Modlin
 * @version 1.0
 * Created - 9/31/2018
 * Last updated - 10/01/2018
 */
package com.games.baileymodlin.csci_490_project;

import java.io.BufferedInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class WebServiceConnect {

    private URL url;
    private HttpURLConnection urlConnection;
    private final String URLString = "";
    private String dataIn;

    /**
     * WebServiceConnect(String) - Makes a connection to the webservice and retrieves the queried information
     * @param command - The information to retrieve from the webservice
     */
    public WebServiceConnect(String command) {
        try {
            //The URL to the webserver
            url = new URL(URLString + command);
            //Make a connection to the webserver
            urlConnection = (HttpURLConnection) url.openConnection();
            //Get the input stream
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            dataIn = readStream(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //After the data is transacted disconnect from the webserver
            urlConnection.disconnect();
        }
    }

    /**
     * readStream - takes the provided input stream and converts it in to a usable string
     * @param in - The buffered input stream download from the webservice
     * @return - The translated input stream
     */
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

    /**
     * getDataIn() - Gets the value of dataIn
     * @return - The string value of dataIn
     */
    public String getDataIn(){
        return dataIn;
    }
}
