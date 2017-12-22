package com.format.gesturelauncher;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.format.gesturelauncher.MainActivity.wearconnect;
import static com.format.gesturelauncher.WearConnectService.ShowQuickLauncher;

public class DialogFirst extends Activity {

    Button button;
    Button buttonskip;
    TextView text;


    boolean skipped =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_first);


        button=findViewById(R.id.buttonsure);
        buttonskip=findViewById(R.id.buttonskip);
        text=findViewById(R.id.textwelcome);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&!Settings.canDrawOverlays(getApplicationContext())) {
                        //If the draw over permission is not available open the settings screen
                        //to grant the permission.

                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                Uri.parse("package:" + getPackageName()));
//           startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
                        try {
                            startActivity(intent);
                            text.setText("Done? Let's begin!\nTo get you started, we've added some popular gestures, you can see them in next page.");

                        }catch (Exception e){
                            text.setText("Sorry, fail to grant permission, your build version is "+Build.VERSION.SDK_INT +" , please contact developer at henryzhang9802@gmail.com and attach the build version. Thank you!");

                        }

                        button.setText("Go");


//                    return;
                    }else {

                        SharedPreferences sharedPref =getSharedPreferences("main",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putBoolean("show",true);
                        editor.apply();

                        Intent intent2 = new Intent(getApplicationContext(),MainActivity.class);
                        intent2.putExtra("extra","notini");
                        startActivity(intent2);
                        finish();
                    }




            }

        });





        buttonskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(skipped){
                    Intent intent2 = new Intent(getApplicationContext(),MainActivity.class);
                    intent2.putExtra("extra","notini");
                    startActivity(intent2);
                    finish();
                }


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&!Settings.canDrawOverlays(getApplicationContext())) {


                    text.setText("You have not granted the permission, the quick launcher will not be available. You can change it later in the settings. To get you started, we've added some popular gestures, you can see them in next page!");
                    SharedPreferences sharedPref =getSharedPreferences("main",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("show",false);
                    editor.apply();
                    ShowQuickLauncher=false;

                    button.setVisibility(View.GONE);
                    buttonskip.setText("Got it");
                    buttonskip.setTextColor(Color.WHITE);

                    skipped=true;



                }else {
                    Intent intent2 = new Intent(getApplicationContext(),MainActivity.class);
                    intent2.putExtra("extra","notini");
                    startActivity(intent2);
                    finish();
                }

            }
        });
    }
}
