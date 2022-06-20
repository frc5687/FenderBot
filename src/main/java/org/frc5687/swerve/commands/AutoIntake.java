package org.frc5687.swerve.commands;

import org.frc5687.swerve.subsystems.Indexer;
import org.frc5687.swerve.subsystems.Intake;

public class AutoIntake extends OutliersCommand{

    private Intake _intake;
    private Indexer _indexer;

    public AutoIntake(Intake intake, Indexer indexer){
        _intake = intake;
        _indexer = indexer;
        addRequirements(intake, indexer);
    }

    @Override
    public void execute(){
        super.execute();
        _intake.IntakeBall();
        _indexer.Intake();
    }

    @Override
    public boolean isFinished(){
        super.isFinished();
        return false;
    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        _intake.Retract();
        _indexer.Idle();
    }
}