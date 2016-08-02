package com.payfood;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class DetalleMenu extends ActionBarActivity {

    private String[] carta;
   // private String precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        int tipo = getIntent().getIntExtra(MenUtil.TIPO, 0);
        int restaurante = getIntent().getIntExtra(MenUtil.RESTAURANTE, 0);
        //getSupportActionBar().setTitle(MenUtil.title[tipo]);
        //precio = MenUtil.precios[tipo];
        Seleccionar(tipo, restaurante);
        setContentView(R.layout.detalle_menu);
        final ListView list = (ListView) findViewById(R.id.detalle_list);
        list.setAdapter(new AdapterRes(this));

    }

    private void Seleccionar(int tipo, int res) {
        if (res == 0) {
            switch (tipo) {
                case 0:
                    getSupportActionBar().setTitle(MenUtil.title1[0]);
                    carta = MenUtil.almuerzo1;
                    break;
                case 1:
                    getSupportActionBar().setTitle(MenUtil.title2[0]);
                    carta = MenUtil.bandeja1;
                    break;
                case 2:
                    getSupportActionBar().setTitle(MenUtil.title3[0]);
                    carta = MenUtil.sopas1;
                    break;
                case 3:
                    getSupportActionBar().setTitle(MenUtil.title4[0]);
                    carta = MenUtil.principio1;
                    break;
                case 4:
                    getSupportActionBar().setTitle(MenUtil.title5[0]);
                    carta = MenUtil.carne1;
                    break;
                case 5:
                    getSupportActionBar().setTitle(MenUtil.title6[0]);
                    carta = MenUtil.jugo1;
                    break;
                default:
                    getSupportActionBar().setTitle(MenUtil.title1[0]);
                    carta = MenUtil.almuerzo1;
                    break;
            }
        } else if (res == 1) {
            switch (tipo) {
                case 0:
                    getSupportActionBar().setTitle(MenUtil.title1[1]);
                    carta = MenUtil.ejecutivo1;
                    break;
                case 1:
                    getSupportActionBar().setTitle(MenUtil.title2[1]);
                    carta = MenUtil.estudiantil1;
                    break;
                case 2:
                    getSupportActionBar().setTitle(MenUtil.title3[1]);
                    carta = MenUtil.principio2;
                    break;
                case 3:
                    getSupportActionBar().setTitle(MenUtil.title4[1]);
                    carta = MenUtil.carne2;
                    break;
                case 4:
                    getSupportActionBar().setTitle(MenUtil.title5[1]);
                    carta = MenUtil.complemento2;
                    break;
                case 5:
                    getSupportActionBar().setTitle(MenUtil.title6[1]);
                    carta = MenUtil.jugo2;
                    break;
                default:
                    getSupportActionBar().setTitle(MenUtil.title1[1]);
                    carta = MenUtil.ejecutivo1;
                    break;
            }
        }
        else if (res == 2) {
            switch (tipo) {
                case 0:
                    getSupportActionBar().setTitle(MenUtil.title1[2]);
                    carta = MenUtil.ejecutivo2;
                    break;
                case 1:
                    getSupportActionBar().setTitle(MenUtil.title2[2]);
                    carta = MenUtil.sopas3;
                    break;
                case 2:
                    getSupportActionBar().setTitle(MenUtil.title3[2]);
                    carta = MenUtil.principio3;
                    break;
                case 3:
                    getSupportActionBar().setTitle(MenUtil.title4[2]);
                    carta = MenUtil.carne3;
                    break;
                case 4:
                    getSupportActionBar().setTitle(MenUtil.title5[2]);
                    carta = MenUtil.jugo3;
                    break;
                case 5:
                    getSupportActionBar().setTitle(MenUtil.title6[2]);
                    carta = MenUtil.especialidades;
                    break;
                default:
                    getSupportActionBar().setTitle(MenUtil.title1[2]);
                    carta = MenUtil.ejecutivo2;
                    break;
            }
        }
        else if(res == 3){
            switch (tipo) {
                case 0:
                    getSupportActionBar().setTitle(MenUtil.title1[3]);
                    carta = MenUtil.almuerzos_saludables;
                    break;
                case 1:
                    getSupportActionBar().setTitle(MenUtil.title2[3]);
                    carta = MenUtil.wraps;
                    break;
                case 2:
                    getSupportActionBar().setTitle(MenUtil.title3[3]);
                    carta = MenUtil.ensaladas;
                    break;
                case 3:
                    getSupportActionBar().setTitle(MenUtil.title4[3]);
                    carta = MenUtil.ceviches;
                    break;
                case 4:
                    getSupportActionBar().setTitle(MenUtil.title5[3]);
                    carta = MenUtil.wok;
                    break;
                case 5:
                    getSupportActionBar().setTitle(MenUtil.title6[3]);
                    carta = MenUtil.bebidas;
                    break;
                default:
                    getSupportActionBar().setTitle(MenUtil.title1[3]);
                    carta = MenUtil.almuerzos_saludables;
                    break;
            }
        }
        else {
            switch (tipo) {
                case 0:
                    getSupportActionBar().setTitle(MenUtil.title1[4]);
                    carta = MenUtil.bebidas1;
                    break;
                case 1:
                    getSupportActionBar().setTitle(MenUtil.title2[4]);
                    carta = MenUtil.frutas;
                    break;
                case 2:
                    getSupportActionBar().setTitle(MenUtil.title3[4]);
                    carta = MenUtil.comidasrapidas;
                    break;
                default:
                    getSupportActionBar().setTitle(MenUtil.title1[4]);
                    carta = MenUtil.bebidas1;
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int tipo = getIntent().getIntExtra(MenUtil.TIPO, 0);
        int res = getIntent().getIntExtra(MenUtil.RESTAURANTE, 0);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if(res==0)
        {
            if(tipo==0){
                menu.findItem(R.id.menu_precio).setTitle("$5000");
            }
            else if(tipo==1){
                menu.findItem(R.id.menu_precio).setTitle("$4500");
            }
            else if(tipo==2){
                menu.findItem(R.id.menu_precio).setTitle("$2000");
            }
            else if(tipo==3){
                menu.findItem(R.id.menu_precio).setTitle("$1000");
            }
            else if(tipo==4){
                menu.findItem(R.id.menu_precio).setTitle("$2000");
            }
            else if(tipo==5){
                menu.findItem(R.id.menu_precio).setTitle("$500");
            }
            else{
                menu.findItem(R.id.menu_precio).setTitle("$5000");
            }
        }
        else if(res==1){

            if(tipo==0){
                menu.findItem(R.id.menu_precio).setTitle("$6500");
            }
            else if(tipo==1){
                menu.findItem(R.id.menu_precio).setTitle("$2050");
            }
            else if(tipo==2){
                menu.findItem(R.id.menu_precio).setTitle("");
            }
            else if(tipo==3){
                menu.findItem(R.id.menu_precio).setTitle("");
            }
            else if(tipo==4){
                menu.findItem(R.id.menu_precio).setTitle("");
            }
            else if(tipo==5){
                menu.findItem(R.id.menu_precio).setTitle("$500");
            }
            else{
                menu.findItem(R.id.menu_precio).setTitle("$5000");
            }
        }
        else if(res==2){

            if(tipo==0){
                menu.findItem(R.id.menu_precio).setTitle("$5000");
            }
            else if(tipo==1){
                menu.findItem(R.id.menu_precio).setTitle("");
            }
            else if(tipo==2){
                menu.findItem(R.id.menu_precio).setTitle("");
            }
            else if(tipo==3){
                menu.findItem(R.id.menu_precio).setTitle("");
            }
            else if(tipo==4){
                menu.findItem(R.id.menu_precio).setTitle("$500");
            }
            else if(tipo==5){
                menu.findItem(R.id.menu_precio).setTitle("");
            }
            else{
                menu.findItem(R.id.menu_precio).setTitle("");
            }
        }
        else if(res==3){

            if(tipo==0){
                menu.findItem(R.id.menu_precio).setTitle("");
            }
            else if(tipo==1){
                menu.findItem(R.id.menu_precio).setTitle("");
            }
            else if(tipo==2){
                menu.findItem(R.id.menu_precio).setTitle("");
            }
            else if(tipo==3){
                menu.findItem(R.id.menu_precio).setTitle("");
            }
            else if(tipo==4){
                menu.findItem(R.id.menu_precio).setTitle("");
            }
            else if(tipo==5){
                menu.findItem(R.id.menu_precio).setTitle("");
            }
            else{
                menu.findItem(R.id.menu_precio).setTitle("");
            }

        }
        else if(res==4){

            if(tipo==0){
                menu.findItem(R.id.menu_precio).setTitle("");
            }
            else if(tipo==1){
                menu.findItem(R.id.menu_precio).setTitle("");
            }
            else if(tipo==2){
                menu.findItem(R.id.menu_precio).setTitle("");
            }
            else{
                menu.findItem(R.id.menu_precio).setTitle("");
            }

        }
        else{
            menu.findItem(R.id.menu_precio).setTitle("");


        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int tipo = getIntent().getIntExtra(MenUtil.TIPO, 0);
        int res = getIntent().getIntExtra(MenUtil.RESTAURANTE, 0);
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class AdapterRes extends BaseAdapter {

        private LayoutInflater inflater;

        public AdapterRes(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return carta.length;
        }

        @Override
        public String getItem(int position) {
            return carta[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_carta, parent, false);
                holder.name = (TextView) convertView.findViewById(R.id.item);
                convertView.setTag(holder);
            } else
                holder = (ViewHolder) convertView.getTag();

            holder.name.setText(carta[position]);
            return convertView;
        }
    }

    private static class ViewHolder {
        TextView name;
    }
}
