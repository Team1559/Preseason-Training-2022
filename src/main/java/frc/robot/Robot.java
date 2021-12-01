// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  TalonSRX motor1;
  TalonSRX motor2;
  TalonSRX motor3;
  TalonSRX motor4;
  OperatorInterface oi;
  boolean doSpinnySpin = false;
  boolean iCraveInfo = false;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    motor1 = new TalonSRX(7);
    motor2 = new TalonSRX(3);
    if(doSpinnySpin)
      motor3 = new TalonSRX(3); // FIXME: Need to correct ID (Spinner motor)
    if(iCraveInfo)
      motor4 = new TalonSRX(3); // FIXME: Need to correct ID (input motor)
    oi = new OperatorInterface();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
   
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */

  // (vl-mn)/(mx-mn)*(nmx - nmn) + nmn

  public static double map(double value, double min, double max, double newmin, double newmax){
    return (value-min) / (max-min) * (newmax - newmin) + newmin;
  }

  @Override
  public void teleopPeriodic() {
    if(oi.pilot.getRawButton(1)){
      if(iCraveInfo)
        motor4.set(ControlMode.PercentOutput, 0.2);
      if(doSpinnySpin)
        motor3.set(ControlMode.PercentOutput, 1);
    }
    if(oi.getPilotX() < -0.2){
      motor1.set(ControlMode.PercentOutput, oi.getPilotX());
      motor2.set(ControlMode.PercentOutput, Math.abs(oi.getPilotX()));
    } else if (oi.getPilotX() > 0.2){
      motor1.set(ControlMode.PercentOutput, oi.getPilotX());
      motor2.set(ControlMode.PercentOutput, -oi.getPilotX());
    } else {
      motor1.set(ControlMode.PercentOutput, oi.getPilotY());
      motor2.set(ControlMode.PercentOutput, oi.getPilotY());
    }
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}