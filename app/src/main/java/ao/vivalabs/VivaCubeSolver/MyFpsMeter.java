package ao.vivalabs.VivaCubeSolver;

import org.opencv.core.Core;
import java.text.DecimalFormat;

public class MyFpsMeter {

    private static final int    STEP              = 20;
    private static final DecimalFormat FPS_FORMAT = new DecimalFormat("0.00");

    private int                 mFramesCounter;
    private double              mFrequency;
    private long                mprevFrameTime;
    private String              mStrfps;
    boolean                     mIsInitialized = false;
    int                         mWidth = 0;
    int                         mHeight = 0;

    public void init() {
        mFramesCounter = 0;
        mFrequency = Core.getTickFrequency();
        mprevFrameTime = Core.getTickCount();
        mStrfps = "";
    }

    public String measure() {
        if (!mIsInitialized) {
            init();
            mIsInitialized = true;
        } else {
            mFramesCounter++;
            if (mFramesCounter % STEP == 0) {
                long time = Core.getTickCount();
                double fps = STEP * mFrequency / (time - mprevFrameTime);
                mprevFrameTime = time;
                if (mWidth != 0 && mHeight != 0)
                {
                    mStrfps = FPS_FORMAT.format(fps) + " FPS@" + Integer.valueOf(mWidth) + "x" + Integer.valueOf(mHeight);
                }
                else
                {
                    mStrfps = FPS_FORMAT.format(fps) + " FPS";
                }
            }
        }
        return mStrfps;
    }

    public void setResolution(int width, int height) {
        mWidth = width;
        mHeight = height;
    }
}