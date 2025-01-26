package ui;

import methods.OrderMethods;
import methods.CustomerMethods;
import order.Order;
import order.OrderItem;
import user.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// OrderScreen sınıfı, sipariş işlemleri için kullanılan bir Swing arayüzüdür.
public class OrderScreen extends JFrame {

    // Swing bileşenleri
    private JPanel OrderPanel; // Ana panel
    private JLabel LastOrdersLBL; // Son siparişleri görüntülemek için başlık etiketi
    private JTabbedPane OrderTabbedPane; // İşlem adımları için sekmeli panel
    private JPanel FirstOperationTab; // İlk işlem sekmesi (Müşteri doğrulama)
    private JPanel SecondOperationTab; // İkinci işlem sekmesi (Sipariş ekleme)
    private JPanel LastOperationTab; // Son işlem sekmesi (Ödeme)
    private JLabel PhoneNoLBL; // Telefon numarası etiketi
    private JTextField PhoneNoTF; // Telefon numarası girdi alanı
    private JButton PhoneNoCheckBTN; // Telefon numarası kontrol butonu
    private JLabel CheckResultLBL; // Telefon kontrol sonucu etiketi
    private JButton AddUserOrContinueBTN; // Yeni müşteri ekleme veya devam etme butonu
    private JLabel BurgerMenuTitle; // Burger menüsü başlığı
    private JLabel ComboMealMenuTitle; // Menü başlığı
    private JLabel DrinkTitle; // İçecek başlığı
    private JCheckBox KetchupCKB; // Ketçap seçeneği
    private JCheckBox MayonnaiseCKB; // Mayonez seçeneği
    private JCheckBox CheeseCKB; // Peynir seçeneği
    private JCheckBox TomatoCKB; // Domates seçeneği
    private JCheckBox PickleCKB; // Turşu seçeneği
    private JCheckBox LettuceCKB; // Marul seçeneği
    private JCheckBox OnionCKB; // Soğan seçeneği
    private JCheckBox ExtraCheeseCKB; // Ekstra peynir seçeneği
    private JRadioButton SmallDrinkRB; // Küçük içecek seçeneği
    private JRadioButton MiddleDrinkRB; // Orta içecek seçeneği
    private JRadioButton BigDrinkRB; // Büyük içecek seçeneği
    private JSpinner DrinkSpinner; // İçecek adedi seçmek için spinner
    private JSpinner BurgerSpinner; // Burger adedi seçmek için spinner
    private JSpinner ComboMealMenuSpinner; // Menü adedi seçmek için spinner
    private JLabel OrderContentTitle; // Sipariş içeriği başlığı
    private JList<String> OrderContentList; // Sipariş içeriği listeleme alanı
    private JButton DeleteSelectContentBTN; // Seçili içeriği silme butonu
    private JButton ClearOrderContentBTN; // Tüm içeriği temizleme butonu
    private JButton OrderContinueBTN; // Siparişi ödeme adımına geçirme butonu
    private JLabel CustomerInfoLBL; // Müşteri bilgisi etiketi
    private JTextField CustomerInfoTF; // Müşteri bilgisi girdi alanı
    private JLabel CustomerBalanceLBL; // Müşteri bakiyesi etiketi
    private JTextField CustomerBalanceTF; // Müşteri bakiyesi girdi alanı
    private JLabel OrderContentLBL; // Sipariş içeriği başlığı
    private JList<String> OrderContentToBePaidList; // Ödeme adımındaki sipariş içeriği listesi
    private JLabel AmountToBePaidLBL; // Ödenecek tutar etiketi
    private JTextField AmountToBePaidTF; // Ödenecek tutar girdi alanı
    private JButton PayBTN; // Ödeme butonu
    private JButton AddBurgerBTN; // Burger ekleme butonu
    private JButton AddMenuBTN; // Menü ekleme butonu
    private JButton AddDrinkBTN; // İçecek ekleme butonu
    private JScrollPane LastOrdersScrollPane; // Son siparişler için kaydırma paneli
    private JTable LastOrdersTable; // Son siparişleri göstermek için tablo
    private JButton GoBackBTN;
    private JCheckBox ExtraBigDrinkCKB;

    // Sipariş bilgilerini yönetmek için modeller ve müşteri referansı
    private final DefaultTableModel lastOrdersTableModel; // Son siparişler için tablo modeli
    private final DefaultListModel<String> orderContentModel; // Sipariş içeriği için model
    private final ArrayList<OrderItem> orderItems; // Sipariş öğeleri
    private Customer currentCustomer; // Şu anki müşteri

