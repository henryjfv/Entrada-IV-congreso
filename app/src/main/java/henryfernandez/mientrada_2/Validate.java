package henryfernandez.mientrada_2;

import android.accessibilityservice.GestureDescription;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Validate extends AppCompatActivity {

    Button btnIdentificacion;
    LinearLayout contendor;
    EditText editIdentificacion;
    Context mContext;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate);
        mContext = this;
        btnIdentificacion = (Button)findViewById(R.id.btnIdentificacion);
        btnIdentificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarInformacion();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void validarInformacion(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            if (json.getBoolean("success")) {
                                startActivity(new Intent(mContext, App.class).putExtra("response", json.toString()).addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION));
                                finish();
                                return;
                            }
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
                            alertDialog.setView(getLayoutInflater().inflate(R.layout.layout_custom_alert_dialog, null));
                            alertDialog.setCancelable(false);
                            alertDialog.setPositiveButton((CharSequence) "Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            alertDialog.create().show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        volleySingle.getInstance(mContext).getfRequestQueue().add(stringRequest);
    }
}
