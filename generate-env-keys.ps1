# PowerShell script to generate base64 encoded RSA keys for environment variables
# Run this script to generate the values for JWT_PUBLIC_KEY_BASE64 and JWT_PRIVATE_KEY_BASE64

Write-Host "==================================================" -ForegroundColor Cyan
Write-Host "   Generating Base64 Encoded RSA Keys" -ForegroundColor Cyan
Write-Host "==================================================" -ForegroundColor Cyan
Write-Host ""

$publicKeyPath = "src\main\resources\app.pub"
$privateKeyPath = "src\main\resources\app.key"

# Check if keys exist
if (-not (Test-Path $publicKeyPath)) {
    Write-Host "ERROR: Public key not found at $publicKeyPath" -ForegroundColor Red
    Write-Host "Please generate RSA keys first using:" -ForegroundColor Yellow
    Write-Host "  openssl genrsa -out src/main/resources/app.key 2048" -ForegroundColor Yellow
    Write-Host "  openssl rsa -in src/main/resources/app.key -pubout -out src/main/resources/app.pub" -ForegroundColor Yellow
    exit 1
}

if (-not (Test-Path $privateKeyPath)) {
    Write-Host "ERROR: Private key not found at $privateKeyPath" -ForegroundColor Red
    exit 1
}

Write-Host "Reading RSA keys..." -ForegroundColor Green

# Read and encode public key
$publicKeyBytes = [System.IO.File]::ReadAllBytes($publicKeyPath)
$publicKeyBase64 = [Convert]::ToBase64String($publicKeyBytes)

# Read and encode private key
$privateKeyBytes = [System.IO.File]::ReadAllBytes($privateKeyPath)
$privateKeyBase64 = [Convert]::ToBase64String($privateKeyBytes)

Write-Host ""
Write-Host "==================================================" -ForegroundColor Cyan
Write-Host "   Base64 Encoded Keys Generated" -ForegroundColor Cyan
Write-Host "==================================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Copy these values to your Render environment variables:" -ForegroundColor Yellow
Write-Host ""
Write-Host "JWT_PUBLIC_KEY_BASE64=" -ForegroundColor Green -NoNewline
Write-Host $publicKeyBase64
Write-Host ""
Write-Host "JWT_PRIVATE_KEY_BASE64=" -ForegroundColor Green -NoNewline
Write-Host $privateKeyBase64
Write-Host ""
Write-Host "==================================================" -ForegroundColor Cyan
Write-Host ""

# Optionally save to a file
$outputFile = ".env.render"
$envContent = @"
# Generated RSA Keys for Render Deployment
# DO NOT COMMIT THIS FILE TO VERSION CONTROL
# Copy these values to Render Dashboard -> Environment Variables

JWT_PUBLIC_KEY_BASE64=$publicKeyBase64
JWT_PRIVATE_KEY_BASE64=$privateKeyBase64
"@

$envContent | Out-File -FilePath $outputFile -Encoding UTF8

Write-Host "Keys also saved to: $outputFile" -ForegroundColor Green
Write-Host "IMPORTANT: Do not commit $outputFile to version control!" -ForegroundColor Red
Write-Host ""
