package product;

// Drink sınıfı, bir içeceği temsil eder ve Food sınıfından türetilmiştir.
public class Drink extends Food {
    private String size; // İçeceğin boyutu: "Küçük", "Orta", "Büyük"

    // Constructor (Yapıcı Metot)
    public Drink(String name, double price, String size) {
        super(name, price); // `Food` sınıfından adı ve fiyatı alır
        this.size = size;   // İçeceğin boyutunu ayarlar
    }

    // İçeceğin boyutunu döndüren getter metodu
    public String getSize() {
        return size;
    }

    // İçeceğin boyutunu değiştiren setter metodu
    public void setSize(String size) {
        this.size = size;
    }

    // İçecek detaylarını döndüren metot
    @Override
    public String getDetails() {
        // İçeceğin adı, fiyatı ve boyutunu bir string olarak döndürür
        return "İçecek: " + getName() + ", Fiyat: " + getPrice() + ", Boyut: " + size;
    }
}
