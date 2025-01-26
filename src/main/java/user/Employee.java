package user;

// Employee sınıfı, çalışan bilgilerini yönetmek ve ilgili işlemleri gerçekleştirmek için kullanılır.
public class Employee {
    private int id; // Çalışanın benzersiz kimliği
    private String name; // Çalışanın adı
    private String surname; // Çalışanın soyadı
    private int roleId; // Çalışanın rolünü belirten kimlik (örneğin, yönetici, personel vb.)
    private String username; // Çalışanın kullanıcı adı
    private String password; // Çalışanın şifresi

    // Constructor (Yapıcı Metot)
    public Employee(int id, String name, String surname, int roleId, String username, String password) {
        this.id = id; // Çalışan kimliğini ayarlar
        this.name = name; // Çalışan adını ayarlar
        this.surname = surname; // Çalışan soyadını ayarlar
        this.roleId = roleId; // Çalışan rol kimliğini ayarlar
        setUsername(username); // Kullanıcı adını ayarlar
        setPassword(password); // Şifreyi ayarlar
    }

    // Getter ve Setter metotları
    // Çalışan bilgilerine erişim ve bu bilgilerin değiştirilmesi için kullanılır.

    // Çalışanın kimliğini döndürür
    public int getId() {
        return id;
    }

    // Çalışanın kimliğini ayarlar
    public void setId(int id) {
        this.id = id;
    }

    // Çalışanın adını döndürür
    public String getName() {
        return name;
    }

    // Çalışanın adını ayarlar
    public void setName(String name) {
        this.name = name;
    }

    // Çalışanın soyadını döndürür
    public String getSurname() {
        return surname;
    }

    // Çalışanın soyadını ayarlar
    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Çalışanın rol kimliğini döndürür
    public int getRoleId() {
        return roleId;
    }

    // Çalışanın rol kimliğini ayarlar
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    // Çalışanın kullanıcı adını döndürür
    public String getUsername() {
        return username;
    }

    // Çalışanın kullanıcı adını ayarlar
    public void setUsername(String username) {
        this.username = username;
    }

    // Çalışanın şifresini döndürür
    public String getPassword() {
        return password;
    }

    // Çalışanın şifresini ayarlar
    public void setPassword(String password) {
        this.password = password;
    }

    // Çalışan bilgilerini formatlı bir şekilde döndüren metot
    public String getEmployeeDetails() {
        // Çalışan bilgilerini düzgün bir formatta döndürür.
        return String.format("ID: %d, Çalışan: %s %s, Kullanıcı Adı: %s, Rol ID: %d",
                id, name, surname, username, roleId);
    }
}
