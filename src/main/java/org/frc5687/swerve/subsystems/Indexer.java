package org.frc5687.swerve.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import org.frc5687.swerve.Constants;
import org.frc5687.swerve.RobotMap;
import org.frc5687.swerve.util.OutliersContainer;

public class Indexer extends OutliersSubsystem{
    
    private TalonFX indexer;
    
    public Indexer(OutliersContainer container){
        super(container);
        indexer = new TalonFX(RobotMap.CAN.TALONFX.INDEXER);
    }

    /**
     * Feeds the ball into the robot
     */
    public void Feed(){
        indexer.set(TalonFXControlMode.PercentOutput, Constants.INDEXER.INDEXING_SPEED);
    }

    /**
     * Stops the feeding 
     */
    public void IdleIndexer(){
        indexer.set(ControlMode.PercentOutput, Constants.INDEXER.IDLE_INDEXER);
    }

    @Override
    public void updateDashboard() {
        metric("Feeder velocity", indexer.getSelectedSensorVelocity());
    }
}