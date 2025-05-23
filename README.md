# GMDB – Gazi Movie Database 

Bu proje, CENG106 – Nesneye Dayalı Programlamaya Giriş dersi kapsamında geliştirilmiştir. GMDB, masaüstü bir film platformudur. Kullanıcılar film kategorilerine göre içerikleri görüntüleyebilir, yorum yapabilir, puan verebilir, öne çıkan filmleri görebilir ve favori filmlerini listeleyebilir.

##  Proje Ekibi

| İsim                 | Numara      | Şube    | Rol                         |
|--------------------- |-------------|---------|
| Şevket Berat Tetik   | 23118080062 | 2. şube | Arayüz Tasarımı             |
| Alperen Örene        | 23118080006 | 2. şube | Veritabanı Tasarımı         |
|Muhammed Eymen Yiğit  | 23118080087 | 2. şube | OOP & Yorum/Puanlama        |
|Mustafa Kerem Kaya    | 23118080045 | 2. şube | Ana Sayfa & MVC & Akış      |

##  Uygulamanın Çalıştırılması

### Adımlar:
1. Yukarıdaki linkten GMDB.jar, film.db ve images klasörünü indir.
2. Hepsini *aynı klasörün içine koy*.
3. GMDB.jar dosyasına çift tıkla.

> Java 17 veya üstü yüklü olmalıdır. Geliştirme ortamı olarak Eclipse önerilir.

##alternatif çalıştırma

Projeyi doğrudan Eclipse’e import etmek için:  
[GMDB_Eclipse.zip indir](https://github.com/beratetikk/CENG106-GMDB-Proje/blob/main/GMDB/GMDBmovie.zip)

### Açmak İçin:
1. Eclipse → File → Import → General → Existing Projects into Workspace
2. GMDBmovie.zip dosyasını seç → içinden proje otomatik tanınır.
3. Finish → Proje hazır ve maine girerek çalıştırabilirsiniz

 
##  Kullanılan Teknolojiler

- *Java* dili
- *Swing* ile GUI (arayüz) tasarımı
- *SQLite* ile veritabanı
- *JDBC* ile veritabanı bağlantısı
- *MVC* tasarım deseni
- *Regex* ile kullanıcı ismi doğrulama
- *Multithreading* ile hızlı geçiş işlemleri
- *OOP* prensipleri: encapsulation, inheritance, polymorphism
- **JAR (çalıştırılabilir Java arşiv dosyası)

##  Uygulama Özellikleri

-  Filmler kategorilere göre listelenir (Bilim Kurgu, Aksiyon, Dram...)
-  Filmler puanlanabilir ve ortalama puanları görüntülenir
-  Kullanıcılar yorum ekleyebilir (isim doğrulaması yapılır)
-  Favorilere ekleme / çıkarma
-  En yüksek puanlı filmler “Öne Çıkanlar” olarak listelenir
-  Karanlık mod ve özel tema
-  Arka plan resmi (ana sayfa için)

##  Sunum Videosu

GMDB proje sunum videosu(https://drive.google.com/drive/folders/1H30XvbgYlSZRlQrlG0wj9pcg3cc1eRde)

##  Rapor

Projenin detaylı raporu CENG106_ID14_ProjeRaporu dosyasındadır.

##  Dosyaları İndirme

Tüm proje dosyaları (JAR dosyası, veritabanı, görseller ve kaynak kodlar) için:  
 [GMDB klasörü (GitHub içindeki)]
(https://github.com/beratetikk/CENG106-GMDB-Proje/tree/main/GMDB)

İçindekiler:
- GMDB.jar: Uygulamanın çalıştırılabilir hali
- film.db: SQLite veritabanı
- images/: Arayüzde kullanılan görseller
- Movieappcodes.zip/: Java kaynak kodları
- CENG106_ID14_ProjeRaporu: Proje raporu
- 'GMDBmovie.zip': Projenin Eclips'te çalıştırılabilir hali

##  Katkılar ve Gelecek Planlar

- Kullanıcı girişi ve kayıt sistemi eklenebilir
- Film arama özelliği geliştirilebilir
- Çevrimiçi veri entegrasyonu sağlanabilir

##  Lisans

Sadece eğitim amaçlıdır.
