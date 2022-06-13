package org.frc5687.swerve.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import org.frc5687.swerve.RobotMap;

import edu.wpi.first.math.controller.PIDController;

public class Shooter {

    private TalonFX _shooter;
    private PIDController _controller;
    
    public Shooter(){
        _shooter = new TalonFX(RobotMap.CAN.TALONFX.SHOOTER);
    }


}