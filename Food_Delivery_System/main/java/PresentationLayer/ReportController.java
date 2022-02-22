package PresentationLayer;

import BusinessLayer.DeliveryService;
import BusinessLayer.ReportMaker;

import javax.swing.*;
import java.io.IOException;

public class ReportController {

    ReportMaker r = new ReportMaker();

    /**
     * The function initializes the actionListeners of the report view
     * @param  adminView the administrators user interface
     * @param  d the delivery service where various operations are performed with the given data
     * @param  rView the reports user interface
     */
    public void rViewActionListener(ReportView rView, DeliveryService d, AdministratorView adminView) {

        rView.backButtonListener(e->{
            rView.setVisible(false);
            adminView.setVisible(true);
        });

        rView.r1ButtonListener(e->{
            if(rView.getStartingHourTextField().isEmpty() || rView.getEndingHourTextField().isEmpty()){
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Improper input", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                int sHour=Integer.parseInt(rView.getStartingHourTextField());
                int fHour=Integer.parseInt(rView.getEndingHourTextField());
                try{
                    r.makeReport1(sHour,fHour,d);
                } catch(IOException sw){
                    System.out.println("NO good");
                }
            }

        });

        rView.r2ButtonListener(e->{
            if(rView.getNumberTimesTextField().isEmpty()){
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Improper input", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                int times=Integer.parseInt(rView.getNumberTimesTextField());
                try{
                    r.makeReport2(times,d);
                } catch(IOException sw){
                    System.out.println("NO good");
                }

            }
        });

        rView.r3ButtonListener(e->{
            if(rView.getNumberTimes2TextField().isEmpty() || rView.getAmountTextField().isEmpty()){
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Improper input", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                int times2=Integer.parseInt(rView.getNumberTimes2TextField());
                int value=Integer.parseInt(rView.getAmountTextField());
                try {
                    r.makeReport3(times2,value,d);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        rView.r4ButtonListener(e->{
            if(rView.getSpecificDayTextField().isEmpty()){
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Improper input", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                String date=rView.getSpecificDayTextField();
                try {
                    r.makeReport4(date,d);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}

