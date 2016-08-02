package com.payfood.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.payfood.Detalle;
import com.payfood.DetalleMenu;
import com.payfood.MenUtil;
import com.payfood.R;


public class MenuA extends Fragment {

    private int pos = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pos = getArguments().getInt(Detalle.key);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_menu, null);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((ImageView) view.findViewById(R.id.title_imagen1)).setImageResource(MenUtil.imagen1[pos]);
        ((TextView) view.findViewById(R.id.title1)).setText(MenUtil.title1[pos]);
        view.findViewById(R.id.title_imagen1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ir(0);
            }
        });

        ((ImageView) view.findViewById(R.id.title_imagen2)).setImageResource(MenUtil.imagen2[pos]);
        ((TextView) view.findViewById(R.id.title2)).setText(MenUtil.title2[pos]);
        view.findViewById(R.id.title_imagen2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ir(1);
            }
        });

        ((ImageView) view.findViewById(R.id.title_imagen3)).setImageResource(MenUtil.imagen3[pos]);
        ((TextView) view.findViewById(R.id.title3)).setText(MenUtil.title3[pos]);
        view.findViewById(R.id.title_imagen3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ir(2);
            }
        });

        ((ImageView) view.findViewById(R.id.title_imagen4)).setImageResource(MenUtil.imagen4[pos]);
        ((TextView) view.findViewById(R.id.title4)).setText(MenUtil.title4[pos]);
        view.findViewById(R.id.title_imagen4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ir(3);
            }
        });

        ((ImageView) view.findViewById(R.id.title_imagen5)).setImageResource(MenUtil.imagen5[pos]);
        ((TextView) view.findViewById(R.id.title5)).setText(MenUtil.title5[pos]);
        view.findViewById(R.id.title_imagen5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ir(4);
            }
        });

        ((ImageView) view.findViewById(R.id.title_imagen6)).setImageResource(MenUtil.imagen6[pos]);
        ((TextView) view.findViewById(R.id.title6)).setText(MenUtil.title6[pos]);
        view.findViewById(R.id.title_imagen6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ir(5);
            }
        });


    }

    private void Ir(int ti) {
        final Intent intent = new Intent(getActivity(), DetalleMenu.class);
        intent.putExtra(MenUtil.TIPO, ti);
        intent.putExtra(MenUtil.RESTAURANTE, pos);
        startActivity(intent);
    }
}
