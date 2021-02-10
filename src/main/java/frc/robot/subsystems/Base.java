
package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import frc.robot.controller.CalculateSwerve;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Base extends SubsystemBase {
  //Creating the Talons
  private final TalonFX leftFrontSpeed, leftBackSpeed, rightBackSpeed, rightFrontSpeed;
  private final TalonFX leftFrontAngle, leftBackAngle, rightBackAngle, rightFrontAngle;

  //Creating the Calculator
  private final CalculateSwerve calculateSwerve;
  public Base() {
    // Instantiating the Talons
    leftFrontSpeed = new TalonFX(KLeftFrontSpeedTalon);
    leftBackSpeed = new TalonFX(KLeftBackSpeedTalon);
    rightFrontSpeed = new TalonFX(KRightFrontSpeedTalon);
    rightBackSpeed = new TalonFX(KRightBackSpeedTalon);

    leftFrontAngle = new TalonFX(KLeftFrontAngleTalon);
    leftBackAngle = new TalonFX(KLeftBackAngleTalon);
    rightFrontAngle = new TalonFX(KRightFrontAngleTalon);
    rightBackAngle = new TalonFX(KRightBackAngleTalon);

    // Instantiatiing the Calculator
    calculateSwerve = new CalculateSwerve(19.5, 23.5); 

    //Set brake mode
    leftBackSpeed.setNeutralMode(NeutralMode.Brake);
    leftFrontSpeed.setNeutralMode(NeutralMode.Brake);
    rightBackSpeed.setNeutralMode(NeutralMode.Brake);
    rightFrontSpeed.setNeutralMode(NeutralMode.Brake);

    //Set brake mode
    leftBackAngle.setNeutralMode(NeutralMode.Brake);
    leftFrontAngle.setNeutralMode(NeutralMode.Brake);
    rightBackAngle.setNeutralMode(NeutralMode.Brake);
    rightFrontAngle.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
  }

  @Override
  public void simulationPeriodic() {
  }
}