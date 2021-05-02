package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.Robot;
import frc.robot.commands.Base.MoveBaseFor;

public class SlalomAuton extends SequentialCommandGroup {
	public SlalomAuton() {
        addCommands(
            sequence(
                //Testing needed!
                // new MoveBaseFor(0.65, 1, 200),     //Forward --> left
                // new MoveBaseFor(1, 1, 1000),       //Forward
                // new MoveBaseFor(1, 0.9, 2000),     //Right curve
                // new MoveBaseFor(1, 1, 200),        //Forward
                // new MoveBaseFor(0.25, 1, 1000),    //Left roll
                // new MoveBaseFor(1, 1, 200),        //Forward
                // new MoveBaseFor(1, 0.9, 2000),     //Right curve
                // new MoveBaseFor(1, 1, 200),        //Forward
                // new MoveBaseFor(0.65, 1, 200)      //RTB

            )
        );
    }
}