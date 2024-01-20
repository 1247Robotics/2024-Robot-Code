package frc.robot;

import frc.robot.I2CHandler;

public class IMU {
    private I2CHandler acc;
    private I2CHandler gyro;
    public IMU () {
        acc = new I2CHandler(Definitions.ACC_ADDRESS, Definitions.ACC_A_TO_READ);
        gyro = new I2CHandler(Definitions.GYRO_ADDRESS, Definitions.GYRO_G_TO_READ);
    }

    public void retrieveData() {
        byte[] accData = acc.retrieveData(Definitions.ACC_DATA);
        byte[] gyroData = gyro.retrieveData(Definitions.GYRO_DATA);
    }
    
}
