package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;



import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.controller.PIDController;

public class Flywheel extends SubsystemBase {

  //Motor controller, Moror encoder, PID controller initialization 
  private final TalonFX flywheelTop, flywheelBottom;
  private SlewRateLimiter topLimiter, bottomLimiter;
  private PIDController topController, bottomController;

  // this is constant for Talon FX
  private final int kUnitsPerRevolution = 2048; 

  //Talon built-in sensor readings
  private double topSenVel;
  private double bottomSenVel;
  private double topSenVelPercent;
  private double bottomSenVelPercent;
  private double topSenPos;
  private double bottomSenPos;
  private double topRotPerSec;
  private double bottomRotPerSec;
  
  //Motor output initialization
  private double topPWMOutput = 0, bottomPWMOutput = 0;
  private double topSetpoint = 0, bottomSetpoint = 0;
  
  //PID constatns
  private double flywheelTop_kP = 0.16;
  private double flywheelTop_kI = 0.0022;
  private double flywheelTop_kD = 0.1;
  private double flywheelTop_kF = 1;
  private double flywheelBottom_kP = 0.4;
  private double flywheelBottom_kI = 0.02;
  private double flywheelBottom_kD = 0.095;
  private double flywheelBottom_kF = 1;

  public Flywheel() 
  {
     // Create TalonFX objects
     flywheelTop = new TalonFX(KFlywheelTopTalon);
     flywheelBottom = new TalonFX(KFlywheelBottomTalon);
 
    //Factory Default all hardware to prevent unexpected behaviour
		flywheelTop.configFactoryDefault();
    flywheelBottom.configFactoryDefault();
    
     //inverting the bottom flywheel's spin direction
     flywheelBottom.setInverted(true);

     //setting talons' neutral mode
     flywheelTop.setNeutralMode(NeutralMode.Brake);     //Note: Coast mode might be better?
     flywheelBottom.setNeutralMode(NeutralMode.Brake);
 
    //  flywheelTop.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,0,0);
    //  flywheelBottom.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,0,0);

      //Slew rate limits to prevent the motor PWM values from changing too fast
      topLimiter = new SlewRateLimiter(4);
      bottomLimiter = new SlewRateLimiter(4);

      //get sensor raw values (packed in the form of TalonFXSensorCollection objects)
      TalonFXSensorCollection topSensorVals = flywheelTop.getSensorCollection();
      TalonFXSensorCollection bottomSensorVals = flywheelBottom.getSensorCollection();

      topSenPos = topSensorVals.getIntegratedSensorPosition(); /* position units */
      bottomSenPos = bottomSensorVals.getIntegratedSensorPosition(); 

      //extracting raw sensor values from TalonFXSensorCollection objects
		  topSenVel = topSensorVals.getIntegratedSensorVelocity(); /* position units per 100ms */
      bottomSenVel = bottomSensorVals.getIntegratedSensorVelocity();
      
      topSenVelPercent = topSenVel / 21630.0;
      bottomSenVelPercent = bottomSenVel / 21630.0;
      
      //conversion to per second
		  topRotPerSec = topSenVel / kUnitsPerRevolution * 10; /* scale per100ms to perSecond */
      bottomRotPerSec = bottomSenVel / kUnitsPerRevolution * 10;
      
      //Current limiting 
      flywheelTop.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 10, 15, 0.5));
      flywheelBottom.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 10, 15, 0.5));
      flywheelTop.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 20, 25, 1.0));
      flywheelBottom.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 20, 25, 1.0));
      
      //* PID Stuff *//
      topController = new PIDController(flywheelTop_kP, flywheelTop_kI, flywheelTop_kD);
      bottomController = new PIDController(flywheelBottom_kP, flywheelBottom_kI, flywheelBottom_kD);
      topController.setTolerance(5, 10);
      bottomController.setTolerance(5, 10);
      
      //create shuffleboard/smartdashboard input fields for pidf constants tuning
      // SmartDashboard.putNumber("Flywheel Top P", 0);
      // SmartDashboard.putNumber("Flywheel Top I", 0);
      // SmartDashboard.putNumber("Flywheel Top D", 0);
      // SmartDashboard.putNumber("Flywheel Top F", 0);
      // SmartDashboard.putNumber("Flywheel Bottom P", 0);
      // SmartDashboard.putNumber("Flywheel Bottom I", 0);
      // SmartDashboard.putNumber("Flywheel Bottom D", 0);
      // SmartDashboard.putNumber("Flywheel Bottom F", 0);

      SmartDashboard.putNumber("Top Flywheel Setpoint", 0.0);
      SmartDashboard.putNumber("Bottom Flywheel Setpoint", 0.0);
    }
    
    @Override
    public void periodic() 
    {
      //SmartDashboard prints for tuning
      // SmartDashboard.putNumber("Top Flywheel Output", topPWMOutput);
      // SmartDashboard.putNumber("Bottom Flywheel Output", bottomPWMOutput);
      // SmartDashboard.putNumber("Top Velocity (Percent)", getTopVelPercent());
      // SmartDashboard.putNumber("Bottom Velocity (Percent)", getBottomVelPercent());

      //grab pidf constants from shuffleboard/smartdashboard
      // smartDashSetTopConstants();
      // smartDashSetBottomConstants();

      // double topSetpoint = SmartDashboard.getNumber("Top Flywheel Setpoint", 0.0);
      // double bottomSetpoint = SmartDashboard.getNumber("Bottom Flywheel Setpoint", 0.0);
      // setSetpoints(topSetpoint, bottomSetpoint);
   
      //get sensor raw values (packed in the form of TalonFXSensorCollection objects)
      TalonFXSensorCollection topSensorVals = flywheelTop.getSensorCollection();
      TalonFXSensorCollection bottomSensorVals = flywheelBottom.getSensorCollection();
      
      //extracting raw sensor values from TalonFXSensorCollection objects
      topSenPos = topSensorVals.getIntegratedSensorPosition(); /* position units */
      bottomSenPos = bottomSensorVals.getIntegratedSensorPosition(); 
  
      topSenVel = topSensorVals.getIntegratedSensorVelocity(); /* position units per 100ms */
      bottomSenVel = bottomSensorVals.getIntegratedSensorVelocity();

      //Conversion from Talon unit to percent
      topSenVelPercent = topSenVel / 21630.0;
      bottomSenVelPercent = bottomSenVel / 21630.0;
      
      //conversion to per second
      topRotPerSec = topSenVel / kUnitsPerRevolution * 10; /* scale per100ms to perSecond */
      bottomRotPerSec = bottomSenVel / kUnitsPerRevolution * 10;
  
    }

  public void move(double topPWM, double bottomPWM) 
  {
    //using slewrate limiters to limit output values
    topPWMOutput = topLimiter.calculate(topPWM);
    bottomPWMOutput = bottomLimiter.calculate(bottomPWM);

    //send the output values to the motors
    flywheelTop.set(ControlMode.PercentOutput, topPWMOutput);
    flywheelBottom.set(ControlMode.PercentOutput, bottomPWMOutput);
  }

  /*move using PID controller and setpoints*/
  public void PIDMove() 
  {
    //calculate top and bottom output values using PID controller class
    topPWMOutput = topLimiter.calculate(topController.calculate(topSenVelPercent, topSetpoint))
                    +flywheelTop_kF * topSetpoint;
    bottomPWMOutput = bottomLimiter.calculate(bottomController.calculate(-bottomSenVelPercent, bottomSetpoint)) 
                      + flywheelBottom_kF * bottomSetpoint; //reversed rotation sensor input so the calculations would be right

    //send the output values to the motors
    flywheelTop.set(ControlMode.PercentOutput, topPWMOutput);
    flywheelBottom.set(ControlMode.PercentOutput, bottomPWMOutput);
  }

  public double getTopRotPerSec() {
    return topRotPerSec;
  }
  
  public double getBottomRotPerSec() {
    return bottomRotPerSec;
  }

  public double getTopVel() {
    return topSenVel;
  }

  public double getBottomVel() {
    return bottomSenVel;
  }

  public double getTopVelPercent() {
    return topSenVelPercent;
  }

  public double getBottomVelPercent() {
    return bottomSenVelPercent;
  }
  
  public double getTopPos() {
    return topSenPos;
  }

  public double getBottomPos() {
    return bottomSenPos;
  }

  public double getTopSetpoint() {
    return topController.getSetpoint();
  }
  public double getBottomSetpoint() {
    return bottomController.getSetpoint();
  }

  //PID controller method, check to see if top setpoint is reached
  public boolean atTopSetpoint() {
    return topController.atSetpoint();
  }

  //PID controller method, check to see if bottom setpoint is reached
  public boolean atBottomSetpoint() {
    return bottomController.atSetpoint();
  }

  //set top and bottom setpoints
  public void setSetpoints(double topSetpoint, double bottomSetpoint) {
    this.topSetpoint = topSetpoint;
    this.bottomSetpoint = bottomSetpoint;
  }

  public void setTopF(double kF)
  {
    flywheelTop_kF = kF;
  }

  public void setBottomF(double kF)
  {
    flywheelBottom_kF = kF;
  }

  //reset the top and bottom PID controllers
  public void reset() {
    topController.reset();
    bottomController.reset();
  }

  //getting the top flywheel PID constants for the top PID controller from smartdashboard
  public void smartDashSetTopConstants() {
    topController.setP(SmartDashboard.getNumber("Flywheel Top P", 0));
    topController.setI(SmartDashboard.getNumber("Flywheel Top I", 0));
    topController.setD(SmartDashboard.getNumber("Flywheel Top D", 0));
    setTopF(SmartDashboard.getNumber("Flywheel Top F", 0));
  }

  //getting the bottom flywheel PID constants for the top PID controller from smartdashboard
  public void smartDashSetBottomConstants() {
    bottomController.setP(SmartDashboard.getNumber("Flywheel Bottom P", 0));
    bottomController.setI(SmartDashboard.getNumber("Flywheel Bottom I", 0));
    bottomController.setD(SmartDashboard.getNumber("Flywheel Bottom D", 0));
    setBottomF(SmartDashboard.getNumber("Flywheel Bottom F", 0));
  }

  @Override
  public void simulationPeriodic() 
  {

  }
}