#!/bin/bash
# Bash script to generate base64 encoded RSA keys for environment variables
# Run this script to generate the values for JWT_PUBLIC_KEY_BASE64 and JWT_PRIVATE_KEY_BASE64

echo "=================================================="
echo "   Generating Base64 Encoded RSA Keys"
echo "=================================================="
echo ""

PUBLIC_KEY_PATH="src/main/resources/app.pub"
PRIVATE_KEY_PATH="src/main/resources/app.key"

# Check if keys exist
if [ ! -f "$PUBLIC_KEY_PATH" ]; then
    echo "ERROR: Public key not found at $PUBLIC_KEY_PATH"
    echo "Please generate RSA keys first using:"
    echo "  openssl genrsa -out src/main/resources/app.key 2048"
    echo "  openssl rsa -in src/main/resources/app.key -pubout -out src/main/resources/app.pub"
    exit 1
fi

if [ ! -f "$PRIVATE_KEY_PATH" ]; then
    echo "ERROR: Private key not found at $PRIVATE_KEY_PATH"
    exit 1
fi

echo "Reading RSA keys..."

# Read and encode public key
PUBLIC_KEY_BASE64=$(cat "$PUBLIC_KEY_PATH" | base64 -w 0)

# Read and encode private key
PRIVATE_KEY_BASE64=$(cat "$PRIVATE_KEY_PATH" | base64 -w 0)

echo ""
echo "=================================================="
echo "   Base64 Encoded Keys Generated"
echo "=================================================="
echo ""
echo "Copy these values to your Render environment variables:"
echo ""
echo "JWT_PUBLIC_KEY_BASE64=$PUBLIC_KEY_BASE64"
echo ""
echo "JWT_PRIVATE_KEY_BASE64=$PRIVATE_KEY_BASE64"
echo ""
echo "=================================================="
echo ""

# Optionally save to a file
OUTPUT_FILE=".env.render"
cat > "$OUTPUT_FILE" << EOF
# Generated RSA Keys for Render Deployment
# DO NOT COMMIT THIS FILE TO VERSION CONTROL
# Copy these values to Render Dashboard -> Environment Variables

JWT_PUBLIC_KEY_BASE64=$PUBLIC_KEY_BASE64
JWT_PRIVATE_KEY_BASE64=$PRIVATE_KEY_BASE64
EOF

echo "Keys also saved to: $OUTPUT_FILE"
echo "IMPORTANT: Do not commit $OUTPUT_FILE to version control!"
echo ""
