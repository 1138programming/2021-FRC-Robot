// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands.Flywheel;

import frc.robot.Robot;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinUpFlywheel extends CommandBase 
{
  private double topSetpoint, bottomSetpoint;

  public SpinUpFlywheel(double topSetpoint, double bottomSetpoint) 
  {
    //setting top and bottom setpoints in this command
    this.topSetpoint = topSetpoint;
    this.bottomSetpoint = bottomSetpoint;
    
    addRequirements(Robot.flywheel);
  }
  
  public SpinUpFlywheel() 
  {
    this(0.6, 0.6);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    //sending the setpoints to the flywheel subsystem
    Robot.flywheel.setSetpoints(topSetpoint, bottomSetpoint);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    
    Robot.flywheel.setSetpoints(topSetpoint, bottomSetpoint);

    //move the flywheel according to the PID controller calculations
    Robot.flywheel.PIDMove();
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
    //no end condition, command does not end on its own
    return false;
  }
}
