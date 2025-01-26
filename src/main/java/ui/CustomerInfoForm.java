package ui;

import methods.CustomerMethods;
import user.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// CustomerInfoForm sınıfı, bir müşterinin bilgilerini görüntülemek ve güncellemek için kullanılan bir formdur.
public class CustomerInfoForm extends JFrame {

    // Arayüz bileşenleri
    private JLabel CustomerNameLBL;   // Müşteri adı etiketi
    private JLabel CustomerSurnameLBL; // Müşteri soyadı etiketi
    private JLabel CustomerPhoneLBL;  // Müşteri telefon etiketi
    private JLabel CustomerAddressLBL; // Müşteri adres etiketi
    private JLabel CustomerBalanceLBL; // Müşteri bakiyesi etiketi
    private JTextField CustomerNameTF; // Müşteri adı girdi alanı
    private JTextField CustomerSurnameTF; // Müşteri soyadı girdi alanı
    private JTextField CustomerPhoneTF; // Müşteri telefon girdi alanı
    private JTextField CustomerAddressTF; // Müşteri adres girdi alanı
    private JTextField CustomerBalanceTF; // Müşteri bakiyesi girdi alanı
    private JButton AcceptBTN; // Kabul et butonu
    private JPanel CustomerInfoPanel; // Tüm bileşenleri içeren panel

    // Müşteri bilgileri
    private int customerID; // Müşteri kimliği
    private CustomerScreen customerScreen; // Ana müşteri ekranı referansı

    // Constructor (Yapıcı Metot)
    public CustomerInfoForm(int customerID, String name, String surname, String phone, String address, double balance, CustomerScreen customerScreen) {
        this.customerID = customerID; // Müşteri kimliği
        this.customerScreen = customerScreen; // Ana ekran referansı
        add(CustomerInfoPanel); // Paneli JFrame'e ekler
        setTitle("Müşteri Bilgileri"); // Pencere başlığı
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Pencere kapandığında yalnızca bu pencereyi kapatır
        setSize(400, 300); // Pencere boyutu
        setLocationRelativeTo(null); // Pencereyi ekranın ortasına yerleştirir

        // Bilgileri form alanlarına yerleştir
        CustomerNameTF.setText(name); // Ad alanına müşteri adı yerleştirilir
        CustomerSurnameTF.setText(surname); // Soyad alanına müşteri soyadı yerleştirilir
        CustomerPhoneTF.setText(phone); // Telefon alanına müşteri telefon numarası yerleştirilir
        CustomerAddressTF.setText(address); // Adres alanına müşteri adresi yerleştirilir
        CustomerBalanceTF.setText(String.valueOf(balance)); // Balance alanına müşteri bakiyesi yerleştirilir

        // Kabul et butonuna tıklama olayı
        AcceptBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCustomerInfo(); // Müşteri bilgilerini güncelle
            }
        });
    }

    // Müşteri bilgilerini güncelleyen metot
    private void updateCustomerInfo() {
        try {
            // Form alanlarından güncellenmiş bilgileri alır
            String updatedName = CustomerNameTF.getText();
            String updatedSurname = CustomerSurnameTF.getText();
            String updatedPhone = CustomerPhoneTF.getText();
            String updatedAddress = CustomerAddressTF.getText();
            double updatedBalance;

            // Bakiye alanını kontrol et ve doğrula
            try {
                updatedBalance = Double.parseDouble(CustomerBalanceTF.getText()); // Bakiye alanını al
                if (updatedBalance < 0) {
                    JOptionPane.showMessageDialog(this, "Bakiye negatif olamaz!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return; // İşlemi sonlandır
                }
                if (updatedBalance > 10000) { // MAX_BALANCE kontrolü
                    JOptionPane.showMessageDialog(this, "Bakiye 10,000 TL'den fazla olamaz!", "Hata", JOptionPane.ERROR_MESSAGE);
                    return; // İşlemi sonlandır
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Geçersiz bir bakiye girdiniz!", "Hata", JOptionPane.ERROR_MESSAGE);
                return; // İşlemi sonlandır
            }

            // Güncellenmiş müşteri nesnesi oluşturulur
            Customer updatedCustomer = new Customer(customerID, updatedName, updatedSurname, updatedPhone, updatedAddress, updatedBalance);

            // Veritabanında müşteri bilgilerini güncellemeye çalışır
            boolean success = CustomerMethods.updateCustomer(customerID, updatedCustomer);

            if (success) {
                // Güncelleme başarılıysa müşteri listesini günceller ve formu kapatır
                customerScreen.loadCustomers(); // Ana ekranda müşteri listesini yeniler
                dispose(); // Formu kapatır
            } else {
                // Güncelleme başarısızsa hata mesajı gösterir
                JOptionPane.showMessageDialog(this, "Müşteri bilgileri güncellenirken bir hata oluştu.");
            }
        } catch (Exception e) {
            // Beklenmedik bir hata durumunda kullanıcıya bilgi verir
            JOptionPane.showMessageDialog(this, "Bir hata oluştu: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
}
