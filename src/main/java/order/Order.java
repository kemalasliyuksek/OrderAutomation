package order;

import user.Customer;
import java.util.ArrayList;
import java.util.Date;

public class Order {
    // Sipariş özellikleri
    private int orderID; // Siparişin benzersiz kimliği
    private Customer customer; // Siparişle ilişkilendirilmiş müşteri
    private Date orderDate; // Sipariş tarihi
    private double totalPrice; // Siparişin toplam fiyatı
    private String status; // Sipariş durumu (örneğin: "Bekleniyor", "Tamamlandı")
    private ArrayList<OrderItem> items; // Sipariş öğelerinin listesi

    // Constructor (sipariş nesnesi oluşturucu)
    public Order(Customer customer) {
        this.customer = customer; // Siparişle ilişkilendirilmiş müşteriyi ayarla
        this.status = "Bekleniyor"; // Varsayılan durum
        this.items = new ArrayList<>(); // Boş bir öğe listesi oluştur
    }

    // Getter ve Setter (özelliklere erişim ve güncelleme metotları)
    public int getOrderID() {
        return orderID; // Sipariş kimliğini döndür
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID; // Sipariş kimliğini ayarla
    }

    public Customer getCustomer() {
        return customer; // Siparişle ilişkilendirilmiş müşteriyi döndür
    }

    public void setCustomer(Customer customer) {
        this.customer = customer; // Siparişle ilişkilendirilmiş müşteriyi ayarla
    }

    public Date getOrderDate() {
        return orderDate; // Sipariş tarihini döndür
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate; // Sipariş tarihini ayarla
    }

    public double getTotalPrice() {
        return totalPrice; // Siparişin toplam fiyatını döndür
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice; // Siparişin toplam fiyatını ayarla
    }

    public String getStatus() {
        return status; // Siparişin durumunu döndür
    }

    public void setStatus(String status) {
        this.status = status; // Siparişin durumunu ayarla
    }

    public ArrayList<OrderItem> getItems() {
        return items; // Sipariş öğelerinin listesini döndür
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items; // Sipariş öğelerinin listesini ayarla
    }

    // Siparişi tamamlayan metot
    public boolean completeOrder() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }
        if (customer.getBalance() >= total) {
            customer.setBalance(customer.getBalance() - total);
            this.totalPrice = total;
            this.status = "Tamamlandı";
            return true;
        } else {
            return false; // Yetersiz bakiye
        }
    }

    // Siparişe bir öğe ekleme metodu
    public void addItem(OrderItem item) {
        this.items.add(item); // Sipariş öğesini listeye ekle
    }
}
