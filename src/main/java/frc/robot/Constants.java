package frc.robot;

public final class Constants {
// Motors IDs
    //Base
    public static final int KLeftFrontSpeedTalon = 0;
    public static final int KLeftBackSpeedTalon = 1;
    public static final int KRightFrontSpeedTalon = 2;
    public static final int KRightBackSpeedTalon = 3;
    public static final int KLeftFrontAngleTalon = 4;
    public static final int KLeftBackAngleTalon = 5;
    public static final int KRightFrontAngleTalon = 6;
    public static final int KRightBackAngleTalon = 7;

    //FLywheel
    public static final int KFlywheelTopTalon = 2;      //confirm
    public static final int KFlywheelBottomTalon = 1;   //confirm

    //Funnel
    public static final int KFunnelSpark = 10;

    //Storage
    public static final int KStorageFrontSpark = 2; //confirm
    public static final int KStorageBackSpark = 1;  //confirm
    public static final int KBallSensor1 = 1;       //confirm
    //public static final int KBallSensor2 = 14;

// Default PWM Values
    //Funnel
    public static final double KFunnelPWM = 1;
    public static final double KStoragePWM = 1;
}