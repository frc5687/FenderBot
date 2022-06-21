package org.frc5687.swerve.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.frc5687.swerve.Constants;
import org.frc5687.swerve.commands.OutliersCommand;
import org.frc5687.swerve.subsystems.Indexer;
import org.frc5687.swerve.subsystems.Intake;

public class AutoIntake extends OutliersCommand {

    private Intake _intake;
    private Indexer _indexer;

    public AutoIntake(Intake intake, Indexer indexer){
        _intake = intake;
        _indexer = indexer;
        addRequirements(intake, indexer);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute(){
        super.execute();
        // Deploy the intake and spin rollers.
        _intake.setSpeed(Constants.INTAKE.INTAKEING_SPEED);
    }

    @Override
    public boolean isFinished(){
        super.isFinished();
        return false;
    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        // Retract the intake when the command is interrupted.
        (new RetractIntake(_intake)).schedule();
    }
}