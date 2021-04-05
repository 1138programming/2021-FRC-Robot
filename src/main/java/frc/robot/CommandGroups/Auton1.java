package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.Robot;
import frc.robot.commands.Base.MoveBaseFor;

public class Auton1 extends SequentialCommandGroup {
  public Auton1() {
    addCommands(new MoveBaseFor(0.2, 0.2, 0.0, true, 1000));
  }
}