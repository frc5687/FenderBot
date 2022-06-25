package org.frc5687.swerve.commands;

import org.frc5687.swerve.subsystems.Shooter;

public class IdleShooter extends OutliersCommand{

    private Shooter _shooter;
    
    public IdleShooter(Shooter shooter){
        _shooter = shooter;
        addRequirements(_shooter);
    }

    @Override
    public void execute(){
        super.execute();
        _shooter.Idle();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }
}