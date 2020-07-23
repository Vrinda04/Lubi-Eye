package blink;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;


public class Detection extends Run{

public static void load() {
System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
}
public static void detect (Mat frame, int i) {

Mat eyes = new Mat();
CascadeClassifier face_cascade = new CascadeClassifier();
face_cascade.load("C:\\Users\\admin\\Desktop\\nihal\\openCV_workspace\\src\\blink\\cascades\\haarcascade_frontalface_alt.xml");
MatOfRect faceDetections = new MatOfRect();

face_cascade.detectMultiScale(frame, faceDetections);
Rect crop = null;
if(faceDetections.toArray().length == 0)
frame=null;

else {
Rect rect = faceDetections.toArray()[0];
crop = new Rect(rect.x, rect.y, rect.width/2, rect.height);
Mat croppedFace = new Mat(frame, crop);
frame=croppedFace;

Mat eye = null;
CascadeClassifier eye_cascade = new CascadeClassifier();
eye_cascade.load("C:\\Users\\admin\\Desktop\\nihal\\openCV_workspace\\src\\blink\\cascades\\haarcascade_righteye_2splits.xml");
MatOfRect eyeDetections = new MatOfRect();
eye_cascade.detectMultiScale(frame, eyeDetections);
if(eyeDetections.toArray().length == 0)
eyes=eye;
else {
Rect eyeCrop = null;
Rect rect2 = eyeDetections.toArray()[0];
eyeCrop = new Rect(rect2.x, rect2.y, rect2.width, rect2.height);
eye = new Mat(frame, eyeCrop);
Size sz = new Size(60, 60);
Imgproc.resize( eye, eye, sz );

Imgcodecs.imwrite("C:\\Users\\admin\\Desktop\\nihal\\pictures7\\e"+i+".jpg", eye);
eyeCount++;
pic_counter++;

eyes=eye;}
}

}

public static int getEyeCount() {
return (eyeCount);
}
}