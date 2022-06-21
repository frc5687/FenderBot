package org.frc5687.swerve.commands.intake;

import org.frc5687.swerve.Constants;
import org.frc5687.swerve.commands.OutliersCommand;
import org.frc5687.swerve.subsystems.Intake;

public class IdleIntake extends OutliersCommand {
    private final Intake _intake;

    public IdleIntake(Intake intake) {
        _intake = intake;
        addRequirements(_intake);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        super.execute();
        _intake.setSpeed(Constants.INTAKE.IDLE_INTAKEING_SPEED);
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }
}
