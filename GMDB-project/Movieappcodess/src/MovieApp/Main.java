package MovieApp;

public class Main {
    public static void main(String[] args) {
        DataBase.createTables();
        new MainWindow();
         
        /* movie and category adding
        DataBase.addCategory("Bilim Kurgu"); // 1
        DataBase.addCategory("Aksiyon");     // 2
        DataBase.addCategory("Dram");         // 3
        DataBase.addCategory("Komedi");       // 4
        DataBase.addCategory("Korku");         // 5
        DataBase.addCategory("Gerilim");       // 6
        DataBase.addCategory("Polisiye");       // 7
     // Add Films
        DataBase.addFilm("Başlangıç", "Kurgusuyla gerçeklik algısını bozan bir film.", 1, "images/inception.jpg");
        DataBase.addFilm("Yıldızlararası", "Dünyadan uzakta yıldızlar arasında fizik kurallarıı sorgulatan film.", 1, "images/interstellar.jpg");
        DataBase.addFilm("Matrix", "Gerçeklik ile simülasyon arasındaki çizgiyi sorgulatan film.", 1, "images/matrix.jpg");
        DataBase.addFilm("Blade Runner 2049", "Geleceğin karanlık dünyasında robotlarla geçen bir hikaye.", 1, "images/bladerunner.jpg");
        DataBase.addFilm("Geleceğe Dönüş", "Döneminin ötesinde kurgulanmış klasik bir zaman yolculuğu filmi.", 1, "images/gd.jpg");
        DataBase.addFilm("Yarının Sınırında", "Zaman döngüsünde sıkışan bir askerin filmi.", 1, "images/edgeoftomorrow.jpg");

        // Aksiyon
        DataBase.addFilm("John Wick", "İntikam peşindeki ünlü bir suikastçi.", 2, "images/johnwick.jpg");
        DataBase.addFilm("Hızlı ve Öfkeli", "Aksiyon dolu araba yarışları ve soygunlar.", 2, "images/fastfurious.jpg");
        DataBase.addFilm("Mad Max: Öfkeli Yollar", "Kıyamet sonrası dünyada çılgınlıklarla dolu bir macera.", 2, "images/madmax.jpg");
        DataBase.addFilm("Görevimiz Tehlike", "Tehlikeli görevler ve casusluk hikayesi.", 2, "images/mimp.jpg");
        DataBase.addFilm("Extraction", "Tehlikeli bir kurtarma operasyonu.", 2, "images/extraction.jpg");
        DataBase.addFilm("Deadpool", "Alaycı ve psikopat bir süper kahramanın hikayesi.", 2, "images/deadpool.jpg");

        // Dram
        DataBase.addFilm("Baba", "Mafya ailesinin dram dolu gerçekçi öyküsü.", 3, "images/godfather.jpg");
        DataBase.addFilm("Dövüş Kulübü", "Toplumun kurallarını hiçe sayan bir adam.", 3, "images/fightclub.jpg");
        DataBase.addFilm("Forrest Gump", "Sıradışı bir hayat yaşayan basit bir adam.", 3, "images/fg.jpg");
        DataBase.addFilm("Yeşil Yol", "Mucizevi bir adamın yaşadığı bir hapishane hikayesi.", 3, "images/YesilYol.jpg");
        DataBase.addFilm("Esaretin Bedeli", "Umudun gücünü anlatan bir mahkum hikayesi.", 3, "images/esaretinbedeli.jpg");
        DataBase.addFilm("Bir Rüya İçin Ağıt", "Uyuşturucu bağımlılığının trajedisi.", 3, "images/requiem.jpg");

        // Komedi
        DataBase.addFilm("Hababam Sınıfı", "Özel bir lisede yaşanan klasikleşmiş bir komedi filmi.", 4, "images/hababamsınıfı.jpg");
        DataBase.addFilm("Hangover", "Bekarlığa veda partisinde yaşanan komik olaylar.", 4, "images/hangover.jpg");
        DataBase.addFilm("Superbad", "Lise yıllarının komik anıları.", 4, "images/superbad.jpg");
        DataBase.addFilm("21 Jump Street", "İki polisin liseye sızma operasyonu.", 4, "images/jumpstreet.jpg");
        DataBase.addFilm("Aman Tanrım!", "Tanrının güçlerini kazanan sıradan bir adam.", 4, "images/amantanrım.jpg");
        DataBase.addFilm("Bay Evet", "Hayata 'evet' demeye başlayan bir adamın filmi.", 4, "images/yesman.jpg");

        // Korku
        DataBase.addFilm("Korku Seansı", "Paranormal olayları araştıran bir çiftin filmi.", 5, "images/korkuseansı.jpg");
        DataBase.addFilm("Sessiz Bir Yer", "En ufak sesin bile tehlikeli olduğu bir dünya.", 5, "images/quietplace.jpg");
        DataBase.addFilm("IT", "Dehşet veren bir palyaço.", 5, "images/it.jpg");
        DataBase.addFilm("Hereditary", "Korku dolu ayinlerin bir aileye açtığı belalar.", 5, "images/hereditary.jpg");
        DataBase.addFilm("Halka", "Laneti bir video kasetin hikayesi.", 5, "images/ring.jpg");
        DataBase.addFilm("Gülümse", "Bir doktor, şahit olduğu travma sonucu lanetin etkisine girer.", 5, "images/smile.jpg");

        // Gerilim
        DataBase.addFilm("Se7en", "Yedi ölümcül günah üzerine işlenen cinayetler.", 6, "images/seven.jpg");
        DataBase.addFilm("Gone Girl", "Kaybolan bir kadının ardındaki karanlık sırlar.", 6, "images/gonegirl.jpg");
        DataBase.addFilm("Siyah Kuğu", "Bir balerinin psikolojik dönüşümü.", 6, "images/blackswan.jpg");
        DataBase.addFilm("Tutsak", "Kaçırılan kızların babasının mücadelesi.", 6, "images/prisoners.jpg");
        DataBase.addFilm("Zodiac", "Bir seri katil üzerine gerçek olaylara dayalı hikaye.", 6, "images/zodiac.jpg");
        DataBase.addFilm("Bird Box", "Görenlerin acı şekilde öldüğü gerilim filmi.", 6, "images/birdbox.jpg");

        // Polisiye
        DataBase.addFilm("Sherlock Holmes", "Ünlü dedektifin gizemli maceraları.", 7, "images/sherlock.jpg");
        DataBase.addFilm("İçerideki Adam", "Bir banka soygununun ardındaki gizem.", 7, "images/insideman.jpg");
        DataBase.addFilm("Büyük Hesaplaşma", "Polis ve soyguncu arasındaki mücadele.", 7, "images/Heat.jpg");
        DataBase.addFilm("L.A. Confidential", "1940'ların Los Angeles'ındaki polis hikayesi.", 7, "images/LA.jpg");
        DataBase.addFilm("Gizemli Nehir", "Çocukluk travmalarının peşinden gelen dram.", 7, "images/nehir.jpg");
        DataBase.addFilm("Cinayet Günlüğü", "Bir dizi cinayetin gizemi.", 7, "images/memories.jpg");
         */
    }
    
}

