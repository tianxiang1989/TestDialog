package com.example.liuxiuquan.testdialog;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * test for dialog auto show and dismiss
 */
public class MainActivity extends AppCompatActivity {
    private Button oneBtn;
    private int i = 0;
    private AlertDialog.Builder dialog;
    private AlertDialog alert;

    private void runThread() {
        new Thread() {
            public void run() {
                while (i++ < 1000) {
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.setTitle("#" + i);
                                alert = dialog.create();
                                alert.show();
                            }
                        });

                        Thread.sleep(1000);

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                alert.dismiss();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        oneBtn = (Button) findViewById(R.id.button_one);

        dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setPositiveButton("ok", null);
        alert = dialog.create();

        oneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runThread();
            }
        });
    }
}
