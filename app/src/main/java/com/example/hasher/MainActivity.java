package com.example.hasher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.hashBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText textPrefix = (EditText) findViewById(R.id.prefixText);
                String prefix = textPrefix.getText().toString();
                EditText textSuffix = (EditText) findViewById(R.id.suffixText);
                CharSequence suffix = textSuffix.getText();

                final MessageDigest digest;
                try {
                    digest = MessageDigest.getInstance("SHA-256");
                    String text = prefix + suffix;
                    byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));

                    StringBuffer hexString = new StringBuffer();
                    for (int i = 0; i < hash.length; i++) {
                        String hex = Integer.toHexString(0xff & hash[i]);
                        if(hex.length() == 1) hexString.append('0');
                        hexString.append(hex);
                    }

                    EditText mainText = (EditText) findViewById(R.id.hashedPass);
                    mainText.setText(hexString.toString());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
    });
}}
