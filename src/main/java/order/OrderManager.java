package order;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> orders; // Tüm siparişleri saklayan liste

    // Constructor (Yapıcı metod)
    public OrderManager() {
        this.orders = new ArrayList<>(); // Siparişler için boş bir liste oluştur
    }

    // Siparişi işleme metodu
    public boolean processOrder(Order order) {
        return order.completeOrder();
    }

    // Yeni bir sipariş ekleme metodu
    public void addOrder(Order order) {
        this.orders.add(order); // Verilen siparişi listeye ekle
    }

    // Mevcut tüm siparişleri döndüren metod
    public List<Order> getOrders() {
        return orders; // Siparişler listesini döndür
    }
}
