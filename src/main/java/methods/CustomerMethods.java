package methods;

import user.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerMethods {

    // Veritabanı bağlantısı için gerekli bilgileri tanımlıyoruz.
    private static final String DB_URL = "jdbc:mysql://localhost:3306/OrderAutomationDB"; // Veritabanı URL'si
    private static final String DB_USER = "root"; // Veritabanı kullanıcı adı
    private static final String DB_PASSWORD = "password"; // Veritabanı şifresi

    // Veritabanından tüm müşterileri çeken metod
    public static ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customerList = new ArrayList<>(); // Müşterilerin saklanacağı liste
        String selectSQL = "SELECT id, name, surname, phone_number, address, balance FROM customers"; // SQL sorgusu

        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // Veritabanı bağlantısı oluşturuluyor
                PreparedStatement preparedStatement = conn.prepareStatement(selectSQL); // Sorgu hazırlanıyor
                ResultSet resultSet = preparedStatement.executeQuery() // Sorgu çalıştırılıyor ve sonuçlar alınıyor
        ) {
            // Sonuçlar üzerinden geçip her müşteri için Customer nesnesi oluşturuluyor
            while (resultSet.next()) {
                int customerID = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String phoneNumber = resultSet.getString("phone_number");
                String address = resultSet.getString("address");

                // Customer nesnesini oluştur ve listeye ekle
                double balance = resultSet.getDouble("balance");
                customerList.add(new Customer(customerID, name, surname, phoneNumber, address, balance));
            }

        } catch (SQLException e) {
            // Eğer hata oluşursa mesaj yazdırılıyor
            System.out.println("Müşteriler alınırken hata oluştu: " + e.getMessage());
        }

        return customerList; // Müşteri listesini döndür
    }

    // Yeni müşteri ekleyen metod
    public static void addCustomer(Customer customer) {
        String insertSQL = "INSERT INTO customers (name, surname, phone_number, address) VALUES (?, ?, ?, ?)"; // SQL ekleme sorgusu

        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // Veritabanı bağlantısı
                PreparedStatement preparedStatement = conn.prepareStatement(insertSQL) // Sorgu hazırlanıyor
        ) {
            // Parametreler sorguya atanıyor
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.executeUpdate(); // Sorgu çalıştırılıyor

        } catch (SQLException e) {
            // Hata durumunda mesaj yazdırılıyor
            System.out.println("Müşteri eklenirken hata oluştu: " + e.getMessage());
        }
    }

    // Müşteri bilgilerini güncelleyen metod
    public static boolean updateCustomer(int customerID, Customer updatedCustomer) {
        String updateSQL = "UPDATE customers SET name = ?, surname = ?, phone_number = ?, address = ?, balance = ? WHERE id = ?";

        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement preparedStatement = conn.prepareStatement(updateSQL)
        ) {
            preparedStatement.setString(1, updatedCustomer.getName());
            preparedStatement.setString(2, updatedCustomer.getSurname());
            preparedStatement.setString(3, updatedCustomer.getPhoneNumber());
            preparedStatement.setString(4, updatedCustomer.getAddress());
            preparedStatement.setDouble(5, updatedCustomer.getBalance());
            preparedStatement.setInt(6, customerID);

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // Müşteri silen metod
    public static void deleteCustomer(int customerID) {
        String deleteSQL = "DELETE FROM customers WHERE id = ?"; // SQL silme sorgusu

        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // Veritabanı bağlantısı
                PreparedStatement preparedStatement = conn.prepareStatement(deleteSQL) // Sorgu hazırlanıyor
        ) {
            preparedStatement.setInt(1, customerID); // Silinecek müşteri ID'si sorguya atanıyor
            int rowsAffected = preparedStatement.executeUpdate(); // Sorgu çalıştırılıyor

            if (rowsAffected > 0) {
                System.out.println("Müşteri başarıyla silindi."); // Eğer bir müşteri silindiyse
            } else {
                System.out.println("Müşteri bulunamadı."); // Silinecek müşteri bulunamadıysa
            }

        } catch (SQLException e) {
            // Hata durumunda mesaj yazdırılıyor
            System.out.println("Müşteri silinirken hata oluştu: " + e.getMessage());
        }
    }

    // Telefon numarasına göre müşteri arayan metod
    public static Customer getCustomerByPhoneNumber(String phoneNumber) {
        String query = "SELECT id, name, surname, phone_number, address, balance FROM customers WHERE phone_number = ?";

        try (
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement preparedStatement = conn.prepareStatement(query)
        ) {
            preparedStatement.setString(1, phoneNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int customerID = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String address = resultSet.getString("address");
                    double balance = resultSet.getDouble("balance");

                    // Yeni müşteri nesnesini oluşturup döndür
                    return new Customer(customerID, name, surname, phoneNumber, address, balance);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Müşteri bulunamazsa null döner
    }
}
