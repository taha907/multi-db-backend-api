# Performans Test Raporu

## Araclar

- **k6** – `performance/k6-load-test.js`
- **JMeter** – `performance/jmeter-gamermatch.jmx`

## On kosul

API calisiyor: `http://localhost:8080/api/health`

## k6 yuk testi

```bash
k6 run performance/k6-load-test.js
```

Senaryo: 5 → 15 sanal kullanici, health + users + tournaments endpointleri.

Sonuc dosyasi: `performance/k6-summary.json` (calistirinca olusur).

## JMeter

GUI ile `jmeter-gamermatch.jmx` acin veya:

```bash
jmeter -n -t performance/jmeter-gamermatch.jmx -l performance/results.jtl
```

20 thread, 30 saniye, GET health/users/tournaments.

## Sonuc tablosu (test sonrasi doldurun)

| Metrik | k6 | JMeter |
|--------|-----|--------|
| Toplam istek | | |
| Ort. sure (ms) | | |
| p95 (ms) | | |
| Hata % | | |
| req/s | | |

## Yorum

Sunum oncesi kendi bilgisayarinizda testi calistirip tabloyu gercek degerlerle doldurun.
