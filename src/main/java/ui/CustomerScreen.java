package ui;

import methods.CustomerMethods;
import user.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;

// CustomerScreen sınıfı, müşteri işlemlerini yöneten bir Swing tabanlı arayüzdür.
public class CustomerScreen extends JFrame {
    private JPanel CustomerPanel;         // Tüm bileşenleri içeren panel
    private JButton CustomerAddBTN;      // Müşteri ekleme butonu
    private JButton CustomerDeleteBTN;   // Müşteri silme butonu
    private JButton CustomerInfoBTN;     // Müşteri bilgilerini düzenleme butonu
    private JButton BackMainPanel;       // Ana menüye dönüş butonu
    private JTable CustomerTable;        // Müşteri listesini görüntüleyen tablo
    private JScrollPane CustomerScrollPane; // Tabloyu kaydırılabilir yapmak için kullanılan bileşen

    private final DefaultTableModel tableModel; // Tablonun modelini yönetmek için kullanılır

    // Constructor (Yapıcı Metot)
    public CustomerScreen() {
        add(CustomerPanel); // Ana paneli pencereye ekler
        setTitle("Müşteri İşlemleri"); // Pencere başlığını ayarlar
        setSize(800, 350); // Pencere boyutunu ayarlar
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Pencere kapandığında yalnızca bu pencereyi kapatır
        setLocationRelativeTo(null); // Pencereyi ekranın ortasına yerleştirir

        // JTable için model oluşturma
        tableModel = new DefaultTableModel(new String[]{"ID", "Ad", "Soyad", "Telefon", "Adres", "Bakiye"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tüm hücreler düzenlenemez
            }
        };
        CustomerTable.setModel(tableModel); // Modeli tabloya bağlar
        CustomerScrollPane.setViewportView(CustomerTable); // Tabloyu kaydırma paneline ekle

        // Tablonun görünümünü ayarla
        configureCustomerTable();

        loadCustomers(); // Mevcut müşteri verilerini yükler

        // Butonlara olay dinleyicileri ekler
        CustomerAddBTN.addActionListener(e -> addCustomer());
        CustomerDeleteBTN.addActionListener(e -> deleteCustomer());
        CustomerInfoBTN.addActionListener(e -> showCustomerInfo());
        BackMainPanel.addActionListener(e -> {
            // Ana menüye geri dön
            MainScreen mainScreen = new MainScreen();
            mainScreen.setVisible(true);
            dispose();
        });

        setVisible(true); // Pencereyi görünür hale getirir
    }

    // Tablonun estetik görünümünü ayarlayan metot
    private void configureCustomerTable() {
        CustomerTable.setRowHeight(30); // Satır yüksekliğini ayarla
        CustomerTable.setFillsViewportHeight(true); // Tablonun kaydırma panelini tamamen doldurmasını sağlar

        // Tablo başlıklarını özelleştir
        JTableHeader header = CustomerTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14)); // Başlık yazı tipi
        header.setBackground(Color.LIGHT_GRAY); // Başlık arka plan rengi
        header.setForeground(Color.BLACK); // Başlık yazı rengi

        // Sütun genişliklerini ayarla
        TableColumnModel columnModel = CustomerTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);  // ID sütunu genişliği
        columnModel.getColumn(1).setPreferredWidth(150); // Ad sütunu genişliği
        columnModel.getColumn(2).setPreferredWidth(150); // Soyad sütunu genişliği
        columnModel.getColumn(3).setPreferredWidth(120); // Telefon sütunu genişliği
        columnModel.getColumn(4).setPreferredWidth(200); // Adres sütunu genişliği
        columnModel.getColumn(5).setPreferredWidth(100); // Bakiye sütunu genişliği

        // Hücrelerin hizalamasını ayarla
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Ortaya hizala
        CustomerTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // ID sütunu
        CustomerTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // Telefon sütunu
        CustomerTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer); // Bakiye sütunu

        // Alternatif satır renkleri için hücre renderer
        CustomerTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    component.setBackground(row % 2 == 0 ? Color.WHITE : new Color(240, 240, 240)); // Alternatif satır renkleri
                } else {
                    component.setBackground(new Color(184, 207, 229)); // Seçili satır rengi
                }
                return component;
            }
        });
    }

    // Tablodaki müşteri verilerini yükleyen metot
    public void loadCustomers() {
        // Mevcut tabloyu temizle
        tableModel.setRowCount(0);

        // Müşteri verilerini veritabanından al ve tabloya ekle
        ArrayList<Customer> customers = CustomerMethods.getAllCustomers();
        for (Customer customer : customers) {
            tableModel.addRow(new Object[]{ // Tabloya bir satır ekler
                    customer.getCustomerID(),
                    customer.getName(),
                    customer.getSurname(),
                    customer.getPhoneNumber(),
                    customer.getAddress(),
                    customer.getBalance()
            });
        }
    }

    // Yeni müşteri ekleme metodu
    private void addCustomer() {
        // Kullanıcıdan müşteri bilgilerini almak için dialog kutuları
        String name = JOptionPane.showInputDialog(this, "Müşteri Adı:");
        String surname = JOptionPane.showInputDialog(this, "Müşteri Soyadı:");
        String phoneNumber = JOptionPane.showInputDialog(this, "Telefon Numarası:");
        String address = JOptionPane.showInputDialog(this, "Adres:");

        if (name != null && surname != null && phoneNumber != null && address != null) {
            // Yeni müşteri nesnesi oluştur ve veritabanına ekle
            Customer customer = new Customer(0, name, surname, phoneNumber, address);
            CustomerMethods.addCustomer(customer);
            loadCustomers(); // Tablodaki verileri güncelle
        }
    }

    // Seçilen müşteriyi silen metot
    private void deleteCustomer() {
        int selectedRow = CustomerTable.getSelectedRow(); // Seçili satırı alır
        if (selectedRow != -1) { // Eğer bir satır seçiliyse
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Seçili müşteriyi silmek istediğinize emin misiniz?",
                    "Onay",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int customerID = (int) tableModel.getValueAt(selectedRow, 0); // Müşteri ID'sini alır
                CustomerMethods.deleteCustomer(customerID); // Müşteriyi veritabanından sil
                loadCustomers(); // Tablodaki verileri güncelle
            }
        } else {
            JOptionPane.showMessageDialog(this, "Lütfen bir müşteri seçin.");
        }
    }

    // Seçili müşterinin bilgilerini gösteren ve düzenleme formunu açan metot
    private void showCustomerInfo() {
        int selectedRow = CustomerTable.getSelectedRow(); // Seçili satırı alır
        if (selectedRow != -1) { // Eğer bir satır seçiliyse
            int customerID = (int) tableModel.getValueAt(selectedRow, 0);
            String name = (String) tableModel.getValueAt(selectedRow, 1);
            String surname = (String) tableModel.getValueAt(selectedRow, 2);
            String phoneNumber = (String) tableModel.getValueAt(selectedRow, 3);
            String address = (String) tableModel.getValueAt(selectedRow, 4);
            double balance = (double) tableModel.getValueAt(selectedRow, 5); // Balance sütununu al

            // CustomerInfoForm'a yönlendirme
            CustomerInfoForm customerInfoForm = new CustomerInfoForm(customerID, name, surname, phoneNumber, address, balance, this);
            customerInfoForm.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Lütfen bir müşteri seçin.");
        }
    }
}
