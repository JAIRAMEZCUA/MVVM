package com.example.mvvm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.mvvm.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);

        loginViewModel.getUser().observe(this, new Observer<LoginUser>() {
            @Override
            public void onChanged(@Nullable LoginUser loginUser) {

                if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getStrEmailAddress())) {
                    binding.txtEmailAddress.setError("Ingrese su correo");
                    binding.txtEmailAddress.requestFocus();
                }
                else if (!loginUser.isEmailValid()) {
                    binding.txtEmailAddress.setError("Ingrese su correo valido");
                    binding.txtEmailAddress.requestFocus();
                }
                else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getStrPassword())) {
                    binding.txtPassword.setError("Ingrese Contraseña");
                    binding.txtPassword.requestFocus();
                }
                else if (!loginUser.isPasswordLengthGreaterThan5()) {
                    binding.txtPassword.setError("Ingrese Contraseña mayor a 6 digitos");
                    binding.txtPassword.requestFocus();
                }
                else if (loginUser.setValidaUsu()){
                    Toast.makeText(getApplicationContext(),"BIENVENIDO",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"USUARIO O CONTRASEÑA INCORRECTA",Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}