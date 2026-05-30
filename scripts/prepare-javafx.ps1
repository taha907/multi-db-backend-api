# JavaFX jar dosyalarini .m2 klasorunden javafx-lib'e kopyalar (Cursor icin)
$version = "21.0.2"
$modules = @("javafx-base", "javafx-controls", "javafx-graphics")
$dest = Join-Path $PSScriptRoot "..\javafx-lib"
$dest = [System.IO.Path]::GetFullPath($dest)

New-Item -ItemType Directory -Force -Path $dest | Out-Null

$m2 = Join-Path $env:USERPROFILE ".m2\repository\org\openjfx"
if (-not (Test-Path $m2)) {
    Write-Host "HATA: Maven kutuphanesi yok. Cursor'da projeyi acip Java import bitene kadar bekleyin."
    exit 1
}

$copied = 0
foreach ($m in $modules) {
    $dir = Join-Path $m2 "$m\$version"
    if (-not (Test-Path $dir)) {
        Write-Host "Eksik: $dir"
        continue
    }
    Get-ChildItem $dir -Filter "*.jar" |
        Where-Object { $_.Name -notmatch "sources|javadoc" } |
        ForEach-Object {
            Copy-Item $_.FullName -Destination $dest -Force
            $copied++
        }
}

if ($copied -eq 0) {
    Write-Host "HATA: JavaFX jar bulunamadi. Once projeyi Cursor'da acin (Maven indirsin)."
    exit 1
}

Write-Host "Tamam: $copied jar -> $dest"
