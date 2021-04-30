package frc.robot.subsystems;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.controller.PIDController;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  
  // Intake PID
  // private PIDController intakeController;

  //Create victor
  private final CANSparkMax intake;
  private double intakePWM = 0;
  
  public Intake() {

     // instantiate victor
     intake = new CANSparkMax(KIntakeSpark, CANSparkMaxLowLevel.MotorType.kBrushless);

     // Configure spark. Factory defaults are restored, so every necessary configuration is included here
     intake.restoreFactoryDefaults();
     intake.setSmartCurrentLimit(30, 30, 3000);
     // Burn configurations to flash memory. This is where the sparks get configured upon being rebooted.
     // This protects against wrong configurations if the robot reboots during a match
     intake.burnFlash();
 
    //  intakeController = new PIDController(0, 0, 0, 0.0001, 0.02);

  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("intakeFrontPWM", getIntakePWM());
  }

  public void move(double PWM) {
    intakePWM = -PWM;
    intake.set(-PWM);
  }

  public double getIntakePWM(){
    return intakePWM;
  }

  @Override
  public void simulationPeriodic() {
  }
}