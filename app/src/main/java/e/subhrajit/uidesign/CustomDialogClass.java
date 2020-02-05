package e.subhrajit.uidesign;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    //we are storing all the products in a list
    private List<Product> productList;
    public Activity c;
    public Dialog d;
    public Button yes, no;
    public SeekBar content, description, understanding;
    public String varcontent, vardescription, varunderstanding;
    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        content = (SeekBar)findViewById(R.id.vvv1);
        description = (SeekBar)findViewById(R.id.description);
        understanding = (SeekBar)findViewById(R.id.undersatnding);
        yes = (Button) findViewById(R.id.btn_yes);
        yes.setOnClickListener(this);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mmm();
            }
        });
        content.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                varcontent = ""+i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        description.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                vardescription = ""+i;
                final Context context = GlobalApplication.getAppContext();
                Toast.makeText(context, vardescription.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        understanding.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                varunderstanding = ""+i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                mmm();
                break;
            default:
                break;
        }
        dismiss();
    }

    public void mmm()
    {
        Context context = GlobalApplication.getAppContext();
        Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show();
       // context.startActivity(new Intent(context,Profile.class));
        Intent openIntent = new Intent(context, Profile.class);
        openIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(openIntent);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String orgCode = sharedPreferences.getString("org_code", "");
        giveFeaadback(orgCode);
    }
    public void giveFeaadback(final String orgCode)
    {
        final Context context = GlobalApplication.getAppContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        final String employeeID = sharedPreferences.getString("empID", "");
        final String dbname = sharedPreferences.getString("dbname", "");
        final String semester = sharedPreferences.getString("semester", "");

        //Toast.makeText(this, semester.toString(), Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferencescheck = PreferenceManager.getDefaultSharedPreferences(context);
        final String room_no = sharedPreferencescheck.getString("room_no", "");
        final String qr_code = sharedPreferencescheck.getString("qr_code", "");
        //Toast.makeText(this, qr_code.toString(), Toast.LENGTH_SHORT).show();
        String URL = "attendance/classAttendance.php";

        //customDialog.showDialog();

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //customDialog.hideDialog();
                        Toast.makeText(context, response.trim(), Toast.LENGTH_SHORT).show();
//
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //customDialog.hideDialog();
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                Log.e("REST Error", error.toString());
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> param = new HashMap<String, String>();

                param.put("serverpassword", "");
                param.put("employeeID", employeeID);
                param.put("dbname", dbname);
                param.put("orgCode", orgCode);
                param.put("semester", semester);
                param.put("qr_code", qr_code);
                param.put("teacher_code", "");
                param.put("room_no", room_no);
                return param;
            }
        };

        requestQueue.add(stringRequest);
    }
}