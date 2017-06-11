package FormMasuk;

/**
 * Created by user on 07/06/2017.
 */

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


public class FormMasuk extends JFrame implements ActionListener {
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    koneksi db;
    private JLabel  label2,label3;
    private JTextField plat_no;
    private JComboBox comboBox1;

    private JButton btnSimpan;

    public FormMasuk(){
        super ("Kendaraan Masuk");

        Container container = getContentPane();
        container.setLayout(new FlowLayout());


        label2 = new JLabel ("Plat.No : ");
        label3 = new JLabel ("Jenis Kendaraan : ");

        label2.setFont(new Font("Comic Sans", Font.ITALIC, 14));
        label3.setFont(new Font("Comic Sans", Font.ITALIC, 14));


        plat_no = new JTextField(20);
        plat_no.setFont(new Font("Comic Sans", Font.BOLD, 14));
        plat_no.setForeground(Color.blue);

        comboBox1 = new JComboBox();
        comboBox1 .addItem("Mobil");
        comboBox1 .addItem("Motor");
        comboBox1.setFont(new Font("comic",Font.TYPE1_FONT,14));


        ImageIcon iconsimpan = new ImageIcon("gambar/simpan.png");
        Image img = iconsimpan.getImage() ;
        Image newimg = img.getScaledInstance( 30, 20,  java.awt.Image.SCALE_SMOOTH ) ;
        iconsimpan= new ImageIcon( newimg );
        btnSimpan = new JButton ("Simpan",iconsimpan);
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


        setSize (310,200);
        setVisible (true);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void initComponents() {
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


    public void actionPerformed (ActionEvent evt){

        if( !"".equals(plat_no.getText())){
            try {
                playsound("gambar/button.wav");
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

