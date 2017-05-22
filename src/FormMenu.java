import Form.Form2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by user on 14/05/2017.
 */



/**
 * Created by user on 13/05/2017.
 */
public class FormMenu extends JFrame implements ActionListener {
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    koneksi db;
    //private JLabel label1, label2;
    //private JTextField txtusername;
    //private JPasswordField txtpassword;
    private JButton btnMasuk;
    private JButton btnKeluar;
    //private String user = "",pass = "";

    public FormMenu(){
        super ("Login Parkir Kendaraan");

        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        //label1 = new JLabel ("Username : ");
        //label2 = new JLabel ("Password : ");

        //txtusername = new JTextField(20);
        //txtusername.setToolTipText("Input Username");
        //txtpassword = new JPasswordField(20);

        btnMasuk = new JButton ("Kendaraan Masuk");
        btnMasuk.addActionListener(this);
        btnKeluar = new JButton ("Kendaraan Keluar");
        btnKeluar.addActionListener(this);

        db =new koneksi();

        //container.add(label1);
        //container.add(txtusername);
        //container.add(label2);
        //container.add(txtpassword);
        container.add(btnMasuk);
        container.add(btnKeluar);


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



    @Override
    public void actionPerformed(ActionEvent e) {

            Form2 a = new Form2(); //mggil Form2 dan menjadikannya variabel a
            a.setVisible(true); //memunculkan variabel a(form2)
        }//i
    }


