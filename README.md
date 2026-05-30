# GamerMatch v1

 Yüksek Performanslı Eşleştirme için Dağıtık Veri Mimarisi V1

## Klasor yapisi

```
com/gamermatch/
  GamerMatchApplication.java   <- buradan baslat
  common/    ortak kod (generic, hata, gomulu redis)
  jdbc/      SQL verileri (H2)
  redis/     mac ara kuyrugu
  mongo/     mac istatistik raporu
  gui/       JavaFX arayuz
docs/        proje raporu
performance/ k6 + JMeter
```

## Veritabanlari (hepsi uygulama icinde)

| Katman | Motor | Nasil calisir |
|--------|-------|---------------|
| jdbc | H2 | Bellekte SQL, API acilinca hazir |
| redis |  Redis | `GamerMatchApplication` basinda acilir |
| mongo | MongoDB | Spring Boot ile otomatik acilir |

## Calistirma

1. Cursor → `GamerMatchApplication.java` → **Run**  
2. Tarayici: http://localhost:8080/api/health
3. GUI: → **JavaFX - GamerMatch GUI** (once API acik olsun)


## Test (PowerShell)

```powershell
  1. Sistem Sağlık Kontrolü
  Invoke-RestMethod http://localhost:8080/api/health

  2. Yeni Kullanıcı Kaydı (H2 SQL)
  Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/users `
  -ContentType "application/json" `
  -Body '{"username":"ali","email":"ali@test.com","password":"1234","gameRank":"Gold"}'

  3. Kullanıcıları Listeleme
  Invoke-RestMethod http://localhost:8080/api/users


  4. Matchmaking Kuyruğuna Ekleme (Redis)
  Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/matchmaking/join `
  -ContentType "application/json" -Body '{"playerId":"1","game":"VALORANT","rank":"Gold"}'

  Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/matchmaking/join `
  -ContentType "application/json" -Body '{"playerId":"2","game":"VALORANT","rank":"Silver"}'

  5. Eşleştirme ve Lobi Kurma
  Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/matchmaking/match/VALORANT


  6. Maç Raporu Kaydetme (MongoDB)
  $body = @{ matchId = "m-001"; game = "VALORANT"; winnerId = 1; playerStats = @{ "1" = @{ kills = 22; deaths = 10 }; "2" = @{ kills = 15; deaths = 18 } } } | ConvertTo-Json -Depth 5

  Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/match-reports -ContentType "application/json" -Body $body


  7. Turnuva Oluşturma (H2 SQL)
  Invoke-RestMethod -Method POST -Uri http://localhost:8080/api/tournaments `
  -ContentType "application/json" -Body '{"name":"Bahar Kupasi","game":"VALORANT"}'
```

