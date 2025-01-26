package methods;

import ui.MainScreen;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginMethods {

    // Veritabanı bağlantı bilgileri
    private static final String DB_URL = "jdbc:mysql://localhost:3306/OrderAutomationDB"; // Veritabanı URL'si
    private static final String DB_USER = "root";  // Veritabanı kullanıcı adı
    private static final String DB_PASSWORD = "password";  // Veritabanı şifresi

    // Kullanıcı girişini kontrol eden metod
    public static void login(String username, String password, JFrame parentFrame) {
        // Kullanıcı adı ve şifreyi kontrol eden SQL sorgusu
        String query = "SELECT * FROM employees WHERE username = ? AND password = ?";

        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // Veritabanına bağlanır
                PreparedStatement stmt = conn.prepareStatement(query) // SQL sorgusunu hazırlar
        ) {

            // Sorgu parametrelerini ayarlar (kullanıcı adı ve şifre)
            stmt.setString(1, username); // 1. parametre: kullanıcı adı
            stmt.setString(2, password); // 2. parametre: şifre

            // Sorguyu çalıştırır ve sonucu alır
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // Eğer bir sonuç dönerse (giriş bilgileri doğru)
                new MainScreen().setVisible(true); // Ana ekranı aç
                parentFrame.dispose(); // Login ekranını kapat
            } else { // Eğer kullanıcı adı veya şifre yanlışsa
                JOptionPane.showMessageDialog(parentFrame, "Hatalı kullanıcı adı veya şifre.", "Hata", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            // Veritabanı bağlantısında hata olursa bir mesaj gösterir
            JOptionPane.showMessageDialog(parentFrame, "Veritabanı bağlantı hatası: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Hata detaylarını konsola yazdırır
        }
    }
}
