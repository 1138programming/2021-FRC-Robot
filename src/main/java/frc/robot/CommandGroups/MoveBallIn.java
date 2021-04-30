package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.Robot;
import frc.robot.commands.Flywheel.SpinUpFlywheel;
import frc.robot.commands.Funnel.FunnelIn;
import frc.robot.commands.Micellaneous.Delay;
import frc.robot.commands.Storage.MoveStorageFor;
import frc.robot.commands.Storage.StorageIn;
import frc.robot.commands.Storage.LoadBall;
import frc.robot.commands.Intake.IntakeIn;

public class MoveBallIn extends SequentialCommandGroup {
	public MoveBallIn() {
        addCommands(
            parallel(
                //new IntakeIn(),
                new FunnelIn(),
                new LoadBall()
            )
        );
    }
    
}