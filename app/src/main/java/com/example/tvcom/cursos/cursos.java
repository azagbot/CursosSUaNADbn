package com.example.tvcom.cursos;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class cursos extends AppCompatActivity {
    BottomBar mBottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottombar);
        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case 0:cursos.FCursos a = new cursos.FCursos();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,a).commit();
                    case 1:cursos.FSeusCursos b = new cursos.FSeusCursos();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,b).commit();
                    case 2:cursos.FAjuda d = new cursos.FAjuda();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,d).commit();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cursos, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //startActivity(new Intent(this,FragmentClasses.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public static class FCursos extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_cursos_scursos, container, false);
            ListView listView = (ListView) rootView.findViewById(R.id.listView_cursos);
            String[] a={"Desenvolvimento Android","Desenvolvimento iOS","Analista de redes"};
            ListAdapter adapter = new AdapterCursos(getContext(),a);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    switch (i){
                        case 0:startActivity(new Intent(getContext(),DescricaoCursos.class));
                        case 1:startActivity(new Intent(getContext(),DescricaoiOS.class));
                        case 2:startActivity(new Intent(getContext(),DescricaoAnalista.class));
                    }
                }
            });
            return rootView;
        }
    }
    public static class FSeusCursos extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_cursos_scursos, container, false);
            ListView listView = (ListView) rootView.findViewById(R.id.listView_cursos);
            String[] a={"Desenvolvimento iOS","Desenvolvimento Android"};
            ListAdapter adapter = new AdapterSeusCursos(getContext(),a);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //  startActivity(new Intent(getContext(),DF.class));
                }
            });
            return rootView;
        }
    }
    public static class FAjuda extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_ajuda, container, false);
            return rootView;
        }
    }
}
class AdapterCursos extends ArrayAdapter<String> {
    public AdapterCursos(Context context, String[] values) {
        super(context, R.layout.entry_cursos, values);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.entry_cursos, parent, false);
        String text = getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.textView_entry_cursos);
        textView.setText(text);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        switch (position) {
            //android,iOS,analista
            case 0:
                imageView.setBackgroundResource(R.drawable.workingandroid);
            case 1:
                imageView.setBackgroundResource(R.drawable.workingmac);
            case 2:
                imageView.setBackgroundResource(R.drawable.workinganalista);
            default:
                imageView.setBackgroundResource(R.mipmap.ic_launcher);
        }
        return view;
    }
}
class AdapterSeusCursos extends ArrayAdapter<String> {
    public AdapterSeusCursos(Context context, String[] values) {
        super(context, R.layout.entry_cursos, values);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.entry_cursos, parent, false);
        String text = getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.textView_entry_cursos);
        textView.setText(text);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        switch (position) {
            //iOS,Android
            case 0:
                imageView.setBackgroundResource(R.drawable.workingmac);
            case 1:
                imageView.setBackgroundResource(R.drawable.workingandroid);
            default:
                imageView.setBackgroundResource(R.mipmap.ic_launcher);
        }
        return view;
    }
}

