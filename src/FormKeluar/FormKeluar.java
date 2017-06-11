package FormKeluar;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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

public class FormKeluar extends JFrame implements ActionListener {

    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    FormMasuk.koneksi db;
    private JLabel label1, label2,label3,label4,label5,label6,label7,label8;
    private JTextField cari,jenis,tgl_masuk,jam_keluar,durasi,jam_masuk,perjam,total;



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

        label1.setFont(new Font("Comic Sans", Font.ITALIC, 14));
        label2.setFont(new Font("Comic Sans", Font.ITALIC, 14));
        label3.setFont(new Font("Comic Sans", Font.ITALIC, 14));
        label4.setFont(new Font("Comic Sans", Font.ITALIC, 14));
        label5.setFont(new Font("Comic Sans", Font.ITALIC, 14));
        label6.setFont(new Font("Comic Sans", Font.ITALIC, 14));
        label7.setFont(new Font("Comic Sans", Font.ITALIC, 14));
        label8.setFont(new Font("Comic Sans", Font.ITALIC, 14));





        cari = new JTextField(20);
        cari.setForeground(Color.blue);
        cari.setFont(new Font("Comic Sans", Font.BOLD, 14));
        jenis = new JTextField(20);
        jenis.setBackground (Color.LIGHT_GRAY);
        jenis.setForeground(Color.RED);
        tgl_masuk = new JTextField(20);
        tgl_masuk.setBackground (Color.LIGHT_GRAY);
        tgl_masuk.setForeground(Color.RED);
        jam_keluar = new JTextField(20);
        jam_keluar.setBackground (Color.LIGHT_GRAY);
        jam_keluar.setForeground(Color.RED);
        durasi = new JTextField(20);
        durasi.setBackground (Color.LIGHT_GRAY);
        durasi.setForeground(Color.RED);
        perjam = new JTextField(20);
        perjam.setBackground(Color.LIGHT_GRAY);
        perjam.setForeground(Color.RED);
        jam_masuk = new JTextField(20);
        jam_masuk.setBackground (Color.LIGHT_GRAY);
        jam_masuk.setForeground(Color.RED);
        total = new JTextField(20);
        total.setBackground (Color.LIGHT_GRAY);
        total.setForeground(Color.RED);

        ImageIcon iconcari = new ImageIcon("gambar/cari.png");
        Image img = iconcari.getImage() ;
        Image newimg = img.getScaledInstance( 30, 20,  java.awt.Image.SCALE_SMOOTH ) ;
        iconcari = new ImageIcon( newimg );
        btnCari= new JButton ("Cari",iconcari);
        btnCari.addActionListener(this);


        ImageIcon iconprint = new ImageIcon("gambar/print.png");
        Image img2 = iconprint.getImage() ;
        Image newimg2 = img2.getScaledInstance( 30, 20,  java.awt.Image.SCALE_SMOOTH ) ;
        iconprint = new ImageIcon( newimg2 );
        btnPrint = new JButton ("Print",iconprint);
        btnPrint.addActionListener(this);


        ImageIcon iconkeluar = new ImageIcon("gambar/keluar.png");
        Image img3 = iconkeluar.getImage() ;
        Image newimg3 = img3.getScaledInstance( 30, 20,  java.awt.Image.SCALE_SMOOTH ) ;
        iconkeluar = new ImageIcon( newimg3 );
        btnKeluar = new JButton ("Keluar",iconkeluar);
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



        setSize (280,520);
        setVisible (true);
        setLocationRelativeTo(null);
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



    public void updatekeluar(){


        try {

            String sql = "update parkirmasuk set jam_keluar=NOW(),durasi=TIME_FORMAT(`jam_keluar`,'%H')-TIME_FORMAT(`jam_masuk`,'%H'),tarif=3000 where plat_no='"+cari.getText()+"'";


            Statement stt = con.createStatement();
            stt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void tampilkeluar() {


        try {
            Statement stt = con.createStatement();
            String sql = "SELECT   jam_keluar, durasi,tarif FROM parkirmasuk where plat_no='"+cari.getText()+"'";
            ResultSet res = stt.executeQuery(sql);

            while(res.next()){
                Object[] ob = new Object[3];
                ob[0]=  res.getString(1);
                ob[1]= res.getString(2);



                jam_keluar.setText((String) ob[0]);
                durasi.setText((String) ob[1]);

            }
            res.close(); stt.close();
        }  catch (SQLException e) {
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


    public void total(){
        try {

            String sql = "update parkirmasuk set total=durasi*3000 where plat_no='"+cari.getText()+"'";


            Statement stt = con.createStatement();
            stt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }
    public void tampiltotal(){
        try {

            Statement stt = con.createStatement();
            String sql = "SELECT   total FROM parkirmasuk where plat_no='"+cari.getText()+"'";
            ResultSet res = stt.executeQuery(sql);

            while(res.next()){
                Object[] ob = new Object[1];
                ob[0]=  res.getString(1);


                total.setText((String) ob[0]);

            }
            res.close(); stt.close();
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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



    public void actionPerformed (ActionEvent evt) {
        if (!"".equals(cari.getText()))
        {

            tampil();
            tampiltarif();
            playsound("gambar/button.wav");

            try {
                if (evt.getSource() == btnKeluar) {
                    updatekeluar();
                    tampilkeluar();

                    total();
                    tampiltotal();
                    playsound("gambar/button.wav");


                }
                if (evt.getSource() == btnPrint) {
                    try {
                        playsound("gambar/byebye2.wav");
                        String str = "				Karcis Kendaraan Anda :\n" +
                                "====================================================================\n\n\n\n" +
                                "\nNo.Plat   			:  " + cari.getText() + "\n" +
                                "\nJenis Kendaraan		:  " + jenis.getText() + "\n" +
                                "\nTarif              	:  " + perjam.getText() + "\n" +
                                "\nTotal	            :  " + total.getText() + "\n" +


                                "Terimakasih :)" +
                                " Semoga selamat sampai tujuan. ";

                        File newTextFile = new File("karcis/Karcis - " + cari.getText() + ".txt");
                        FileWriter fw = new FileWriter(newTextFile);
                        fw.write(str);
                        fw.close();

                        JOptionPane.showMessageDialog(null, "Karcis Anda Sudah dicetak", "Terima kasih", JOptionPane.PLAIN_MESSAGE);
                        cari.setText("");
                        jenis.setText("");
                        tgl_masuk.setText("");
                        jam_keluar.setText("");
                        durasi.setText("");
                        jam_masuk.setText("");
                        perjam.setText("");
                        total.setText("");


                    } catch (IOException iox) {
                        iox.printStackTrace();
                    }
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

