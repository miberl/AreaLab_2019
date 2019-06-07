import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.fazecast.jSerialComm.SerialPort;

public class Grafico_Umidita {
    static SerialPort chosenPort;
    static int x=0;
    
    public static void main(String[] args) {
        JFrame window =new JFrame();
        window.setTitle("Umidità");
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800,500);
        
        JComboBox<String> portList = new JComboBox();
	JButton connectButton = new JButton("Connetti");
	JPanel topPanel = new JPanel();
	topPanel.add(portList);
	topPanel.add(connectButton);
	window.add(topPanel, BorderLayout.NORTH);
        
        SerialPort[] portNames = SerialPort.getCommPorts();
        for(int i=0; i<portNames.length;i++){
            portList.addItem(portNames[i].getSystemPortName());
        }
        
        XYSeries series=new XYSeries("Umidità");
        XYSeriesCollection dataset=new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("Umidità Rilevata", "Tempo (s)", "Umidità", dataset);
        window.add(new ChartPanel(chart), BorderLayout.SOUTH);
        
        connectButton.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent arg0){
                if(connectButton.getText().equals("Connetti")){
                    chosenPort=SerialPort.getCommPort(portList.getSelectedItem().toString());
                    chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER,0,0);
                    if(chosenPort.openPort()){
                        connectButton.setText("Disconnetti");
                        portList.setEnabled(false);
                    }
                    
                    Thread thread=new Thread(){
                        @Override public void run(){
                            Scanner scanner=new Scanner(chosenPort.getInputStream());
                            while(scanner.hasNextLine()){
                                try{
                                    int pos=0;
                                    String l=scanner.nextLine();
                                    for(int i=0;i<l.length();i++){
                                        if(l.charAt(i)=='U'){
                                            pos=i+1;
                                        }
                                    }
                                    l=l.substring(pos,l.length());
                                    int n=Integer.parseInt(l);
                                    series.add(x++,n);
                                    window.repaint();
                                }catch(Exception e){}
                            }
                            scanner.close();
                        }
                    };
                    thread.start();
                }else{
                    chosenPort.closePort();
                    portList.setEnabled(true);
                    connectButton.setText("Connetti");
                    series.clear();
                    x=0;
                }
            }
        });
        window.setVisible(true);
    }
}