package e.subhrajit.uidesign;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class sliderActivity extends AppCompatActivity {

    ViewFlipper viewFlipper;
    //FragmentActivity fragmentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        viewFlipper = (ViewFlipper)findViewById(R.id.flipper);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.loadingLayout);

        constraintLayout.setBackgroundColor(Color.TRANSPARENT);

//        int[] images = {R.drawable.ic_android_black_24dp, R.drawable.ic_fingerprint_black_24dp, R.drawable.ic_power_settings_new_black_24dp};

        String[] images = {"Bhargav", "Subhrajit", "Dinesh"};

        for(int i = 0; i < images.length; i++)
        {
            flipperString(images[i]);
        }

        for(String image: images)
        {
            flipperString(image);
        }

        viewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "ho gayaaaaaa......", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public  void flipperImage(int image)
    {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);


        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }
    public void flipperString(String string)
    {
        TextView textView = new TextView(this);
        textView.setText(string);
        viewFlipper.addView(textView);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

}
