package com.example.tvcom.cursos;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.logo);
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                    if(tabId==R.id.menu_cursos){cursos.FCursos a = new cursos.FCursos();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,a).commit();
                    } else if(tabId==R.id.menu_seus_cursos) {
                        cursos.FSeusCursos b = new cursos.FSeusCursos();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, b).commit();
                    } else if (tabId== R.id.menu_ajuda) {
                        cursos.FAjuda d = new cursos.FAjuda();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, d).commit();
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
            String[] a={"Artes visuais","Técnico em mecânica","Design de interiores"
                    ,"Desenvolvimento iOS","Desenvolvimento Android"};
            ListAdapter adapter = new AdapterCursos(getContext(),a);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    switch (i){
                        case 0:startActivity(new Intent(getContext(),DescricaoArtes.class));break;
                        case 1:startActivity(new Intent(getContext(),DescricaoMecanica.class));break;
                        case 2:startActivity(new Intent(getContext(),DescricaoDesign.class));break;
                        case 3:startActivity(new Intent(getContext(),DescricaoiOS.class));break;
                        case 4:startActivity(new Intent(getContext(),DescricaoCursos.class));break;
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
            ListAdapter adapter = new AdapterCursos(getContext(),a);
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
            Button buttonPerguntas = (Button) rootView.findViewById(R.id.button_perguntas_frequentes);
            buttonPerguntas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getContext(),Perguntas.class));
                }
            });
            Button buttonFale = (Button) rootView.findViewById(R.id.button_fale_conosco);
            buttonFale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getContext(),FaleConosco.class));
                }
            });
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
        if(text=="Desenvolvimento iOS") {
            imageView.setBackgroundResource(R.drawable.workingmac);
        } else if(text == "Desenvolvimento Android"){
            imageView.setBackgroundResource(R.drawable.workingandroid);
        } else if(text=="Artes visuais") {
            imageView.setBackgroundResource(R.drawable.workingartist);
        } else if(text=="Técnico em mecânica") {
            imageView.setBackgroundResource(R.drawable.workingmecanico);
        } else if(text=="Design de interiores") {
            imageView.setBackgroundResource(R.drawable.workingdesign);
        }
        return view;
    }
}


