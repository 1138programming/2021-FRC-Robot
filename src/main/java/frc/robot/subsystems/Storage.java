package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.revrobotics.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SlewRateLimiter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpiutil.math.MathUtil;

public class Storage extends SubsystemBase {
  // Create the neos
  private final CANSparkMax storageFront;
  private final CANSparkMax storageBack;

  // Create the sensors
  private final DigitalInput ballSensor1;
  private final DigitalInput ballSensor2;

  //Variables
  private boolean ballSensor1LastState = false; // Keeps track of the last state of the 1st ball sensor
  private boolean ballSensor2LastState = false; // Keeps track of the last state of the 2nd ball sensor
  private double storageFrontPWM = 0; // Keeps track of the speed 
  private double storageBackPWM = 0; // Keeps track of the speed  

  //private SlewRateLimiter stage2Limiter;

  /**
   * @brief This is the Storage subsystem
   */

  public Storage() {
    // Instantiate everything
    storageFront = new CANSparkMax(KStorageFrontSpark, CANSparkMaxLowLevel.MotorType.kBrushless);
    storageBack = new CANSparkMax(KStorageBackSpark, CANSparkMaxLowLevel.MotorType.kBrushless);
    ballSensor1 = new DigitalInput(KBallSensor1);
    ballSensor2 = new DigitalInput(KBallSensor2);


    //stage2Limiter = new SlewRateLimiter(1.95);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("storageFrontPWM", getStorageFrontPWM());
    SmartDashboard.putNumber("storageBackPWM", getStorageBackPWM());

  }

  /**
   * @brief Moves both conveyor belts directly
   * 
   * @param PWM Speed to move the conveyor belts at
   */

 

  public void move(double PWM) {
    storageFrontPWM = PWM;
    storageBackPWM = -PWM;         //Storage Motors run in opposite directions when powering the belt

    storageFront.set(PWM);
    storageBack.set(-PWM);         //Storage Motors run in opposite directions when powering the belt
  }

  public boolean getBallSensor1() {
    return ballSensor1.get();

  }
  
  public boolean getBallSensor2() {
    return ballSensor2.get();
  }

  public double getStorageFrontPWM() {
    return storageFrontPWM;
  }

  public double getStorageBackPWM() {
    return storageBackPWM;
  }


}