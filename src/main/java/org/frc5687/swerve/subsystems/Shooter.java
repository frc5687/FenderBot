package org.frc5687.swerve.subsystems;

import javax.lang.model.element.Element;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import org.frc5687.swerve.Constants;
import org.frc5687.swerve.RobotMap;
import edu.wpi.first.math.controller.PIDController;
import org.frc5687.swerve.Constants.*;
import org.frc5687.swerve.util.OutliersContainer;

public class Shooter extends OutliersSubsystem{

    private int TIMEOUT = 200;
    private TalonFX _shooter;
    private ShooterState _state;
    
    public Shooter(OutliersContainer container) {
        super(container);

        _shooter = new TalonFX(RobotMap.CAN.TALONFX.SHOOTER);
        _shooter.setInverted(SHOOTER.INVERTED);
        _shooter.setNeutralMode(NeutralMode.Coast);

        _shooter.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, TIMEOUT);
        _shooter.configVoltageCompSaturation(SHOOTER.VOLTAGE, TIMEOUT);
        _shooter.enableVoltageCompensation(true);

        // don't need very fast can frame, keep default at 20ms. If RIO can bus as issues increase time.
        _shooter.setStatusFramePeriod(
                StatusFrame.Status_1_General, 20, Constants.DifferentialSwerveModule.TIMEOUT);
        _shooter.setStatusFramePeriod(
                StatusFrame.Status_2_Feedback0, 20, Constants.DifferentialSwerveModule.TIMEOUT);

        _shooter.config_kP(0, Constants.SHOOTER.kP, TIMEOUT);
        _shooter.config_kI(0, Constants.SHOOTER.kI, TIMEOUT);
        _shooter.config_kD(0, Constants.SHOOTER.kD, TIMEOUT);

        _state = ShooterState.UNKNOWN;
    }

    /**
     * set speed of falcon.
     * @param speed
     */
    public void setSpeed(double speed) {
        _shooter.set(TalonFXControlMode.PercentOutput, speed);
    }

    /**
     * Get the RPM of the shooter
     * @return RPM
     */
    public double getRPM() {
        return getVelocity() / SHOOTER.TICKS_TO_ROTATIONS * 600 * SHOOTER.GEAR_RATIO;
    }

    /**
     * Gets the current velocity of the shooter wheel
     * @return velocity
     */
    public double getVelocity(){
        return _shooter.getSelectedSensorVelocity();
    }

    private enum ShooterState{
        SHOOTING(0),
        IDLE(1),
        UNKNOWN(2);

        private final int _value;

        ShooterState(int value){
            _value = value;
        }

        public int getValue(){
            return _value;
        }
    }

    @Override
    public void updateDashboard() {
        metric("Shooter velocity", getVelocity());
        metric("Shooter RPM", getRPM());
        metric("Shooter state", _state.getValue());
    }
}