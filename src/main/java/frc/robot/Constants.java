package frc.robot;

import edu.wpi.first.wpilibj.util.Units;

public final class Constants {
// Motors IDs
    //Base
    public static final int frontLeftDriveId = 9; 
    public static final int frontLeftCANifierId = 9; 
    public static final int frontLeftSteerId = 8;
    public static final int frontRightDriveId = 7; 
    public static final int frontRightCANifierId = 11; 
    public static final int frontRightSteerId = 5; 
    public static final int backLeftDriveId = 6; 
    public static final int backLeftCANifierId = 10; 
    public static final int backLeftSteerId = 3;
    public static final int backRightDriveId = 1; 
    public static final int backRightCANifierId = 12; 
    public static final int backRightSteerId = 4; 

    //FLywheel
    public static final int KFlywheelTopTalon = 2;      //confirmed
    public static final int KFlywheelBottomTalon = 10;   //confirmed

    //Funnel
    public static final int KFunnelSpark = 3;       //confirmed

    //Storage
    public static final int KStorageFrontSpark = 2; //confirmed
    public static final int KStorageBackSpark = 1;  //confirmed
    public static final int KBallSensor1 = 1;       //confirmed
    //public static final int KBallSensor2 = 14;

    //Intake
    public static final int KIntakeSpark = 0;

// Default PWM Values
    //Funnel
    public static final double KFunnelPWM = 0.6;
    public static final double KStoragePWM = 0.7;

    //Intake
    public static final double KIntakePWM = 1;

//Base Constants
    public static final double kMaxSpeed = Units.feetToMeters(13.6) / 100; // 20 feet per second
    public static final double kMaxAngularSpeed = Math.PI; // 1/2 rotation per second
    public static final double ticksPerRevolution = 4096;
    public static double feildCalibration = 0;
    public static double frontLeftOffset = 0;
    public static double frontRightOffset = 0;
    public static double backLeftOffset = 0;
    public static double backRightOffset = 0;
    
}
