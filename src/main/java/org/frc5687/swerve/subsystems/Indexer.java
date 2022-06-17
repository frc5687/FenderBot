package org.frc5687.swerve.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import org.frc5687.swerve.Constants;
import org.frc5687.swerve.RobotMap;
import org.frc5687.swerve.Constants.INDEXER;
import org.frc5687.swerve.util.OutliersContainer;

public class Indexer extends OutliersSubsystem{
    
    private TalonFX _indexer;
    private Indexer_State _state;
    
    public Indexer(OutliersContainer container){
        super(container);
        _indexer = new TalonFX(RobotMap.CAN.TALONFX.INDEXER);
        _state = Indexer_State.UNKNOW;
    }

    /**
     * Feeds the ball into the robot
     */
    public void Feed(){
        _indexer.set(TalonFXControlMode.PercentOutput, Constants.INDEXER.INDEXING_SPEED);
        _state = Indexer_State.INDEXING;
    }

    /**
     * Stops the feeding 
     */
    public void Idle(){
        _indexer.set(ControlMode.PercentOutput, Constants.INDEXER.IDLE_INDEXER);
        _state = Indexer_State.IDLE;
    }

    private enum Indexer_State{
        INDEXING(0),
        IDLE(1),
        UNKNOW(2);

        private final int _value;
        Indexer_State(int value){
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
    public double GetRPM(){
        return GetVelocity() / INDEXER.TICKS_TO_ROTATIONS * 600 * INDEXER.GEAR_RATIO;
    }

    /**
     * Gets the veloctiy of the indexer motor
     * @return double
     */
    public double GetVelocity(){
        return _indexer.getSelectedSensorVelocity();
    }

    @Override
    public void updateDashboard() {
        metric("Feeder velocity", GetVelocity());
        metric("Feeder state", _state.getValue());
        metric("Feeder RPM", GetRPM());
    }
}