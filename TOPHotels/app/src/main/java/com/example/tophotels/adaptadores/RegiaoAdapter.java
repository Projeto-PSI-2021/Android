package com.example.tophotels.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.tophotels.R;
import com.example.tophotels.modelos.Regiao;

import java.util.ArrayList;
import java.util.List;

public class RegiaoAdapter extends ArrayAdapter<Regiao> {
    Context contexto;
    int recurso, tvRecursoId;
    List<Regiao> items, tempItems, sugestoes;

    public RegiaoAdapter(Context context, int resource, int textViewResourceId, List<Regiao> items) {
        super(context, resource, textViewResourceId, items);
        this.contexto = context;
        this.recurso = resource;
        this.tvRecursoId = textViewResourceId;
        this.items = items;
        tempItems = new ArrayList<Regiao>(items);
        sugestoes = new ArrayList<Regiao>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_autocomplete_regioes, parent, false);
        }

        Regiao regiao = items.get(position);

        if (regiao != null) {
            TextView tvRegiao = (TextView) view.findViewById(R.id.tvRegiaoAutoComplete);
            if (tvRegiao != null) {
                tvRegiao.setText(regiao.getNome());
            }
        }

        return view;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((Regiao) resultValue).getNome();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                sugestoes.clear();
                for (Regiao regiao : tempItems) {
                    if (regiao.getNome().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        sugestoes.add(regiao);
                    }
                }
                FilterResults fitrarResultados = new FilterResults();
                fitrarResultados.values = sugestoes;
                fitrarResultados.count = sugestoes.size();
                return fitrarResultados;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<Regiao> filterList = (ArrayList<Regiao>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Regiao regiao : filterList) {
                    add(regiao);
                    notifyDataSetChanged();
                }
            }
        }
    };
}
