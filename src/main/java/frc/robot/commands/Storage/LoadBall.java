package frc.robot.commands.Storage;

import frc.robot.Robot;
import static frc.robot.Constants.*;
import frc.robot.subsystems.Storage;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class LoadBall extends CommandBase {
  private long duration;
  private long lastDetectedTime;

  public LoadBall() {
    this.duration = 85;
    this.lastDetectedTime = 0;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.storage);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    boolean ballSensor1State = Robot.storage.getBallSensor1();
    if (ballSensor1State) {
      Robot.storage.move(KStoragePWM);
      lastDetectedTime = System.currentTimeMillis();
    }
    if (!ballSensor1State){
      if ((System.currentTimeMillis() - lastDetectedTime) >= duration){
        Robot.storage.move(0);
      }
      else {
        Robot.storage.move(KStoragePWM);
      }
    }
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}