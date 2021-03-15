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
  private final CANSparkMax storage;

  // Create the sensors
  private final DigitalInput ballSensor1;
  private final DigitalInput ballSensor2;

  // Variables, enums, etc.
  //private int ballCount = 0;
  private boolean ballSensor1LastState = false; // Keeps track of the last state of the 1st ball sensor
  private boolean ballSensor2LastState = false; // Keeps track of the last state of the 2nd ball sensor
  private double storagePWM = 0; // Keeps track of the speed of stage 

  //private SlewRateLimiter stage2Limiter;

  /**
   * @brief This is the Storage subsystem
   */

  public Storage() {
    // Instantiate everything
    storage = new CANSparkMax(KStorageSpark, CANSparkMaxLowLevel.MotorType.kBrushless);
    ballSensor1 = new DigitalInput(KBallSensor1);
    ballSensor2 = new DigitalInput(KBallSensor2);


    //stage2Limiter = new SlewRateLimiter(1.95);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // updateBallCount();

    // SmartDashboard.putNumber("Storage BallCount", ballCount);
    //SmartDashboard.putBoolean("Storage Ball Sensor 1", getBallSensor1());
    //SmartDashboard.putBoolean("Storage Ball Sensor 2", getBallSensor2());
    //SmartDashboard.putBoolean("Storage Full", (ballCount == 5));
  }

  /**
   * @brief Moves the given conveyor belt(s) directly
   * 
   * @param PWM Speed to move the conveyor belt(s) at
   * @param stage The stage(s) of the storage we want to move
   */

 

  private void move(double PWM) {
    storagePWM = PWM;
    storage.set(PWM);
  }


  
//   public int getBallCount() {
//     return ballCount;
//   }

//   public void setBallCount(int ballCount) {
//     this.ballCount = ballCount;
//  

  public boolean getBallSensor1() {
    return ballSensor1.get();

  }

  
  public boolean getBallSensor2() {
    return ballSensor2.get();
  }

}