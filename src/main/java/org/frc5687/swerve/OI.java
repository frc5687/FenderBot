/* Team 5687 (C)2020-2021 */
package org.frc5687.swerve;

import static org.frc5687.swerve.Constants.DriveTrain.*;
import static org.frc5687.swerve.util.Helpers.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import org.frc5687.swerve.subsystems.DriveTrain;
import org.frc5687.swerve.subsystems.Indexer;
import org.frc5687.swerve.subsystems.Intake;
import org.frc5687.swerve.subsystems.Maverick;
import org.frc5687.swerve.subsystems.Shooter;
import org.frc5687.swerve.util.AxisButton;
import org.frc5687.swerve.util.Gamepad;
import org.frc5687.swerve.util.OutliersProxy;
import org.frc5687.swerve.commands.AutoIntake;
import org.frc5687.swerve.commands.Clean;
import org.frc5687.swerve.commands.Fly;
import org.frc5687.swerve.commands.Shoot;
import edu.wpi.first.wpilibj.GenericHID;

public class OI extends OutliersProxy {
    protected Gamepad _driverGamepad;
    protected Gamepad _operatorGamepad;

    private JoystickButton _intake;
    private JoystickButton _shoot;
    private JoystickButton _clean;
    private JoystickButton _Maverick;


    private double yIn = 0;
    private double xIn = 0;

    public OI() {
        _driverGamepad = new Gamepad(0);
        _operatorGamepad = new Gamepad(1);

        _intake = new JoystickButton(_operatorGamepad, Gamepad.Buttons.RIGHT_BUMPER.getNumber());
        _shoot = new JoystickButton(_operatorGamepad, Gamepad.Buttons.LEFT_BUMPER.getNumber());
        _clean = new JoystickButton(_operatorGamepad, Gamepad.Buttons.A.getNumber());
        _Maverick = new JoystickButton(_driverGamepad, Gamepad.Buttons.RIGHT_BUMPER.getNumber());
    }

    public void initializeButtons(DriveTrain driveTrain, Indexer indexer, Shooter shooter, Intake intake, Maverick maverick) {
        _intake.whenHeld(new AutoIntake(intake, indexer));
        _shoot.whenHeld(new Shoot(shooter, indexer));
        _clean.whenHeld(new Clean(intake, indexer, shooter));
        _Maverick.whenHeld(new Fly(maverick));
    }

    public double getDriveY() {
        //        yIn = getSpeedFromAxis(_leftJoystick, _leftJoystick.getYChannel());
        yIn = -getSpeedFromAxis(_driverGamepad, Gamepad.Axes.LEFT_Y.getNumber());
        yIn = applyDeadband(yIn, DEADBAND);

        double yOut = yIn / (Math.sqrt(yIn * yIn + (xIn * xIn)) + Constants.EPSILON);
        yOut = (yOut + (yIn * 2)) / 3.0;
        return -yOut; // inverted cuz navx is upside down.
    }

    public double getDriveX() {
        //        xIn = -getSpeedFromAxis(_leftJoystick, _leftJoystick.getXChannel());
        xIn = -getSpeedFromAxis(_driverGamepad, Gamepad.Axes.LEFT_X.getNumber());
        xIn = applyDeadband(xIn, DEADBAND);

        double xOut = xIn / (Math.sqrt(yIn * yIn + (xIn * xIn)) + Constants.EPSILON);
        xOut = (xOut + (xIn * 2)) / 3.0;
        return -xOut; // inverted cuz navx is upside down.
    }

    public double getRotationX() {
        //double speed = getSpeedFromAxis(_rightJoystick, _rightJoystick.getZChannel());
        double speed = -getSpeedFromAxis(_driverGamepad, Gamepad.Axes.RIGHT_X.getNumber());
        speed = applyDeadband(speed, 0.2);
        return speed;
    }

    protected double getSpeedFromAxis(Joystick gamepad, int axisNumber) {
        return gamepad.getRawAxis(axisNumber);
    }

    public void FreedomRumble(){
        _driverGamepad.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
        _driverGamepad.setRumble(GenericHID.RumbleType.kRightRumble, 1);
        _operatorGamepad.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
        _operatorGamepad.setRumble(GenericHID.RumbleType.kRightRumble, 1);
    }

    public void EnginesOff(){
        _driverGamepad.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
        _driverGamepad.setRumble(GenericHID.RumbleType.kRightRumble, 0);
        _operatorGamepad.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
        _operatorGamepad.setRumble(GenericHID.RumbleType.kRightRumble, 0);
    }

    @Override
    public void updateDashboard() {

    }
}
