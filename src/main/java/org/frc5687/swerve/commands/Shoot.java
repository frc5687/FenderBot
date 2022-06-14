package org.frc5687.swerve.commands;

import org.frc5687.swerve.subsystems.Shooter;

public class Shoot extends OutliersCommand{

    private Shooter _shooter;

    public Shoot(Shooter shooter){
        _shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void execute(){
        super.execute();
        _shooter.Shoot();
    }

    @Override
    public boolean isFinished(){
        super.isFinished();
        return true;
    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        _shooter.Idle();
    }
}
