package frc.robot.commands.Base;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class SwerveWithJoysticks extends CommandBase {
  // Slew rate limiters to make joystick inputs more gentle; 1/3 sec from 0 to 1.
  private final SlewRateLimiter xspeedLimiter = new SlewRateLimiter(6);
  private final SlewRateLimiter yspeedLimiter = new SlewRateLimiter(6);
  private final SlewRateLimiter rotLimiter = new SlewRateLimiter(6);

  public SwerveWithJoysticks() {
    addRequirements(Robot.base);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.base.resetAllAngleMotors();
  }
  
  @Override
  public void execute() {
    // Get the x speed. We are inverting this because Xbox controllers return
    // negative values when we push forward.
    final var xSpeed =
      -xspeedLimiter.calculate(Robot.m_robotContainer.getLogiLeftYAxis())
        * kMaxSpeed * 0.4;

    // Get the y speed or sideways/strafe speed. We are inverting this because
    // we want a positive value when we pull to the left. Xbox controllers
    // return positive values when you pull to the right by default.
    final var ySpeed =
      -yspeedLimiter.calculate(Robot.m_robotContainer.getLogiLeftXAxis())
        * kMaxSpeed * 0.4;

    // Get the rate of angular rotation. We are inverting this because we want a
    // positive value when we pull to the left (remember, CCW is positive in
    // mathematics). Xbox controllers return positive values when you pull to
    // the right by default.
    final var rot =
      -rotLimiter.calculate(Robot.m_robotContainer.getLogiRightXAxis())
        * kMaxAngularSpeed * 0.4;

    //boolean calibrate = controller.getBumper(GenericHID.Hand.kLeft);
    
    Robot.base.drive(xSpeed, ySpeed, rot, true);
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