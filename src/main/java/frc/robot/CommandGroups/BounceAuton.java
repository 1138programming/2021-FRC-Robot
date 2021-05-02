package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.Robot;
import frc.robot.commands.Base.MoveBaseFor;

public class BounceAuton extends SequentialCommandGroup {
	public BounceAuton() {
        addCommands(
            sequence(
                //Testing needed!
                // new MoveBaseFor(0.45, 1, 500),      //Forward --> left
                // new MoveBaseFor(-1, -0.9, 1000),    //Back out --> right back
                // new MoveBaseFor(-1, -0.25, 1000),   //Right back half roll
                // new MoveBaseFor(-1, -1, 1000),      //Back
                // new MoveBaseFor(1, 1, 1000),        //Forward
                // new MoveBaseFor(0.7, 1, 200),       //Left curve roll
                // new MoveBaseFor(1, 1, 1000),        //Forward
                // new MoveBaseFor(-1, -0.45, 200)     //Right back curve (RTB)

            )
        );
    }
}