<<<<<<< HEAD
package com.games.baileymodlin.csci_490_project;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "https://ccuresearch.coastal.edu/mykistner/490project/Login.php";
    private Map<String, String> params;

    public LoginRequest(String email, String pass, Response.Listener<String> listener){
        super(Method.POST, LoginRequest.LOGIN_REQUEST_URL, listener, null );
        params= new HashMap<>();
        params.put("Email", email);
        params.put("Password", pass);
    }
    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
=======
package com.games.baileymodlin.csci_490_project;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "https://ccuresearch.coastal.edu/mykistner/490project/Login.php";
    private Map<String, String> params;

    public LoginRequest(String email, String pass, Response.Listener<String> listener){
        super(Method.POST, LoginRequest.LOGIN_REQUEST_URL, listener, null );
        params= new HashMap<>();
        params.put("Email", email);
        params.put("Password", pass);
    }
    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
>>>>>>> origin/master
