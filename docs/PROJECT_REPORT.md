# Proje Raporu (v1)

**Proje:** GamerMatch  
**Ders:** TBL324 Ileri Java Uygulamalari

## Amac

E-spor oyuncu eslestirme, turnuva ve mac istatistik platformu (backend + JavaFX istemci).

## Veri katmanlari (Docker yok)

| Paket | Motor | Aciklama |
|-------|-------|----------|
| jdbc | H2 + JdbcTemplate | Kullanici, takim, turnuva (SQL) |
| redis | Gomulu Redis | Mac ara kuyrugu (NoSQL) |
| mongo | Gomulu MongoDB | Mac raporu (NoSQL, JSON) |

Uc katman ayri paketlerde; birbirine karismaz.

## Zorunlu kriterler

- API & Back-end: REST `/api/*`
- Generic: `ApiResponse<T>`, `PagedList<T>`
- Custom GUI: JavaFX + Canvas cizimleri
- JDBC & NoSQL: jdbc / redis / mongo
- SOLID: Service arayuzleri, Strategy pattern
- Hata yonetimi: 400, 404, 500
- Performans: `performance/` klasoru
- Dokuman: bu dosya + ARCHITECTURE.md

## Calistirma

Sadece `GamerMatchApplication` calistir. Redis ve Mongo ayri kurulmaz.

## API

- GET /api/health
- /api/users, /api/teams, /api/tournaments (jdbc)
- /api/matchmaking/* (redis)
- /api/match-reports (mongo)
