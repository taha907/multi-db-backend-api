# Proje Raporu

**Proje:** GamerMatch  
**Ders:** TBL324 İleri Java Uygulamaları

## Amaç

GamerMatch; e-spor oyuncuları için kullanıcı yönetimi, takım/turnuva yönetimi, matchmaking kuyruğu ve maç istatistik raporu sunan Java tabanlı bir sistemdir. Proje, zorunlu isterlere ek olarak mikroservis mimarisi ve gateway katmanı içerecek şekilde genişletilmiştir.

## Mimari Karar

Başlangıçtaki monolitik API korunmuştur; ancak ek özellikleri karşılamak için `services/` klasörü altında izole mikroservis mimarisi kurulmuştur. Her mikroservisin kendi Spring Boot başlangıç sınıfı, Maven dosyası, Dockerfile'ı ve konfigürasyonu vardır.

## Servis Dağılımı

| Servis | Görev | Veri |
|--------|-------|------|
| `gateway-service` | Trafiği ilgili servise yönlendirir | Yok |
| `user-service` | Kullanıcı ve takım yönetimi | H2/JDBC |
| `tournament-service` | Turnuva yönetimi | H2/JDBC |
| `matchmaking-service` | Eşleştirme kuyruğu ve lobi | Redis |
| `report-service` | Maç raporu | MongoDB |

## Zorunlu ve Ek Kriter Karşılığı

| Kriter | Projedeki karşılığı |
|--------|---------------------|
| API & Backend | Her mikroservis REST API sunar |
| Generic yapılar | `ApiResponse<T>` ve `PagedList<T>` |
| Custom GUI | JavaFX arayüz, `QueueCanvas` ve `TournamentBracketCanvas` |
| JDBC & NoSQL | H2/JDBC, Redis, MongoDB |
| SOLID & OOP | Servis arayüzleri, repository ayrımı ve Strategy pattern |
| Hata yönetimi | `GlobalExceptionHandler` ile standart hata cevapları |
| Performans testleri | k6 ve JMeter dosyaları |
| Analiz & doküman | Markdown raporları ve Mermaid diyagramları |
| Mikroservis mimarisi | `services/` altında izole Spring Boot servisleri |
| Gateway | `gateway-service` ile tüm `/api/**` trafiği yönlendirilir |
| Dockerize sistem | `docker-compose.yml` ile tüm servisler ayağa kalkar |

## Docker Çalıştırma

```powershell
docker compose up --build
```

Bu komut gateway, dört mikroservis, Redis ve MongoDB servislerini aynı Docker ağı içinde başlatır.
