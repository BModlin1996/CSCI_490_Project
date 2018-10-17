package com.games.baileymodlin.csci_490_project;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "insert url for database";
    private Map<String, String> params;

    public ResgisterRequest(String fname, String lname, String email, String ccuid, String pass, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null );
        params= new HashMap<>();
        params.put("First Name", fname);
        params.put("Last Name", lname);
        params.put("Email", email);
        params.put("CCU ID",ccuid);
        params.put("Password", pass);
    }
    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
