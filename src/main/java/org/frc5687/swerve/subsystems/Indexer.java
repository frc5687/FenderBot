
package org.frc5687.swerve.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import org.frc5687.swerve.Constants;
import org.frc5687.swerve.RobotMap;
import org.frc5687.swerve.Constants.INDEXER;
import org.frc5687.swerve.util.OutliersContainer;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;

public class Indexer extends OutliersSubsystem{
    
    private TalonFX _indexer;
    private Indexer_State _state;
    private DigitalInput _indexerBeamBreak;
    private Timer _timer;
    
    public Indexer(OutliersContainer container){
        super(container);
        _indexer = new TalonFX(RobotMap.CAN.TALONFX.INDEXER);
        _indexerBeamBreak = new DigitalInput(RobotMap.DIO.INDEXER_BEAM_BREAK);
        _state = Indexer_State.UNKNOW;
        _timer = new Timer();
    }

    /**
     * Feeds the ball into the robot
     */
    public void Feed(){
        _indexer.set(TalonFXControlMode.PercentOutput, Constants.INDEXER.INDEXING_SPEED);
        _state = Indexer_State.INDEXING;
    }

    /**
     * Intake ball
     */
    public void Intake(){
        _indexer.set(ControlMode.PercentOutput, INDEXER.INTAKING_SPEED);
        _state = Indexer_State.INDEXING_BALL;
    }

    /**
     * Stops the feeding 
     */
    public void Idle(){
        _indexer.set(ControlMode.PercentOutput, INDEXER.IDLE_INDEXER);
        _state = Indexer_State.IDLE;
    }

    public void EdgeIn(){
        _timer.start();
        while(_timer.get() < 1){
            _indexer.set(ControlMode.PercentOutput, Constants.INDEXER.INDEXING_SPEED);
        }
        Kill();
        _timer.stop();
        _timer.reset();
    }

    /**
     * Stops the indexer form indexing
     */
    public void Kill(){
        _indexer.set(ControlMode.PercentOutput, 0.0);
        _state = Indexer_State.KILL;
    }

    /**
     * Spins the indexer backwards to clean it out
     */
    public void Clean(){
        _indexer.set(ControlMode.PercentOutput, INDEXER.CLEANING_SPEED);
        _state = Indexer_State.CLEAN;
    }

    /**
     * Is the indexer beam break triggered
     * @return boolean
     */
    public boolean isTriggered(){
        return !_indexerBeamBreak.get();
    }

    private enum Indexer_State{
        INDEXING(0),
        IDLE(1),
        INDEXING_BALL(2),
        CLEAN(3),
        KILL(4),
        UNKNOW(5);

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
        metric("Beam broken", isTriggered());
    }
}