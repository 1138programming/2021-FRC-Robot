// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Flywheel;

import frc.robot.Robot;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ManualMoveFlywheel extends CommandBase {
  /** Creates a new ManualMoveFlywheel. */
  public ManualMoveFlywheel() {
    addRequirements(Robot.flywheel);  
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //reading and setting flywheel setpoints manually via SmartDashboard
    double topSetpoint = SmartDashboard.getNumber("Top Flywheel Setpoint", 0.0);
    double bottomSetpoint = SmartDashboard.getNumber("Bottom Flywheel Setpoint", 0.0);
    Robot.flywheel.setSetpoints(topSetpoint, bottomSetpoint);

    //move the flywheel according to the PID controller calculations
    Robot.flywheel.PIDMove();

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.flywheel.move(0.0, 0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
