package org.frc5687.swerve.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import org.frc5687.swerve.util.OutliersContainer;

import edu.wpi.first.wpilibj.Timer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

import org.frc5687.swerve.RobotMap.*;
import org.frc5687.swerve.Constants;
import org.frc5687.swerve.Constants.*;
import org.frc5687.swerve.RobotMap.CAN.TALONFX;

public class Intake extends OutliersSubsystem{

    private TalonFX _roller;
    private Timer _timer;

    public Intake(OutliersContainer container) {
        super(container);
        _roller = new TalonFX(TALONFX.INTAKE);
        _roller.setInverted(INTAKE.INVERTED);
    }

    public void deploy() {
        _roller.set(ControlMode.PercentOutput, INTAKE.THE_BEANS);
    }

    public void retract() {
        while (_timer.get() < 1) {
            _roller.set(ControlMode.PercentOutput, INTAKE.BACKWARDS_BEANS);
        }
        _roller.set(ControlMode.PercentOutput, INTAKE.IDLE_BEANS);
        _timer.reset();
    }

    @Override
    public void updateDashboard() {

    }
}
