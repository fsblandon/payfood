package com.payfood;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Home extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private List<Restaurante> lista = new ArrayList<>();
    private AdapterRes adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setContentView(R.layout.activity_home);
        final ListView list = (ListView) findViewById(R.id.home_list);
        LLenar();
        adapter = new AdapterRes(this, lista);
        list.addFooterView(getLayoutInflater().inflate(R.layout.list_footer, null));
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void LLenar() {
        for (int a = 0; a < MenUtil.rest.length; a++) {
            lista.add(new Restaurante(MenUtil.rest[a], MenUtil.rest_img[a]));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_home, menu);

        final SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView search = (SearchView) menu.findItem(R.id.menu_search).getActionView();

        search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.Filter(s);
                return true;
            }
        });

        search.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                adapter.Filter("");
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        return true;
    }

    private class AdapterRes extends BaseAdapter {

        private LayoutInflater inflater;
        private ArrayList<Restaurante> result;
        private List<Restaurante> items = null;

        public AdapterRes(Context context, List<Restaurante> data) {
            inflater = LayoutInflater.from(context);
            this.items = data;
            result = new ArrayList<>();
            result.addAll(items);
        }

        @Override
        public int getCount() {
            return result.size();
        }

        @Override
        public Restaurante getItem(int position) {
            return result.get(position);
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
                convertView = inflater.inflate(R.layout.item_restaurant, parent, false);
                holder.image = (ImageView) convertView.findViewById(R.id.item_image);
                convertView.setTag(holder);
            } else
                holder = (ViewHolder) convertView.getTag();

            holder.image.setImageResource(result.get(position).IMAGE);
            return convertView;
        }

        public void Filter(String search) {
            search = search.toLowerCase(Locale.getDefault());
            result.clear();
            if (search.length() == 0) {
                result.addAll(items);
            } else {
                for (Restaurante wp : items) {
                    if (wp.NAME.toLowerCase(Locale.getDefault())
                            .contains(search)) {
                        result.add(wp);
                    }
                }
            }
            notifyDataSetChanged();
        }

    }

    private static class ViewHolder {
        ImageView image;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position != MenUtil.rest.length) {
            final Intent intent = new Intent(this, Detalle.class);
            intent.putExtra(Detalle.key, position);
            startActivity(intent);
        }
    }
}
