package org.frc5687.swerve.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import org.frc5687.swerve.RobotMap;

public class Shooter {

    private TalonFX _shooter;
    
    public Shooter(){
        _shooter = new TalonFX(RobotMap.CAN.TALONFX.SHOOTER);
    }
}