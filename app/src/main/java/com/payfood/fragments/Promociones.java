package com.payfood.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.payfood.Detalle;
import com.payfood.MenUtil;
import com.payfood.R;


public class Promociones extends Fragment {

    private int pos = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pos = getArguments().getInt(Detalle.key);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_promociones, null);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) view.findViewById(R.id.promo_des1)).setText(MenUtil.promo1[pos]);
        ((TextView) view.findViewById(R.id.promo_des2)).setText(MenUtil.promo2[pos]);
        ((ImageView) view.findViewById(R.id.img_1)).setImageResource(MenUtil.promo_img1[pos]);
        ((ImageView) view.findViewById(R.id.img_2)).setImageResource(MenUtil.promo_img2[pos]);


    }
}