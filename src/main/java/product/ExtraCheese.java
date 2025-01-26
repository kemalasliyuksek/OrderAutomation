package product;

// ExtraCheese sınıfı, bir Burger sınıfını temsil eder ve ekstra peynir seçeneği sunar.
public class ExtraCheese extends Burger {
    private static final double EXTRA_CHEESE_COST = 50.0;  // Ekstra peynirin maliyeti sabit bir değer olarak tanımlanır.

    // Constructor (Yapıcı Metot)
    public ExtraCheese(String name, double price, boolean cheese, boolean tomato, boolean lettuce,
                       boolean pickle, boolean onion, boolean ketchup, boolean mayonnaise) {
        // Burger constructor'ını çağırırken ekstra peynir maliyetini temel fiyata ekleriz.
        super(name, price + EXTRA_CHEESE_COST, cheese, tomato, lettuce, pickle, onion, ketchup, mayonnaise);
    }

    // Burger detaylarını döndüren metodu genişletir.
    @Override
    public String getDetails() {
        // Orijinal Burger detaylarına ekstra peynir bilgisini ve fiyat artışını ekler.
        return super.getDetails() + ", Ekstra Peynir: Var, Fiyat Artışı: " + EXTRA_CHEESE_COST;
    }
}
