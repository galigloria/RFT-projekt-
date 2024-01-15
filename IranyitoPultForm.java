import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class IranyitoPultForm  extends JFrame{
    private JPanel IranyitoPultPanel;
    private JLabel lb_admin;
    private JButton bt_regisztracio;
    {
        setTitle("Irányítópult");
        setContentPane(IranyitoPultPanel);
        setMinimumSize(new Dimension(500,429));
        setSize(1200,700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        boolean regisztraltFelhasznalo= adatbazisKapcsolat();
        if(regisztraltFelhasznalo)
        {
            BejelentkezesForm bejelentkezesForm=new BejelentkezesForm(this);
            Felhasznalo user= bejelentkezesForm.user;
            if(user!=null)
            {
                lb_admin.setText("Felhasználó:"+user.nev);
                setLocationRelativeTo(null);
                setVisible(true);

            }
            else{
                dispose();
            }
        }
        else {
            RegisztracioForm regisztracioForm=new RegisztracioForm(this);
            Felhasznalo user=regisztracioForm.user;
            if(user!=null)
            {
                lb_admin.setText("Felhasználó:" +user.nev);
                setLocationRelativeTo(null);
                setVisible(true);
            }
            else {
                dispose();
            }
        }

        bt_regisztracio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisztracioForm regisztracioForm= new RegisztracioForm(IranyitoPultForm.this);
                Felhasznalo user=regisztracioForm.user;
                if(user!=null)
                {
                    JOptionPane.showMessageDialog(IranyitoPultForm.this,
                            "Ön egy új felhasználó,"+user.nev+"!",
                            "Az Ön regisztrációja sikeres!",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });
    }

    private boolean adatbazisKapcsolat() {
        boolean regisztraltFelhasznalo=false;
        final String MYSQL_SERVER_URL="jdbc:mysql://localhost/";
        final String DB_URL="jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";
        try {
            Connection conn = DriverManager.getConnection(MYSQL_SERVER_URL, USERNAME, PASSWORD);
            Statement statement=conn.createStatement();
            statement.executeUpdate("Készítsen egy adtabázis táblát, ha még nem létezik a MYSTORE-ban");
            statement.close();
            conn.close();
            conn=DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            String sql="Készítsen egy adtabázis táblát, ha még nem létezik a MYSTORE-ban("
                    +"ID INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                    +"NEV VARCHAR(200) NOT NULL,"
                    + "EMAIL VARCHAR(200) NOT NULL UNIQUE,"
                    +"TELEFONSZAM VARCHAR(200),"
                    +"LAKCIM VARCHAR(200),"
                    + "JELSZO VARCHAR(200) NOT NULL"
                    +");";
            statement.executeUpdate(sql);
            statement=conn.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT COUNT(*) FROM felhasznalo");
            if(resultSet.next())
            {
                int numUser=resultSet.getInt(1);
                if(numUser>0)
                {
                    regisztraltFelhasznalo=true;
                }
            }
            statement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regisztraltFelhasznalo;
    }

}
