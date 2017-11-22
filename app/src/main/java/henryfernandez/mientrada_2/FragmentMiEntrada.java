package henryfernandez.mientrada_2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONObject;

public class FragmentMiEntrada extends Fragment {
    public static String response;

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_mi_entrada, container, false);
        ImageView imv = (ImageView) view.findViewById(R.id.imageView);
        TextView txtViewNombresApellidos = (TextView) view.findViewById(R.id.TextViewNombres);
        TextView txtEntidad = (TextView) view.findViewById(R.id.TextViewEntidad);
        String datosIntente = response;
        try {
            JSONObject objetoJson = new JSONObject(datosIntente).getJSONArray("informacion").optJSONObject(0);
            String nombresApellidos = objetoJson.getString("codgradoscursos").toUpperCase().toString() + " " + objetoJson.getString("orden").toUpperCase().toString();
            String entidad = objetoJson.getString("entidad").toUpperCase().toString();
            txtViewNombresApellidos.setText(nombresApellidos);
            txtEntidad.setText(entidad);
            datosIntente = "{\"identificacion\":\"" + objetoJson.getString("descripcion") + "\",\"nombres\":\"" + objetoJson.getString("codgradoscursos") + " " + objetoJson.getString("orden") + "\"}";
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            imv.setImageBitmap(new BarcodeEncoder().createBitmap(new MultiFormatWriter().encode(datosIntente,
                    BarcodeFormat.QR_CODE, ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION)));
        } catch (Exception e2) {
            Log.e("Exception QR: ", e2.toString());
        }
        return view;
    }
}
