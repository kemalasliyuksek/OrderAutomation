package order;

import product.Food;

public class OrderItem {
    // Sipariş öğesi özellikleri
    private Food food; // Sipariş edilen yiyecek veya içecek
    private int quantity; // Sipariş edilen miktar
    private String orderNote; // Siparişle ilgili not (örneğin, "acı olmasın")

    // Constructor (Nesne oluşturucu)
    public OrderItem(Food food, int quantity, String orderNote) {
        this.food = food; // Yiyecek veya içeceği atar
        this.quantity = quantity; // Miktarı atar
        this.orderNote = orderNote; // Sipariş notunu atar
    }

    // Getter ve Setter metodları (özelliklere erişim ve güncelleme)
    public Food getFood() {
        return food; // Sipariş edilen yiyeceği döndür
    }

    public void setFood(Food food) {
        this.food = food; // Sipariş edilen yiyeceği güncelle
    }

    public int getQuantity() {
        return quantity; // Sipariş edilen miktarı döndür
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity; // Sipariş edilen miktarı güncelle
    }

    public String getOrderNote() {
        return orderNote; // Sipariş notunu döndür
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote; // Sipariş notunu güncelle
    }

    // Toplam fiyatı hesaplayan metod
    public double getTotalPrice() {
        // Yiyeceğin fiyatı ile miktarı çarparak toplam fiyatı hesaplar
        return food.getPrice() * quantity;
    }

    // Sipariş bilgilerini döndüren metod
    public String getOrderItemDetails() {
        // Siparişin adı, miktarı, notu ve toplam fiyatını döndürür
        return food.getName() + " (x" + quantity + ") - Not: " + orderNote + ", Toplam Fiyat: " + getTotalPrice();
    }
}
