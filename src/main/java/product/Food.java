package product;

// Food sınıfı, tüm yiyecek türlerini temsil eden soyut bir sınıftır.
public abstract class Food {
    private String name;  // Yiyeceğin adı
    private double price; // Yiyeceğin fiyatı

    // Constructor (Yapıcı Metot)
    public Food(String name, double price) {
        this.name = name;   // Yiyeceğin adını ayarlar
        this.price = price; // Yiyeceğin fiyatını ayarlar
    }

    // Getter ve Setter Metotları
    public String getName() {
        return name; // Yiyeceğin adını döndürür
    }

    public void setName(String name) {
        this.name = name; // Yiyeceğin adını değiştirir
    }

    public double getPrice() {
        return price; // Yiyeceğin fiyatını döndürür
    }

    public void setPrice(double price) {
        this.price = price; // Yiyeceğin fiyatını değiştirir
    }

    // Polimorfizm için soyut bir metot
    public abstract String getDetails();
    // Yiyecek hakkında detaylı bilgi döndürmek için alt sınıflar tarafından uygulanır.
}
