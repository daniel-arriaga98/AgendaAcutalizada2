package edu.mx.tesoem.itics.daniel.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView lvv;
    ArrayList<String> lista;
    ArrayAdapter<String> adaptador;
    EditText et_codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_codigo = (EditText)findViewById(R.id.editText6);

        edu.mx.tesoem.itics.daniel.myapplication.AdminSQLiteOpenHelper admin = new edu.mx.tesoem.itics.daniel.myapplication.AdminSQLiteOpenHelper(getApplicationContext(),"administracion",null,1);
        lvv = (GridView)findViewById(R.id.lista);
        lista = admin.llenarv();
        adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,lista);
        lvv.setAdapter(adaptador);
    }

    public void editar(View v){
        Intent intent = new Intent(this,editar.class);

        intent.putExtra("Id",et_codigo.getText().toString());
        /*findViewById(R.id.editText6)*/
        intent.putExtra("Nombre",et_codigo.getText().toString());
        intent.putExtra("Correo",et_codigo.getText().toString());
        intent.putExtra("Edad",et_codigo.getText().toString());

        startActivity(intent);
        this.finish();
    }

    public void registrar(View v){
        Intent intent = new Intent(this,registrar.class);
        startActivity(intent);
        this.finish();
    }

    public void Eliminar(View view){
        edu.mx.tesoem.itics.daniel.myapplication.AdminSQLiteOpenHelper admin = new edu.mx.tesoem.itics.daniel.myapplication.AdminSQLiteOpenHelper
                (this, "administracion", null, 1);
        SQLiteDatabase BaseDatabase = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if(!codigo.isEmpty()){

            int cantidad = BaseDatabase.delete("articulos", "codigo=" + codigo, null);
            BaseDatabase.close();

            et_codigo.setText("");

            if(cantidad == 1){
                Toast.makeText(this, "Artículo eliminado exitosamente", Toast.LENGTH_SHORT).show();
                edu.mx.tesoem.itics.daniel.myapplication.AdminSQLiteOpenHelper adminn = new edu.mx.tesoem.itics.daniel.myapplication.AdminSQLiteOpenHelper(getApplicationContext(),"administracion",null,1);
                lvv = (GridView)findViewById(R.id.lista);
                lista = adminn.llenarv();
                adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,lista);
                lvv.setAdapter(adaptador);
            } else {
                Toast.makeText(this, "El artículo no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debes de introducir el código del artículo", Toast.LENGTH_SHORT).show();
        }
    }


}