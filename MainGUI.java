package blink;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.*;

public class MainGui extends Run implements ActionListener, Runnable  {

JLabel l;
static JSpinner spinner;
public void run() {
try{  
Run.a();
}catch(Exception error){System.out.println(error);}

}

public static void main(String[] args) {
MainGui gui=new MainGui();
gui.go();

}

public void actionPerformed(ActionEvent e){
String action= e.getActionCommand();

if(action.equals("START")){
spinner_value = (Integer) spinner.getValue();
System.out.println(spinner_value);
MainGui m1=new MainGui();
Thread t= new Thread(m1);
t.start();
}

if(action.equals("STOP")){
System.out.println(blinkCount);
System.exit(1);

}
}



public void go() {
JFrame frame = new JFrame("BLINKER");
JPanel panel = new JPanel(); // the panel is not visible in output
JButton button1= new JButton("START");
button1.addActionListener(this);
JButton button2= new JButton("STOP");
SpinnerModel value =  new SpinnerNumberModel(1, //initial value  
1, //minimum value  
3, //maximum value  
1); //step  
spinner = new JSpinner(value);  
spinner.setBounds(100,100,50,30);    

l=new JLabel("Select hour to run for");
button1.addActionListener(this);
button2.addActionListener(this);
panel.add(button1);
panel.add(button2);
panel.add(l);
panel.add(spinner);

frame.getContentPane().add(BorderLayout.CENTER, panel);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(500,100);
frame.setVisible(true);
}

}
