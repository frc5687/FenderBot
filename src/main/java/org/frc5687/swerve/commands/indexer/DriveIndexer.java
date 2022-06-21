package org.frc5687.swerve.commands.indexer;

import org.frc5687.swerve.Constants;
import org.frc5687.swerve.commands.OutliersCommand;
import org.frc5687.swerve.subsystems.Indexer;

public class DriveIndexer extends OutliersCommand {

    private final Indexer _indexer;

    public DriveIndexer(Indexer indexer) {
        _indexer = indexer;
        addRequirements(_indexer);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        super.execute();
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
