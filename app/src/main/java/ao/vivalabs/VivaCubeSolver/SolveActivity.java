package ao.vivalabs.VivaCubeSolver;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import cs.min2phase.Search;

public class SolveActivity extends AppCompatActivity {

    private static final String TAG = "SolveActivity";
    private TextView solution;
    private TextView info;
    private ProgressBar progressBar;
    private String str = "";
    private Boolean isSuccess = false;
    private PyObject pyo;
    private PyObject obj;
    private Python py;
    private String finalPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve);

        solution = findViewById(R.id.solution);
        info = findViewById(R.id.info);
        info.setVisibility(View.INVISIBLE);

        solution.setText("Tap me!");

        progressBar = findViewById(R.id.pbProgress);
        progressBar.setVisibility(View.INVISIBLE);

        finalPath = getFilesDir().getPath() + "/CV";

        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
            Log.d(TAG, "Python started!");
        }

        solution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSuccess){
                    Intent i = new Intent(SolveActivity.this, AnimatedCubeActivity.class);
                    i.putExtra("solution",str);
                    startActivity(i);
                }else {
                    new SolveImages().execute();
                }
            }
        });
    }

    public class SolveImages extends AsyncTask<String, String, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            solution.setText("Analyzing images");
            progressBar.setVisibility(View.VISIBLE);
            Handler handler= new Handler();

            handler.postDelayed(new Runnable() {
                public void run() {
                    solution.setText("Computing Solution");
                }
            }, 2500);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);
            solution.setText(String.format("Solution:\n\n%s",str));
            info.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            py = Python.getInstance();
            pyo = py.getModule("solver");
            obj = pyo.callAttr("solver", finalPath);
            str = obj.toString();
            str = simpleSolve(str);
            isSuccess = true;
            return null;
        }
    }

    public static String simpleSolve(String scrambledCube) {
        String result = new Search().solution(scrambledCube, 20, 100000000, 0, 0);
        return result;
    }
}