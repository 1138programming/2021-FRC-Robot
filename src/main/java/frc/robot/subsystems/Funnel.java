package frc.robot.subsystems;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpiutil.math.MathUtil;

public class Funnel extends SubsystemBase {
  //Creating motor
  private final CANSparkMax funnel;
  private double funnelPWM = 0;

  //Creating funnel
  public Funnel() {
    funnel = new CANSparkMax(KFunnelSpark, CANSparkMaxLowLevel.MotorType.kBrushless);
    // funnel.restoreFactoryDefaults();
    // funnel.setSmartCurrentLimit(30, 30, 3000);
    funnel.setIdleMode(IdleMode.kBrake);
    // funnel.burnFlash();
  }

  @Override
  public void periodic() {
    //This method is called once per scheduler run
    SmartDashboard.putNumber("funnelFrontPWM", getFunelPWM());
  }

  @Override
  public void simulationPeriodic() {
  }

  public void move(double PWM) {
    funnelPWM = -PWM;
    funnel.set(-PWM);
  }

  public double getFunelPWM(){
    return funnelPWM;
  }
}