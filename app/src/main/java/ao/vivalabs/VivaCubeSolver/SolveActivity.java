package ao.vivalabs.VivaCubeSolver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import cs.min2phase.Search;

public class SolveActivity extends AppCompatActivity {

    private static final String TAG = "SolveActivity";
    private TextView scramble;
    private String str;
    private Boolean isSuccess = false;
    private PyObject pyo;
    private PyObject obj;
    private Python py;
    private String finalPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve);

        scramble = findViewById(R.id.scramble);

        finalPath = getFilesDir().getPath() + "/CV";

        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
            Log.d(TAG, "Python started!");
        }

        scramble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSuccess){
                    Intent i = new Intent(SolveActivity.this, AnimatedCubeActivity.class);
                    i.putExtra("solution",str);
                    startActivity(i);
                }else {
                    py = Python.getInstance();
                    pyo = py.getModule("solver");
                    obj = pyo.callAttr("solver", finalPath);
                    str = obj.toString();
                    str = simpleSolve(str);
                    scramble.setText(String.format("Scramble: %s",str));
                    isSuccess = true;
                }
            }
        });
    }

    public static String simpleSolve(String scrambledCube) {
        String result = new Search().solution(scrambledCube, 20, 100000000, 0, 0);
        return result;
    }
}