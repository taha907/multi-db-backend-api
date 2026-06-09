# Performans Test Raporu

## Araçlar

- **k6:** `performance/k6-load-test.js`
- **JMeter:** `performance/jmeter-gamermatch.jmx`

## Test ortamı

- Tarih: 9 Haziran 2026
- İşletim sistemi: Windows
- Test adresi: `http://localhost:8080`
- Trafik girişi: `gateway-service`
- Sağlık kontrolü: `GET /api/health` başarılı
- Test edilen endpoint'ler:
  - `GET /api/health`
  - `GET /api/users`
  - `GET /api/tournaments`

## k6 yük testi

Çalıştırılan komut:

```powershell
k6 run performance/k6-load-test.js
```

Senaryo:

- 10 saniyede 5 sanal kullanıcıya çıkış
- 20 saniyede 15 sanal kullanıcıya çıkış
- 10 saniyede 0 sanal kullanıcıya iniş
- Her iterasyonda 3 GET isteği

Sonuç dosyası: `performance/k6-summary.json`

## JMeter testi

JMeter senaryosu `performance/jmeter-gamermatch.jmx` içinde hazırdır:

```powershell
jmeter -n -t performance/jmeter-gamermatch.jmx -l performance/results.jtl
```

Bu makinede `jmeter` komutu PATH üzerinde bulunmadığı için JMeter testi çalıştırılamadı. JMeter kurulu bir ortamda yukarıdaki komutla 20 thread, 30 saniye senaryosu çalıştırılabilir.

## Sonuç tablosu

| Metrik | k6 |
|--------|----|
| Toplam HTTP isteği | 1767 
| Başarılı check sayısı | 1767 |
| Hatalı check sayısı | 0 | 
| Hata oranı | 0% | 
| Ortalama cevap süresi | 1.76 ms | 
| p95 cevap süresi | 3.59 ms | 
| Maksimum cevap süresi | 19.22 ms | 
| İstek hızı | 44.09 req/s | 
| Tamamlanan iterasyon | 589 |

## Değerlendirme

k6 testi `localhost:8080` üzerindeki gateway katmanına istek gönderir. Gateway, `/api/health`, `/api/users` ve `/api/tournaments` isteklerini ilgili mikroservislere yönlendirir.

Önceki ölçümde tüm HTTP istekleri beklenen 200 cevaplarını döndürdü. Hata oranı 0% olarak ölçüldü ve p95 cevap süresi 3.59 ms ile tanımlı `p(95)<2000` eşik değerinin oldukça altında kaldı.

JMeter dosyası projede yer almaktadır; ancak testin gerçekten çalıştırılması için JMeter'ın kurulu olduğu bir ortam gerekir.
