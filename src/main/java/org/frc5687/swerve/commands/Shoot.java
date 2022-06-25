package org.frc5687.swerve.commands;

import org.frc5687.swerve.subsystems.Indexer;
import org.frc5687.swerve.subsystems.Shooter;

public class Shoot extends OutliersCommand{

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
        error("Shooting");
        _shooter.Shoot();
        _indexer.Feed();
    }

    @Override
    public boolean isFinished(){
        super.isFinished();
        return false;
    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        _shooter.Idle();
        _indexer.Idle();
    }
}