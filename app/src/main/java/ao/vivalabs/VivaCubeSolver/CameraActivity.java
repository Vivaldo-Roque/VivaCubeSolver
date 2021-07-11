package ao.vivalabs.VivaCubeSolver;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.opencv.core.CvType.CV_8UC1;
import static org.opencv.core.CvType.CV_8UC4;


public class CameraActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    final private static String tag = "MainActivity";

    private Mat mRgba;
    private Mat mGray;

    //private CameraBridgeViewBase mOpenCvCameraView;
    private MyNativeView mOpenCvCameraView;
    private ImageView flip_camera_button;
    private ImageView turn_flash_button;
    private ImageView take_picture_button;
    private ImageView gallery_button;
    private TextView counter_text;
    private int take_image = 0;
    private boolean flashIconMode = false;
    private int mCameraId = 0;
    private int count = 0;

    private List<Camera.Size> mResolutionList;
    private Menu mMenu;
    private MenuItem[] mResolutionMenuItems;
    private SubMenu mResolutionMenu;
    private boolean mCameraStarted = false;
    private boolean mMenuItemsCreated = false;


    List<String> filesName = Arrays.asList("rubiks-side-F", "rubiks-side-R", "rubiks-side-B", "rubiks-side-L", "rubiks-side-U", "rubiks-side-D");

    final private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {

            if (status == LoaderCallbackInterface
                    .SUCCESS) {
                Log.d(tag, "OpenCv is loaded!");
                mOpenCvCameraView.enableView();
            } else {
                super.onManagerConnected(status);
            }
        }
    };

    public CameraActivity() {
        Log.d(tag, "Instantiated now" + this.getClass());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_camera);

        ActivityCompat.requestPermissions(CameraActivity.this,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);

        mOpenCvCameraView = findViewById(R.id.frame_surface);
        mOpenCvCameraView.setMinimumHeight(720);
        mOpenCvCameraView.setMinimumWidth(720);
        mOpenCvCameraView.setMaxFrameSize(721, 721);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);

        counter_text = findViewById(R.id.counter);

        flip_camera_button = findViewById(R.id.flip_camera);
        flip_camera_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapCamera();
            }
        });

        turn_flash_button = findViewById(R.id.turn_flash);
        turn_flash_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapFlashIcon();
            }
        });

        take_picture_button = findViewById(R.id.take_picture);
        take_picture_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

        gallery_button = findViewById(R.id.gallery);
        gallery_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGallery();
            }
        });

        //deleteImages();
    }

    private void showGallery() {
        startActivity(new Intent(CameraActivity.this, GalleryActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    private void takePicture() {
        if (take_image == 0) {
            take_image = 1;
        } else {
            take_image = 0;
        }
    }

    private void swapFlashIcon() {
        if (flashIconMode == false) {
            turn_flash_button.setImageResource(R.drawable.flash_on);
            flashIconMode = true;
        } else if (flashIconMode) {
            turn_flash_button.setImageResource(R.drawable.flash_off);
            flashIconMode = false;
        }
        mOpenCvCameraView.setupCameraFlashLight();
    }

    private void swapCamera() {
        mCameraId = mCameraId ^ 1;

        mOpenCvCameraView.disableView();
        mOpenCvCameraView.setCameraIndex(mCameraId);

        mOpenCvCameraView.enableView();
    }

    public void deleteImages() {
        try {
            File dir = new File(Environment.getExternalStorageDirectory() + "/CV");
            if (dir.isDirectory()) {
                String[] children = dir.list();
                for (int i = 0; i < children.length; i++) {
                    new File(dir, children[i]).delete();
                }
            }
        } catch (Exception e) {
            Log.d(tag, "deleteImages: Empty");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mOpenCvCameraView.setCameraPermissionGranted();  // <------ THIS!!!
                    Log.d(tag, "Permissions granted");
                } else {
                    // permission denied
                    ActivityCompat.requestPermissions(CameraActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                }
                return;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (OpenCVLoader.initDebug()) {
            Log.d(tag, "Opencv initialization is done");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        } else {
            Log.d(tag, "Opencv initialization isn't done");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, this, mLoaderCallback);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mOpenCvCameraView != null) {
            mOpenCvCameraView.disableView();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null) {
            mOpenCvCameraView.disableView();
        }
    }

    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CV_8UC4);
        mGray = new Mat(height, width, CV_8UC1);
        //mCameraStarted = true;
        setupMenuItems();
    }

    public void onCameraViewStopped() {
        mRgba.release();
        mGray.release();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        setupMenuItems();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();
        mGray = inputFrame.gray();

        if (mCameraId == 1) {
            Core.flip(mRgba, mRgba, 1);
            Core.flip(mGray, mGray, 1);
        }

        Scalar black = new Scalar(0, 0, 0);
        Scalar green = new Scalar(0,255,0);
        Scalar red = new Scalar(255,0,0);

        System.out.println(String.format("Mat size w: %s, h: %s", (int) mRgba.size().width, (int) mRgba.size().height));
        int w = mRgba.width();
        int h = mRgba.height();
        int w_rect = w * 3 / 4;
        int h_rect = h * 3 / 4;
        Point rect_point1 = new Point((h - h_rect) / 2, (h - h_rect) / 2);
        Point rect_point2 = new Point((h + h_rect) / 2, (h + h_rect) / 2);
        Rect rect1 = new Rect(rect_point1, rect_point2);

        //Imgproc.rectangle (mRgba, new Point(factor,factor), new Point(factor*4,factor*4), color, 25);
        Imgproc.rectangle(mRgba, rect1, black, 25);

            Imgproc.line(mRgba, new Point(((h - h_rect) / 2) * 3, (h - h_rect) / 2), new Point(((h - h_rect) / 2) * 3, (h + h_rect) / 2), black, 25, 8);
            Imgproc.line(mRgba, new Point(((h - h_rect) / 2) * 5, (h - h_rect) / 2), new Point(((h - h_rect) / 2) * 5, (h + h_rect) / 2), black, 25, 8);

            Imgproc.line(mRgba, new Point(((h - h_rect) / 2), ((h - h_rect) / 2) * 3), new Point((h + h_rect) / 2, ((h - h_rect) / 2) * 3), black, 25, 8);
            Imgproc.line(mRgba, new Point(((h - h_rect) / 2), ((h - h_rect) / 2) * 5), new Point((h + h_rect) / 2, ((h - h_rect) / 2) * 5), black, 25, 8);

        //Point end
        //Imgproc.line(mRgba, new Point(factor*2, factor), new Point(factor*2, factor*4), color, 25,8);
        //Imgproc.line(mRgba, new Point(factor*3, factor), new Point(factor*3, factor*4), color, 25,8);

        //point start
        //Imgproc.line(mRgba, new Point(factor*4, factor*2), new Point(factor, factor*2), color,25,8);
        //Imgproc.line(mRgba, new Point(factor*4, factor*3), new Point(factor, factor*3), color,25,8);


        //Point p1, p4;

        //p1 = new Point(factor, factor);
        //p4 = new Point(factor*4, factor*4);

        Rect rectCrop = new Rect((int) rect_point1.x, (int) rect_point1.y, (int) (rect_point2.x - rect_point1.x + 1), (int) (rect_point2.y - rect_point1.y + 1));
        Mat mCrop = mRgba.submat(rectCrop);

        take_image = take_picture_function_rgb(take_image, mCrop);

        //take_image = take_picture_function_gray(take_image, mGray);

        //take_image = take_picture_function_edges(take_image, edges);

        return mRgba;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private int take_picture_function_rgb(int take_image, Mat mRgba) {

        if (take_image == 1) {
            Mat save_mat = new Mat();

            Core.flip(mRgba.t(), save_mat, 1);

            Imgproc.cvtColor(save_mat, save_mat, Imgproc.COLOR_RGBA2BGR);

            File folder = new File(getFilesDir().getPath() + "/CV");

            boolean success = true;
            if (!folder.exists()) {
                success = folder.mkdirs();
            }

            if (count >= 0 && count <= 5) {
                String fileName = getFilesDir().getPath() + "/CV/" + filesName.get(count) + ".png";
                //System.out.println("=======>" + fileName);

                Imgcodecs.imwrite(fileName, save_mat);

                //System.out.println(filesName.get(count) + ".png was scanned");
                counter_text.setText(String.format("N: %d", count + 1));

                if (count == 5) {
                    take_picture_button.setImageResource(R.drawable.ic_baseline_done_24);
                    show_Notification();
                }

                count++;
            }
            take_image = 0;
        }

        return take_image;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)

    public void show_Notification() {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        String CHANNEL_ID = "MYCHANNEL";
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "CV", NotificationManager.IMPORTANCE_LOW);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, 0);
        Notification notification = new Notification.Builder(getApplicationContext(), CHANNEL_ID)
                .setContentText("Estado")
                .setContentTitle("Scan completo!")
                .setContentIntent(pendingIntent)
                .addAction(android.R.drawable.stat_sys_warning, "Alerta!", pendingIntent)
                .setChannelId(CHANNEL_ID)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
        notificationManager.notify(1, notification);
    }

    private void setupMenuItems() {
        if (mMenu == null || !mCameraStarted || mMenuItemsCreated) {
            return;
        }

        mResolutionMenu = mMenu.addSubMenu("Resolution");
        mResolutionList = mOpenCvCameraView.getResolutionList();
        mResolutionMenuItems = new MenuItem[mResolutionList.size()];

        int idx = 0;
        for (Camera.Size resolution : mResolutionList) {
            mResolutionMenuItems[idx] = mResolutionMenu.add(2, idx, Menu.NONE,
                    Integer.valueOf(resolution.width).toString() + "x" + Integer.valueOf(resolution.height).toString());
            idx++;
        }
        mMenuItemsCreated = true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(tag, "called onOptionsItemSelected; selected item: " + item);
        if (item.getGroupId() == 1) {
            int id = item.getItemId();
            Camera.Size resolution = mResolutionList.get(id);
            //mOpenCvCameraView.setResolution(resolution);
            mOpenCvCameraView.disableView();
            mOpenCvCameraView.setMaxFrameSize(resolution.width, resolution.height);
            mOpenCvCameraView.enableView();
            //resolution = mOpenCvCameraView.getResolution();
            //String caption = Integer.valueOf(resolution.width).toString() + "x" + Integer.valueOf(resolution.height).toString();
            //Toast.makeText(this, caption, Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    public double euclideanDistance(Point a, Point b){
        double distance = 0.0;
        try{
            if(a != null && b != null){
                double xDiff = a.x - b.x;
                double yDiff = a.y - b.y;
                distance = Math.sqrt(Math.pow(xDiff,2) + Math.pow(yDiff, 2));
            }
        }catch(Exception e){
            System.err.println("Something went wrong in euclideanDistance function in "+CameraActivity.class+" "+e.getMessage());
        }
        return distance;
    }
}