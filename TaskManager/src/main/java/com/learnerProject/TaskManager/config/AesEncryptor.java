//package com.learnerProject.TaskManager.config;
//
//import jakarta.persistence.AttributeConverter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import java.nio.charset.StandardCharsets;
//import java.security.InvalidAlgorithmParameterException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
//
//@Configuration
//public class AesEncryptor implements AttributeConverter<Object, String> {
//
//
//
//    @Value("${service.secret.encryption.key}")
//    String secretKey;
//
//
//    private static final String IV = "NC0V0$0T0L030RME"; // Fixed IV (should ideally be dynamic for security)
//    private static final String ALGORITHM = "AES/CBC/PKCS5Padding"; // Encryption algorithm
//
//    @Override
//    public String convertToDatabaseColumn(Object data) {
//        if (data == null) return null; // Handle null input
//
//        try {
//            Cipher cipher = Cipher.getInstance(ALGORITHM); // Get cipher instance
//            IvParameterSpec iv = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
//            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
//
//            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv); // Initialize cipher in encrypt mode
//
//            byte[] encryptedData = cipher.doFinal(data.toString().getBytes(StandardCharsets.UTF_8)); // Perform encryption
//            return Base64.getEncoder().encodeToString(encryptedData); // Convert to Base64 string
//        } catch (Exception e) {
//            throw new RuntimeException("Error encrypting data", e); // Unified exception handling
//        }
//    }
//    @Override
//    public Object convertToEntityAttribute(String encryptedData) {
//        if (encryptedData == null) return null; // Handle null input
//
//        try {
//            Cipher cipher = Cipher.getInstance(ALGORITHM);
//            IvParameterSpec iv = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
//            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
//
//            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv); // Initialize cipher in decrypt mode
//
//            byte[] decodedBytes = Base64.getDecoder().decode(encryptedData); // Decode Base64
//            byte[] decryptedData = cipher.doFinal(decodedBytes); // Perform decryption
//
//            return new String(decryptedData, StandardCharsets.UTF_8); // Convert back to String
//        } catch (Exception e) {
//            throw new RuntimeException("Error decrypting data", e);
//        }
//    }
//}
//}
