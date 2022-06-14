package org.frc5687.swerve.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import org.frc5687.swerve.RobotMap;
import edu.wpi.first.math.controller.PIDController;
import org.frc5687.swerve.Constants.*;
import org.frc5687.swerve.util.OutliersContainer;

public class Shooter extends OutliersSubsystem{

    private TalonFX _shooter;
    private PIDController _controller;
    
    public Shooter(OutliersContainer container){
        super(container);
        _shooter = new TalonFX(RobotMap.CAN.TALONFX.SHOOTER);
        _shooter.setInverted(SHOOTER.INVERTED);
        _controller = new PIDController(SHOOTER.kP, SHOOTER.kI, SHOOTER.kD);
    }

    /**
     * Sets the shooter to shooting speed
     */
    public void Shoot(){
        _shooter.set(ControlMode.PercentOutput, _controller.calculate(GetVelocity(), SHOOTER.SHOOTING_SPEED));
        //_shooter.set(ControlMode.PercentOutput, SHOOTER.SHOOTING_SPEED);
    }

    /**
     * Sets the shooter to idle speed
     */
    public void Idle(){
        _shooter.set(ControlMode.PercentOutput, _controller.calculate(GetVelocity(), SHOOTER.IDLE_SHOOTING_SPEED));
        //_shooter.set(ControlMode.PercentOutput, SHOOTER.IDLE_SHOOTING_SPEED);
    }

    /**
     * Get the RPM of the shooter
     * @return RPM
     */
    public double GetRPM(){
        return GetVelocity() / SHOOTER.TICKS_TO_ROTATIONS * 600 * SHOOTER.GEAR_RATIO;
    }

    /**
     * Gets the current velocity of the shooter wheel
     * @return velocity
     */
    public double GetVelocity(){
        return _shooter.getSelectedSensorVelocity();
    }

    @Override
    public void updateDashboard() {
        metric("Shooter velocity", GetVelocity());
        metric("Shooter RPM", GetRPM());
    }
}