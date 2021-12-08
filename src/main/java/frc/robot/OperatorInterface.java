/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OperatorInterface {
    Joystick pilot;
    Joystick copilot;
    DTButton[]    pilotButtons;
    DTButton[]    copilotButtons;
    DTButton[]    cocopilotButtons;

    public OperatorInterface() {
        pilot = new Joystick(Constants.PILOT_JOYSTICK);
        copilot = new Joystick(Constants.COPILOT_JOYSTICK);
        copilotButtons = new DTButton[20];
        for (int i = 0; i < copilotButtons.length; i++) {
            copilotButtons[i] = new DTButton(copilot, i + 1);
        }
        pilotButtons = new DTButton[20];
        for (int i = 0; i < pilotButtons.length; i++) {
            pilotButtons[i] = new DTButton(pilot, i + 1);
        }
    }

    public double getPilotLeftX() {
        double pilotX = pilot.getRawAxis(0);
        // gets the x axis on the ps4 contoller (side to side)
        if (pilotX > 0D) {
            // robot with bad grabber and stepper is inverted in the second
            // statement
            return Math.pow(pilotX, 2);
        } else {
            return -Math.pow(pilotX, 2);
        }
    }

    public double getPilotLeftY() {
        double pilotY = pilot.getRawAxis(1);
        // gets the y axis on the ps4 controller (forward and back)
        if (pilotY > 0D) {
            return -Math.pow(pilotY, 2);
        } else {
            return Math.pow(pilotY, 2);
        }
    }

    public double getPilotRightY() {
        double pilotZ = pilot.getRawAxis(4);
        // gets the z axis on the ps4 controller (rotation)
        if (pilotZ > 0) {
            return Math.pow(pilotZ, 2);
        } else {
            return -Math.pow(pilotZ, 2);
        }
    }

    public DTButton getCopilotButton(int num) {
        // this gets the id number of the button on the copilot box
        return copilotButtons[num];
    }

    public boolean coButtonIsPressed(int button) {
        // this will tell us if a button is pressed on the copilot box and
        // return true
        return copilot.getRawButton(button);
    }

    public double getCopilotAxis(int num) {
        // gets the axis on the copilot box
        return copilot.getRawAxis(num);
    }

    public DTButton getCocopilotButton(int num) {
        // returns the id num of the copilot box
        return cocopilotButtons[num];
    }

    public boolean axisToButtonIsPressed(int axis) {
        // returns true if the axis button is pressed
        return copilot.getRawAxis(axis) > 0.99D;
    }

    public int DPadPilot() {
        return pilot.getPOV(0);
    }

    public int DPadCopilot() {
        return copilot.getPOV(0);
    }
}
