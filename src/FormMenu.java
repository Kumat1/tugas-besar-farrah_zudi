

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by user on 22/05/2017.
 */




public class FormMenu extends JFrame implements ActionListener {
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    koneksi db;

    private JButton btnMasuk;
    private JButton btnKeluar;
    //private String user = "",pass = "";

    public FormMenu(){
        super ("Login Parkir Kendaraan");

        Container container = getContentPane();
        container.setLayout(new FlowLayout());



        btnMasuk = new JButton ("Kendaraan Masuk");
        btnMasuk.addActionListener(this);
        btnKeluar = new JButton ("Kendaraan Keluar");
        btnKeluar.addActionListener(this);

        db =new koneksi();


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
        if(e.getSource() == btnMasuk){


            FormMasuk c = new FormMasuk(); //mggil Formmasuk dan menjadikannya variabel c
            c.setVisible(true); //memunculkan variabel c(formmasuk)


        }
        else
        {
            FormKeluar b = new FormKeluar(); //mggil Formkeluar dan menjadikannya variabel b
            b.setVisible(true); //memunculkan variabel b(formkeluar)

        }
    }
    }


