package product;

// ComboMeal sınıfı, bir menüyü (burger ve içecek) temsil eder ve Food sınıfından türetilmiştir.
public class ComboMeal extends Food {
    private Burger burger; // Menüdeki burger
    private Drink drink;   // Menüdeki içecek

    // Constructor (Yapıcı Metot)
    public ComboMeal(String name, double price, Burger burger, Drink drink) {
        super(name, price); // `Food` sınıfından adı ve fiyatı alır
        this.burger = burger; // Menüdeki burger atanır
        this.drink = drink;   // Menüdeki içecek atanır
    }

    // Getter ve Setter Metotları
    public Burger getBurger() {
        return burger; // Menüdeki burgeri döndürür
    }

    public void setBurger(Burger burger) {
        this.burger = burger; // Menüdeki burgeri günceller
    }

    public Drink getDrink() {
        return drink; // Menüdeki içeceği döndürür
    }

    public void setDrink(Drink drink) {
        this.drink = drink; // Menüdeki içeceği günceller
    }

    // Menü detaylarını döndüren metot
    @Override
    public String getDetails() {
        // Menü adı, fiyatı, içindeki burger ve içeceğin detaylarını döndürür
        return "Menü: " + getName() + ", Fiyat: " + getPrice() +
                "\n   -> " + burger.getDetails() +
                "\n   -> " + drink.getDetails();
    }
}
