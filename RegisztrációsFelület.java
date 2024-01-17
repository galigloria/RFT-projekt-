import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegisztracioForm extends JDialog{
    private JTextField tf_nev;
    private JTextField tf_email;
    private JTextField tf_telefonszam;
    private JTextField tf_lakcim;
    private JPasswordField pf_jelszo;
    private JPasswordField pf_megerositoJelszo;
    private JButton bt_regisztracio;
    private JButton bt_kilepes;
    private JLabel lbl_nev;
    private JLabel lbl_email;
    private JLabel lbl_telefonszam;
    private JLabel lbl_lakcim;
    private JLabel lbl_jelszo;
    private JLabel lbl_megerositoJelszo;
    private JPanel RegisztracioPanel;
 public RegisztracioForm(JFrame parent)
    {
        super(parent);
        setTitle("Új fiók létrehozása");
        setContentPane(RegisztracioPanel);
        setMinimumSize(new Dimension(700,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        bt_regisztracio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                registerUser();



            }
        });
bt_kilepes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                dispose();


            }
        });
        setVisible(true);

    }
private void registerUser() {
        //A "fieldek" beolvasása
        String nev= tf_nev.getText();
        String email=tf_email.getText();
        String telefonszam= tf_telefonszam.getText();
        String lakcim = tf_lakcim.getText();
        String jelszo= String.valueOf(pf_jelszo.getPassword());
        String megerositoJelszo= String.valueOf(pf_megerositoJelszo.getPassword());
        //Hiba üzenet lekezelése
        if(nev.isEmpty() || email.isEmpty() || telefonszam.isEmpty() || lakcim.isEmpty() || jelszo.isEmpty())
        {
            JOptionPane.showMessageDialog(this,
                    "Kérem,adja meg az összes adatait!",
                    "Kérem, próbálja meg újra!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!jelszo.equals(megerositoJelszo)){
            JOptionPane.showMessageDialog(this,
                    "A jelszavak nem egyeznek!",
                    "Kérem, próbálja meg újra!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
user= addUserToDatabase(nev,email,telefonszam,lakcim,jelszo);
        if(user != null)
        {
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(this,
                    "Hiba, a felhasználó regisztrálása közben!",
                            "Kérem, próbálja meg újra!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
 public Felhasznalo user;

    private Felhasznalo addUserToDatabase(String nev, String email, String telefonszam, String lakcim, String jelszo) {
        Felhasznalo user=null;

        final String DB_URL="jdbc:mysql://localhost/mystore?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";


            try {
                Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

                Statement statement=conn.createStatement();
                String sql="INSERT INTO felhasznalo (nev,email,telefonszam,lakcim,jelszo) VALUES (?,?,?,?,?)";
                PreparedStatement preparedStatement=conn.prepareStatement(sql);
                preparedStatement.setString(1,nev);
                preparedStatement.setString(2,email);
                preparedStatement.setString(3,telefonszam);
                preparedStatement.setString(4,lakcim);
                preparedStatement.setString(5,jelszo);

                int addRows=preparedStatement.executeUpdate();
                if(addRows>0){
                    user=new Felhasznalo();
                    user.nev=nev;
                    user.email=email;
                    user.telefonszam=telefonszam;
                    user.lakcim=lakcim;
                    user.jelszo=jelszo;
                }

                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        return user;
    }
 public static void main(String[] args) {
        RegisztracioForm myform =new RegisztracioForm(null);
        Felhasznalo user=myform.user;

        if(user != null)
        {
            System.out.println("Ön sikeresen regisztrált,"+user.nev+"!");
        }
        else
        {
            System.out.println("Az Ön regisztrációja sikertelen!");
        }
    }



}
