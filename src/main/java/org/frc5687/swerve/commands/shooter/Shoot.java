package org.frc5687.swerve.commands.shooter;

import org.frc5687.swerve.Constants;
import org.frc5687.swerve.commands.OutliersCommand;
import org.frc5687.swerve.subsystems.Indexer;
import org.frc5687.swerve.subsystems.Shooter;

public class Shoot extends OutliersCommand {

    private Shooter _shooter;
    private Indexer _indexer;

    public Shoot(Shooter shooter, Indexer indexer){
        _shooter = shooter;
        _indexer = indexer;
        addRequirements(shooter, indexer);
    }

    @Override
    public void execute(){
        super.execute();
        _shooter.setSpeed(Constants.SHOOTER.SHOOTING_SPEED);
        _indexer.setSpeed(Constants.INDEXER.INDEXING_SPEED);
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