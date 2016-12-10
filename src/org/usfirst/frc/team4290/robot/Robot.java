package org.usfirst.frc.team4290.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	Joystick leftJoystick;
	Joystick rightJoystick;
	CANTalon left1, left2, left3;
	CANTalon right1, right2, right3;
	long timer;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		leftJoystick = new Joystick(1); // from driver program port 1
		rightJoystick = new Joystick(2); // from driver program port 2
		
		left1 = new CANTalon(62); // not sure why 62
		left2 = new CANTalon(1); // not sure why set to 1
		left3 = new CANTalon(2); // can be set
		
		right1 = new CANTalon(5); // not sure why 5
		right2 = new CANTalon(6); // not sure why set to 6
		right3 = new CANTalon(7); // can be set
		
	
		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	public void autonomousInit() {

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		
		//frequency 60 times a second maybe - like game loop
		
		double leftY = leftJoystick.getY();
		double rightY = rightJoystick.getY();
		
		if (Math.abs(leftY) < 0.1)
			leftY = 0.0d;
		
		if (Math.abs(rightY) < 0.1)
			leftY = 0.0d;
		
		leftControl(leftY);
		rightControl(rightY);
		
		//turn left at half speed
		if (leftJoystick.getRawButton(4))
		{
			rightControl(-0.5);
			leftControl(0.5);
		}
		
		if (leftJoystick.getRawButton(5))
		{
			rightControl(0.5);
			leftControl(-0.5);
		}
		
		if (leftJoystick.getRawButton(3) && timer == 0)
		{
			timer = System.currentTimeMillis() + 3000;
		}else if (timer != 0 && timer > System.currentTimeMillis())
		{
			rightControl(-0.5);
			leftControl(0.5);
		}
		else
		{
			timer = 0;
		}
		

		SmartDashboard.putNumber("Left Y:", leftY);
		SmartDashboard.putNumber("Right Y:", rightY);
	}
	
	private void leftControl(double input){
		left1.set(input);
		left2.set(input);
		left3.set(input);
		
		
	}
	
	private void rightControl(double input){
		right1.set(input);
		right2.set(input);
		right3.set(input);
		
		right1.setInverted(true);
		right2.setInverted(true);
		right3.setInverted(true);
	}
	

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}
