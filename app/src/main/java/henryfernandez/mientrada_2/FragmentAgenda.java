package henryfernandez.mientrada_2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.JsonObjectRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FragmentAgenda extends Fragment {

    SimpleDateFormat dateFormatOriginal = new SimpleDateFormat("dd/MM/yyyy h:mm:ss");
    SimpleDateFormat dateFormatRequerido = new SimpleDateFormat("EEEE, d MMM yyyy h:mm");
    LinearLayout lienarContenedor;
    Context mContext;
    RecyclerView rc;

    public class Agenda {
        String Descripcion;
        String Horas;

        public Agenda(String horas, String descripcion) {
            this.Horas = horas;
            this.Descripcion = descripcion;
        }

        public String getHoras() {
            return this.Horas;
        }

        public String getDescripcion() {
            return this.Descripcion;
        }
    }
/*
    class C03721 implements Listener<JSONObject> {
        C03721() {
        }

        public void onResponse(JSONObject response) {
            try {
                if (response.getBoolean("success")) {
                    JSONArray array = response.getJSONArray("informacion");
                    ArrayList<Agenda> listaAgenda = new ArrayList();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject objeto = array.getJSONObject(i);
                        try {
                            String fecha1 = FragmentAgenda.this.dateFormatRequerido.format(FragmentAgenda.this.dateFormatOriginal.parse(objeto.getString("fecha_hora_inicio")));
                            String fecha2 = FragmentAgenda.this.dateFormatRequerido.format(FragmentAgenda.this.dateFormatOriginal.parse(objeto.getString("fecha_hora_cierre")));
                            listaAgenda.add(new Agenda(fecha1.substring(0, 1).toUpperCase() + fecha1.substring(1) + " / " + fecha2.substring(0, 1).toUpperCase() + fecha2.substring(1), objeto.getString("descripcion")));
                        } catch (Exception e) {
                        }
                    }
                    FragmentAgenda.this.MostrarInformacion(listaAgenda);
                    return;
                }
                Toast.makeText(FragmentAgenda.this.mContext, "No se encontro información", 1).show();
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    class C03732 implements ErrorListener {
        C03732() {
        }

        public void onErrorResponse(VolleyError error) {
            Toast.makeText(FragmentAgenda.this.mContext, "Revise su conexión", 1).show();
        }
    }
*/
    public class AdapterRecyclerView extends RecyclerView.Adapter<ViewHolder> {
        ArrayList<Agenda> ListaAgenda = new ArrayList();

        public AdapterRecyclerView(ArrayList<Agenda> arrayList) {
            this.ListaAgenda = arrayList;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_agenda, parent, false);

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.txt1.setText(((Agenda) this.ListaAgenda.get(position)).getHoras().toString());
            holder.txt2.setText(((Agenda) this.ListaAgenda.get(position)).getDescripcion());
        }

        public int getItemCount() {
            return this.ListaAgenda.size();
        }
    }

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        LinearLayout contenedor;
        TextView txt1;
        TextView txt2;

        ViewHolder(View v) {
            super(v);
            this.txt1 = (TextView) v.findViewById(R.id.textViewHoras);
            this.txt2 = (TextView) v.findViewById(R.id.textViewDescripcion);
            this.contenedor = (LinearLayout) v.findViewById(R.id.contenedorItemaAngenda);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_agenda, container, false);
        this.mContext = getContext();
        this.rc = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.lienarContenedor = (LinearLayout) view.findViewById(R.id.contenedorPrincialAgenda);
        traerInformacion();
        return view;
    }

    public void traerInformacion() {
        //volleySingle.getInstance(this.mContext).getfRequestQueue().add(new JsonObjectRequest(0, getString(C0253R.string.agenda), null, new C03721(), new C03732()));
    }

    public void MostrarInformacion(ArrayList<Agenda> lista) {
        this.rc.setAdapter(new AdapterRecyclerView(lista));
        LinearLayoutManager llm = new LinearLayoutManager(this.mContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        this.rc.setLayoutManager(llm);
    }
}
