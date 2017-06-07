package FormMasuk;

/**
 * Created by user on 07/06/2017.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


import javax.swing.JOptionPane;


public class FormMasuk extends JFrame implements ActionListener {
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    koneksi db;
    private JLabel label1, label2,label3,label4,label5;
    private JTextField no_tiket,plat_no,tarif;
    private JComboBox comboBox1;

    private JButton btnSimpan;

    public FormMasuk(){
        super ("Kendaraan Masuk");

        Container container = getContentPane();
        container.setLayout(new FlowLayout());


        label2 = new JLabel ("Plat.No : ");
        label3 = new JLabel ("Jenis Kendaraan : ");






        plat_no = new JTextField(7);
        comboBox1 = new JComboBox();
        comboBox1 .addItem("Mobil");
        comboBox1 .addItem("Motor");




        btnSimpan = new JButton ("Simpan");
        btnSimpan.addActionListener(this);

        db =new koneksi();


        container.add(label2);
        container.add(plat_no);
        container.add(label3);
        container.add(comboBox1);




        container.add(btnSimpan);


        //pemanggilan fungsi koneksi database yang sudah kita buat pada class koneksi.java
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        initComponents();


        setSize (250,150);
        setVisible (true);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void initComponents() {
    }




    public void actionPerformed (ActionEvent evt){

        if( !"".equals(plat_no.getText())){
            try {

                stat=con.createStatement();
                String sql="INSERT INTO parkirmasuk(plat_no,jenis,tgl_masuk,jam_masuk) VALUES ('"+plat_no.getText()+"','"+ comboBox1.getSelectedItem() +"',NOW(),NOW());";
                stat.executeUpdate(sql);

                JOptionPane.showMessageDialog(null, "Berhasil Tersimpan");
                plat_no.setText("");


            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Data Gagal Disimpan");
                System.out.println("erornya : "+e);
            }


        }else{
            JOptionPane.showMessageDialog(null, "Anda Belum Input Nopol");
        }
    }//if
}

