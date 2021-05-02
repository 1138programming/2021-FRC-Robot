package frc.robot.commands.Funnel;

import frc.robot.Robot;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ManualMoveFunnel extends CommandBase {
    
    public ManualMoveFunnel() {
        addRequirements(Robot.funnel);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double PWM = SmartDashboard.getNumber("FunnelPWM", 0);
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