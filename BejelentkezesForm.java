import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BejelentkezesForm extends JDialog{
    private JTextField tf_email;
    private JPasswordField pf_jelszo;
    private JButton bt_bejelentkezes;
    private JButton bt_kilepes;
    private JPanel BejelentkezesPanel;
    public BejelentkezesForm(JFrame parent) {
        super(parent);
        setTitle("Bejelentkezés");
        setContentPane(BejelentkezesPanel);
        setMinimumSize(new Dimension(700, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        bt_bejelentkezes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tf_email.getText();
                String jelszo = String.valueOf(pf_jelszo.getPassword());
                user = felhasznaloHitelesitese(email, jelszo);
                if (user != null) {
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(BejelentkezesForm.this,
                            "Helytelen e-mail vagy jelszó!",
                            "Próbálja meg újra!",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
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
        public Felhasznalo user;
        private Felhasznalo felhasznaloHitelesitese(String email, String jelszo) {
            Felhasznalo user=null;
            final String DB_URL="jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
            final String USERNAME="root";
            final String PASSWORD="";
            try {
                Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                Statement statement=conn.createStatement();
                String sql="SELECT*FROM felhasznalo WHERE email=? AND jelszo=?";
                PreparedStatement preparedStatement=conn.prepareStatement(sql);
                preparedStatement.setString(1,email);
                preparedStatement.setString(2,jelszo);
                ResultSet resultSet= preparedStatement.executeQuery();
                if(resultSet.next())
                {
                    user=new Felhasznalo();
                    user.nev=resultSet.getString("nev");
                    user.email=resultSet.getString("email");
                    user.telefonszam=resultSet.getString("telefonszam");
                    user.lakcim=resultSet.getString("lakcim");
                    user.jelszo=resultSet.getString("jelszo");
                }
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return user;

        }

    }
}


