package com.example.guiapracticasem5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et1, et2, et3;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recibimiento de parametros desde el layout
        et1=findViewById(R.id.codigo);
        et2=findViewById(R.id.descripcion);
        et3=findViewById(R.id.precio);

        //recibimiento de parametros de lo botones desde el layout
        btn1=findViewById(R.id.guardar);
        btn2=findViewById(R.id.consultar);
        btn3=findViewById(R.id.consultarDescripcion);
        btn4=findViewById(R.id.eliminar);
        btn5=findViewById(R.id.modificar);
        btn6=findViewById(R.id.salir);
        btn7=findViewById(R.id.nuevo);

        //funcionamiento para el click
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
//        String codigo = et1.getText().toString();
//        String descripcion = et2.getText().toString();
//        String precio = et3.getText().toString();
//        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null,1);
//        SQLiteDatabase bd = admin.getWritableDatabase();
        switch (view.getId()){

            case R.id.guardar:
                    guardar();
                break;
            case R.id.consultar:
                    consultarCodigo();
                break;
            case R.id.consultarDescripcion:
                    consultarDescrip();
                break;
            case R.id.eliminar:
                    eliminar();
                break;
            case R.id.modificar:
                        modificar();
                break;
            case R.id.salir:
                    salir();
                break;
            default:
                break;
        }

    }

    public  void guardar(){
        //          METODO GUARDAR
        String codigo = et1.getText().toString();
        String descripcion = et2.getText().toString();
        String precio = et3.getText().toString();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        if (codigo.isEmpty()) {
            et1.setError("campo obligatorio");
        }else if (descripcion.isEmpty()){
            et2.setError("campo obligatorio");
        }else if (precio.isEmpty()) {
            et3.setError("campo obligatorio");
        }else{

            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descipcion", descripcion);
            registro.put("precio", precio);
            bd.insert("articulos", null, registro);
            bd.close();
            et1.setText("");
            et2.setText("");
            et3.setText("");
            Toast.makeText(this, "Se cargaron los datos del articulo", Toast.LENGTH_SHORT).show();
        }
    }
    public  void consultarCodigo(){

        //                                          METODO CONSULTAR PR CODIGO
        String codigo = et1.getText().toString();
        String descripcion = et2.getText().toString();
        String precio = et3.getText().toString();
        if (codigo.isEmpty()) {
            et1.setError("campo obligatorio");
        }else if (descripcion.isEmpty()){
            et2.setError("campo obligatorio");
        }else if (precio.isEmpty()) {
            et3.setError("campo obligatorio");
        }else{
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor fila = bd.rawQuery("select descripcion, precio from articulos where codigo=" + codigo, null);
            if (fila.moveToFirst()){
                et2.setText(fila.getString(0));
                et3.setText(fila.getString(1));
            }else{
                Toast.makeText(this, "No existe un articulo con dicho codigo", Toast.LENGTH_SHORT).show();
                bd.close();
            }
        }

    }
    public  void consultarDescrip(){
        String codigo = et1.getText().toString();
        String descripcion = et2.getText().toString();
        String precio = et3.getText().toString();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        //                                      METODO CONSULTAR POR DESCRIPCION
        if (codigo.isEmpty()) {
            et1.setError("campo obligatorio");
        }else if (descripcion.isEmpty()){
            et2.setError("campo obligatorio");
        }else if (precio.isEmpty()) {
            et3.setError("campo obligatorio");
        }else{
            Cursor fila= bd.rawQuery("select codigo, precio fron articulos where descripcion='" + descripcion +"'", null);
            if (fila.moveToFirst()){
                et1.setText(fila.getString(0));
                et3.setText(fila.getString(1));
            }else{
                Toast.makeText(this, "No existe un articulo con dicha descripcion", Toast.LENGTH_SHORT).show();
                bd.close();
            }
        }
    }
    public  void eliminar(){
        //                                              METODO ELIMINAR
        String codigo = et1.getText().toString();
        String descripcion = et2.getText().toString();
        String precio = et3.getText().toString();
        if (codigo.isEmpty()) {
            et1.setError("campo obligatorio");
        }else if (descripcion.isEmpty()){
            et2.setError("campo obligatorio");
        }else if (precio.isEmpty()) {
            et3.setError("campo obligatorio");
        }else{
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            int cant = bd.delete("articulos", "codigo=" + codigo, null);
            bd.close();
            et1.setText("");
            et2.setText("");
            et3.setText("");
            if (cant == 1){
                Toast.makeText(this, "Se borro el articulocon dicho codigo", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No existe un articulo con dicho codigo", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public  void modificar(){
        //                                          METODO ELIMINAR
        String codigo = et1.getText().toString();
        String descripcion = et2.getText().toString();
        String precio = et3.getText().toString();
        if (codigo.isEmpty()) {
            et1.setError("campo obligatorio");
        }else if (descripcion.isEmpty()){
            et2.setError("campo obligatorio");
        }else if (precio.isEmpty()) {
            et3.setError("campo obligatorio");
        }else{
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null,1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precion", precio);
            int cant1 = bd.update("articulos", registro, "codigo=" + codigo, null);
            bd.close();
            if (cant1 ==1){
                Toast.makeText(this, "Se modificaron los datos", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "no existe un articulo con el codigo ingresado", Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void salir(){
        finish();
    }
}