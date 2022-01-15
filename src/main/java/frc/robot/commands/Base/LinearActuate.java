// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Base;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class LinearActuate extends CommandBase {
  /** Creates a new LinearActuate. */
  private double amount;
  public LinearActuate(double amount) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.amount = amount;
    addRequirements(Robot.base);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.base.linearActuatorMove(amount);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
