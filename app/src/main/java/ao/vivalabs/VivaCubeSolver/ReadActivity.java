package ao.vivalabs.VivaCubeSolver;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.opencv.imgproc.Imgproc.COLOR_BGR2HSV;
import static org.opencv.imgproc.Imgproc.Canny;
import static org.opencv.imgproc.Imgproc.FONT_HERSHEY_SIMPLEX;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY;
import static org.opencv.imgproc.Imgproc.dilate;
import static org.opencv.imgproc.Imgproc.threshold;

public class ReadActivity extends AppCompatActivity {

    final private static String TAG = "ReadActivity";

    Mat img = new Mat();
    Mat hsv = new Mat();
    Mat blurred = new Mat();
    Mat lab = new Mat();
    Mat thresh = new Mat();
    Mat dilated = new Mat();
    Mat gray = new Mat();
    Mat edges = new Mat();
    boolean viewStatus = true;
    int counter = 10;
    private final MatOfPoint2f approxCurve = new MatOfPoint2f();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        ImageView imageView = findViewById(R.id.img);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewStatus == true)
                {
                    viewStatus = false;
                    imageView.setImageResource(R.drawable.shape);
                }
                else {
                    viewStatus = true;
                    ReadActivity.this.recreate();
                }
            }
        });

        try {
            img = Utils.loadResource(getApplicationContext(), R.drawable.shape);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2BGR);
        Imgproc.cvtColor(img, hsv, COLOR_BGR2HSV);
        Imgproc.GaussianBlur(img, blurred, new Size(5,5), 0);
        Imgproc.cvtColor(img, gray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(img, lab, Imgproc.COLOR_BGR2Lab);
        Canny(gray, edges, 30, 200);
        dilate(edges, dilated, new Mat(), new Point(-1,-1),2);
        threshold(dilated,thresh,60, 255, THRESH_BINARY);

        List<MatOfPoint> contours = new ArrayList<>();
        List<MatOfPoint> finalContours = new ArrayList<>();

        Imgproc.findContours(thresh.clone(), contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        for (MatOfPoint cnt : contours) {
            counter--;
            MatOfPoint2f curve = new MatOfPoint2f(cnt.toArray());

            // approximates a polygonal curve with the specified precision
            Imgproc.approxPolyDP(
                    curve,
                    approxCurve,
                    0.04 * Imgproc.arcLength(curve, true),
                    true
            );

            int numberVertices = (int)approxCurve.total();
            double contourArea = Imgproc.contourArea(cnt);

            Log.d(TAG, "vertices:" + numberVertices);

            // ignore to small areas
            if (Math.abs(contourArea) < 100
                // || !Imgproc.isContourConvex(
            ) {
                continue;
            }

            // Square
            if (numberVertices == 4) {

                List<Double> cos = new ArrayList<>();
                for (int j = 2; j < numberVertices + 1; j++) {
                    cos.add(
                            angle(
                                    approxCurve.toArray()[j % numberVertices],
                                    approxCurve.toArray()[j - 2],
                                    approxCurve.toArray()[j - 1]
                            )
                    );
                }
                Collections.sort(cos);

                double mincos = cos.get(0);
                double maxcos = cos.get(cos.size()-1);

                // rectangle detection
                if (numberVertices == 4
                        && mincos >= -0.1 && maxcos <= 0.3
                ) {
                    setLabel(img, String.format("%d",counter), cnt);
                    finalContours.add(cnt);
                }
            }
        }

        drawRectangles(finalContours, img);

        //Imgproc.drawContours(img, contours, -1, new Scalar(0,255,0), 2, LINE_AA);

        Bitmap img_bitmap = Bitmap.createBitmap(img.cols(), img.rows(),Bitmap.Config.ARGB_8888);

        Utils.matToBitmap(img, img_bitmap);

        imageView.setImageBitmap(img_bitmap);

    }

    private static double angle(Point pt1, Point pt2, Point pt0) {
        double dx1 = pt1.x - pt0.x;
        double dy1 = pt1.y - pt0.y;
        double dx2 = pt2.x - pt0.x;
        double dy2 = pt2.y - pt0.y;
        return (dx1 * dx2 + dy1 * dy2)
                / Math.sqrt(
                (dx1 * dx1 + dy1 * dy1) * (dx2 * dx2 + dy2 * dy2) + 1e-10
        );
    }

    private void setLabel(Mat im, String label, MatOfPoint contour) {
        int fontface = FONT_HERSHEY_SIMPLEX;
        double scale = 1;
        int thickness = 3;
        int[] baseline = new int[1];

        Size text = Imgproc.getTextSize(label, fontface, scale, thickness, baseline);
        Rect r = Imgproc.boundingRect(contour);

        Point pt = new Point(
                r.x + ((r.width - text.width) / 2),
                r.y + ((r.height + text.height) /2)
        );
        /*
        Imgproc.rectangle(
                im,
                new Point(r.x + 0, r.y + baseline[0]),
                new Point(r.x + text.width, r.y -text.height),
                new Scalar(255,255,255),
                -1
                );
        */

        Imgproc.putText(im, label, pt, fontface, scale, new Scalar(255,255,255), thickness);
    }

    private void drawRectangles(List <MatOfPoint> finalContours , Mat frameToDrawOn){

        MatOfPoint2f approxCurve = new MatOfPoint2f();

        for(int n = 0; n < finalContours.size(); n++){
            //Convert contours(i) from MatOfPoint to MatOfPoint2f
            MatOfPoint2f contour2f = new MatOfPoint2f( finalContours.get(n).toArray() );
            //Epsilon (size of rectangle)
            double approxDistance = Imgproc.arcLength(contour2f, true)*0.02;
            Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);
            //Convert back to MatOfPoint
            MatOfPoint points = new MatOfPoint( approxCurve.toArray());
            // Get bounding rect of contour
            Rect rect = Imgproc.boundingRect(points);

            Imgproc.rectangle(frameToDrawOn, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height),new Scalar (255, 255, 255), 3);
        }
    }
}