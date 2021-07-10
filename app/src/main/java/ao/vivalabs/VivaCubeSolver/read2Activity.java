package ao.vivalabs.VivaCubeSolver;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.ByteArrayOutputStream;

public class read2Activity extends AppCompatActivity {

    Drawable drawable;
    Bitmap bitmap;
    String imgString = "";
    boolean viewStatus = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read2);

        ImageView imageView = findViewById(R.id.img);
        //imageView.setImageResource(R.drawable.shape);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewStatus == true)
                {
                    viewStatus = false;
                    imageView.setImageResource(R.drawable.shape2);
                }
                else {
                    viewStatus = true;
                    read2Activity.this.recreate();
                }
            }
        });

        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        final Python py = Python.getInstance();

        bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.shape2);
        imgString = getStringImage(bitmap);

        PyObject pyo = py.getModule("myscript");
        PyObject obj = pyo.callAttr("main", imgString);
        String str = obj.toString();
        byte[] data = android.util.Base64.decode(str, Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
        imageView.setImageBitmap(bmp);
    }

    private String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}