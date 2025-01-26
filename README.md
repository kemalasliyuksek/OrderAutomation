# OrderAutomation

OrderAutomation is a Java-based application designed to streamline order and product management processes. It utilizes a modular structure, built with Maven, and supports efficient handling of users, orders, and products.

## Features

- User-friendly interface for order management.
- Modular code structure for easy maintenance and scalability.
- Database integration for storing user, order, and product details.
- Developed using Java and follows object-oriented design principles.

## Project Structure

```plaintext
OrderAutomation/
├── pom.xml                # Maven configuration file
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── Main.java   # Entry point of the application
│   │   │   ├── ui/         # User interface components
│   │   │   ├── methods/    # Utility methods and logic
│   │   │   ├── user/       # User-related classes
│   │   │   ├── order/      # Order-related classes
│   └── └── └── product/    # Product-related classes
└── Database/               # Database-related files
```

## Installation and Usage

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Maven

### Steps

1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```bash
   cd OrderAutomation
   ```

3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   java -cp target/OrderAutomation-1.0-SNAPSHOT.jar Main
   ```

---

# OrderAutomation

OrderAutomation, sipariş ve ürün yönetimi süreçlerini kolaylaştırmak için tasarlanmış, Java tabanlı bir uygulamadır. Modüler bir yapıya sahip olup, kullanıcı, sipariş ve ürün işlemlerinin verimli bir şekilde yönetilmesini sağlar.

## Özellikler

- Sipariş yönetimi için kullanıcı dostu arayüz.
- Kolay bakım ve ölçeklenebilirlik için modüler kod yapısı.
- Kullanıcı, sipariş ve ürün detaylarını depolamak için veri tabanı entegrasyonu.
- Java ile geliştirilmiş ve nesne yönelimli tasarım prensiplerini takip eder.

## Proje Yapısı

```plaintext
OrderAutomation/
├── pom.xml                # Maven yapılandırma dosyası
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── Main.java   # Uygulamanın giriş noktası
│   │   │   ├── ui/         # Kullanıcı arayüzü bileşenleri
│   │   │   ├── methods/    # Yardımcı metotlar ve mantık
│   │   │   ├── user/       # Kullanıcıyla ilgili sınıflar
│   │   │   ├── order/      # Siparişle ilgili sınıflar
│   └── └── └── product/    # Ürünle ilgili sınıflar
└── Database/               # Veri tabanıyla ilgili dosyalar
```

## Kurulum ve Kullanım

### Gereksinimler

- Java Development Kit (JDK) 8 veya üzeri
- Apache Maven

### Adımlar

1. Depoyu klonlayın:
   ```bash
   git clone <repository-url>
   ```

2. Proje dizinine gidin:
   ```bash
   cd OrderAutomation
   ```

3. Maven ile projeyi derleyin:
   ```bash
   mvn clean install
   ```

4. Uygulamayı çalıştırın:
   ```bash
   java -cp target/OrderAutomation-1.0-SNAPSHOT.jar Main
   ```
