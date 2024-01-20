package frc.robot;

// import the necessary packages for I2C
import edu.wpi.first.wpilibj.I2C;

public class I2CHandler {
    private I2C i2c;
    private int deviceAddress;
    private int length;

    public I2CHandler(int deviceAddress, int length) {
        this.deviceAddress = deviceAddress;
        this.length = length;
        i2c = new I2C(I2C.Port.kOnboard, deviceAddress);
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public byte[] retrieveData(int byteAddress) {
        byte[] data = new byte[length];
        i2c.read(byteAddress, length, data);
        return data;
    }

    public void sendData(int byteAddress, int data) {
        i2c.write(byteAddress, data);
    }

    public String toString() {
        return "I2C device at " + deviceAddress + " with length " + length;
    }


}