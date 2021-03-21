package frc.robot.commands.Funnel;

import frc.robot.Robot;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class MoveFunnelWithJoysticks extends CommandBase {
    
    public MoveFunnelWithJoysticks() {
        addRequirements(Robot.funnel);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double PWM = Robot.m_robotContainer.getXboxRightXAxis();
        Robot.funnel.move(PWM);
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}