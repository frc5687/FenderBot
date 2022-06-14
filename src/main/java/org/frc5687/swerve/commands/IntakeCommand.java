package org.frc5687.swerve.commands;

import org.frc5687.swerve.subsystems.Intake;

public class IntakeCommand extends OutliersCommand{
    
    private Intake _intake;

    public IntakeCommand(Intake intake) {
        _intake = intake;
    }

    @Override
    public void initialize(){
        _intake.deploy();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        _intake.retract();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
