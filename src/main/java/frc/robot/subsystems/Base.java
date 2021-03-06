// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.motorcontrol.can.TalonFX;


import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

public class Base extends SubsystemBase {
   
  public static AHRS gyro = new AHRS(SPI.Port.kMXP);

  private SwerveDriveKinematics kinematics;

  private SwerveModuleMK3[] modules;

  public Base() {
    kinematics = new SwerveDriveKinematics(
      new Translation2d(
        Units.inchesToMeters(14.5),
        Units.inchesToMeters(14.5)
      ),
      new Translation2d(
        Units.inchesToMeters(14.5),
        Units.inchesToMeters(-14.5)
      ),
      new Translation2d(
        Units.inchesToMeters(-14.5),
        Units.inchesToMeters(14.5)
      ),
      new Translation2d(
        Units.inchesToMeters(-14.5),
        Units.inchesToMeters(-14.5)
      )
    );

  modules = new SwerveModuleMK3[] {

    new SwerveModuleMK3(new TalonFX(frontLeftDriveId), new TalonFX(frontLeftSteerId), new CANifier(frontLeftCANifierId), Rotation2d.fromDegrees(frontLeftOffset)), // Front Left
    new SwerveModuleMK3(new TalonFX(frontRightDriveId), new TalonFX(frontRightSteerId), new CANifier(frontRightCANifierId), Rotation2d.fromDegrees(frontRightOffset)), // Front Right
    new SwerveModuleMK3(new TalonFX(backLeftDriveId), new TalonFX(backLeftSteerId), new CANifier(backLeftCANifierId), Rotation2d.fromDegrees(backLeftOffset)), // Back Left
    new SwerveModuleMK3(new TalonFX(backRightDriveId), new TalonFX(backRightSteerId), new CANifier(backRightCANifierId), Rotation2d.fromDegrees(backRightOffset))  // Back Right

  };

  // SmartDashboard.putNumber("Base kP", 0.0);
  // SmartDashboard.putNumber("Base kI", 0.0);
  // SmartDashboard.putNumber("Base kD", 0.0);
  
  gyro.reset(); 
  }

  /**
   * Method to drive the robot using joystick info.
   *
   * @param xSpeed Speed of the robot in the x direction (forward).
   * @param ySpeed Speed of the robot in the y direction (sideways).
   * @param rot Angular rate of the robot.
   * @param fieldRelative Whether the provided x and y speeds are relative to the field.
   * @param calibrateGyro button to recalibrate the gyro offset
   */
  public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
    
    //if(calibrateGyro){
      //gyro.reset(); //recalibrates gyro offset
    //}

  SwerveModuleState[] states =
    kinematics.toSwerveModuleStates(
      fieldRelative
        ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rot, Rotation2d.fromDegrees(-gyro.getAngle()))
        : new ChassisSpeeds(xSpeed, ySpeed, rot));
  SwerveDriveKinematics.normalizeWheelSpeeds(states, kMaxSpeed);
  for (int i = 0; i < states.length; i++) {
    SwerveModuleMK3 module = modules[i];
    SwerveModuleState state = states[i];
    module.setDesiredState(state);
    //SmartDashboard.putNumber("gyro Angle", gyro.getAngle());
  }
}

public void resetGyro() {
  gyro.reset(); //recalibrates gyro offset
}

public void zeroEncoder() {
  modules[0].zeroEncoders();
  modules[1].zeroEncoders();
  modules[2].zeroEncoders();
  modules[3].zeroEncoders();
}

@Override
public void periodic() {
  // SmartDashboard.putNumber("Left Front Raw Angle", modules[0].getRawAngle());
  // SmartDashboard.putNumber("Right Front Raw Angle", modules[1].getRawAngle());
  // SmartDashboard.putNumber("Left Back Raw Angle", modules[2].getRawAngle());
  // SmartDashboard.putNumber("Right Back Raw Angle", modules[3].getRawAngle());
  // SmartDashboard.putNumber("Desired Ticks", modules[3].getDesiredTicks());
  // SmartDashboard.putNumber("Closed Loop Target", modules[3].getSelectedSensonPosition());

  //getting angle motor PID gains from SmartDashboard and setting them
  // setModuleGains(SmartDashboard.getNumber("Base kP", 0.0), SmartDashboard.getNumber("Base kI", 0.0), SmartDashboard.getNumber("Base kD", 0.0));
}

public void setModuleGains(double kP, double kI, double kD){
  //used for PID gains tuning
  // modules[1].setAnglePIDGains(kP, kI, kD);
  // modules[2].setAnglePIDGains(kP, kI, kD);
  // modules[3].setAnglePIDGains(kP, kI, kD);
  // modules[4].setAnglePIDGains(kP, kI, kD);
}

@Override
public void simulationPeriodic() {
  // This method will be called once per scheduler run during simulation
}
}