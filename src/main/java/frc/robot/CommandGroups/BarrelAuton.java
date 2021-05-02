package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.Robot;
import frc.robot.commands.Base.MoveBaseFor;

public class BarrelAuton extends SequentialCommandGroup {
	public BarrelAuton() {
        addCommands(
            sequence(
                //Testing needed!
                // new MoveBaseFor(1, 0.95, 1000),     //Forward --> right
                // new MoveBaseFor(1, 0.25, 1000),     //Right roll
                // new MoveBaseFor(0.95, 0.1, 1000),   //Forward --> left
                // new MoveBaseFor(0.25, 1, 1000),     //Left roll
                // new MoveBaseFor(0.95, 1, 1000),     //Forward --> left
                // new MoveBaseFor(0.25, 1, 1000),     //Left roll
                // new MoveBaseFor(1, 1, 1000)         //Forward (RTB)

            )
        );
    }
}