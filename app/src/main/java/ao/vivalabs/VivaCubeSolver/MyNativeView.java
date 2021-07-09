package ao.vivalabs.VivaCubeSolver;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import org.opencv.android.JavaCameraView;
//import org.opencv.core.Size;


public class MyNativeView extends JavaCameraView {

    private Context myreference;

    private static boolean isFlashLightON = false;

    public MyNativeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.myreference = context;
    }

    public void setResolution() {
        disconnectCamera();
        mMaxHeight = 480;
        mMaxWidth = 640;
        connectCamera(getWidth(), getHeight());
    }

    // Setup the camera
    public void setupCameraFlashLight() {
        Camera  camera = mCamera;
        if (camera != null) {
            Camera.Parameters params = camera.getParameters();

            if (params != null) {
                if (isFlashLightON) {
                    isFlashLightON = false;
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    camera.setParameters(params);
                    camera.startPreview();
                } else {
                    isFlashLightON = true;
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(params);
                    camera.startPreview();
                }
            }
        }
    }
}
