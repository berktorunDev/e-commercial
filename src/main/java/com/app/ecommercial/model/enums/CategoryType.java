package com.app.ecommercial.model.enums;

public enum CategoryType {
    // Ana kategoriler
    EMLAK("Emlak"),
    VASITA("Vasıta"),
    YEDEK_PARCA_AKSESUAR("Yedek Parça, Aksesuar, Donanım & Tuning"),
    IKINCI_EL_VE_SIFIR_ALISVERIS("İkinci El ve Sıfır Alışveriş"),
    IS_MAKINELERI_SANAYI("İş Makineleri & Sanayi"),
    USTALAR_VE_HIZMETLER("Ustalar ve Hizmetler"),
    OZEL_DERS_VERENLER("Özel Ders Verenler"),
    IS_ILANLARI("İş İlanları"),
    YARDIMCI_ARAYANLAR("Yardımcı Arayanlar"),

    // Emlak alt kategorileri
    KONUT("Konut", EMLAK),
    IS_YERI("İş Yeri", EMLAK),
    ARSA("Arsa", EMLAK),
    KONUT_PROJELERI("Konut Projeleri", EMLAK),
    BINA("Bina", EMLAK),
    DEVRE_MULK("Devre Mülk", EMLAK),
    TURISTIK_TESIS("Turistik Tesis", EMLAK),

    // Vasıta alt kategorileri
    OTOMOBIL("Otomobil", VASITA),
    ARAZI_SUV_PICKUP("Arazi, SUV & Pickup", VASITA),
    MOTOSIKLET("Motosiklet", VASITA),
    MINIVAN_PANELVAN("Minivan & Panelvan", VASITA),
    TICARI_ARACLAR("Ticari Araçlar", VASITA),
    ELEKTRIKLI_ARACLAR("Elektrikli Araçlar", VASITA),
    KIRALIK_ARACLAR("Kiralık Araçlar", VASITA),
    DENIZ_ARACLARI("Deniz Araçları", VASITA),
    HASARLI_ARACLAR("Hasarlı Araçlar", VASITA),
    KARAVAN("Karavan", VASITA),
    KLASIK_ARACLAR("Klasik Araçlar", VASITA),
    HAVA_ARACLARI("Hava Araçları", VASITA),
    ATV("ATV", VASITA),
    UTV("UTV", VASITA),
    ENGINELLI_PLAKALI_ARACLAR("Engelli Plakalı Araçlar", VASITA),

    // Yedek Parça, Aksesuar, Donanım & Tuning alt kategorileri
    OTOMOTIV_EKIPMANLARI("Otomotiv Ekipmanları", YEDEK_PARCA_AKSESUAR),
    MOTOSIKLET_EKIPMANLARI("Motosiklet Ekipmanları", YEDEK_PARCA_AKSESUAR),
    DENIZ_ARACI_EKIPMANLARI("Deniz Aracı Ekipmanları", YEDEK_PARCA_AKSESUAR),

