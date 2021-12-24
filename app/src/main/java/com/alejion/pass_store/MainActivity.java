package com.alejion.pass_store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alejion.pass_store.db.UsuarioHandler;
import com.alejion.pass_store.models.Usuario;

public class MainActivity extends AppCompatActivity {

    UsuarioHandler data_source;
    EditText et_nombre;
    EditText et_password;
    Button btn_ingresar;
    ImageView iv_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Tienda de Contraseñas");

        et_nombre = findViewById(R.id.et_nombre);
        et_password = findViewById(R.id.et_password);
        btn_ingresar = findViewById(R.id.btn_ingresar);
        iv_logo = findViewById(R.id.iv_logo);

        iv_logo.getLayoutParams().height = 512;

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(et_nombre.getText().toString()) ||
                        TextUtils.isEmpty(et_password.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (verificar_usuario()) {
                    Intent intent = new Intent(MainActivity.this, ActivityListaEntradas.class);
                    startActivity(intent);
                    finish();
                } else
                    Toast.makeText(MainActivity.this, "No existe el usuario o la contraseña es incorrecta", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean verificar_usuario() {

        data_source = new UsuarioHandler(MainActivity.this);
        Usuario user = data_source.get_usuario();
        String nombre = et_nombre.getText().toString();
        String pass = et_password.getText().toString();

        if(!nombre.equals(user.getNombre()))
            return false;

        if(!pass.equals(user.getPassword()))
            return false;

        return true;
    }
}