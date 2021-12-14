/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

import static frc.robot.Constants.squareKeepSign;

public class OperatorInterface {
    Joystick   pilot;
    Joystick   copilot;
    DTButton[] pilotButtons;
    DTButton[] copilotButtons;
    DTButton[] cocopilotButtons;

    public OperatorInterface() {
        this.pilot = new Joystick(Constants.PILOT_JOYSTICK);
        this.copilot = new Joystick(Constants.COPILOT_JOYSTICK);

        this.copilotButtons = new DTButton[20];
        for (int i = 0; i < this.copilotButtons.length; i++)
            this.copilotButtons[i] = new DTButton(this.copilot, i + 1);

        this.pilotButtons = new DTButton[20];
        for (int i = 0; i < this.pilotButtons.length; i++)
            this.pilotButtons[i] = new DTButton(this.pilot, i + 1);
    }

    /**
     * Gets the left joystick x-value on the pilot controller
     */
    public double pilotLeftStickX() {
        return squareKeepSign(this.pilot.getRawAxis(0));
    }

    /**
     * Gets the left joystick y-value on the pilot controller
     */
    public double pilotLeftStickY() {
        return squareKeepSign(this.pilot.getRawAxis(1));
    }

    /**
     * Gets the right joystick x-value on the pilot controller
     */
    public double pilotRightStickX() {
        return squareKeepSign(this.pilot.getRawAxis(4));
    }

    /**
     * Gets the right joystick y-value on the pilot controller
     */
    public double pilotRightStickY() {
        return squareKeepSign(this.pilot.getRawAxis(5));
    }

    /**
     * Gets a copilot button
     *
     * @param id
     *            - the id of the button to get
     */
    public DTButton getCopilotButton(int id) {
        return this.copilotButtons[id];
    }

    /**
     * Gets the state of the given button on the copilot controller
     *
     * @return {@code true} if pressed, otherwise {@code false}
     */
    public boolean coButtonIsPressed(int button) {
        return this.copilot.getRawButton(button);
    }

    public double getPilotAxis(int num) {
        return this.pilot.getRawAxis(num);
    }

    public double getCopilotAxis(int num) {
        // gets the axis on the copilot box
        return this.copilot.getRawAxis(num);
    }

    public DTButton getCocopilotButton(int num) {
        // returns the id num of the copilot box
        return this.cocopilotButtons[num];
    }

    public boolean axisToButtonIsPressed(int axis) {
        // returns true if the axis button is pressed
        return this.copilot.getRawAxis(axis) > 0.99D;
    }

    public int dpadPilot() {
        return this.pilot.getPOV(0);
    }

    public int dpadCopilot() {
        return this.copilot.getPOV(0);
    }
}
