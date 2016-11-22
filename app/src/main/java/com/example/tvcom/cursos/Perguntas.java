package com.example.tvcom.cursos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by tvcom on 04/11/16.
 */

public class Perguntas extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview);
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ListView listView = (ListView) findViewById(R.id.listView_perguntas);
        String[] a={"Como eu começo um curso?","Quais são as formas de pagamento?",
                "Como eu começo um curso?","Quais são as formas de pagamento?",
                "Como eu começo um curso?","Quais são as formas de pagamento?"};
        ListAdapter adapter = new AdapterPerguntas(this,a);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    startActivity(new Intent(Perguntas.this,Respostas.class));
            }
        });
    }
}
class AdapterPerguntas extends ArrayAdapter<String> {
    public AdapterPerguntas(Context context, String[] values) {
        super(context, R.layout.entry_perguntas, values);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.entry_perguntas, parent, false);
        String text = getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.textView_entry_perguntas);
        textView.setText(text);
        return view;
    }
}


