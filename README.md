# 🌿 PlantIdentifyApp

Bitkileri tanımanıza, kategorilere göre keşfetmenize ve sorularla bilgilenmenize olanak tanıyan modern, temiz mimariyle geliştirilmiş bir Android uygulaması. 

---

## 🧭 Uygulama Akışı
Splash → Get Started → Onboarding → Paywall → Homepage (Categories & Questions)

-  **Splash**: Kullanıcının onboarding tamamlayıp tamamlamadığına göre yönlendirir.
    - Kullanıcı onboarding akışını tamamladıysa Splash ekranından doğrudan ana ekrana yönlendirilir.
    - İlk kez kullananlar Get Started, Onboarding ve Paywall akışlarını sırasıyla deneyimler.
-  **Get Started** → **Onboarding**: Figma'ya birebir sadık intro akışı
-  **Paywall**: Premium ekran; CTA ile onboarding tamamlanır
-  **Homepage**: Kategoriler (grid), sorular (horizontal list)
-  **QuestionFragment**: Seçilen kategoriye ait sorular (hazırlanabilir)


---

##  Durum Yönetimi

- Her veri çağrısı `Resource<T>` sınıfı ile `Loading`, `Success`, `Error` olarak ayrılır.
- `ViewModel` → `StateFlow` ile UI’ye reaktif veri akışı sağlanır.
- `collectLatest` ile lifecycle-aware veri gözlemlenir.
  
---

##  Katmanlı Mimari Yapı (Clean Architecture + MVVM)


```
com.ezgieren.plantidentifyapp
├── data
│   ├── local
│   │   ├── dao                # Room DAO arayüzleri
│   │   └── entity             # Room veri sınıfları
│   ├── model
│   │   ├── category           # Category DTO & Response modelleri
│   │   └── question           # Question DTO modeli
│   └── remote                 # Retrofit API arayüzleri
│
├── domain
│   ├── model                 # UI-friendly sade modeller
│   ├── repository            # Soyut repository arayüzleri
│   └── usecase               # Tek iş yapan mantıksal sınıflar
│
├── repository
│   ├── CategoryRepositoryImpl.kt
│   └── QuestionRepositoryImpl.kt
│
├── di
│   └── LocalModule.kt        # Hilt modülleri (Room, Repo binding)
│
├── ui
│   ├── splash
│   ├── start
│   ├── onboarding
│   ├── paywall
│   └── home                  # HomeFragment, adaptörler
│
├── viewmodel
│   ├── CategoryViewModel.kt
│   └── QuestionViewModel.kt
│
└── utils
    ├── Constants.kt
    ├── Extensions.kt
    ├── PreferencesManager.kt
    └── Resource.kt
```

---

## 🔧 Kullanılan Teknolojiler

- **Language**: Kotlin
- **UI**: XML (zorunlu), Jetpack Compose opsiyonel
- **State Management**: ViewModel + Kotlin Flow
- **Network**: Retrofit + OkHttp
- **Local Cache**: Room
- **Dependency Injection**: Hilt
- **Navigation**: Jetpack Navigation Component
- **Async**: Kotlin Coroutines
- **Design Tools**: Figma referans tasarımı

---

## 🗃️ Önemli Özellikler

- [x] Get Started → Onboarding → Paywall → Homepage akışı
- [x] **Splash yönlendirmesi**: Onboarding tamamlandıysa doğrudan ana ekran.
- [x] **SharedPreferences**: Onboarding durumu saklanır.
- [x] **Modern mimari**: MVVM + Clean Architecture
- [x] **Retrofit + OkHttp**: REST API entegrasyonu
- [x] **Room cache**: Kategori ve soru verileri offline destek için Room’da saklanır. (Online API + Room Cache desteği)
- [x] **StateFlow & ViewModel**: UI güncellemeleri reaktif olarak yönetilir.
- [x] **Jetpack Navigation**: Jetpack Navigation ile akıcı sayfa geçişleri yönetilir.
- [x] **Sealed Resource sınıfı**: Yükleme, hata, başarı durumları yönetilir.
- [x] **Figma uyumlu UI**: Piksel çalışmaya yakınlık.
- [x] **Constants & Strings**: Tüm sabit metinler merkezi yapıdan yönetilir.

---

## 🌐 API Entegrasyonu

| Endpoint                        | Açıklama         |
|---------------------------------|------------------|
| `/getCategories`               | Kategori listesi |
| `/getQuestions`                | Soru listesi     |

Gelen veri yapıları `DTO → Entity → Domain` adımlarıyla ayrıştırılır.


## 📄 Lisans

Bu proje sadece işe alım görevi amaçlı geliştirilmiştir. Ticari amaçlı kullanılamaz.
