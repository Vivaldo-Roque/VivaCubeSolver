package ao.vivalabs.VivaCubeSolver;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class SettingActivity extends AppCompatActivity {

    private String TAG = "SettingActivity";
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Context context = SettingActivity.this;
    private int[] colors = new int[6];
    private int[] scheme = new int[6];
    private int count = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        colors = GetColors(context);

        button_1 = findViewById(R.id.color_button_1);
        button_2 = findViewById(R.id.color_button_2);
        button_3 = findViewById(R.id.color_button_3);
        button_4 = findViewById(R.id.color_button_4);
        button_5 = findViewById(R.id.color_button_5);
        button_6 = findViewById(R.id.color_button_6);

        if(CheckPref(context, "SchemeArray") == true){
            scheme = getFromPrefs(context,"SchemeArray");

            ChangeColor(button_1, scheme[0]);
            ChangeColor(button_2, scheme[5]);
            ChangeColor(button_3, scheme[2]);
            ChangeColor(button_4, scheme[1]);
            ChangeColor(button_5, scheme[4]);
            ChangeColor(button_6, scheme[3]);
        }else {
            ChangeColor(button_1, colors[1]);
            ChangeColor(button_2, colors[2]);
            ChangeColor(button_3, colors[5]);
            ChangeColor(button_4, colors[0]);
            ChangeColor(button_5, colors[3]);
            ChangeColor(button_6, colors[4]);
        }

        button_1.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(count > colors.length - 1) {
                    count = 0;
                }
                ChangeColor(button_1, colors[count]);
                count++;
            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(count > colors.length - 1) {
                    count = 0;
                }
                ChangeColor(button_2, colors[count]);
                count++;
            }
        });

        button_3.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(count > colors.length - 1) {
                    count = 0;
                }
                ChangeColor(button_3, colors[count]);
                count++;
            }
        });

        button_4.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(count > colors.length - 1) {
                    count = 0;
                }
                ChangeColor(button_4, colors[count]);
                count++;
            }
        });

        button_5.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(count > colors.length - 1) {
                    count = 0;
                }
                ChangeColor(button_5, colors[count]);
                count++;
            }
        });

        button_6.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(count > colors.length - 1) {
                    count = 0;
                }
                ChangeColor(button_6, colors[count]);
                count++;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        scheme[0] = GetColor(button_1); //U
        scheme[1] = GetColor(button_4); //D
        scheme[2] = GetColor(button_3); //F
        scheme[3] = GetColor(button_6); //B
        scheme[4] = GetColor(button_5); //L
        scheme[5] = GetColor(button_2); //R

        storeIntArray(context,"SchemeArray",scheme);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void ChangeColor(Button button, int color) {
        button.setBackgroundTintList(ColorStateList.valueOf(color));

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private int GetColor(Button button) {
       int color = button.getBackgroundTintList().getDefaultColor();
       return color;
    }

    private int[] GetColors(Context context) {
        TypedArray ta = context.getResources().obtainTypedArray(R.array.custom_cube_colors);
        int[] colors = new int[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            colors[i] = ta.getColor(i, 0);
        }
        ta.recycle();

        return colors;
    }

    private static final String LEN_PREFIX = "Count_";
    private static final String VAL_PREFIX = "IntValue_";

    public static void storeIntArray(Context context, String name, int[] array){
        SharedPreferences.Editor edit= context.getSharedPreferences("SchemeArray", Context.MODE_PRIVATE).edit();
        edit.putInt(LEN_PREFIX + name, array.length);
        int count = 0;
        for (int i: array){
            edit.putInt(VAL_PREFIX + name + count++, i);
        }
        edit.commit();
    }
    public static int[] getFromPrefs(Context context, String name){
        int[] ret;
        SharedPreferences prefs = context.getSharedPreferences("SchemeArray", Context.MODE_PRIVATE);
        int count = prefs.getInt(LEN_PREFIX + name, 0);
        ret = new int[count];
        for (int i = 0; i < count; i++){
            ret[i] = prefs.getInt(VAL_PREFIX+ name + i, i);
        }
        return ret;
    }

    public  static boolean CheckPref(Context context, String name){
        File f = new File("/data/data/ao.vivalabs.VivaCubeSolver/shared_prefs/SchemeArray.xml");
        if (f.exists())
            return true;
        else
            return false;

    }
}