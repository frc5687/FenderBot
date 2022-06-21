package org.frc5687.swerve.commands.shooter;

import org.frc5687.swerve.Constants;
import org.frc5687.swerve.commands.OutliersCommand;
import org.frc5687.swerve.subsystems.Shooter;

public class IdleShooter extends OutliersCommand {
    private final Shooter _shooter;

    public IdleShooter(Shooter shooter) {
        _shooter = shooter;
        addRequirements(_shooter);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        super.execute();
        _shooter.setSpeed(Constants.SHOOTER.IDLE_SHOOTING_SPEED);
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
