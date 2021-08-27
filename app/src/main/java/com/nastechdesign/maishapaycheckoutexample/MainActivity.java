package com.nastechdesign.maishapaycheckoutexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText apiKey, gateWayMode, amount, money, logo_url;
    Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiKey = findViewById(R.id.apiKey);
        gateWayMode = findViewById(R.id.gateway_mode);
        amount = findViewById(R.id.montant);
        money = findViewById(R.id.monnaie);
        logo_url = findViewById(R.id.logo_url);

        payButton = findViewById(R.id.payButton);

        payButton.setOnClickListener(v -> {
            Rect displayRectangle = new Rect();
            Window window = MainActivity.this.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.CustomAlertDialog);
            ViewGroup viewGroup = findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog, viewGroup, false);
            dialogView.setMinimumWidth((int) (displayRectangle.width() * 1f));
            dialogView.setMinimumHeight((int) (displayRectangle.height() * 1f));
            builder.setView(dialogView);
            final AlertDialog alertDialog = builder.create();
            Button buttonOk = dialogView.findViewById(R.id.buttonOk);
            buttonOk.setOnClickListener(v1 -> alertDialog.dismiss());
            alertDialog.show();
        });
    }
}