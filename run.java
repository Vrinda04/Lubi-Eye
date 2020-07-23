package blink;

import org.opencv.core.Core;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.opencv.videoio.*;
import org.opencv.core.Mat;

public class Run{


public static long startTime = System.currentTimeMillis();
public static int eyeCount = 0;
public static int blinkCount=0;
public static int pic_counter=0;
public static int array_counter=0;
public static int terminate=90;
public static boolean exit=true;
public static int spinner_value;

public static void a() throws InterruptedException {
System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
System.out.println("Start");
int loop=0;

switch(spinner_value) {
case 1: loop=4;
break;
case 2: loop=8;
break;
case 3: loop=12;
break;

default:  break;
}

for(int l=0; l<loop; l++) {
ArrayList<Thread> object=new ArrayList<Thread>();
for(int j=0; j<terminate; j++) {
Thread t =new Thread( new Thread1());
object.add(t);}
//for(int a=0; a<3; a++) {
//VideoCapture camera = new VideoCapture(0);

for (int k=0; k<terminate; k++) {
VideoCapture camera = new VideoCapture(0);
long loopStartTime = System.currentTimeMillis();
long end = loopStartTime + 10*1000; // 60 seconds * 1000 ms/sec
Mat frame = new Mat();
camera.read(frame);

if(!camera.isOpened() || frame.empty()){
System.out.println("Error with camera");
camera.release();
}
else {          
while(System.currentTimeMillis()<=end){        

if (camera.read(frame)){
Detection.detect(frame, pic_counter);
}
else{
System.out.println("Reading frame error");
}

}  
}
if(k==0)
object.get(k).start();
else {
try{  
object.get(k-1).join();
}catch(Exception e){System.out.println(e);}
object.get(k).start();
}
//}
camera.release();


try{  
object.get(terminate-1).join();
}catch(Exception e){System.out.println(e);}
}
long endTime = System.currentTimeMillis();
System.out.println("Total time: " + (endTime - startTime)/1000.0);

int correct_blinkCount= (blinkCount/2);
JFrame parent = new JFrame();
System.out.println(blinkCount);
System.out.println(correct_blinkCount);
if(correct_blinkCount<15) {
JOptionPane.showMessageDialog(parent, "Defocus from the screen");
}

}
System.exit(1);
}
}