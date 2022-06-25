package org.frc5687.swerve.commands;

import org.frc5687.swerve.subsystems.Indexer;

public class IdleIndexer extends OutliersCommand{

    private Indexer _indexer;
    
    public IdleIndexer(Indexer indexer){
        _indexer = indexer;
        addRequirements(_indexer);
    }

    @Override
    public void execute(){
        super.execute();
        _indexer.Idle();
    }

    @Override
    public boolean isFinished(){
        super.isFinished();
        return false;
    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
    }
}