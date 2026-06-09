# Proje Raporu

**Proje:** GamerMatch  
**Ders:** TBL324 İleri Java Uygulamaları

## Amaç

GamerMatch; e-spor oyuncuları için kullanıcı yönetimi, takım/turnuva yönetimi, matchmaking kuyruğu ve maç istatistik raporu sunan bir Java uygulamasıdır. Proje Spring Boot REST API ile veri katmanlarını yönetir, JavaFX istemciyle masaüstü arayüz sağlar.

## Veri katmanları

| Paket | Motor | Açıklama |
|-------|-------|----------|
| `jdbc` | H2 + JdbcTemplate | Kullanıcı, takım ve turnuva verileri |
| `redis` | Redis | Maç arama kuyruğu ve geçici lobi verileri |
| `mongo` | MongoDB | Esnek JSON maç raporları |

Veri katmanları ayrı paketlerde tutulmuştur. SQL kayıtları JDBC ile, NoSQL verileri Redis ve MongoDB ile yönetilir.

## Zorunlu kriter karşılığı

| Kriter | Projedeki karşılığı |
|--------|---------------------|
| API & Backend | Spring Boot REST endpoint'leri: `/api/users`, `/api/teams`, `/api/tournaments`, `/api/matchmaking`, `/api/match-reports` |
| Generic yapılar | `ApiResponse<T>` ve `PagedList<T>` |
| Custom GUI | JavaFX arayüz, `QueueCanvas` ve `TournamentBracketCanvas` custom çizimleri |
| JDBC & NoSQL | JDBC/H2, Redis ve MongoDB katmanları |
| SOLID & OOP | Servis arayüzleri, repository ayrımı ve Strategy pattern |
| Hata yönetimi | `GlobalExceptionHandler` ile 400, 404 ve 500 cevapları |
| Performans testleri | `performance/k6-load-test.js`, `performance/jmeter-gamermatch.jmx` ve `docs/PERFORMANCE_REPORT.md` |
| Analiz & doküman | Markdown raporları ve Mermaid mimari diyagramı |

## Dockerize sistem

Proje kök dizinindeki `docker-compose.yml` dosyası API, Redis ve MongoDB servislerini birlikte başlatır:

```powershell
docker compose up --build
```

API konteyneri ortam değişkenleriyle Redis ve MongoDB servislerine bağlanır:

- `REDIS_HOST=redis`
- `REDIS_PORT=6379`
- `MONGODB_URI=mongodb://mongo:27017/gamermatch`

## API özeti

- `GET /api/health`
- `GET|POST /api/users`
- `GET|POST /api/teams`
- `GET|POST /api/tournaments`
- `/api/matchmaking/*`
- `/api/match-reports/*`
