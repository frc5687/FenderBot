package org.frc5687.swerve.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import org.frc5687.swerve.Constants.INTAKE;
import org.frc5687.swerve.RobotMap.CAN.TALONFX;
import org.frc5687.swerve.util.OutliersContainer;


public class Intake extends OutliersSubsystem{

    private TalonFX _intake;
    private Intake_State _state;

    public Intake(OutliersContainer container){
        super(container);
        _intake = new TalonFX(TALONFX.INTAKE);
        _state = Intake_State.UNKNOW;
    }
    
    /**
     * Intake a ball
     */
    public void IntakeBall(){
        _intake.set(ControlMode.PercentOutput, INTAKE.INTAKEING_SPEED);
        _state = Intake_State.INTAKEING;
    }

    /**
     * Idle intake
     */
    public void Idle(){
        _intake.set(ControlMode.PercentOutput, INTAKE.IDLE_INTAKEING_SPEED);
        _state = Intake_State.IDLE;
    }

    private enum Intake_State{
        INTAKEING(0),
        IDLE(1),
        UNKNOW(2);

        private final int _value;
        Intake_State(int value){
            _value = value;
        }

        public int getValue(){
            return _value;
        }
    }

    @Override
    public void updateDashboard() {
        metric("Intake state", _state.getValue());
        metric("Intake position", _intake.getSelectedSensorPosition());
        metric("Intake velocity", _intake.getSelectedSensorVelocity());
    }  
}