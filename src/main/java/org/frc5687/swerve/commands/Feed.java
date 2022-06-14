package org.frc5687.swerve.commands;

import org.frc5687.swerve.subsystems.Indexer;

public class Feed extends OutliersCommand{

    private Indexer _indexer;
    
    public Feed(Indexer indexer){
        _indexer = indexer;
    }

    @Override
    public void execute(){
        super.execute();
        _indexer.Feed();
    }

    @Override
    public boolean isFinished(){
        super.isFinished();
        return true;
    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        _indexer.Idle();
    }
}
