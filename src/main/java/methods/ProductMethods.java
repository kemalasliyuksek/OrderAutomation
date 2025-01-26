package methods;

import product.Burger;
import product.ComboMeal;
import product.Drink;
import product.Food;

public class ProductMethods {

    // Özel Burger oluşturma metodu
    public static Burger createBurger(boolean cheese, boolean tomato, boolean lettuce, boolean onion, boolean ketchup, boolean mayonnaise) {
        double basePrice = 150.0; // Temel burger fiyatı
        double extraCost = 0.0; // Ek malzemelerin maliyeti

        // Ek malzeme seçeneklerine göre maliyet ekleniyor
        if (cheese) extraCost += 40.0;
        if (tomato) extraCost += 15.0;
        if (lettuce) extraCost += 15.0;
        if (onion) extraCost += 15.0;
        if (ketchup) extraCost += 10.0;
        if (mayonnaise) extraCost += 10.0;

        // Yeni bir Burger nesnesi döndürülüyor
        return new Burger("Özel Burger", basePrice + extraCost, cheese, tomato, lettuce, false, onion, ketchup, mayonnaise);
    }

    // Özel Menü (Combo Meal) oluşturma metodu
    public static ComboMeal createComboMeal(boolean extraBurger, boolean drinkCola) {
        // Varsayılan bir burger oluşturuluyor
        Burger burger = createBurger(true, true, true, true, false, false);
        double basePrice = 250.0; // Temel menü fiyatı

        // Eğer ekstra burger istenirse bazı malzemeler ayarlanıyor
        if (extraBurger) {
            burger.setCheese(true); // Ekstra peynir
            burger.setTomato(true); // Ekstra domates
        }

        // Seçilen içecek boyutuna göre bir içecek oluşturuluyor
        Drink drink = createDrink(drinkCola ? "Küçük" : "Orta");

        // Yeni bir ComboMeal nesnesi döndürülüyor
        return new ComboMeal("Özel Menü", basePrice + burger.getPrice() + drink.getPrice(), burger, drink);
    }

    // İçecek oluşturma metodu
    public static Drink createDrink(String size) {
        // İçecek boyutuna göre fiyat belirleniyor
        double price = switch (size) {
            case "Küçük" -> 30.0; // Küçük boy fiyatı
            case "Orta" -> 40.0; // Orta boy fiyatı
            case "Büyük" -> 50.0; // Büyük boy fiyatı
            default -> 40.0; // Varsayılan fiyat (hatalı boyut seçilirse)
        };
        // Yeni bir Drink nesnesi döndürülüyor
        return new Drink("Seçili İçecek", price, size);
    }

    // Belirli detaylara göre Food nesnesi döndüren metot (dummy implementation)
    public static Food getFoodByDetails(String details) {
        // Dummy bir Food nesnesi döndürülüyor (örnek kod)
        return new Food("Dummy Food", 0) {
            @Override
            public String getDetails() {
                return details; // Detayları geri döndürür
            }
        };
    }
}
