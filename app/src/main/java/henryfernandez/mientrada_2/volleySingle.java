package henryfernandez.mientrada_2;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by user on 12/11/17.
 */


public class volleySingle {
    private static volleySingle request;
    private Context context;
    private RequestQueue fRequestQueue;

    private volleySingle(Context context) {
        this.context = context;
        this.fRequestQueue = Volley.newRequestQueue(context);
    }

    public RequestQueue getfRequestQueue() {
        return this.fRequestQueue;
    }

    public static volleySingle getInstance(Context context) {
        if (request == null) {
            request = new volleySingle(context);
        } else if (request.context != context) {
            request = new volleySingle(context);
        }
        return request;
    }
}

