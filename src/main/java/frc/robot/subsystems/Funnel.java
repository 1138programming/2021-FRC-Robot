package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.Constants.*;

import com.revrobotics.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;

public class Funnel extends SubsystemBase {
  //Creating motor
  private final CANSparkMax funnel;
  
  private final CANEncoder funnelEncoder;

  //Creating funnel
  public Funnel() {
    funnel = new CANSparkMax(KFunnelSpark, CANSparkMaxLowLevel.MotorType.kBrushless);
    funnel.restoreFactoryDefaults();
    funnel.setSmartCurrentLimit(30, 30, 3000);
    funnel.burnFlash();

    funnelEncoder = funnel.getEncoder();
  }

  @Override
  public void periodic() {
    //This method is called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
  }

  public void move(double speed) {
    funnel.set(speed);
  }

  public double getFunnelVel() {
    return funnelEncoder.getVelocity();
  }
}