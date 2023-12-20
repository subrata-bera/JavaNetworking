// write a java program using java to Implement CRC error checking mechanism

public class CRCEncoderDecoder {

    // Generator polynomial: x^3 + x + 1
    private static final int[] GENERATOR_POLYNOMIAL = {1, 0, 1, 1};
    private static final int GENERATOR_DEGREE = GENERATOR_POLYNOMIAL.length - 1;

    public static void main(String[] args) {
        // Input data to be transmitted
        int[] inputData = {1, 0, 1, 0, 1, 1, 0, 1}; // Example data

        System.out.println("Original Data:");
        printArray(inputData);

        // Append zeros for CRC bits
        int[] encodedData = encodeData(inputData);

        System.out.println("\nData with CRC:");
        printArray(encodedData);

        // Simulate transmission by introducing errors
        int[] receivedData = simulateTransmission(encodedData);

        System.out.println("\nReceived Data (with Errors):");
        printArray(receivedData);

        // Check for errors and correct if possible
        boolean isErrorDetected = checkForErrors(receivedData);
        if (isErrorDetected) {
            System.out.println("\nError Detected! Attempting to correct...");
            correctErrors(receivedData);
        } else {
            System.out.println("\nNo Error Detected.");
        }

        System.out.println("\nFinal Received Data:");
        printArray(receivedData);
    }

    private static int[] encodeData(int[] inputData) {
        int[] dataWithCRC = new int[inputData.length + GENERATOR_DEGREE];
        System.arraycopy(inputData, 0, dataWithCRC, 0, inputData.length);

        // Perform CRC encoding
        for (int i = 0; i < inputData.length; i++) {
            if (dataWithCRC[i] == 1) {
                for (int j = 0; j < GENERATOR_DEGREE; j++) {
                    dataWithCRC[i + j] ^= GENERATOR_POLYNOMIAL[j];
                }
            }
        }

        return dataWithCRC;
    }

    private static int[] simulateTransmission(int[] data) {
        // Simulate transmission errors by flipping a bit
        int[] receivedData = data.clone();
        int errorBitIndex = 3; // Index of the bit to be flipped (just for example)
        receivedData[errorBitIndex] ^= 1;
        return receivedData;
    }

    private static boolean checkForErrors(int[] receivedData) {
        // Perform CRC checking
        for (int i = 0; i <= receivedData.length - GENERATOR_DEGREE; i++) {
            if (receivedData[i] == 1) {
                for (int j = 0; j < GENERATOR_DEGREE; j++) {
                    receivedData[i + j] ^= GENERATOR_POLYNOMIAL[j];
                }
            }
        }

        // If there is any remainder after checking, an error is detected
        for (int i = receivedData.length - GENERATOR_DEGREE; i < receivedData.length; i++) {
            if (receivedData[i] != 0) {
                return true; // Error detected
            }
        }

        return false; // No error detected
    }

    private static void correctErrors(int[] receivedData) {
        // In this simple example, we won't implement error correction.
        // In a real-world scenario, an error correction algorithm would be applied.
        System.out.println("Error correction not implemented in this example.");
    }

    private static void printArray(int[] array) {
        for (int bit : array) {
            System.out.print(bit);
        }
        System.out.println();
    }
}