    // Constructor (Yapıcı Metot)
    public OrderScreen() {
        add(OrderPanel); // Ana paneli pencereye ekle

        setTitle("Sipariş Menüsü"); // Pencere başlığı
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Kapatma işlemi
        setSize(1200, 600); // Pencere boyutu
        setLocationRelativeTo(null); // Pencereyi ekranın merkezine yerleştirir

        // Tablo modeli oluştur ve sütunları ayarla
        lastOrdersTableModel = new DefaultTableModel(new Object[]{"ID", "Müşteri", "Tarih", "Tutar", "Ödeme"}, 0);
        LastOrdersTable.setModel(lastOrdersTableModel);
        configureLastOrdersTable(); // Tabloyu yapılandır

        // Modelleri oluştur
        orderContentModel = new DefaultListModel<>();
        OrderContentList.setModel(orderContentModel); // Sipariş içeriği listesine model ata
        orderItems = new ArrayList<>(); // Sipariş öğeleri listesi

        // Radio Buttonları bir ButtonGroup'a ekle
        ButtonGroup drinkGroup = new ButtonGroup();
        drinkGroup.add(SmallDrinkRB); // Küçük içecek
        drinkGroup.add(MiddleDrinkRB); // Orta içecek
        drinkGroup.add(BigDrinkRB); // Büyük içecek

        loadLastOrders(); // Son siparişleri yükle

        // Telefon numarasını kontrol etme butonuna işlev ekle
        PhoneNoCheckBTN.addActionListener(e -> checkPhoneNumber());

        // Kullanıcı ekle veya devam et butonuna işlev ekle
        AddUserOrContinueBTN.addActionListener(e -> handleAddUserOrContinue());

        // Sipariş öğesi ekleme butonlarına işlev ekle
        AddBurgerBTN.addActionListener(e -> addBurgerToOrder());
        AddDrinkBTN.addActionListener(e -> addDrinkToOrder());
        AddMenuBTN.addActionListener(e -> addMenuToOrder());

        // Sipariş içeriğini temizleme ve silme butonlarına işlev ekle
        DeleteSelectContentBTN.addActionListener(e -> deleteSelectedOrderItem());
        ClearOrderContentBTN.addActionListener(e -> clearOrderContent());

        // Ödeme adımına geçiş butonuna işlev ekle
        OrderContinueBTN.addActionListener(e -> proceedToLastOperation());

        // Ödeme tamamlama butonuna işlev ekle
        PayBTN.addActionListener(e -> finalizeOrder());
        GoBackBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainScreen mainScreen = new MainScreen();
                mainScreen.setVisible(true);
                dispose();
            }
        });
    }

    // Tabloyu yapılandıran metot
    private void configureLastOrdersTable() {
        LastOrdersTable.setRowHeight(30); // Satır yüksekliğini ayarla
        LastOrdersTable.getColumnModel().getColumn(0).setPreferredWidth(50); // ID sütunu
        LastOrdersTable.getColumnModel().getColumn(1).setPreferredWidth(170); // Müşteri sütunu
        LastOrdersTable.getColumnModel().getColumn(2).setPreferredWidth(170); // Tarih sütunu
        LastOrdersTable.getColumnModel().getColumn(3).setPreferredWidth(80); // Tutar sütunu
        LastOrdersTable.getColumnModel().getColumn(4).setPreferredWidth(100); // Ödeme sütunu
    }

    // Son siparişleri tabloya yükleyen metot
    private void loadLastOrders() {
        lastOrdersTableModel.setRowCount(0); // Tabloyu temizle
        ArrayList<Order> orders = (ArrayList<Order>) OrderMethods.getAllOrders(); // Tüm siparişleri al
        for (Order order : orders) {
            lastOrdersTableModel.addRow(new Object[]{
                    order.getOrderID(),
                    order.getCustomer().getName() + " " + order.getCustomer().getSurname(),
                    order.getOrderDate(),
                    order.getTotalPrice() + " TL",
                    order.getStatus()
            });
        }
    }

    // Telefon numarasını kontrol eden metot
    private void checkPhoneNumber() {
        String phoneNumber = PhoneNoTF.getText(); // Telefon numarasını al
        currentCustomer = CustomerMethods.getCustomerByPhoneNumber(phoneNumber); // Müşteriyi veritabanından getir

        if (currentCustomer != null) {
            CheckResultLBL.setText(String.format("Müşteri: %s %s | Bakiye: %.2f",
                    currentCustomer.getName(), currentCustomer.getSurname(), currentCustomer.getBalance()));
            AddUserOrContinueBTN.setText("Devam Et"); // Buton metnini güncelle
        } else {
            CheckResultLBL.setText("Kullanıcı bulunamadı. Ekleme işlemine devam edebilirsiniz.");
            AddUserOrContinueBTN.setText("Kullanıcı Ekle"); // Buton metnini güncelle
        }

        CheckResultLBL.setVisible(true); // Sonuç etiketini görünür yap
        AddUserOrContinueBTN.setVisible(true); // Butonu görünür yap
    }

    // Kullanıcı ekleme veya devam etme işlemi
    private void handleAddUserOrContinue() {
        if (AddUserOrContinueBTN.getText().equals("Devam Et")) {
            OrderTabbedPane.setSelectedIndex(1); // Sipariş adımına geç
        } else {
            String name = JOptionPane.showInputDialog(this, "Müşteri Adı:");
            String surname = JOptionPane.showInputDialog(this, "Müşteri Soyadı:");
            String address = JOptionPane.showInputDialog(this, "Adres:");
            double initialBalance = Double.parseDouble(JOptionPane.showInputDialog(this, "Başlangıç Bakiyesi:"));

            Customer newCustomer = new Customer(0, name, surname, PhoneNoTF.getText(), address, initialBalance);
            CustomerMethods.addCustomer(newCustomer);

            JOptionPane.showMessageDialog(this, "Yeni müşteri başarıyla eklendi.");
        }
    }

    // Burger siparişi ekleyen metot
    private void addBurgerToOrder() {
        boolean cheese = CheeseCKB.isSelected();
        boolean tomato = TomatoCKB.isSelected();
        boolean lettuce = LettuceCKB.isSelected();
        boolean pickle = PickleCKB.isSelected();
        boolean onion = OnionCKB.isSelected();
        boolean ketchup = KetchupCKB.isSelected();
        boolean mayonnaise = MayonnaiseCKB.isSelected();
        int quantity = (int) BurgerSpinner.getValue();

        OrderItem item = OrderMethods.createBurger(cheese, tomato, lettuce, pickle, onion, ketchup, mayonnaise, quantity);

        StringBuilder selectedIngredients = new StringBuilder();
        if (cheese) selectedIngredients.append("Pey. ");
        if (tomato) selectedIngredients.append("Dom. ");
        if (lettuce) selectedIngredients.append("Mar. ");
        if (pickle) selectedIngredients.append("Tur. ");
        if (onion) selectedIngredients.append("Soğ. ");
        if (ketchup) selectedIngredients.append("Ket. ");
        if (mayonnaise) selectedIngredients.append("May. ");

        String displayText = String.format("Burger (%s) | Adet: %d", selectedIngredients.toString().trim(), quantity);
        orderItems.add(item);
        orderContentModel.addElement(displayText);
    }

    // İçecek siparişi ekleyen metot
    private void addDrinkToOrder() {
        String size = SmallDrinkRB.isSelected() ? "Küçük" : MiddleDrinkRB.isSelected() ? "Orta" : "Büyük";
        int quantity = (int) DrinkSpinner.getValue();

        OrderItem item = OrderMethods.createDrink(size, quantity);
        orderItems.add(item);
        orderContentModel.addElement(item.getFood().getDetails() + " | Adet: " + quantity);
    }

    // Menü siparişi ekleyen metot
    private void addMenuToOrder() {
        int quantity = (int) ComboMealMenuSpinner.getValue(); // Menü miktarı
        boolean extraBurger = ExtraCheeseCKB.isSelected(); // Ekstra burger seçimi
        boolean bigDrink = BigDrinkRB.isSelected(); // Büyük içecek seçimi

        // Sipariş öğesini listeye ekle
        for (int i = 0; i < quantity; i++) {
            // OrderMethods'dan Combo Meal sipariş öğesi oluştur
            OrderItem item = OrderMethods.createComboMeal(extraBurger, bigDrink, quantity);

            orderItems.add(item);

            // Ekstra detayları kullanıcıya göster
            StringBuilder selectedIngredients = new StringBuilder("Pey. Dom. Marul. Soğan.");
            if (extraBurger) {
                selectedIngredients.append(" + Ekstra Peynir");
            }
            if (bigDrink) {
                selectedIngredients.append(" + Büyük İçecek");
            }

            String displayText = String.format("Combo Menü (%s) | Adet: 1 | %s", item.getFood().getName(), selectedIngredients);
            orderContentModel.addElement(displayText);
        }
    }


    // Seçili sipariş öğesini silen metot
    private void deleteSelectedOrderItem() {
        int selectedIndex = OrderContentList.getSelectedIndex();
        if (selectedIndex != -1) {
            orderItems.remove(selectedIndex);
            orderContentModel.remove(selectedIndex);
        }
    }

    // Sipariş içeriğini temizleyen metot
    private void clearOrderContent() {
        orderItems.clear();
        orderContentModel.clear();
    }

    // Ödeme adımına geçiş yapan metot
    private void proceedToLastOperation() {
        OrderTabbedPane.setSelectedIndex(2);
        CustomerInfoTF.setText(currentCustomer.getName() + " " + currentCustomer.getSurname());
        CustomerBalanceTF.setText(String.valueOf(currentCustomer.getBalance()));

        DefaultListModel<String> toBePaidModel = new DefaultListModel<>();
        double totalAmount = 0;
        for (OrderItem item : orderItems) {
            toBePaidModel.addElement(item.getOrderItemDetails());
            totalAmount += item.getTotalPrice();
        }
        OrderContentToBePaidList.setModel(toBePaidModel);
        AmountToBePaidTF.setText(String.valueOf(totalAmount));
    }

    // Siparişi tamamlayan metot
    private void finalizeOrder() {
        double totalAmount = Double.parseDouble(AmountToBePaidTF.getText());
        if (currentCustomer.getBalance() >= totalAmount) {
            currentCustomer.setBalance(currentCustomer.getBalance() - totalAmount);
            CustomerMethods.updateCustomer(currentCustomer.getCustomerID(), currentCustomer);

            Order order = new Order(currentCustomer);
            order.setTotalPrice(totalAmount);
            order.setStatus("Tamamlandı");
            OrderMethods.addOrder(order);

            JOptionPane.showMessageDialog(this, "Ödeme başarıyla tamamlandı.");
            loadLastOrders();
            resetPaymentComponents();
        } else {
            // Eğer bakiye yetersizse
            int option = JOptionPane.showConfirmDialog(this, "Bakiye yetersiz! Bakiye eklemek ister misiniz?", "Yetersiz Bakiye", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                // Bakiye ekleme işlemi
                try {
                    double additionalBalance = Double.parseDouble(JOptionPane.showInputDialog(this, "Eklenecek bakiye miktarı:"));
                    if (additionalBalance <= 0) {
                        JOptionPane.showMessageDialog(this, "Lütfen geçerli bir bakiye giriniz!", "Hata", JOptionPane.ERROR_MESSAGE);
                        return; // Geçersiz bakiye için işlem iptal edilir
                    }

                    currentCustomer.setBalance(currentCustomer.getBalance() + additionalBalance); // Bakiye ekle
                    CustomerMethods.updateCustomer(currentCustomer.getCustomerID(), currentCustomer); // Müşteriyi güncelle
                    CustomerBalanceTF.setText(String.valueOf(currentCustomer.getBalance()));
                    JOptionPane.showMessageDialog(this, "Bakiye başarıyla eklendi. Lütfen ödemeyi tekrar deneyin.");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Geçersiz bir değer girdiniz. İşlem iptal edildi.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Siparişi iptal et
                Order order = new Order(currentCustomer);
                order.setTotalPrice(totalAmount);
                order.setStatus("İptal Edildi");
                OrderMethods.addOrder(order); // Siparişi iptal olarak kaydet
                JOptionPane.showMessageDialog(this, "Sipariş iptal edildi.");
                loadLastOrders(); // Son siparişleri güncelle
                resetPaymentComponents(); // Ekranı sıfırla (Ödeme bileşenlerini temizle)
            }
        }
    }

    // Ödeme adımındaki tüm bileşenleri sıfırlayan metot
    private void resetPaymentComponents() {
        currentCustomer = null;
        PhoneNoTF.setText("");
        CheckResultLBL.setVisible(false);
        AddUserOrContinueBTN.setVisible(false);

        CheeseCKB.setSelected(false);
        TomatoCKB.setSelected(false);
        LettuceCKB.setSelected(false);
        PickleCKB.setSelected(false);
        OnionCKB.setSelected(false);
        KetchupCKB.setSelected(false);
        MayonnaiseCKB.setSelected(false);
        ExtraCheeseCKB.setSelected(false);

        BurgerSpinner.setValue(0);
        ComboMealMenuSpinner.setValue(0);
        DrinkSpinner.setValue(0);

        SmallDrinkRB.setSelected(false);
        MiddleDrinkRB.setSelected(false);
        BigDrinkRB.setSelected(false);

        CustomerInfoTF.setText("");
        CustomerBalanceTF.setText("");
        AmountToBePaidTF.setText("");
        OrderContentToBePaidList.setModel(new DefaultListModel<>());

        OrderContentList.clearSelection();
        orderItems.clear();
        orderContentModel.clear();

        OrderTabbedPane.setSelectedIndex(0);
    }
}
