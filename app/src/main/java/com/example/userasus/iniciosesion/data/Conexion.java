package com.example.userasus.iniciosesion.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by UserAsus on 27/11/2016.
 */
public class Conexion extends SQLiteOpenHelper {

    String table =" CREATE TABLE "+ ContractUsuarios.UsuarioEntry.TABLE_NAME+
            " ("+ ContractUsuarios.UsuarioEntry.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ""+ ContractUsuarios.UsuarioEntry.NAME+" TEXT);  ";

    static String  BASE_DATOS="univalle";

    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(table);
    }

    public static void BD_backup() throws IOException {
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());

        final String inFileName = "/data/data/com.example.userasus.iniciosesion/databases/"+ BASE_DATOS;
        File dbFile = new File(inFileName);
        FileInputStream fis = null;

        fis = new FileInputStream(dbFile);

        //String directorio = obtenerDirectorioCopias();
        String directorio = "/storage/sdcard0/Download/";
        File d = new File(directorio);
        if (!d.exists()) {
            d.mkdir();
        }
        String outFileName = directorio + "/"+BASE_DATOS + "_"+timeStamp;

        OutputStream output = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = fis.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }

        output.flush();
        output.close();
        fis.close();
    }
}
