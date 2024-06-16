package com.example.secondapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView welcomeTextView;
    private TextView promptTextView;
    private TextView forgotPasswordTextView;
    private ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id._text3);
        passwordEditText = findViewById(R.id._text4);
        loginButton = findViewById(R.id._button);
        welcomeTextView = findViewById(R.id._text1);
        promptTextView = findViewById(R.id._text2);
        forgotPasswordTextView = findViewById(R.id.forgot_password_text);
        mainLayout = findViewById(R.id.main);

        emailEditText.addTextChangedListener(textWatcher);
        passwordEditText.addTextChangedListener(textWatcher);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            updateButtonState();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private void updateButtonState() {
        if (emailEditText.getText().toString().trim().isEmpty() ||
                passwordEditText.getText().toString().trim().isEmpty()) {
            loginButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.gray));
        } else {
            loginButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.orange));
        }
    }

    private void checkLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.equals("maksatoverjan@gmail.com") && password.equals("admin")) {
            Toast.makeText(this, "Вы успешно зарегистрировались", Toast.LENGTH_SHORT).show();
            emailEditText.setVisibility(View.GONE);
            passwordEditText.setVisibility(View.GONE);
            loginButton.setVisibility(View.GONE);
            promptTextView.setVisibility(View.GONE);
            forgotPasswordTextView.setVisibility(View.GONE);
            welcomeTextView.setText("Добро пожаловать!");
        } else {
            Toast.makeText(this, "Неправильный логин и пароль", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Поднятие контента при появлении клавиатуры
        getWindow().setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }
}
