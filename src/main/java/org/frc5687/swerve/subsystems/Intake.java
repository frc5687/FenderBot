package org.frc5687.swerve.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import org.frc5687.swerve.Constants.INTAKE;
import org.frc5687.swerve.RobotMap.CAN.TALONFX;
import org.frc5687.swerve.util.OutliersContainer;

import edu.wpi.first.wpilibj.Timer;


public class Intake extends OutliersSubsystem{

    private TalonFX _intake;
    private Timer _timer;

    public Intake(OutliersContainer container){
        super(container);
        _intake = new TalonFX(TALONFX.INTAKE);
        _timer = new Timer();
    }

    /**
     * Intake a ball
     */
    public void IntakeBall(){
        _intake.set(ControlMode.PercentOutput, INTAKE.INTAKEING_SPEED);
    }

    /**
     * Sets the intake to idle speed
     */
    public void Idle(){
        _intake.set(ControlMode.PercentOutput, INTAKE.IDLE_INTAKEING_SPEED);
    }

    /**
     * Retract the intake with a delay
     */
    //Should be a command
    public void Retract(){
        _timer.start();
        while (_timer.get() < INTAKE.RETRACT_DELAY) {
            _intake.set(ControlMode.PercentOutput, INTAKE.RETRACTING_SPEED);
            error("Intake retracting");
        }
        Idle();
        error("Intake retracted");
        _timer.stop();
        _timer.reset();
    }
    
    @Override
    public void updateDashboard() {
        metric("Intake falcon velocity", _intake.getSelectedSensorVelocity());
    }  
}