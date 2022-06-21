package org.frc5687.swerve.commands.intake;

import org.frc5687.swerve.Constants;
import org.frc5687.swerve.commands.OutliersCommand;
import org.frc5687.swerve.subsystems.Intake;

public class DeployIntake extends OutliersCommand {
    private final Intake _intake;
    public DeployIntake(Intake intake) {
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
        _intake.setSpeed(Constants.INTAKE.INTAKEING_SPEED);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }
}