    // İkinci El ve Sıfır Alışveriş alt kategorileri
    BILGISAYAR("Bilgisayar", IKINCI_EL_VE_SIFIR_ALISVERIS),
    CEP_TELEFONU("Cep Telefonu", IKINCI_EL_VE_SIFIR_ALISVERIS),
    FOTOGRAF_KAMERA("Fotoğraf & Kamera", IKINCI_EL_VE_SIFIR_ALISVERIS),
    EV_DEKORASYON("Ev Dekorasyon", IKINCI_EL_VE_SIFIR_ALISVERIS),
    EV_ELEKTRONIGI("Ev Elektroniği", IKINCI_EL_VE_SIFIR_ALISVERIS),
    ELEKTRIKLI_EV_ALETLERI("Elektrikli Ev Aletleri", IKINCI_EL_VE_SIFIR_ALISVERIS),
    GIYIM_AKSESUAR("Giyim & Aksesuar", IKINCI_EL_VE_SIFIR_ALISVERIS),
    SAAT("Saat", IKINCI_EL_VE_SIFIR_ALISVERIS),
    ANNE_BEBEK("Anne & Bebek", IKINCI_EL_VE_SIFIR_ALISVERIS),
    KISISEL_BAKIM_KOZMETIK("Kişisel Bakım & Kozmetik", IKINCI_EL_VE_SIFIR_ALISVERIS),
    HOBI_OYUNCAK("Hobi & Oyuncak", IKINCI_EL_VE_SIFIR_ALISVERIS),
    OYUN_KONSOL("Oyun & Konsol", IKINCI_EL_VE_SIFIR_ALISVERIS),
    KITAP_DERGI_FILM("Kitap, Dergi & Film", IKINCI_EL_VE_SIFIR_ALISVERIS),
    MUZIK("Müzik", IKINCI_EL_VE_SIFIR_ALISVERIS),
    SPOR("Spor", IKINCI_EL_VE_SIFIR_ALISVERIS),
    TAKI_MUCEVHER_ALTIN("Takı, Mücevher & Altın", IKINCI_EL_VE_SIFIR_ALISVERIS),
    KOLEKSIYON("Koleksiyon", IKINCI_EL_VE_SIFIR_ALISVERIS),
    ANTIKA("Antika", IKINCI_EL_VE_SIFIR_ALISVERIS),
    BAHCE_YAPI_MARKET("Bahçe & Yapı Market", IKINCI_EL_VE_SIFIR_ALISVERIS),
    TEKNIK_ELEKTRONIK("Teknik Elektronik", IKINCI_EL_VE_SIFIR_ALISVERIS),
    OFIS_KIRTASIYE("Ofis & Kırtasiye", IKINCI_EL_VE_SIFIR_ALISVERIS),
    YIYECEK_ICECEK("Yiyecek & İçecek", IKINCI_EL_VE_SIFIR_ALISVERIS),
    DIGER_HER_SEY("Diğer Her Şey", IKINCI_EL_VE_SIFIR_ALISVERIS),

    // İş Makineleri & Sanayi alt kategorileri
    IS_MAKINELERI("İş Makineleri", IS_MAKINELERI_SANAYI),
    TARIM_MAKINELERI("Tarım Makineleri", IS_MAKINELERI_SANAYI),
    SANAYI("Sanayi", IS_MAKINELERI_SANAYI),
    ELEKTRIK_ENERJI("Elektrik & Enerji", IS_MAKINELERI_SANAYI),

    // Ustalar ve Hizmetler alt kategorileri
    EV_TADILAT_DEKORASYON("Ev Tadilat & Dekorasyon", USTALAR_VE_HIZMETLER),
    NAKLIYAT("Nakliyat", USTALAR_VE_HIZMETLER),
    ARAC_SERVIS_BAKIM("Araç Servis & Bakım", USTALAR_VE_HIZMETLER),
    TAMIRAT_TEKNIK_SERVIS("Tamirat & Teknik Servis", USTALAR_VE_HIZMETLER),
    DUGUN_ETKINLIK("Düğün & Etkinlik", USTALAR_VE_HIZMETLER),
    DIGER_USTALAR_HIZMETLER("Diğer", USTALAR_VE_HIZMETLER),

    // Özel Ders Verenler alt kategorileri
    LISE_UNIVERSITE("Lise & Üniversite", OZEL_DERS_VERENLER),
    ILKOGRETIM("İlkokul & Ortaokul", OZEL_DERS_VERENLER),
    YABANCI_DIL("Yabancı Dil", OZEL_DERS_VERENLER),
    BILGISAYAR_DERS("Bilgisayar", OZEL_DERS_VERENLER),
    DIREKSIYON("Direksiyon", OZEL_DERS_VERENLER),
    SPOR_DERS("Spor", OZEL_DERS_VERENLER),
    SANAT_DERS("Sanat", OZEL_DERS_VERENLER),
    DANS("Dans", OZEL_DERS_VERENLER),
    MUZIK_ENSTRUMAN("Müzik & Enstrüman", OZEL_DERS_VERENLER),
    TIYATRO_OYUNCULUK("Tiyatro & Oyunculuk", OZEL_DERS_VERENLER),
    KISISEL_GELISIM_DERS("Kişisel Gelişim", OZEL_DERS_VERENLER),
    MESLEKI_DERSLER("Mesleki Dersler", OZEL_DERS_VERENLER),
    OZEL_EGITIM("Özel Eğitim", OZEL_DERS_VERENLER),
    COCUK_GELISIMI("Çocuk Gelişimi", OZEL_DERS_VERENLER),
    GUZEL_KONUSMA_DIKSIYON("Güzel Konuşma & Diksiyon", OZEL_DERS_VERENLER),
    FOTOGRAFCILIK("Fotoğrafçılık", OZEL_DERS_VERENLER),

