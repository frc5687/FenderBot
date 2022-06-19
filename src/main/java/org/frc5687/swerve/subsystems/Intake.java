package org.frc5687.swerve.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import org.frc5687.swerve.Constants.INTAKE;
import org.frc5687.swerve.RobotMap.CAN.TALONFX;
import org.frc5687.swerve.util.OutliersContainer;


public class Intake extends OutliersSubsystem{

    public TalonFX _intake;

    public Intake(OutliersContainer container){
        super(container);
        _intake = new TalonFX(TALONFX.INTAKE);
    }

    public void IntakeBall(){
        _intake.set(ControlMode.PercentOutput, INTAKE.INTAKEING_SPEED);
    }

    public void Idle(){
        _intake.set(ControlMode.PercentOutput, INTAKE.IDLE_INTAKEING_SPEED);
    }

    @Override
    public void updateDashboard() {

    }  
}