package com.games.baileymodlin.csci_490_project;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://ccuresearch.coastal.edu/mykistner/490project/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String fname, String lname, String ccuid, String email, String pass, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null );
        params= new HashMap<>();
        params.put("fname", fname);
        params.put("lname", lname);
        params.put("email", email);
        params.put("ccuID", ccuid);
        params.put("password", pass);
    }
    @Override
    public Map<String, String> getParams(){
        return params;
    }
}