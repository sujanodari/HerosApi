package com.sujan.herosapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sujan.herosapi.api.HerosApi;
import com.sujan.herosapi.model.User;
import com.sujan.herosapi.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
     String  username,password;
    EditText user, pass;
    Button login;
Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=findViewById(R.id.etUsername);
        pass=findViewById(R.id.etPassword);

        signup=findViewById(R.id.signup);
        login=findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        username=user.getText().toString().trim();
        password=pass.getText().toString().trim();

        Toast.makeText(this, password+" "+username, Toast.LENGTH_SHORT).show();
        User User = new User(username,password);
        HerosApi herosApi = Url.getInstance().create(HerosApi.class);
        retrofit2.Call<Void> voidCall= herosApi.login(User);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

               if (!response.isSuccessful()) {
                             Toast.makeText(MainActivity.this, "User not found" , Toast.LENGTH_SHORT).show();
                             Log.d("error", "error" + response.code());
                             return;
                         }
                         Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(MainActivity.this,DashboardActivity.class);
                         intent.putExtra("user",username);
                         startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
