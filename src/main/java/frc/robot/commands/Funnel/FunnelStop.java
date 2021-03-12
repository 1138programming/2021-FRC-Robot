package frc.robot.commands.Funnel;

import frc.robot.Robot;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class FunnelStop extends CommandBase {
    
    public FunnelStop() {
        addRequirements(Robot.funnel);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        Robot.funnel.move(0);
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}