package methods;

import order.Order;
import order.OrderItem;
import product.Burger;
import product.ComboMeal;
import product.Drink;
import user.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// OrderMethods sınıfı, siparişlerle ilgili veritabanı işlemleri ve işlevleri içerir.
public class OrderMethods {

    // Veritabanı bağlantı bilgileri
    private static final String DB_URL = "jdbc:mysql://localhost:3306/OrderAutomationDB"; // Veritabanı URL'si
    private static final String DB_USER = "root"; // Veritabanı kullanıcı adı
    private static final String DB_PASSWORD = "password"; // Veritabanı şifresi

    // Tüm siparişleri veritabanından alan metot
    public static List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>(); // Siparişlerin tutulacağı liste
        String query = "SELECT o.id, c.name, c.surname, o.order_date, o.total_price, o.status " +
                "FROM orders o JOIN customers c ON o.customer_id = c.id ORDER BY o.id DESC"; // SQL sorgusu

        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // Veritabanı bağlantısı
                PreparedStatement stmt = conn.prepareStatement(query); // Sorgu hazırlanıyor
                ResultSet rs = stmt.executeQuery() // Sorgu çalıştırılıyor
        ) {
            while (rs.next()) {
                // Sonuçlardan sipariş bilgilerini al
                int orderId = rs.getInt("id");
                String customerName = rs.getString("name");
                String customerSurname = rs.getString("surname");
                Timestamp orderDate = rs.getTimestamp("order_date");
                double totalPrice = rs.getDouble("total_price");
                String status = rs.getString("status");

                // Müşteri ve sipariş nesneleri oluştur
                Customer customer = new Customer(orderId, customerName, customerSurname, "", "", 0.0);
                Order order = new Order(customer);
                order.setOrderID(orderId);
                order.setOrderDate(orderDate);
                order.setTotalPrice(totalPrice);
                order.setStatus(status);

                // Siparişi listeye ekle
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Hata durumunda detayları yazdır
        }

        return orders; // Sipariş listesini döndür
    }

    // Yeni bir sipariş ekleme metodu
    public static boolean addOrder(Order order) {
        if (order.getOrderDate() == null) {
            order.setOrderDate(new java.util.Date()); // Tarihi otomatik olarak ayarla
        }

        String query = "INSERT INTO orders (customer_id, order_date, total_price, status) VALUES (?, ?, ?, ?)"; // SQL sorgusu

        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // Veritabanı bağlantısı
                PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS) // Sorgu hazırlanıyor
        ) {
            // Sorgu parametrelerini ayarla
            stmt.setInt(1, order.getCustomer().getCustomerID());
            stmt.setTimestamp(2, new Timestamp(order.getOrderDate().getTime()));
            stmt.setDouble(3, order.getTotalPrice());
            stmt.setString(4, order.getStatus());

            int affectedRows = stmt.executeUpdate(); // Sorguyu çalıştır
            if (affectedRows > 0) {
                // Yeni eklenen siparişin ID'sini al
                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    order.setOrderID(keys.getInt(1)); // Sipariş ID'sini ayarla
                }
                return true; // Sipariş başarıyla eklendi
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Hata durumunda detayları yazdır
        }

        return false; // Sipariş eklenemedi
    }

    // Sipariş durumunu güncelleme metodu
    public static boolean updateOrderStatus(int orderId, String newStatus) {
        String query = "UPDATE orders SET status = ? WHERE id = ?"; // SQL sorgusu

        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // Veritabanı bağlantısı
                PreparedStatement stmt = conn.prepareStatement(query) // Sorgu hazırlanıyor
        ) {
            // Sorgu parametrelerini ayarla
            stmt.setString(1, newStatus);
            stmt.setInt(2, orderId);

            return stmt.executeUpdate() > 0; // Güncellenen satır sayısını kontrol et
        } catch (SQLException e) {
            e.printStackTrace(); // Hata durumunda detayları yazdır
        }

        return false; // Güncelleme başarısız
    }

    // Burger sipariş öğesi oluşturma metodu
    public static OrderItem createBurger(boolean cheese, boolean tomato, boolean lettuce, boolean onion, boolean ketchup, boolean mayonnaise, boolean b, int quantity) {
        // ProductMethods sınıfındaki metodu kullanarak burger oluştur
        Burger burger = ProductMethods.createBurger(cheese, tomato, lettuce, onion, ketchup, mayonnaise);
        return new OrderItem(burger, quantity, ""); // Sipariş öğesi döndür
    }

    // İçecek sipariş öğesi oluşturma metodu
    public static OrderItem createDrink(String size, int quantity) {
        // ProductMethods sınıfındaki metodu kullanarak içecek oluştur
        Drink drink = ProductMethods.createDrink(size);
        return new OrderItem(drink, quantity, ""); // Sipariş öğesi döndür
    }

    // Menü sipariş öğesi oluşturma metodu
    public static OrderItem createComboMeal(boolean extraBurger, boolean drinkCola, int quantity) {
        // ProductMethods sınıfındaki metodu kullanarak ComboMeal oluştur
        ComboMeal comboMeal = ProductMethods.createComboMeal(extraBurger, drinkCola);
        return new OrderItem(comboMeal, quantity, ""); // Sipariş öğesi döndür
    }
}
