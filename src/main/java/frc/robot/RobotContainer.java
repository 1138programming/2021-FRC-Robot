package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.commands.Flywheel.StopFlywheel;
import frc.robot.commands.Flywheel.SpinUpFlywheel;
import frc.robot.commands.Flywheel.ManualMoveFlywheel;

import frc.robot.commands.Funnel.FunnelIn;
import frc.robot.commands.Funnel.FunnelOut;
import frc.robot.commands.Funnel.FunnelStop;
import frc.robot.commands.Funnel.ManualMoveFunnel;

import frc.robot.commands.Storage.StorageIn;
import frc.robot.commands.Storage.StorageOut;
import frc.robot.commands.Storage.StorageStop;
import frc.robot.commands.Storage.ManualMoveStorage;
import frc.robot.commands.Storage.LoadBall;

public class RobotContainer {
  // Controller Constants
  private static final int KLogitechDrive = 0;
  private static final int KXboxArms = 1;  

  //Deadzone
  private static final double KDeadZone = 0.02;

  //Logitech Button Constants
  public static final int KLogitechButtonX = 1;
  public static final int KLogitechButtonA = 2;
  public static final int KLogitechButtonB = 3;
  public static final int KLogitechButtonY = 4;
  public static final int KLogitechLeftBumper = 5; 
  public static final int KLogitechRightBumper = 6;
  public static final int KLogitechLeftTrigger = 7;
  public static final int KLogitechRightTrigger = 8;

  private static final int KLeftYAxis = 1;
  private static final int KRightYAxis = 3;
  private static final int KLeftXAxis = 0;
  private static final int KRightXAxis = 2;

  //Xbox Button Constants
  public static final int KXboxButtonA = 1; 
  public static final int KXboxButtonB = 2;
  public static final int KXboxButtonX = 3;  
  public static final int KXboxButtonY = 4; 
  public static final int KXboxLeftBumper = 5; 
  public static final int KXboxRightBumper = 6; 
  public static final int KXboxSelectButton = 7; 
  public static final int KXboxStartButton = 8; 
  public static final int KXboxLeftTrigger = 9; 
  public static final int KXboxRightTrigger = 10; 

  public static Joystick logitech;
  public static XboxController xbox; 
  public JoystickButton logitechBtnX, logitechBtnA, logitechBtnB, logitechBtnY, logitechBtnLB, logitechBtnRB, logitechBtnLT, logitechBtnRT; //Logitech Button
  public JoystickButton xboxBtnA, xboxBtnB, xboxBtnX, xboxBtnY, xboxBtnLB, xboxBtnRB, xboxBtnStrt, xboxBtnSelect, xboxBtnLT, xboxBtnRT;

  /**
   * The container for the robot.  Contains default commands, OI devices, and commands.
   */
  public RobotContainer() {
    // Set default commands-commands that run on default without any user input
    Robot.flywheel.setDefaultCommand(new ManualMoveFlywheel());
    Robot.funnel.setDefaultCommand(new ManualMoveFunnel());
    Robot.storage.setDefaultCommand(new StorageStop());

    // Controllers
    logitech = new Joystick(KLogitechDrive);
    xbox = new XboxController(KXboxArms);
    
    // Logitch Buttons 
    logitechBtnX = new JoystickButton(logitech, KLogitechButtonX);
    logitechBtnA = new JoystickButton(logitech, KLogitechButtonA);
    logitechBtnB = new JoystickButton(logitech, KLogitechButtonB);
    logitechBtnY = new JoystickButton(logitech, KLogitechButtonY);
    logitechBtnLB = new JoystickButton(logitech, KLogitechLeftBumper);
    logitechBtnRB = new JoystickButton(logitech, KLogitechRightBumper);
    logitechBtnLT = new JoystickButton(logitech, KLogitechLeftTrigger);
    logitechBtnRT = new JoystickButton(logitech, KLogitechRightTrigger);

    // XBox Buttons
    xboxBtnA = new JoystickButton(xbox, KXboxButtonA);
  	xboxBtnB = new JoystickButton(xbox, KXboxButtonB);
		xboxBtnX = new JoystickButton(xbox, KXboxButtonX);
		xboxBtnY = new JoystickButton(xbox, KXboxButtonY);
		xboxBtnLB = new JoystickButton(xbox, KXboxLeftBumper);
    xboxBtnRB = new JoystickButton(xbox, KXboxRightBumper);
    xboxBtnSelect = new JoystickButton(xbox, KXboxSelectButton);
		xboxBtnStrt = new JoystickButton(xbox, KXboxStartButton);
		xboxBtnLT = new JoystickButton(xbox, KXboxLeftTrigger);
    xboxBtnRT = new JoystickButton(xbox, KXboxRightTrigger);

    // Configure the button bindings
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }

  public double getLogiRightYAxis() {
    final double Y = logitech.getRawAxis(KRightYAxis);
    if (Y > KDeadZone || Y < -KDeadZone)
      return -Y;
    else
      return 0;
  }

  public double getLogiLeftYAxis() {
    final double Y = logitech.getRawAxis(KLeftYAxis);
    if(Y > KDeadZone || Y < -KDeadZone)
      return -Y;
    else 
      return 0; 
  }

  public double getLogiRightXAxis() {
    double X = logitech.getRawAxis(KRightXAxis);
    if (X > KDeadZone || X < -KDeadZone) {
      return -X;
    } else {
      return 0; 
    }
  }

  public double getLogiLeftXAxis() {
    double X = logitech.getRawAxis(KLeftXAxis);
    if (X > KDeadZone || X < -KDeadZone) {
      return -X;
    } else {
      return 0;
    }
  }

  public double getXboxLeftXAxis() {
    // final double X = xbox.getX(GenericHID.Hand.kLeft);
    // if(X > KDeadZone || X < -KDeadZone)
    //   return -X;
    // else 
    //   return 0;
    return xbox.getX(GenericHID.Hand.kLeft);
  }

  public double getXboxRightXAxis() {
    final double X = xbox.getRawAxis(KRightXAxis);
    if (X > KDeadZone || X < -KDeadZone)
      return -X;
    else
      return 0;
  }

  public double getXboxLeftYAxis() {
    final double Y = xbox.getRawAxis(KLeftYAxis);
    if(Y > KDeadZone || Y < -KDeadZone)
      return -Y;
    else 
      return 0;
  }

  public double getXboxRightYAxis() {
    final double Y = xbox.getRawAxis(KRightYAxis);
    if (Y > KDeadZone || Y < -KDeadZone)
      return -Y;
    else
      return 0;
  }

  public double mapRange(double inputLowerBound, double inputUpperBound, double outputLowerBound, double outputUpperBound, double inputValue){
		return outputLowerBound + ((inputValue - inputLowerBound)*(outputUpperBound - outputLowerBound))/(inputUpperBound - inputLowerBound);
	}
}
