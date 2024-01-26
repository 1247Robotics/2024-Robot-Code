package frc.robot;

public class Definitions {
    public static final short flId = 3;
    public static final short frId = 2;
    public static final short blId = 4;
    public static final short brId = 1;

    public static final byte ACC_ADDRESS = 0x40;
    public static final byte ACC_RESET = 0x10;
    public static final byte ACC_PWR = 0x0D;
    public static final byte ACC_BW = 0x20;
    public static final byte ACC_RANGE = 0x35;
    public static final byte ACC_DATA = 0x02;
    public static final byte ACC_A_TO_READ = 6;

    public static final byte GYRO_ADDRESS = 0x68;
    public static final byte GYRO_DLPF_FS = 0x16;
    public static final byte GYRO_INT_CFG = 0x17;
    public static final byte GYRO_PWR_MGM = 0x3E;
    public static final byte GYRO_DATA = 0x1B;
    public static final byte GYRO_G_TO_READ = 8;
}