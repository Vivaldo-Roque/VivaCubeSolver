package ao.vivalabs.VivaCubeSolver;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.catalinjurjiu.animcubeandroid.AnimCube;

public class AnimatedCubeActivity extends AppCompatActivity implements AnimCube.OnCubeModelUpdatedListener, AnimCube.OnCubeAnimationFinishedListener {

    public static final String ANIM_CUBE_SAVE_STATE_BUNDLE_ID = "animCube";
    private static final String TAG = "AnimatedCubeActivity";
    private AnimCube animCube;
    private Bundle state;
    private String solution;

    private ImageButton play_button;
    private  ImageButton stop_button;
    private  ImageButton forward_button;
    private ImageButton backward_button;
    private ImageButton restore_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_animated_cube);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            solution = extras.getString("solution");
            //The key argument here must match that used in the other activity
        }
        else {
            solution = "RUR'";
        }


        animCube = (AnimCube) findViewById(R.id.animcube);
        animCube.setOnCubeModelUpdatedListener(this);
        animCube.setOnAnimationFinishedListener(this);
        animCube.setMoveSequence(solution);
        animCube.applyMoveSequenceReversed();
        state = animCube.saveState();
        animCube.setSingleRotationSpeed(20);
        animCube.setDoubleRotationSpeed(15);

        play_button = findViewById(R.id.play_button);
        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animCube.animateMoveSequence();
            }
        });

        stop_button = findViewById(R.id.stop_button);
        stop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animCube.stopAnimation();
            }
        });

        forward_button = findViewById(R.id.play_forward);
        forward_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animCube.animateMove();
            }
        });

        backward_button = findViewById(R.id.backward_button);
        backward_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animCube.animateMoveReversed();
            }
        });

        restore_button = findViewById(R.id.reset_button);
        restore_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animCube.restoreState(state);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.play_forward:
                animCube.animateMoveSequence();
                break;
            case R.id.play_backward:
                animCube.animateMoveSequenceReversed();
                break;
            case R.id.stop:
                animCube.stopAnimation();
                break;
            case R.id.one_move_forward:
                animCube.animateMove();
                break;
            case R.id.one_move_backward:
                animCube.animateMoveReversed();
                break;
            case R.id.reset:
                animCube.resetToInitialState();
                break;
            case R.id.save_state:
                state = animCube.saveState();
                break;
            case R.id.restore_state:
                animCube.restoreState(state);
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState ");
        outState.putBundle(ANIM_CUBE_SAVE_STATE_BUNDLE_ID, animCube.saveState());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
        animCube.restoreState(savedInstanceState.getBundle(ANIM_CUBE_SAVE_STATE_BUNDLE_ID));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        animCube.cleanUpResources();
        Log.d(TAG, "onDestroy: finish");
    }

    @Override
    public void onCubeModelUpdate(int[][] newCubeModel) {
        Log.d(TAG, "Cube model updated!");
        printCubeModel(newCubeModel);
    }

    void printCubeModel(int[][] cube) {
        Log.d(TAG, "Cube model:");
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < cube.length; i++) {
            stringBuilder.append("\n");
            stringBuilder.append(i);
            stringBuilder.append(":\n");
            for (int j = 0; j < cube[i].length; j++) {
                stringBuilder.append(" ");
                stringBuilder.append(cube[i][j]);
                if ((j + 1) % 3 == 0) {
                    stringBuilder.append("\n");
                } else {
                    stringBuilder.append(", ");
                }
            }
        }
        Log.d(TAG, stringBuilder.toString());
    }

    @Override
    public void onAnimationFinished() {
        Log.d(TAG, "Cube AnimationFinished!");
    }
}