// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.TimedRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    TalonSRX          leftMotor;
    TalonSRX          rightMotor;
    OperatorInterface operatorInterface;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        this.operatorInterface = new OperatorInterface();
        this.leftMotor = new TalonSRX(1);
        this.rightMotor = new TalonSRX(7);
        this.rightMotor.setInverted(true);
    }

    /**
     * This function is called every robot packet, no matter the mode. Use this
     * for items like diagnostics that you want ran during disabled, autonomous,
     * teleoperated and test.
     * <p>
     * This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        //
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString line to get the auto name from the text box below the Gyro
     * <p>
     * You can add additional auto modes by adding additional comparisons to the
     * switch structure below with additional strings. If using the
     * SendableChooser make sure to add them to the chooser code above as well.
     */
    @Override
    public void autonomousInit() {
        //
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {
        //
    }

    /** This function is called once when teleop is enabled. */
    @Override
    public void teleopInit() {
        //
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        double driveSpeed = 0.5D;
        // arcade drive
        double leftStickY = operatorInterface.pilot.getRawAxis(1);
        if (leftStickY < 0) {
            leftStickY = -Math.pow(leftStickY, 2);
        } else {
            leftStickY = Math.pow(leftStickY, 2);
        }
        double rightStickX = operatorInterface.pilot.getRawAxis(4);
        if (rightStickX < 0) {
            rightStickX = -Math.pow(rightStickX, 2);
        } else {
            rightStickX = Math.pow(rightStickX, 2);
        }
        double leftDriveValue;
        double rightDriveValue;
        if (rightStickX > 0.1D) {
            // // turn right
            // rightDriveValue = leftStickY - rightStickX;
            // leftDriveValue = leftStickY + rightStickX;
            // if (rightDriveValue < -1D) {
            //     leftDriveValue = leftDriveValue + rightDriveValue - 1D;
            //     rightDriveValue = -1D;
            // }
            // if (leftDriveValue > 1D) {
            //     rightDriveValue = rightDriveValue - leftDriveValue + 1D;
            //     leftDriveValue = 1D;
            // }

            rightDriveValue = leftStickY - rightStickX;
            leftDriveValue = leftStickY + rightStickX;
        } else if (rightStickX < -0.1D) {
            // turn left
            // leftDriveValue = leftStickY + rightStickX;
            // rightDriveValue = leftStickY - rightStickX;
            // if (leftDriveValue > 1D) {
            //     rightDriveValue = rightDriveValue - leftDriveValue + 1D;
            //     leftDriveValue = 1D;
            // }
            // if (rightDriveValue < -1D) {
            //     leftDriveValue = leftDriveValue + rightDriveValue - 1D;
            //     rightDriveValue = -1D;
            // }
            rightDriveValue = leftStickY + rightStickX;
            leftDriveValue = leftStickY - rightStickX;
        } else {
            leftDriveValue = leftStickY;
            rightDriveValue = leftStickY;
        }
        if (leftDriveValue < -1D) {
            double difference = leftDriveValue
                    / (rightDriveValue == 0D ? -1D : rightDriveValue);
            leftDriveValue = -1D;
            rightDriveValue /= difference;
        } else if (leftDriveValue > 1D) {
            double difference = leftDriveValue
                    / (rightDriveValue == 0D ? 1D : rightDriveValue);
            leftDriveValue = 1D;
            rightDriveValue /= difference;
        }
        if (rightDriveValue < -1D) {
            double difference = rightDriveValue
                    / (leftDriveValue == 0D ? -1D : leftDriveValue);
            rightDriveValue = -1D;
            leftDriveValue /= difference;
        } else if (rightDriveValue > 1D) {
            double difference = rightDriveValue
                    / (leftDriveValue == 0D ? 1D : leftDriveValue);
            rightDriveValue = 1D;
            leftDriveValue /= difference;
        }
        leftDriveValue *= driveSpeed;
        rightDriveValue *= driveSpeed;
        this.leftMotor.set(ControlMode.PercentOutput, leftDriveValue);
        this.rightMotor.set(ControlMode.PercentOutput, rightDriveValue);
    }

    /** This function is called once when the robot is disabled. */
    @Override
    public void disabledInit() {
        //
    }

    /** This function is called periodically when disabled. */
    @Override
    public void disabledPeriodic() {
        //
    }

    /** This function is called once when test mode is enabled. */
    @Override
    public void testInit() {
        //
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {
        //
    }
}
