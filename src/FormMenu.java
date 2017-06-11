

import FormKeluar.FormKeluar;
import FormMasuk.FormMasuk;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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


    public FormMenu(){
        super ("Login Parkir Kendaraan");

        Container container = getContentPane();
        container.setLayout(new FlowLayout());



        ImageIcon iconmasuk = new ImageIcon("gambar/masuk.png");
        Image img = iconmasuk.getImage() ;
        Image newimg = img.getScaledInstance( 30, 20,  java.awt.Image.SCALE_SMOOTH ) ;
        iconmasuk= new ImageIcon( newimg );
        btnMasuk = new JButton ("Kendaraan Masuk",iconmasuk);
        btnMasuk.addActionListener(this);

        ImageIcon iconkeluar = new ImageIcon("gambar/keluar.png");
        Image img2 = iconkeluar.getImage() ;
        Image newimg2 = img2.getScaledInstance( 30, 20,  java.awt.Image.SCALE_SMOOTH ) ;
        iconkeluar= new ImageIcon( newimg2 );
        btnKeluar = new JButton ("Kendaraan Keluar",iconkeluar);
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
        setLocationRelativeTo(null);
        setLayout(null);

    }

    public static void main(String[] args) {
        FormLogin test = new FormLogin();
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void playsound(String soundName)
    {
        try
        {
            AudioInputStream audioInputStream= AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip=AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();


        }
        catch (Exception ex)
        {
            System.out.println("Error with Playing sound.");
            ex.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnMasuk){
            playsound("gambar/button.wav");


            FormMasuk c = new FormMasuk(); //mggil Formmasuk dan menjadikannya variabel c
            c.setVisible(true); //memunculkan variabel c(formmasuk)


        }
        else
        {
            playsound("gambar/button.wav");

            FormKeluar b = new FormKeluar(); //mggil Formkeluar dan menjadikannya variabel b
            b.setVisible(true); //memunculkan variabel b(formkeluar)

        }
    }
    }


