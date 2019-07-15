package edu.mx.tesoem.itics.daniel.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class editar extends AppCompatActivity {

    private EditText et_codigo, et_descripcion, et_precio, et_correo, et_edad;
    private EditText bienvenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        et_codigo = (EditText)findViewById(R.id.editText11);

        et_descripcion = (EditText)findViewById(R.id.editText8);
        et_precio = (EditText)findViewById(R.id.editText10);
        et_correo = (EditText)findViewById(R.id.editText9);
        et_edad = (EditText)findViewById(R.id.editText7);

        Bundle parametro = (Bundle) this.getIntent().getExtras();
        if(parametro !=null){
            String datos = parametro.getString("Id");
            et_codigo.setText(datos);


        }

    }

    public void Modificar(View view){
        edu.mx.tesoem.itics.daniel.myapplication.AdminSQLiteOpenHelper admin = new edu.mx.tesoem.itics.daniel.myapplication.AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDatabase = admin.getWritableDatabase();

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

            int cantidad = BaseDatabase.update("articulos", registro, "codigo=" + codigo, null);
            BaseDatabase.close();

            if(cantidad == 1){
                Toast.makeText(this, "Artículo modificado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El artículo no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void Buscar(View view){
        edu.mx.tesoem.itics.daniel.myapplication.AdminSQLiteOpenHelper admin = new edu.mx.tesoem.itics.daniel.myapplication.AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();
        String correo = et_correo.getText().toString();
        String edad = et_edad.getText().toString();

        if(!codigo.isEmpty()){
            Cursor fila = BaseDeDatabase.rawQuery
                    ("select descripcion, precio ,correo ,edad from articulos where codigo =" + codigo, null);

            if(fila.moveToFirst()){
                et_descripcion.setText(fila.getString(0));
                et_precio.setText(fila.getString(1));
                et_correo.setText(fila.getString(2));
                et_edad.setText(fila.getString(3));
                BaseDeDatabase.close();
            } else {
                Toast.makeText(this,"No existe el artículo", Toast.LENGTH_SHORT).show();
                BaseDeDatabase.close();
            }

        } else {
            Toast.makeText(this, "Debes introducir el código del artículo", Toast.LENGTH_SHORT).show();
        }
    }



    public void regresar(View v){
        Intent intent = new Intent(this,MainActivity.class);

        startActivity(intent);
        this.finish();
    }

}
