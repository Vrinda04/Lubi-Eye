package blink;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Thread1 extends Run implements Runnable{
// Thread calculating the number of eye blinks
@Override
public void run() {
int NUM_SAVED_IMGS =Detection.getEyeCount();
double[] averages = new double[NUM_SAVED_IMGS];
double[] variances = new double[NUM_SAVED_IMGS];
long[][] grayScaleEye;
int temp=0;
double[] sd=new double[NUM_SAVED_IMGS];

System.out.println("Counting blinks ... ");

for (int x = array_counter; x < NUM_SAVED_IMGS; x++) {
BufferedImage eye = EyeAnalyzer.readInImage("C:\\Users\\admin\\Desktop\\nihal\\pictures7\\e"+x+".jpg");
grayScaleEye = EyeAnalyzer.grayScaleArray(eye);
averages[x] = EyeAnalyzer.calculateAverage(grayScaleEye);
variances[x] = EyeAnalyzer.calculateVariance(grayScaleEye, averages[x]);
sd[x] =  Math.sqrt(variances[x]);
temp=x+1;
}

double sum_sd=0;
double sum = 0;
double average_variance;
int i = array_counter;
for(int x=array_counter; x<NUM_SAVED_IMGS; x++) {//for(double var: variances) {
System.out.println(i + " " + variances[x] +", "+averages[x]+", "+sd[x]);
sum_sd +=sd[x];
sum += variances[x];
i++;
}

double average_sd=sum_sd/sd.length;
average_variance = sum/variances.length;
System.out.println("Average sd: " + average_sd);
System.out.println("Average Variance: " + average_variance);

double sumofsquares = 0;
for(double var: variances) {
sumofsquares += Math.pow(var - average_variance, 2);
}
double varianceofvariances = sumofsquares/(variances.length);
System.out.println("variance of variances:"+varianceofvariances);
double sdofvariances = Math.sqrt(varianceofvariances);
System.out.println("Standard Deviation of Variances: " + sdofvariances);
double factor= average_variance + sdofvariances;

int start;
if(array_counter==0)
start=1;
else
start=array_counter;
for(int x =start; x < NUM_SAVED_IMGS-1; x++) {
//if((variances[x] < (average_variance+sd[x])) && (variances[x-1] > (average_variance+sd[x])))
//if(variances[x]> (variances[x-1]+45) )//|| (variances[x+1]+45)<variances[x])
if((variances[x] < average_variance - (1.75 * sdofvariances)) && (variances[x+1] < average_variance - (sdofvariances) && (variances[x-1] < average_variance - (sdofvariances))))
{
blinkCount++;
System.out.println(x);
}
}
array_counter=temp;




}

}
