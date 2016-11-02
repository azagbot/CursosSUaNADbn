package com.example.tvcom.cursos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by tvcom on 01/11/16.
 */

public class DescricaoiOS extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_curso);
        ImageView imageView=(ImageView) findViewById(R.id.imageView_Descricao);
        TextView textView=(TextView) findViewById(R.id.textView_Descricao_CURSO);
        textView.setText("Desenvolvimento iOS");
        imageView.setBackground(getResources().getDrawable(R.drawable.workingmac));
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
