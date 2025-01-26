package product;

public class Burger extends Food {
    // Özellikler
    private boolean cheese;   // Peynir eklenip eklenmediği
    private boolean tomato;   // Domates eklenip eklenmediği
    private boolean lettuce;  // Marul eklenip eklenmediği
    private boolean pickle;   // Turşu eklenip eklenmediği
    private boolean onion;    // Soğan eklenip eklenmediği
    private boolean ketchup;  // Ketçap eklenip eklenmediği
    private boolean mayonnaise; // Mayonez eklenip eklenmediği

    // Constructor (Yapıcı Metot)
    public Burger(String name, double price, boolean cheese, boolean tomato, boolean lettuce,
                  boolean pickle, boolean onion, boolean ketchup, boolean mayonnaise) {
        super(name, price); // `Food` sınıfından adı ve fiyatı alır
        this.cheese = cheese; // Peynir durumu atanır
        this.tomato = tomato; // Domates durumu atanır
        this.lettuce = lettuce; // Marul durumu atanır
        this.pickle = pickle; // Turşu durumu atanır
        this.onion = onion; // Soğan durumu atanır
        this.ketchup = ketchup; // Ketçap durumu atanır
        this.mayonnaise = mayonnaise; // Mayonez durumu atanır
    }

    // Getter ve Setter Metotları (Her malzeme için erişim ve ayar)
    public boolean hasCheese() {
        return cheese; // Peynir olup olmadığını döndürür
    }

    public void setCheese(boolean cheese) {
        this.cheese = cheese; // Peynir durumunu ayarlar
    }

    public boolean hasTomato() {
        return tomato; // Domates olup olmadığını döndürür
    }

    public void setTomato(boolean tomato) {
        this.tomato = tomato; // Domates durumunu ayarlar
    }

    public boolean hasLettuce() {
        return lettuce; // Marul olup olmadığını döndürür
    }

    public void setLettuce(boolean lettuce) {
        this.lettuce = lettuce; // Marul durumunu ayarlar
    }

    public boolean hasPickle() {
        return pickle; // Turşu olup olmadığını döndürür
    }

    public void setPickle(boolean pickle) {
        this.pickle = pickle; // Turşu durumunu ayarlar
    }

    public boolean hasOnion() {
        return onion; // Soğan olup olmadığını döndürür
    }

    public void setOnion(boolean onion) {
        this.onion = onion; // Soğan durumunu ayarlar
    }

    public boolean hasKetchup() {
        return ketchup; // Ketçap olup olmadığını döndürür
    }

    public void setKetchup(boolean ketchup) {
        this.ketchup = ketchup; // Ketçap durumunu ayarlar
    }

    public boolean hasMayonnaise() {
        return mayonnaise; // Mayonez olup olmadığını döndürür
    }

    public void setMayonnaise(boolean mayonnaise) {
        this.mayonnaise = mayonnaise; // Mayonez durumunu ayarlar
    }

    // Toplam fiyat hesaplama metodu
    public double calculateTotalPrice() {
        return getPrice(); // Şu an için ekstra ücret eklemiyor (sabit fiyat)
    }

    // Detaylı bilgileri döndüren metot
    @Override
    public String getDetails() {
        // Burgerin özelliklerini ve fiyatını detaylı bir string olarak döndürür
        return "Burger: " + getName() +
                ", Fiyat: " + calculateTotalPrice() +
                ", Peynir: " + (cheese ? "Var" : "Yok") +
                ", Domates: " + (tomato ? "Var" : "Yok") +
                ", Marul: " + (lettuce ? "Var" : "Yok") +
                ", Turşu: " + (pickle ? "Var" : "Yok") +
                ", Soğan: " + (onion ? "Var" : "Yok") +
                ", Ketçap: " + (ketchup ? "Var" : "Yok") +
                ", Mayonez: " + (mayonnaise ? "Var" : "Yok");
    }
}
