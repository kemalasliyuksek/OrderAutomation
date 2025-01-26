import ui.LoginScreen;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        
        // İşletim sistemine uygun görünüm ayarı
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Look and Feel ayarlanırken bir hata oluştu: " + e.getMessage());
        }

        // Swing'i iş parçacığı güvenliği kurallarına uygun bir şekilde başlatır.
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                LoginScreen ls = new LoginScreen();
                ls.setVisible(true);
            }
        });
    }
}
