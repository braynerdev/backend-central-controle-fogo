package central_controle_fogo.com.backend_central_controle_fogo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class RsaKeyProperties {

    @Value("${jwt.public.key.base64:}")
    private String publicKeyBase64;

    @Value("${jwt.private.key.base64:}")
    private String privateKeyBase64;

    @Value("${jwt.public.key:#{null}}")
    private Resource publicKeyResource;

    @Value("${jwt.private.key:#{null}}")
    private Resource privateKeyResource;

   
    public RSAPublicKey getPublicKey() throws Exception {
        try {
            
            if (publicKeyBase64 != null && !publicKeyBase64.isEmpty()) {
                return loadPublicKeyFromBase64(publicKeyBase64);
            }


            if (publicKeyResource != null && publicKeyResource.exists()) {
                return loadPublicKeyFromResource(publicKeyResource);
            }

            throw new IllegalStateException("No public key found. Set JWT_PUBLIC_KEY_BASE64 or provide classpath:app.pub");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load RSA public key", e);
        }
    }


    public RSAPrivateKey getPrivateKey() throws Exception {
        try {
            
            if (privateKeyBase64 != null && !privateKeyBase64.isEmpty()) {
                return loadPrivateKeyFromBase64(privateKeyBase64);
            }

            if (privateKeyResource != null && privateKeyResource.exists()) {
                return loadPrivateKeyFromResource(privateKeyResource);
            }

            throw new IllegalStateException("No private key found. Set JWT_PRIVATE_KEY_BASE64 or provide classpath:app.key");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load RSA private key", e);
        }
    }

    private RSAPublicKey loadPublicKeyFromBase64(String base64Key) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(base64Key);
        String pemContent = new String(keyBytes);
        String publicKeyPEM = pemContent
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(publicKeyPEM);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(spec);
    }

    private RSAPrivateKey loadPrivateKeyFromBase64(String base64Key) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(base64Key);
        String pemContent = new String(keyBytes);
        String privateKeyPEM = pemContent
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replace("-----BEGIN RSA PRIVATE KEY-----", "")
                .replace("-----END RSA PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(privateKeyPEM);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) keyFactory.generatePrivate(spec);
    }

    private RSAPublicKey loadPublicKeyFromResource(Resource resource) throws Exception {
        String content = new String(resource.getInputStream().readAllBytes());
        String publicKeyPEM = content
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(publicKeyPEM);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(spec);
    }

    private RSAPrivateKey loadPrivateKeyFromResource(Resource resource) throws Exception {
        String content = new String(resource.getInputStream().readAllBytes());
        String privateKeyPEM = content
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replace("-----BEGIN RSA PRIVATE KEY-----", "")
                .replace("-----END RSA PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(privateKeyPEM);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) keyFactory.generatePrivate(spec);
    }
}
