package com.ptesa.xsdxslvalidation;

import com.ptesa.xsdxslvalidation.app.InterfazApp;
import com.ptesa.xsdxslvalidation.util.EncryptAlgorithmV2;

public class XsdXslValidationApplication {

//    public static void main(String[] args) {
//        InterfazApp app = new InterfazApp();
//        app.setVisible(true);
//    }

    public static void main(String[] args) {
//        String encryptionKeyString = "6UEBd^V1*^HuG*Tz"; // DEVELOPMENT ACTUAL ---> Key PTESA: gRyvLAFXGH3WXYkDq/5UkzTlqCqS2krOZoLFFijoqCPaQvDqlzQ=
//        String encryptionKeyString = "6UEBd^V1*^HuG*Tz"; // DEVELOPMENT NEW    ---> Key PTESA: 601dZcDcZ7tZOxoq6qkCMCnz+qLMa3FErdfuUrCipqN/8sC2PaA=
        String encryptionKeyString = "iVP6YgJ$Cx!QMW5I"; // PRODUCTION    ---> Key PTESA: LESWAVpJ9ZHepzN++jy2uSIPiAhYe24HznN582A/5lErp6Fu3+A=
        String key = "ZURkR2gVSu";
        try {
            String encryptKey = EncryptAlgorithmV2.encrypt(key, encryptionKeyString);
            System.out.println("Encrypt: " + encryptKey);
            String decryptKey = EncryptAlgorithmV2.decrypt("LESWAVpJ9ZHepzN++jy2uSIPiAhYe24HznN582A/5lErp6Fu3+A=", encryptionKeyString);
            System.out.println("Decrypt: " + decryptKey);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
