package ui;

import javax.swing.*;
import java.awt.*;

// AboutDialog sınıfı, "Hakkında" penceresini oluşturmak için kullanılan bir JDialog sınıfıdır.
public class AboutDialog extends JDialog {

    private JPanel AboutPanel; // Pencerenin içeriğini düzenlemek için kullanılan panel
    private JLabel developerLBL; // Geliştirici bilgisi için etiket
    private JLabel versionLBL;   // Versiyon bilgisi için etiket
    private JLabel dateLBL;      // Tarih bilgisi için etiket
    private JLabel descriptionLBL; // Açıklama metni için etiket

    // Constructor (Yapıcı Metot)
    public AboutDialog() {

        // Pencere başlığı ve boyutunu ayarlar
        setTitle("Hakkında");
        setSize(500, 250);
        setLocationRelativeTo(null); // Pencereyi ekranın ortasına yerleştirir
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Pencere kapatıldığında yalnızca bu pencereyi kapatır

        // Paneli oluşturur ve düzeni ayarlar
        AboutPanel = new JPanel();
        AboutPanel.setLayout(new GridLayout(4, 1, 5, 5)); // 4 satır, 1 sütun, her hücre arasında 5px boşluk
        add(AboutPanel); // Paneli diyalog penceresine ekler

        // Etiketleri oluşturur ve içeriklerini ayarlar
        developerLBL = new JLabel("Geliştirici: Kemal Aslıyüksek", JLabel.CENTER); // Geliştirici adı etiketi
        developerLBL.setFont(new Font("Arial", Font.PLAIN, 14)); // Yazı tipi ve boyutu

        versionLBL = new JLabel("Versiyon: 1.0.0", JLabel.CENTER); // Versiyon bilgisi etiketi
        versionLBL.setFont(new Font("Arial", Font.PLAIN, 14));

        dateLBL = new JLabel("Tarih: 2024", JLabel.CENTER); // Tarih bilgisi etiketi
        dateLBL.setFont(new Font("Arial", Font.PLAIN, 14));

        // Açıklama metni için HTML kullanılarak ortalama ve çok satırlı görünüm sağlanır
        descriptionLBL = new JLabel(
                "<html><div style='text-align: center;'>Bu uygulama İleri Nesne Tabanlı Programlama dersi projesi için<br> geliştirilen Swing tabanlı bir masaüstü uygulamasıdır.</div></html>",
                JLabel.CENTER
        );
        descriptionLBL.setFont(new Font("Arial", Font.PLAIN, 14));

        // Etiketleri panele ekler
        AboutPanel.add(developerLBL);
        AboutPanel.add(versionLBL);
        AboutPanel.add(dateLBL);
        AboutPanel.add(descriptionLBL);
    }
}
