package frc.robot;

// import the necessary packages for I2C
import edu.wpi.first.wpilibj.I2C;

/**
 * The I2CHandler class represents a handler for I2C communication on a specific device.
 */
public class I2CHandler {
    private I2C i2c;
    private int deviceAddress;
    private int length;

    /**
     * Constructs a new I2CHandler object with the specified device address and length.
     * 
     * @param deviceAddress the address of the I2C device
     * @param length the length of the data to be read or written
     */
    public I2CHandler(int deviceAddress, int length) {
        this.deviceAddress = deviceAddress;
        this.length = length;
        i2c = new I2C(I2C.Port.kOnboard, deviceAddress);
    }

    /**
     * Sets the length of the data to be read or written.
     * 
     * @param length the length of the data
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Returns the length of the data to be read or written.
     * 
     * @return the length of the data
     */
    public int getLength() {
        return length;
    }

    /**
     * Retrieves data from the I2C device starting at the specified byte address.
     * 
     * @param byteAddress the starting byte address
     * @return an array of bytes containing the retrieved data
     */
    public byte[] retrieveData(int byteAddress) {
        byte[] data = new byte[length];
        i2c.read(byteAddress, length, data);
        return data;
    }

    /**
     * Sends data to the I2C device at the specified byte address.
     * 
     * @param byteAddress the byte address to write to
     * @param data the data to be sent
     */
    public void sendData(int byteAddress, int data) {
        i2c.write(byteAddress, data);
    }

    /**
     * Returns a string representation of the I2CHandler object.
     * 
     * @return a string representation of the object
     */
    public String toString() {
        return "I2C device at " + deviceAddress + " with length " + length;
    }
}