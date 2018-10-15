package org.ieselcaminas.pmdm.nifexercise2018;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NifActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nif);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
        TextView textView = (TextView) findViewById(R.id.greetingLabel);
        textView.setText(name);

        Button buttonOk = (Button) findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editTextNif);
                String s = editText.getText().toString();
                try {
                    Dni dni = new Dni(s);

                    Intent intent = new Intent();
                    intent.putExtra("nif", dni.toFormattedString());
                    setResult(RESULT_OK, intent);
                    finish();

                } catch (NIFException ex) {
                    System.out.println("Nif error");
                    Dialog d = new AlertDialog.Builder(NifActivity.this).
                            setTitle(R.string.Error).
                            setMessage(R.string.WrongNif).
                            create();
                    d.show();
                }

            }
        });
    }
}
