package ui;

import methods.LoginMethods;

import javax.swing.*;

// LoginScreen sınıfı, kullanıcıların giriş yapmasını sağlayan bir Swing tabanlı arayüzdür.
public class LoginScreen extends JFrame {

    // Arayüz bileşenleri
    private JPanel LoginPanel;       // Tüm bileşenleri içeren panel
    private JLabel UsernameLBL;      // Kullanıcı adı etiketi
    private JLabel PasswordLBL;      // Şifre etiketi
    private JTextField UsernameTF;   // Kullanıcı adı metin alanı
    private JButton LoginBTN;        // Giriş butonu
    private JPasswordField PasswordPF; // Şifre metin alanı

    // Constructor (Yapıcı Metot)
    public LoginScreen() {

        // Paneli pencereye ekler
        add(LoginPanel);
        setTitle("Giriş Ekranı"); // Pencere başlığı
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Pencere kapandığında yalnızca bu pencereyi kapatır
        setSize(400, 200); // Pencere boyutunu ayarlar
        setLocationRelativeTo(null); // Pencereyi ekranın ortasına yerleştirir

        // Metin alanlarını ortalar
        UsernameTF.setHorizontalAlignment(JTextField.CENTER); // Kullanıcı adı alanını ortalar
        PasswordPF.setHorizontalAlignment(JPasswordField.CENTER); // Şifre alanını ortalar

        // LoginBTN'e tıklama olayını ekler
        LoginBTN.addActionListener(e -> {
            // Kullanıcı adı ve şifreyi alır
            String username = UsernameTF.getText(); // Kullanıcı adı alanından metni alır
            String password = new String(PasswordPF.getPassword()); // Şifre alanından metni alır

            // LoginMethods ile giriş kontrolü yapar
            LoginMethods.login(username, password, this);
        });

        setVisible(true); // Pencereyi görünür hale getirir
    }
}
