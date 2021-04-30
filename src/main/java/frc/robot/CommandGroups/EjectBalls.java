package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.Robot;
import frc.robot.commands.Funnel.FunnelOut;
import frc.robot.commands.Storage.StorageOut;
import frc.robot.commands.Intake.IntakeOut;

public class EjectBalls extends SequentialCommandGroup {
  public EjectBalls() {
    addCommands(
      parallel (
        new StorageOut(),
        //new IntakeOut(),
        new FunnelOut()
      )
    );
  }
}