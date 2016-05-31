package com.yaiyaiyaa.android.versionchecker;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mahendran on 31/5/16.
 */
public class VersionCheckerRequest extends Request<LatestVersionDetails> {
    private final Map<String, String> postParams;
    private final Response.Listener<LatestVersionDetails> listener;
    private static final int TIMEOUT = 3000;

    public VersionCheckerRequest(String url, HashMap<String, String> postParams,
                                 Response.Listener<LatestVersionDetails> listener,
                                 Response.ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
        this.postParams = postParams;
        this.listener = listener;

        setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
    }

    @Override
    protected Response<LatestVersionDetails> parseNetworkResponse(NetworkResponse response) {
        try {
            if (response.statusCode <= 200 || response.statusCode >= 220) {
                return Response.error(new VolleyError(response));
            }
            Reader jsonReader = Utils.getJsonReader(response);
            response = null;

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            LatestVersionDetails responseObject =
                    gson.fromJson(jsonReader, new TypeToken<LatestVersionDetails>(){}.getType());

            if (null != responseObject) {
                return Response.success(responseObject, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.error(new VolleyError(response));
    }

    @Override
    protected void deliverResponse(LatestVersionDetails response) {
        listener.onResponse(response);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return postParams;
    }

    @Override
    public String getBodyContentType() {
        return super.getBodyContentType();
    }
}
