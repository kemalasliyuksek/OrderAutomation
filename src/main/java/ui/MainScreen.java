package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// MainScreen sınıfı, ana menü ekranını temsil eden Swing tabanlı bir arayüzdür.
public class MainScreen extends JFrame {

    // Arayüz bileşenleri
    private JPanel MainScreenPanel;     // Ana menü bileşenlerini içeren panel
    private JButton CustomerBTN;       // Müşteri işlemleri butonu
    private JButton OrderBTN;          // Sipariş işlemleri butonu
    private JButton AppInfoBTN;        // Uygulama hakkında bilgi butonu
    private JButton LogOutBTN;         // Çıkış butonu

    // Constructor (Yapıcı Metot)
    public MainScreen() {
        add(MainScreenPanel); // Paneli ana pencereye ekler
        setTitle("Ana Menü"); // Pencere başlığını ayarlar
        setSize(400, 200);    // Pencere boyutunu ayarlar
        setLocationRelativeTo(null); // Pencereyi ekranın ortasına yerleştirir
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Pencere kapandığında yalnızca bu pencereyi kapatır

        // Butonların yazı tipini ayarlar
        Font buttonFont = new Font("Arial", Font.BOLD, 16); // Arial yazı tipi, kalın ve 16 punto
        CustomerBTN.setFont(buttonFont);
        OrderBTN.setFont(buttonFont);
        AppInfoBTN.setFont(buttonFont);
        LogOutBTN.setFont(buttonFont);

        // Müşteri Formunu açan buton
        CustomerBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomerScreen(); // Müşteri ekranını açar
                dispose(); // Ana menü ekranını kapatır
            }
        });

        // Uygulama hakkında bilgi gösteren "Hakkında" diyalogunu açar
        AppInfoBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutDialog ab = new AboutDialog(); // Hakkında diyalog ekranını oluşturur
                ab.setVisible(true); // Görünür hale getirir
            }
        });

        // Çıkış yapar ve giriş ekranına döner
        LogOutBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginScreen l = new LoginScreen(); // Giriş ekranını açar
                l.setVisible(true); // Görünür hale getirir
                dispose(); // Ana menü ekranını kapatır
            }
        });

        // Sipariş işlemleri ekranını açar
        OrderBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderScreen os = new OrderScreen(); // Sipariş menüsünü açar
                os.setVisible(true); // Görünür hale getirir
                dispose(); // Ana menü ekranını kapatır
            }
        });
    }
}
