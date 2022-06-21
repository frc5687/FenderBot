
package org.frc5687.swerve.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import org.frc5687.swerve.Constants;
import org.frc5687.swerve.RobotMap;
import org.frc5687.swerve.Constants.INDEXER;
import org.frc5687.swerve.util.OutliersContainer;

public class Indexer extends OutliersSubsystem{

    private int TIMEOUT = 200;
    private TalonFX _indexer;
    private IndexerState _state;

    public Indexer(OutliersContainer container){
        super(container);
        _indexer = new TalonFX(RobotMap.CAN.TALONFX.INDEXER);
        _indexer.setInverted(Constants.INDEXER.INVERTED);
        _indexer.setNeutralMode(NeutralMode.Coast);

        _indexer.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, TIMEOUT);
        _indexer.configVoltageCompSaturation(Constants.INDEXER.VOLTAGE, TIMEOUT);
        _indexer.enableVoltageCompensation(true);

        // don't need very fast can frame, keep default at 20ms. If RIO can bus as issues increase time.
        _indexer.setStatusFramePeriod(
                StatusFrame.Status_1_General, 20, Constants.DifferentialSwerveModule.TIMEOUT);
        _indexer.setStatusFramePeriod(
                StatusFrame.Status_2_Feedback0, 20, Constants.DifferentialSwerveModule.TIMEOUT);
        _state = IndexerState.UNKNOWN;
    }

    /**
     * set the speed of the talon.
     * @param speed
     */
    public void setSpeed(double speed) {
        _indexer.set(ControlMode.PercentOutput, speed);
    }


    /**
     * set the current state of the indexer.
     * @param state
     */
    public void setState(IndexerState state) {
        _state = state;
    }

    /**
     * get the current state of the indexer
     * @return IndexerState
     */
    public IndexerState getState() {
        return _state;
    }

    public enum IndexerState{
        IDLE(0),
        INDEXING(1),
        WRONG_BALL(2),
        UNKNOWN(3);

        private final int _value;
        IndexerState(int value){
            _value = value;
        }

        public int getValue(){
            return _value;
        }
    }

    /**
     * Gets the RPM of the indexer
     * @return double
     */
    public double getRPM(){
        return getVelocity() / INDEXER.TICKS_TO_ROTATIONS * 600 * INDEXER.GEAR_RATIO;
    }

    /**
     * Gets the veloctiy of the indexer motor
     * @return double
     */
    public double getVelocity(){
        return _indexer.getSelectedSensorVelocity();
    }

    @Override
    public void updateDashboard() {
        metric("Feeder velocity", getVelocity());
        metric("Feeder state", _state.getValue());
        metric("Feeder RPM", getRPM());
    }
}