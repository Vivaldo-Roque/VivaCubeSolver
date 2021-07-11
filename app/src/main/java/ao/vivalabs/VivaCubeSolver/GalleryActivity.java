package ao.vivalabs.VivaCubeSolver;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.io.File;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    ViewPager mViewPager;
    ArrayList<String> filePath = new ArrayList<String>();
    ViewPageAdapter mViewPageAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        File folder = new File(getFilesDir().getPath() + "/CV");

        createFileArray(folder);
        mViewPager = findViewById(R.id.ViewPagerMain);
        mViewPageAdapter = new ViewPageAdapter(GalleryActivity.this, filePath);
        mViewPager.setAdapter(mViewPageAdapter);
    }

    private void createFileArray(File folder) {
        File[] listFile = folder.listFiles();

        if(listFile!=null)
        {
            for(int i = 0; i < listFile.length; i++)
            {
                filePath.add(listFile[i].getAbsolutePath());
            }
        }
    }
}