    // İş İlanları alt kategorileri
    AVUKATLIK_HUKUKI_DANISMANLIK("Avukatlık & Hukuki Danışmanlık", IS_ILANLARI),
    EGITIM_ILANLARI("Eğitim", IS_ILANLARI),

    EGLENCE_AKTIVITE("Eğlence & Aktivite", IS_ILANLARI),
    GUZELLIK_BAKIM("Güzellik & Bakım", IS_ILANLARI),
    IT_YAZILIM_GELISTIRME("IT & Yazılım Geliştirme", IS_ILANLARI),
    INSAN_KAYNAKLARI("İnsan Kaynakları", IS_ILANLARI),
    INSAAT_YAPI("İnşaat & Yapı", IS_ILANLARI),
    ISLETME_STRATEJIK_YONETIM("İşletme & Stratejik Yönetim", IS_ILANLARI),
    KORUMA_GUVENLIK("Koruma & Güvenlik", IS_ILANLARI),
    LOJISTIK_TASIMA("Lojistik & Taşıma", IS_ILANLARI),
    MAGAZACILIK_PERAKENDECILIK("Mağazacılık ve Perakendecilik", IS_ILANLARI),
    MUHASEBE_FINANS_BANKACILIK("Muhasebe, Finans & Bankacılık", IS_ILANLARI),
    MUHENDISLIK_ILANLARI("Mühendislik", IS_ILANLARI),
    MUSTERI_HIZMETLERI("Müşteri Hizmetleri", IS_ILANLARI),
    OFIS_YONETIMI_IDARI_ISLER("Ofis Yönetimi & İdari İşler", IS_ILANLARI),
    PART_TIME_EK_IS("Part Time & Ek İş Fırsatları", IS_ILANLARI),
    PAZARLAMA_URUN_YONETIMI("Pazarlama & Ürün Yönetimi", IS_ILANLARI),
    RADYO_SINEMA_TELEVIZYON("Radyo, Sinema & Televizyon", IS_ILANLARI),
    RESTORAN_KONAKLAMA("Restoran & Konaklama", IS_ILANLARI),
    SAGLIK_ILANLARI("Sağlık", IS_ILANLARI),
    SATIS_ILANLARI("Satış", IS_ILANLARI),
    TAMIR_BAKIM("Tamir & Bakım", IS_ILANLARI),
    TARIM_HAYVANCILIK("Tarım & Hayvancılık", IS_ILANLARI),
    TASARIM_YARATICILIK("Tasarım & Yaratıcılık", IS_ILANLARI),
    TEKSTIL_KONFEKSIYON("Tekstil & Konfeksiyon", IS_ILANLARI),
    URETIM_IMALAT("Üretim & İmalat", IS_ILANLARI),

    // Yardımcı Arayanlar alt kategorileri
    BEBEK_COCUK_BAKICISI("Bebek & Çocuk Bakıcısı", YARDIMCI_ARAYANLAR),
    YASLI_HASTA_BAKICISI("Yaşlı & Hasta Bakıcısı", YARDIMCI_ARAYANLAR),
    TEMIZLIKCI_EV_ISLERINE_YARDIMCI("Temizlikçi & Ev İşlerine Yardımcı", YARDIMCI_ARAYANLAR);

    private final String displayName;
    private final CategoryType parentCategory;

    // Ana kategori için constructor
    CategoryType(String displayName) {
        this.displayName = displayName;
        this.parentCategory = null; // Ana kategori için üst kategori yok
    }

    // Alt kategori için constructor
    CategoryType(String displayName, CategoryType parentCategory) {
        this.displayName = displayName;
        this.parentCategory = parentCategory;
    }

    public String getDisplayName() {
        return displayName;
    }

    public CategoryType getParentCategory() {
        return parentCategory;
    }
}
