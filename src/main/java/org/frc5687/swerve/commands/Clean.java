package org.frc5687.swerve.commands;

import org.frc5687.swerve.subsystems.Indexer;
import org.frc5687.swerve.subsystems.Intake;
import org.frc5687.swerve.subsystems.Shooter;

public class Clean extends OutliersCommand{

    private Intake _intake;
    private Indexer _indexer;
    private Shooter _shooter;
    
    public Clean(Intake intake, Indexer indexer, Shooter shooter){
        _intake = intake;
        _indexer = indexer;
        _shooter = shooter;
        addRequirements(intake, indexer, shooter);
    }

    @Override
    public void execute(){
        super.execute();
        _intake.Clean();
        _indexer.Clean();
        _shooter.Clean();
    }

    @Override
    public boolean isFinished(){
        super.isFinished();
        return false;
    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        _intake.Idle();
        _indexer.Idle();
        _shooter.Idle();
    }
}