/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Edwin
 */

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public final class PasswordUtils {
    
    private PasswordUtils() {} // Clase de utilidad, no instanciable

    /**
     * Genera un hash seguro de la contraseña usando salt
     * @param password Arreglo de caracteres con la contraseña
     * @param salt Salt en formato Base64
     * @return Hash en Base64
     */
    public static String hashPassword(char[] password, String salt) {
        try {
            // 1. Decodificar el salt de Base64
            byte[] saltBytes = Base64.getDecoder().decode(salt);
            
            // 2. Configurar el algoritmo de hash
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            // 3. Aplicar el salt
            md.update(saltBytes);
            
            // 4. Convertir password a bytes con codificación explícita
            byte[] passwordBytes = new String(password).getBytes(StandardCharsets.UTF_8);
            
            // 5. Calcular el hash
            byte[] hashedPassword = md.digest(passwordBytes);
            
            // 6. Limpiar arrays sensibles
            Arrays.fill(passwordBytes, (byte) 0);
            Arrays.fill(saltBytes, (byte) 0);
            
            // 7. Devolver hash en Base64
            return Base64.getEncoder().encodeToString(hashedPassword);
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error en el algoritmo de hash", e);
        }
    }

    /**
     * Genera un salt seguro
     * @return Salt en formato Base64
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16]; // 128 bits
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}