package library.view;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Vector;


public class DashboardSearch {

    private JFrame frame;
    private JTable table;
    Connection conn = null;
    PreparedStatement statement = null;
    ResultSet rs = null;


    public void initialize(String aux, String aux2)  {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Vluhpi1921$");
            statement = conn.prepareStatement("Select * from member");
            rs = statement.executeQuery();

            table = new JTable(buildTableModel(rs,aux,aux2));

            JOptionPane.showMessageDialog(null, new JScrollPane(table));

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private TableModel buildTableModel(ResultSet rs, String aux, String aux2) throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        Vector<String> columnNames;
        columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }


        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            String s=rs.getString("Name");
            String s1=rs.getString("Email");
            if(s.compareTo(aux)==0 || s1.compareTo(aux2)==0){
                Vector<Object> vector = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {

                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }
        }

        return new DefaultTableModel(data, columnNames);
    }
}

