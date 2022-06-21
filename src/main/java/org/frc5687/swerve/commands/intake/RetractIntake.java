package org.frc5687.swerve.commands.intake;

import edu.wpi.first.wpilibj.Timer;
import org.frc5687.swerve.Constants;
import org.frc5687.swerve.commands.OutliersCommand;
import org.frc5687.swerve.subsystems.Intake;

public class RetractIntake extends OutliersCommand {
    private final Intake _intake;
    private long _delay;

    public RetractIntake(Intake intake) {
        _intake = intake;
        addRequirements(_intake);
    }

    @Override
    public void initialize() {
        super.initialize();
        _delay = System.currentTimeMillis() + Constants.INTAKE.RETRACT_DELAY;
    }

    @Override
    public void execute() {
        super.execute();
        _intake.setSpeed(Constants.INTAKE.RETRACTING_SPEED);
    }

    @Override
    public boolean isFinished() {
        return System.currentTimeMillis() > _delay;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }
}
