package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

import com.revrobotics.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.controller.PIDController;

public class Flywheel extends SubsystemBase {

  private final CANSparkMax flywheelTop, flywheelBottom;
  private final CANEncoder topEncoder, bottomEncoder;
  private SlewRateLimiter topLimiter, bottomLimiter;
  private double topPWM = 0, bottomPWM = 0;

  public Flywheel() 
  {
     // Create SparkMax objects
     flywheelTop = new CANSparkMax(KFlywheelTopSpark, CANSparkMaxLowLevel.MotorType.kBrushless);
     flywheelBottom = new CANSparkMax(KFlywheelBottomSpark, CANSparkMaxLowLevel.MotorType.kBrushless);
 
     // Configure spark. Factory defaults are restored, so every necessary configuration is included here
     flywheelTop.restoreFactoryDefaults();
     flywheelBottom.restoreFactoryDefaults();
     flywheelTop.setInverted(true);
     flywheelBottom.setInverted(false);
 
     // Burn configurations to flash memory. This is where the sparks get configured upon being rebooted.
     // This protects against wrong configurations if the robot reboots during a match
     flywheelTop.burnFlash();
     flywheelBottom.burnFlash();
 
     // Get encoder objects from each spark
     topEncoder = flywheelTop.getEncoder();
     bottomEncoder = flywheelBottom.getEncoder();

      // Slew rate limits to prevent the motor PWM values from changing too fast
      topLimiter = new SlewRateLimiter(4);
      bottomLimiter = new SlewRateLimiter(4);
  }

  @Override
  public void periodic() 
  {

  }

  public void move(double topPWM, double bottomPWM) {
    topPWM = topLimiter.calculate(topPWM);
    bottomPWM = bottomLimiter.calculate(bottomPWM);

    this.topPWM = topPWM;
    this.bottomPWM = bottomPWM;

    flywheelTop.set(topPWM);
    flywheelBottom.set(bottomPWM);
  }

  public double getTopVel() {
    return topEncoder.getVelocity();
  }

  public double getBottomVel() {
    return bottomEncoder.getVelocity();
  }

  @Override
  public void simulationPeriodic() 
  {

  }
}