package com.example.trabajopractico2;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Telephony;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.Date;

import static android.Manifest.permission.READ_SMS;

public class Servicio extends Service {
    public Servicio() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }





    @Override public int onStartCommand(Intent intent, int flags, int startId) {
            if (ContextCompat.checkSelfPermission(this, READ_SMS) != PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Conceder Permisos", Toast.LENGTH_LONG).show();
            }
            Runnable tarea = new Runnable()
            {
                public void run()
                {
                    try
                    {
                        mostrarSms();
                        Thread.sleep(90000);

                    } catch(InterruptedException e)
                    {
                        Log.e("Error: ", e.getMessage()); e.printStackTrace();
                    } }
            };
            Thread trabajador = new Thread(tarea); trabajador.start();

                return START_STICKY;
    }















    public void mostrarSms() {
        //Uri uri = Uri.parse("content://sms/inbox");
        Uri uri =  Telephony.Sms.CONTENT_URI;
        ContentResolver cr = getContentResolver();

        Cursor puntero = cr.query(uri, null, null, null, null);

        if (puntero.getCount() > 0) {
            int i = 0;
            while (puntero.moveToNext() && i < 5) {

                String numero = puntero.getString(puntero.getColumnIndex(Telephony.Sms.ADDRESS));

                Long fecha = new Long(puntero.getString(puntero.getColumnIndex(Telephony.Sms.Inbox.DATE)));
                Date date = new Date(fecha);

                String mensaje = puntero.getString(puntero.getColumnIndex(Telephony.Sms.BODY));


                Log.d("Salida", "Numero de Telefono: " + numero);
                Log.d("Salida", "Fecha:  " + date.toString());
                Log.d("Salida", "Mensaje: " + mensaje);

                i++;

            }
        }

    }



    }

    /*Long dateLong = new Long(cursor.getString(cursor.getColumnIndex(Telephony.Sms.Inbox.DATE)));Date date = new Date(dateLong);
    Log.d("Fecha: ", date.toString());
    Asi queda la fecha para quie nquiera


        Runnable tarea=new Runnable() {
            public void run() {
                while (true) {

                    try {
                        Thread.sleep(90000);
                    } catch (InterruptedException e) {
                        Log.e("Error: ", e.getMessage());
                        e.printStackTrace();
                    }
                }
            }

            Thread trabajador = new Thread(tarea);
        trabajador.start();
        }
    */

