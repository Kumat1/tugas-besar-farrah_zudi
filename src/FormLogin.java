/**
 * Created by user on 10/05/2017.
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
        label1.setFont(new Font("Comic Sans", Font.ITALIC, 14));
        label2.setFont(new Font("Comic Sans", Font.ITALIC, 14));

        txtusername = new JTextField(20);
        txtusername.setToolTipText("Input Username");
        txtpassword = new JPasswordField(20);
        txtpassword.setToolTipText("Input Password");

        ImageIcon iconlogin = new ImageIcon("gambar/kunci.png");
        Image img = iconlogin.getImage() ;
        Image newimg = img.getScaledInstance( 30, 20,  java.awt.Image.SCALE_SMOOTH ) ;
        iconlogin = new ImageIcon( newimg );
        btnLogin = new JButton ("Login",iconlogin);
        btnLogin.addActionListener(this);




        ImageIcon iconexit= new ImageIcon("gambar/exit.jpg");
        Image img2 = iconexit.getImage() ;
        Image newimg2 = img2.getScaledInstance( 30, 20,  java.awt.Image.SCALE_SMOOTH ) ;
        iconexit= new ImageIcon( newimg2 );
        btnExit = new JButton ("Exit",iconexit);
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

    public void actionPerformed (ActionEvent evt){
        try {
            playsound("gambar/button.wav");
            sql = "SELECT * FROM admin WHERE username='"+txtusername.getText()+"' AND password='"+txtpassword.getText()+"'";
            rs = stat.executeQuery(sql);
            if(rs.next()){
                if(txtusername.getText().equals(rs.getString("username")) && txtpassword.getText().equals(rs.getString("password"))){
                    FormMenu a = new FormMenu(); //mggil FormMenu dan menjadikannya variabel a
                    a.setVisible(true); //
                }
            }else{
                JOptionPane.showMessageDialog(null, "username atau password salah");
            }
            if (evt.getSource() == btnExit){
                playsound("gambar/button.wav");
                JOptionPane.showMessageDialog(null, "Anda akan keluar!");
                System.exit(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//if
}//method

