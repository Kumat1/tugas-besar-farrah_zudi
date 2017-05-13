/**
 * Created by user on 10/05/2017.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * Created by user on 13/05/2017.
 */
public class FormLogin extends JFrame implements ActionListener {
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    koneksi db;
    private JLabel label1, label2;
    private JTextField txtusername;
    private JPasswordField txtpassword;
    private JButton btnLogin, btnExit;
    private String user = "",pass = "";

    public FormLogin(){
        super ("Login Parkir Kendaraan");

        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        label1 = new JLabel ("Username : ");
        label2 = new JLabel ("Password : ");

        txtusername = new JTextField(20);
        txtusername.setToolTipText("Input Username");
        txtpassword = new JPasswordField(20);

        btnLogin = new JButton ("Login");
        btnLogin.addActionListener(this);
        btnExit = new JButton ("Exit");
        btnExit.addActionListener(this);

        db =new koneksi();

        container.add(label1);
        container.add(txtusername);
        container.add(label2);
        container.add(txtpassword);
        container.add(btnLogin);
        container.add(btnExit);


        //pemanggilan fungsi koneksi database yang sudah kita buat pada class koneksi.java
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;

        setSize (310,200);
        setVisible (true);
    }

    public static void main(String[] args) {
        FormLogin test = new FormLogin();
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed (ActionEvent evt){
        try {
            sql = "SELECT * FROM login WHERE username='"+txtusername.getText()+"' AND password='"+txtpassword.getText()+"'";
            rs = stat.executeQuery(sql);
            if(rs.next()){
                if(txtusername.getText().equals(rs.getString("username")) && txtpassword.getText().equals(rs.getString("password"))){
                    JOptionPane.showMessageDialog(null, "berhasil login");
                }
            }else{
                JOptionPane.showMessageDialog(null, "username atau password salah");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//if
}//method

