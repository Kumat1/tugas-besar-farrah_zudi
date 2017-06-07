package FormKeluar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by user on 07/06/2017.
 */
public class FormKeluar extends JFrame implements ActionListener  {




    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    FormMasuk.koneksi db;
    private JLabel label1, label2,label3,label4,label5,label6,label7,label8;
    private JTextField cari,jenis,tgl_masuk,jam_keluar,durasi,jam_masuk,perjam,total;
    private JTextArea txt1;


    private JButton btnCari,btnPrint,btnKeluar;


    public FormKeluar(){
        super ("Kendaraan Keluar");

        Container container = getContentPane();
        container.setLayout(new FlowLayout());


        label1 = new JLabel ("Cari no.plat : ");
        label2 = new JLabel ("Jenis Kendaraan : ");
        label3 = new JLabel ("Tgl_masuk : ");
        label4 = new JLabel ("Jam_keluar : ");
        label5 = new JLabel ("Durasi : ");
        label6 = new JLabel ("Biaya perjam : ");
        label7 = new JLabel ("Jam Masuk: ");
        label8 = new JLabel ("Total: ");




        cari = new JTextField(20);
        jenis = new JTextField(20);
        jenis.setBackground (Color.gray);
        tgl_masuk = new JTextField(20);
        tgl_masuk.setBackground (Color.gray);
        jam_keluar = new JTextField(20);
        jam_keluar.setBackground (Color.gray);
        durasi = new JTextField(20);
        durasi.setBackground (Color.gray);
        perjam = new JTextField(20);
        perjam.setBackground (Color.gray);
        jam_masuk = new JTextField(20);
        jam_masuk.setBackground (Color.gray);
        total = new JTextField(20);
        total.setBackground (Color.gray);


        btnCari= new JButton ("Cari");
        btnCari.addActionListener(this);
        btnPrint = new JButton ("Print");
        btnPrint.addActionListener(this);
        btnKeluar = new JButton ("Keluar");
        btnKeluar.addActionListener(this);


        db =new FormMasuk.koneksi();


        container.add(label1);
        container.add(cari);
        container.add(label2);
        container.add(jenis);
        container.add(label3);
        container.add(tgl_masuk);
        container.add(label4);
        container.add(jam_keluar);
        container.add(label5);
        container.add(durasi);
        container.add(label6);
        container.add(perjam);
        container.add(label7);
        container.add(jam_masuk);
        container.add(label8);
        container.add(total);

        container.add(btnCari);
        container.add(btnPrint);
        container.add(btnKeluar);

        jenis.setEditable(false);
        tgl_masuk.setEditable(false);
        jam_keluar.setEditable(false);
        durasi.setEditable(false);
        perjam.setEditable(false);
        jam_masuk.setEditable(false);
        total.setEditable(false);





        //pemanggilan fungsi koneksi database yang sudah kita buat pada class koneksi.java
        FormMasuk.koneksi DB = new FormMasuk.koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        initComponents();



        setSize (250,470);
        setVisible (true);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void initComponents() {
    }




    public void tampil()
    {
        try {

            Statement stt = con.createStatement();
            String sql = "select jenis, tgl_masuk,jam_masuk from parkirmasuk where plat_no='"+cari.getText()+"'";
            ResultSet res = stt.executeQuery(sql);

            while(res.next()){
                Object[] ob = new Object[3];
                ob[0]=  res.getString(1);
                ob[1]= res.getString(2);
                ob[2]= res.getString(3);

                jenis.setText((String) ob[0]);
                tgl_masuk.setText((String) ob[1]);
                jam_masuk.setText((String) ob[2]);
            }
            res.close(); stt.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




    public void tampiltarif(){
        try {

            Statement stt = con.createStatement();
            String sql = "SELECT   tarif FROM parkirmasuk where plat_no='"+cari.getText()+"'";
            ResultSet res = stt.executeQuery(sql);

            while(res.next()){
                Object[] ob = new Object[1];
                ob[0]=  res.getString(1);


                perjam.setText((String) ob[0]);

            }
            res.close(); stt.close();
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }







    public void actionPerformed (ActionEvent evt) {
        if (!"".equals(cari.getText())) {


            tampil();
            tampiltarif();


            try {
                if (evt.getSource() == btnKeluar) {

                }
                if (evt.getSource() == btnPrint) {

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan");
                System.out.println("erornya : " + e);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Anda Belum Input Nopol");
        }

    }

}//if


