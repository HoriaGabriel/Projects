package library.view;

import javax.swing.JPanel;

public class TableDashboard extends AppFrame{

    public void initialize(){

        this.setSize(400,550);
        this.setTitle("Start");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);

        JPanel panel= new JPanel();
        panel.setLayout(null);

        this.setContentPane(panel);
        this.setVisible(true);

    }

}
