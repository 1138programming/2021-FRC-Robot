package frc.robot.commands.Storage;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import static frc.robot.Constants.*;

public class MoveStorageFor extends CommandBase {
  private double PWM;
  private long duration;
  private long startTime;

  public MoveStorageFor(double PWM, long duration) {
    this.PWM = PWM;
    this.duration = duration;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.storage);
  }

  public MoveStorageFor(long duration) {
    this(KStoragePWM, duration);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.storage.move(PWM);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.storage.move(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (duration != 0) && (System.currentTimeMillis() - startTime) > duration;
  }
}