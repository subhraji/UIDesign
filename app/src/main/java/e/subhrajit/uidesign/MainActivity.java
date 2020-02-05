package e.subhrajit.uidesign;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewDialog viewDialog;
    CardView seekbartouch, notesupload, XomUiCard, alarmCard;
    private static Context appContext;
    private List<Product> empList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekbartouch = (CardView)findViewById(R.id.seekbartouch);
        alarmCard = (CardView)findViewById(R.id.alarm);
        viewDialog = new ViewDialog(this);

        notesupload = (CardView)findViewById(R.id.notesupload);
        XomUiCard = (CardView)findViewById(R.id.XomUi);

        alarmCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AlarmActivity.class));
            }
        });

        XomUiCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
            }
        });

        notesupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NotesUploadActivity.class));
            }
        });
        seekbartouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 123);
//                } else {
//                    ActivityCompat.requestPermissions(MainActivity.this,
//                            new String[]{Manifest.permission.CAMERA}, 123);
//                    Intent intent = new Intent(getApplicationContext(), FlashlightActivity2.class);
//                    startActivityForResult(intent, 123);
//                }
                Intent intent = new Intent(MainActivity.this, FlashlightActivity.class);
                startActivity(intent);
            }
        });
    }

    public  void  slider(View view)
    {
        Intent intent = new Intent(MainActivity.this, sliderActivity.class);
        startActivity(intent);
    }
    public  void  profile(View view)
    {
        Intent intent = new Intent(MainActivity.this, AttendanceActivity.class);
        startActivity(intent);
    }

    public  void  dialog(View view)
    {
//        empList.add(
//                new Product(
//                        MainActivity.this
//
//                ));
        CustomDialogClass cdd=new CustomDialogClass(MainActivity.this);
        cdd.setCanceledOnTouchOutside(false);
        cdd.show();
    }
    public void showCustomLoadingDialog(View view) {

        //..show gif
        viewDialog.showDialog();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //...here i'm waiting 5 seconds before hiding the custom dialog
                //...you can do whenever you want or whenever your work is done
                viewDialog.hideDialog();
            }
        }, 5000);
    }
}
