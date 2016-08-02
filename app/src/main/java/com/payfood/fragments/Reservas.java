package com.payfood.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.payfood.Detalle;
import com.payfood.MenUtil;
import com.payfood.R;
import com.payfood.Register;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.Locale;


public class Reservas extends Fragment implements TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener {

    private int pos = 0;
    private String hora = null, fecha = null, desc, nombre;
    private Calendar now = Calendar.getInstance();
    private Button hor, dat;
    private EditText edit_des, edit_nom;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pos = getArguments().getInt(Detalle.key);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_reserva, null);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dat = (Button) view.findViewById(R.id.select_date);
        dat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DatePickerDialog dpd = DatePickerDialog.newInstance(Reservas.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        fecha = null;
                        hor.setText("Selecciona una fecha");
                    }
                });
                dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");

            }
        });
        hor = (Button) view.findViewById(R.id.select_hour);
        hor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        Reservas.this,
                        12,
                        0,
                        true
                );
                tpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        hora = null;
                        hor.setText("Selecciona una hora");
                    }
                });
                tpd.show(getActivity().getFragmentManager(), "Timepickerdialog");

            }
        });
        final Button rese = (Button) view.findViewById(R.id.reserva_reservar);
        rese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reservar();
            }
        });

        edit_nom = (EditText) view.findViewById(R.id.reserva_name);
        edit_des = (EditText) view.findViewById(R.id.reserva_mas);
    }


    private void Reservar() {
        nombre = edit_nom.getText().toString().trim();
        desc = edit_des.getText().toString().trim();

        if (nombre.equals("") || hora == null || fecha == null) {
            Toast.makeText(getActivity(), "Porfavor completa todas los campos de la reserva", Toast.LENGTH_SHORT).show();
        } else {
            final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            if (preferences.getBoolean(MenUtil.KEY_SESION, false)) {
                Reservacion();
            } else {
                Toast.makeText(getActivity(), "Registrate primero para poder hacer una reservación", Toast.LENGTH_SHORT).show();
                final Intent regis = new Intent(getActivity(), Register.class);
                startActivityForResult(regis, Register.REGISTRO_OK);
            }
        }
    }

    private void Reservacion() {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String usuario = preferences.getString(MenUtil.KEY_USER, "");
        final String body = "A nombre de : " + nombre + "\n" +
                "Usuario: " + usuario + "\n" +
                "Fecha : " + fecha + "\n" + "Hora : " + hora + "\n\n" +
                "Mas información\n\n" + desc;

        final Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"payfoodapp@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Reserva : " + MenUtil.rest[pos]);
        i.putExtra(Intent.EXTRA_TEXT, body);
        try {
            startActivity(Intent.createChooser(i, "Enviando email..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "No tienes cuentas de correo disponibles para hacer la reserva", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Register.REGISTRO_OK && resultCode == getActivity().RESULT_OK) {
            Reservacion();
        } else
            Toast.makeText(getActivity(), "Debes registrarte primero para poder hacer una reserva", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
        String minuteString = minute < 10 ? "0" + minute : "" + minute;
        hora = hourString + ":" + minuteString;
        hor.setText(hora);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        now.set(year, monthOfYear, dayOfMonth);
        fecha = now.get(Calendar.DAY_OF_MONTH) + " " + now.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " de " + now.get(Calendar.YEAR);
        dat.setText(fecha);
    }
}