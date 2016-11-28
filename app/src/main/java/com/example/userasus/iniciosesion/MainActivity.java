package com.example.userasus.iniciosesion;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.userasus.iniciosesion.data.Conexion;
import com.example.userasus.iniciosesion.data.ContractUsuarios;

import java.io.IOException;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {

    Conexion con;
    SQLiteDatabase db;
    EditText nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String clase = getClass().toString();
        Toast.makeText(this,"Hola "+clase,Toast.LENGTH_SHORT).show();

        con = new Conexion(this,"univalle",null,1);
        db = con.getWritableDatabase();

        if(db!=null){
            Toast.makeText(this,"Conectados "+clase,Toast.LENGTH_SHORT).show();
            String usuarioArranque="  INSERT INTO "+ ContractUsuarios.UsuarioEntry.TABLE_NAME+" ('"+ ContractUsuarios.UsuarioEntry.NAME+"') " +
                    "VALUES ('admin');  " ;
            db.execSQL(usuarioArranque);
        }else{
            Toast.makeText(this,"Error en la conexion "+clase,Toast.LENGTH_SHORT).show();
        }

    }


    public void entrar(View v){
        nombre = (EditText) findViewById(R.id.etNombre);
//        String consulta = "SELECT '"+ ContractUsuarios.UsuarioEntry.ID+"', '"+ ContractUsuarios.UsuarioEntry.NAME+"'" +
//                " FROM '"+ ContractUsuarios.UsuarioEntry.TABLE_NAME+"' WHERE "+ ContractUsuarios.UsuarioEntry.NAME+"='"+nombre.toString()+"'";
        String nombreAux=nombre.getText().toString();
        String argumentos[] = new String[]{nombreAux};
        Cursor resultado =db.query(ContractUsuarios.UsuarioEntry.TABLE_NAME,
                null,
                " "+ ContractUsuarios.UsuarioEntry.NAME +" LIKE ?",
                argumentos,
                null,
                null,
                null,
                null);
        if(resultado.moveToNext()){
            String id = resultado.getString(resultado.getColumnIndex(ContractUsuarios.UsuarioEntry.ID));
            String name = resultado.getString(resultado.getColumnIndex(ContractUsuarios.UsuarioEntry.NAME));

            Toast.makeText(this,"Hola "+id+" - "+name,Toast.LENGTH_LONG).show();
            Intent irHOme = new Intent(MainActivity.this,Home.class);
            Bundle datos = new Bundle();
            datos.putString("id",id);
            datos.putString("name",name);
            irHOme.putExtras(datos);
            startActivity(irHOme.addFlags(irHOme.FLAG_ACTIVITY_CLEAR_TOP | irHOme.FLAG_ACTIVITY_SINGLE_TOP));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            con.BD_backup();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this,"Adios",Toast.LENGTH_SHORT).show();
    }


}
