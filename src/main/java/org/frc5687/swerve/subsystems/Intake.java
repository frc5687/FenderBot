package org.frc5687.swerve.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import org.frc5687.swerve.Constants;
import org.frc5687.swerve.Constants.INTAKE;
import org.frc5687.swerve.RobotMap.CAN.TALONFX;
import org.frc5687.swerve.util.OutliersContainer;

import edu.wpi.first.wpilibj.Timer;


public class Intake extends OutliersSubsystem{

    private final int TIMEOUT = 200;
    private TalonFX _intake;
    private Timer _timer;

    public Intake(OutliersContainer container){
        super(container);
        // Create Talon
        _intake = new TalonFX(TALONFX.INTAKE);
        // configure talon
        _intake.setInverted(INTAKE.INVERTED);
        _intake.setNeutralMode(NeutralMode.Coast);

        _intake.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, TIMEOUT);
        _intake.configVoltageCompSaturation(INTAKE.VOLTAGE, TIMEOUT);
        _intake.enableVoltageCompensation(true);

        // don't need very fast can frame, keep default at 20ms. If RIO can bus as issues increase time.
        _intake.setStatusFramePeriod(
                StatusFrame.Status_1_General, 20, Constants.DifferentialSwerveModule.TIMEOUT);
        _intake.setStatusFramePeriod(
                StatusFrame.Status_2_Feedback0, 20, Constants.DifferentialSwerveModule.TIMEOUT);
    }

    /**
     * set speed of intake.
     * @param speed
     */
    public void setSpeed(double speed) {
        _intake.set(TalonFXControlMode.PercentOutput, speed);
    }

    @Override
    public void updateDashboard() {

    }  
}