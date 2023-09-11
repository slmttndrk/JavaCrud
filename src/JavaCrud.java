import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JavaCrud {
    private JPanel Main;
    private JTextField txtName;
    private JTextField txtPrice;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JTextField textField4;
    private JTextField txtQty;

    public static void main(String[] args) {
        JFrame frame = new JFrame("JavaCrud");
        frame.setContentPane(new JavaCrud().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    Connection con;
    PreparedStatement pst;
    public JavaCrud() {
        Connect();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name, price, qty;

                name = txtName.getText();
                price = txtPrice.getText();
                qty = txtQty.getText();

                try {
                    pst = con.prepareStatement("insert into products(pname, price, qty) values (?, ? ,?)");
                    pst.setString(1, name);
                    pst.setString(2, price);
                    pst.setString(3, qty);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Record is added!");

                    txtName.setText("");
                    txtPrice.setText("");
                    txtQty.setText("");
                    txtName.requestFocus();
                }catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }

    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/gbproducts", "root", "");
            System.out.println("Success");
        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
