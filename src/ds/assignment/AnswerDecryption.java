package ds.assignment;

public class AnswerDecryption {
    public int decrypt(int encrypted, int secretKey) { 
        // Step 1: Convert the integer to a padded binary string
        String binaryString = Integer.toBinaryString(encrypted);
        
        // Step 2: Divide the binary string into blocks of three digits
        StringBuilder decryptedBinary = new StringBuilder();
        for (int i = binaryString.length() - 1; i >= 0; i -= 3) {
            int endIndex = i + 1;
            int startIndex = Math.max(i - 2, 0);
            String block = binaryString.substring(startIndex, endIndex);

            // Step 4: Convert the block back to its actual value
            int blockValue = Integer.parseInt(block, 2);
            blockValue -= (secretKey % 2);

            // Step 5: Convert the block value back to binary format
            String decryptedBlock = String.format("%3s", Integer.toBinaryString(blockValue)).replace(' ', '0');

            // Step 6: Append the decrypted block to the final decrypted binary string
            decryptedBinary.insert(0, decryptedBlock);

            // Convert secretKey to binary string
            String secKeyString = Integer.toBinaryString(secretKey);
            String secretKeyBinary = secKeyString.toString();
            // Shift the binary string to the right by one
            String shiftedBinary = secretKeyBinary.substring(1);
            // Update the secretKey with the shifted binary string
            if (shiftedBinary.equals("")) {
              secretKey = 0;
            } else {
              secretKey = Integer.parseInt(shiftedBinary, 2);
            }  
        }

        // Step 8: Convert the decrypted binary string back to an integer
        int decrypted = Integer.parseInt(decryptedBinary.toString(), 2);
        return decrypted;
    }

    public int getDecryptedNumber() {
        int encrypted = 17355;
        int secretKey = 7;
        int decrypted = decrypt(encrypted, secretKey);
        
        return decrypted;

    }
}
