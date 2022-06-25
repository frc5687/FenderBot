package org.frc5687.swerve.commands;

import org.frc5687.swerve.Constants;
import org.frc5687.swerve.subsystems.Indexer;

public class EdgeIn extends OutliersCommand{

    private Indexer _indexer;
    private long _delay;
    
    public EdgeIn(Indexer indexer){
        _indexer = indexer;
        addRequirements(_indexer);
    }

    @Override
    public void initialize(){
        super.initialize();
        _delay = System.currentTimeMillis() + Constants.INDEXER.EDGE_IN_DELAY;
    }

    @Override
    public void execute(){
        super.execute();
        _indexer.setSpeed(Constants.INDEXER.INDEXING_SPEED);
    }

    @Override
    public boolean isFinished(){
        return System.currentTimeMillis() > _delay;
    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        _indexer.Idle();
    }
}
