package com.payfood.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.payfood.Detalle;
import com.payfood.MenUtil;
import com.payfood.R;

public class Info extends Fragment implements OnMapReadyCallback {

    private int pos = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pos = getArguments().getInt(Detalle.key);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View v = inflater.inflate(R.layout.fragment_info, null);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((ImageView) view.findViewById(R.id.info_imagen)).setImageResource(MenUtil.imagen[pos]);
        ((TextView) view.findViewById(R.id.info_title)).setText(MenUtil.rest[pos]);
        //((TextView) view.findViewById(R.id.info_des)).setText(MenUtil.infoDes[pos]);
        ((TextView) view.findViewById(R.id.info_dir)).setText(MenUtil.infoDir[pos]);
        ((TextView) view.findViewById(R.id.info_horario)).setText(MenUtil.infoHor[pos]);
        ((TextView) view.findViewById(R.id.info_dom)).setText(MenUtil.reservas_domicilio[pos]);
        ((TextView) view.findViewById(R.id.tel_tittle)).setText(MenUtil.titulo_tel[pos]);
        //((ImageView) view.findViewById(R.id.img_tel)).setImageResource(MenUtil.tel_img[pos]);
        ((TextView) view.findViewById(R.id.dom_tittle)).setText(MenUtil.titulo_dom[pos]);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        final TextView tel = (TextView) view.findViewById(R.id.info_tel);
        tel.setText(MenUtil.reservas_number[pos]);
        tel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String uri = "tel:" + MenUtil.reservas_number[pos];
                final Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }

        });

        /*final ImageView tel1 = (ImageView) view.findViewById(R.id.img_tel);
        tel1.setImageResource(MenUtil.tel_img[pos]);
        tel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String uri = "tel:" + MenUtil.reservas_number[pos];
                final Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });*/

    }

    @Override
    public void onMapReady(GoogleMap map) {
        final LatLng sydney = new LatLng(MenUtil.Lat[pos], MenUtil.Lng[pos]);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
        map.addMarker(new MarkerOptions()
                .position(sydney));
    }
}
