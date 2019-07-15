package edu.mx.tesoem.itics.daniel.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class registrar extends AppCompatActivity {
    private EditText et_codigo, et_descripcion, et_precio, et_correo, et_edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        et_codigo = (EditText)findViewById(R.id.editText);
        et_descripcion = (EditText)findViewById(R.id.editText2);
        et_precio = (EditText)findViewById(R.id.editText4);
        et_correo = (EditText)findViewById(R.id.editText3);
        et_edad = (EditText)findViewById(R.id.editText5);
    }

    public void Registrar(View view){
        edu.mx.tesoem.itics.daniel.myapplication.AdminSQLiteOpenHelper admin = new edu.mx.tesoem.itics.daniel.myapplication.AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();
        String correo = et_correo.getText().toString();
        String edad = et_edad.getText().toString();

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);
            registro.put("correo", correo);
            registro.put("edad", edad);

            BaseDeDatos.insert("articulos", null, registro);

            BaseDeDatos.close();
            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");
            et_correo.setText("");
            et_edad.setText("");

            Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void regreso(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        this.finish();
    }



}
