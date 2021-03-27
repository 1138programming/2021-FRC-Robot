package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;


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
  private double topSenPos;
  private double bottomSenPos;
  private double topRotPerSec;
  private double bottomRotPerSec;
  
  //Motor output initialization
  private double topPWMOutput = 0, bottomPWMOutput = 0;
  private double topSetpoint = 0, bottomSetpoint = 0;
  
  //PID constatns initialization
  private double flywheelTop_kP = 0;
  private double flywheelTop_kI = 0;
  private double flywheelTop_kD = 0;
  private double flywheelBottom_kP = 0;
  private double flywheelBottom_kI = 0;
  private double flywheelBottom_kD = 0;

  // private double flywheelTop_kF = 0;
  // private double flywheelBottom_kF = 0;

  public Flywheel() 
  {
     // Create TalonFX objects
     flywheelTop = new TalonFX(KFlywheelTopTalon);
     flywheelBottom = new TalonFX(KFlywheelBottomTalon);
 
    //Factory Default all hardware to prevent unexpected behaviour
		flywheelTop.configFactoryDefault();
    flywheelBottom.configFactoryDefault();
    
     //inverting the flywheels' spin direction
    //  flywheelTop.setInverted(false);
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

		  //double topNumOfRotations = topSenPos / kUnitsPerRevolution;
      //double bottomNumOfRotations = bottomSenPos / kUnitsPerRevolution;
      
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
      
      SmartDashboard.putNumber("Flywheel Top P", 0);
      SmartDashboard.putNumber("Flywheel Top I", 0);
      SmartDashboard.putNumber("Flywheel Top D", 0);
      // SmartDashboard.putNumber("Flywheel Top F", topController.getF());
      SmartDashboard.putNumber("Flywheel Bottom P", 0);
      SmartDashboard.putNumber("Flywheel Bottom I", 0);
      SmartDashboard.putNumber("Flywheel Bottom D", 0);
      // SmartDashboard.putNumber("Flywheel Bottom F", bottomController.getF());
    }
    
    @Override
    public void periodic() 
    {
      SmartDashboard.putNumber("Top Flywheel Current Output", topPWMOutput);
      SmartDashboard.putNumber("Bottom Flywheel Current Output", bottomPWMOutput);
      SmartDashboard.putNumber("top rot per sec", getTopRotPerSec());
      SmartDashboard.putNumber("bottom rot per sec", getBottomRotPerSec());

      smartDashSetTopConstants();
      smartDashSetBottomConstants();
   
        //get sensor raw values (packed in the form of TalonFXSensorCollection objects)
      TalonFXSensorCollection topSensorVals = flywheelTop.getSensorCollection();
      TalonFXSensorCollection bottomSensorVals = flywheelBottom.getSensorCollection();
  
      topSenPos = topSensorVals.getIntegratedSensorPosition(); /* position units */
      bottomSenPos = bottomSensorVals.getIntegratedSensorPosition(); 
  
      //extracting raw sensor values from TalonFXSensorCollection objects
      topSenVel = topSensorVals.getIntegratedSensorVelocity(); /* position units per 100ms */
      bottomSenVel = bottomSensorVals.getIntegratedSensorVelocity();
  
      //double topNumOfRotations = topSenPos / kUnitsPerRevolution;
      //double bottomNumOfRotations = bottomSenPos / kUnitsPerRevolution;
      
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
    topPWMOutput = topLimiter.calculate(topController.calculate(topRotPerSec, topSetpoint));
    bottomPWMOutput = bottomLimiter.calculate(bottomController.calculate(-bottomRotPerSec, bottomSetpoint));

    //implement feedforward?

    //send the output values to the motors
    // flywheelTop.set(ControlMode.PercentOutput, topPWMOutput);
    // flywheelBottom.set(ControlMode.PercentOutput, bottomPWMOutput);
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

  //reset the top and bottom PID controllers
  public void reset() {
    topController.reset();
    bottomController.reset();
  }

  //setting the PID constants for the top PID controller
  public void smartDashSetTopConstants() {
    topController.setP(SmartDashboard.getNumber("Flywheel Top P", 0));
    topController.setI(SmartDashboard.getNumber("Flywheel Top I", 0));
    topController.setD(SmartDashboard.getNumber("Flywheel Top D", 0));
    // topController.setF(Kf);
  }

  //setting the PID constants for the bottom PID controller
  public void smartDashSetBottomConstants() {
    bottomController.setP(SmartDashboard.getNumber("Flywheel Bottom P", 0));
    bottomController.setI(SmartDashboard.getNumber("Flywheel Bottom I", 0));
    bottomController.setD(SmartDashboard.getNumber("Flywheel Bottom D", 0));
    // bottomController.setF(Kf);
  }

  @Override
  public void simulationPeriodic() 
  {

  }
}