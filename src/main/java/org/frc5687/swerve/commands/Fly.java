package org.frc5687.swerve.commands;

import org.frc5687.swerve.subsystems.Maverick;

public class Fly extends OutliersCommand{
    
    private Maverick _maverick;
    
    public Fly(Maverick maverick){
        _maverick = maverick;
        metric("Maverick Init", true);
    }

    @Override
    public void initialize(){
        super.initialize();
        _maverick.rumble();
    }

    @Override
    public void execute(){
        super.execute();
        _maverick.wayPointMove();
    }

    @Override
    public boolean isFinished(){
        return _maverick.isAtPose();
    }

    @Override
    public void end(boolean interrupted){
        super.end(interrupted);
        _maverick.stopRumble();
        _maverick.nextPoint();
    }
}