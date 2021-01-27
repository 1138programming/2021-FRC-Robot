// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands.Flywheel;

import frc.robot.Robot;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinUpFlywheel extends CommandBase 
{
  private double topSetpoint, bottomSetpoint;

  public SpinUpFlywheel(double topSetpoint, double bottomSetpoint) 
  {
    this.topSetpoint = topSetpoint;
    this.bottomSetpoint = bottomSetpoint;

    addRequirements(Robot.flywheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    //set setpoints here
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    //PID stuff here
    //move()
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return false;
  }
}
