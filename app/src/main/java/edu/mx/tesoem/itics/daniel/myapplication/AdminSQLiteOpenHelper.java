package edu.mx.tesoem.itics.daniel.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    private SQLiteDatabase basedatos;
    private AdminSQLiteOpenHelper datoshelper;

    ArrayList<String> contenido;


    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public ArrayList llenarv(){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM articulos";
        Cursor registros = database.rawQuery(q,null);
        if(registros.moveToFirst()){

            do{
                lista.add(registros.getString(0));
                lista.add(registros.getString(1));
                lista.add(registros.getString(2));
                lista.add(registros.getString(3));
                lista.add(registros.getString(4));
            }while(registros.moveToNext());
        }
        return lista;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table articulos(codigo int primary key , descripcion text, precio text,correo text,edad text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
