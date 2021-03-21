// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Storage;

import frc.robot.Robot;
import static frc.robot.Constants.*;
import frc.robot.subsystems.Storage;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class StorageOut extends CommandBase {
  /** Creates a new StorageOut. */
  public StorageOut() {
    addRequirements(Robot.storage);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.storage.move(-KStoragePWM);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
