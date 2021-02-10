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
  private PIDController topController;
  private PIDController bottomController;
  
  private double topPWMOutput = 0, bottomPWMOutput = 0;
  private double topSetpoint = 0, bottomSetpoint = 0;
  
  private double topFlywheel_kP = 0;
  private double topFlywheel_kI = 0;
  private double topFlywheel_kD = 0;
  private double bottomFlywheel_kP = 0;
  private double bottomFlywheel_kI = 0;
  private double bottomFlywheel_kD = 0;

  // private double topFlywheel_kF = 0;
  // private double bottomFlywheel_kF = 0;

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

      //* PID Stuff *//
      topController = new PIDController(topFlywheel_kP, topFlywheel_kI, topFlywheel_kD);
      bottomController = new PIDController(bottomFlywheel_kP, bottomFlywheel_kI, bottomFlywheel_kD);
      topController.setTolerance(5, 10);
      bottomController.setTolerance(5, 10);

  }

  @Override
  public void periodic() 
  {

  }

  public void move(double topPWM, double bottomPWM) 
  {
    topPWMOutput = topLimiter.calculate(topPWM);
    bottomPWMOutput = bottomLimiter.calculate(bottomPWM);;

    flywheelTop.set(topPWMOutput);
    flywheelBottom.set(bottomPWMOutput);
  }

  public void PIDMove() 
  {
    topPWMOutput = topLimiter.calculate(topController.calculate(topEncoder.getVelocity(), topSetpoint));
    bottomPWMOutput = bottomLimiter.calculate(bottomController.calculate(bottomEncoder.getVelocity(), bottomSetpoint));

    //implement feedforward?

    flywheelTop.set(topPWMOutput);
    flywheelBottom.set(bottomPWMOutput);
  }

  public double getTopVel() {
    return topEncoder.getVelocity();
  }

  public double getBottomVel() {
    return bottomEncoder.getVelocity();
  }

  public double getTopSetpoint() {
    return topController.getSetpoint();
  }
  public double getBottomSetpoint() {
    return bottomController.getSetpoint();
  }

  public boolean atTopSetpoint() {
    return topController.atSetpoint();
  }

  public boolean atBottomSetpoint() {
    return bottomController.atSetpoint();
  }

  public void setSetpoints(double topSetpoint, double bottomSetpoint) {
    this.topSetpoint = topSetpoint;
    this.bottomSetpoint = bottomSetpoint;
  }

  public void reset() {
    topController.reset();
    bottomController.reset();
  }

  public void setTopConstants(double Kp, double Ki, double Kd, double Kf) {
    topController.setP(Kp);
    topController.setI(Ki);
    topController.setD(Kd);
    // topController.setF(Kf);
  }

  public void setBottomConstants(double Kp, double Ki, double Kd, double Kf) {
    bottomController.setP(Kp);
    bottomController.setI(Ki);
    bottomController.setD(Kd);
    // bottomController.setF(Kf);
  }

  @Override
  public void simulationPeriodic() 
  {

  }
}