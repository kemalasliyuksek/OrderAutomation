package user;

import javax.swing.*;

// Customer sınıfı, bir müşteriyi temsil eder ve müşteri ile ilgili verileri ve işlemleri yönetir.
public class Customer {
    private int customerID; // Müşterinin benzersiz kimliği (veritabanında kullanılan ID)
    private String name; // Müşteri adı
    private String surname; // Müşteri soyadı
    private String phoneNumber; // Müşteri telefon numarası
    private String address; // Müşteri adresi
    private double balance; // Müşteri bakiyesi

    private static final double MAX_BALANCE = 10000.0; // Bakiye için maksimum sınır

    // Constructor (Yapıcı Metot) - Balance olmadan
    public Customer(int customerID, String name, String surname, String phoneNumber, String address) {
        this(customerID, name, surname, phoneNumber, address, 0.0); // Varsayılan bakiye 0.0
    }

    // Constructor (Yapıcı Metot) - Balance ile
    public Customer(int customerID, String name, String surname, String phoneNumber, String address, double balance) {
        this.customerID = customerID; // Müşteri ID'sini ayarlar
        this.name = name; // Müşteri adını ayarlar
        this.surname = surname; // Müşteri soyadını ayarlar
        this.phoneNumber = phoneNumber; // Müşteri telefon numarasını ayarlar
        this.address = address; // Müşteri adresini ayarlar
        setBalance(balance); // Müşteri bakiyesini ayarlar
    }

    // Getter ve Setter metodları:
    // Müşteri bilgilerine erişim ve bu bilgilerin değiştirilmesi için kullanılır.

    // Müşteri ID'sini döndürür
    public int getCustomerID() {
        return customerID;
    }

    // Müşteri ID'sini ayarlar
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    // Müşteri adını döndürür
    public String getName() {
        return name;
    }

    // Müşteri adını ayarlar
    public void setName(String name) {
        this.name = name;
    }

    // Müşteri soyadını döndürür
    public String getSurname() {
        return surname;
    }

    // Müşteri soyadını ayarlar
    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Müşteri telefon numarasını döndürür
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Müşteri telefon numarasını ayarlar
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Müşteri adresini döndürür
    public String getAddress() {
        return address;
    }

    // Müşteri adresini ayarlar
    public void setAddress(String address) {
        this.address = address;
    }

    // Müşteri bakiyesini döndürür
    public double getBalance() {
        return balance;
    }

    // Müşteri bakiyesini ayarlar
    public void setBalance(double balance) {
        if (balance < 0) {
            // Negatif bakiye hatası için bir uyarı diyalogu göster
            JOptionPane.showMessageDialog(null, "Bakiye negatif olamaz!", "Hata", JOptionPane.ERROR_MESSAGE);
            return; // İşlemi sonlandır
        }
        if (balance > MAX_BALANCE) {
            // Maksimum bakiye hatası için bir uyarı diyalogu göster
            JOptionPane.showMessageDialog(null, "Bakiye " + MAX_BALANCE + " TL'den fazla olamaz!", "Hata", JOptionPane.ERROR_MESSAGE);
            return; // İşlemi sonlandır
        }
        this.balance = balance; // Eğer değer geçerliyse bakiyeyi ayarla
    }



    // Müşteri bilgilerini düzgün bir formatta döndürür
    @Override
    public String toString() {
        // % kullanarak belirli genişliklerle hizalanmış bir metin formatı döner.
        return String.format("%-20s %-20s %-15s %-30s %-10.2f", name, surname, phoneNumber, address, balance);
    }
}
