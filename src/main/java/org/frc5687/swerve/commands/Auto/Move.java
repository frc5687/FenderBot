package org.frc5687.swerve.commands.Auto;

import org.frc5687.swerve.commands.OutliersCommand;
import org.frc5687.swerve.subsystems.Maverick;

public class Move extends OutliersCommand{

    Maverick _maverick;
    
    public Move(Maverick maverick){
        _maverick = maverick;
        addRequirements(maverick);
    }

    @Override
    public void initialize(){
        super.initialize();
    }

    @Override
    public void execute(){
        super.execute();
        _maverick.Move();
    }

    @Override
    public boolean isFinished(){
        super.isFinished();
        return _maverick.isAtPose();
    }
}
