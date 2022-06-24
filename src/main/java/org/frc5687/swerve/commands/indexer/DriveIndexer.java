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
        switch (_indexer.getState()) {
            case IDLE:
                // set indexer to constant speed waiting for intake proximity to be triggered.
                _indexer.setSpeed(Constants.INDEXER.IDLE_INDEXER);
                if (_indexer.getIntakeProximity()) {
                    // if triggered switch states.
                    _indexer.setState(Indexer.IndexerState.BALL_DETECTED);
                }
            case BALL_DETECTED:
                // check if top proximity is triggered
                if (_indexer.getTopProximity()) {
                    // if triggered switch state to second ball
                    _indexer.setState(Indexer.IndexerState.SECOND_DETECTED);
                    // if not triggered check if bottom proximity is trigged
                } else if (_indexer.getBottomProximity()) {
                    // if triggered set state to first ball.
                    _indexer.setState(Indexer.IndexerState.FIRST_DETECTED);
                } else {
                    // if none true index ball
                    _indexer.setSpeed(Constants.INDEXER.INDEXING_SPEED);
                }
            case FIRST_DETECTED:
                // wait until intake is triggered again.
                if (_indexer.getIntakeProximity()) {
                    // if the top is triggered switch states
                    if (_indexer.getTopProximity()) {
                        _indexer.setState(Indexer.IndexerState.SECOND_DETECTED);
                    } else {
                        // if not index ball.
                        _indexer.setSpeed(Constants.INDEXER.INDEXING_SPEED);
                    }
                }
            case SECOND_DETECTED:
                // stop indexer.
                _indexer.setSpeed(0.0);
            case SHOOTING:
                // wait till indexer is empty and set back to idle.
                if (!(_indexer.getTopProximity() || _indexer.getBottomProximity() || _indexer.getBottomProximity())) {
                    _indexer.setState(Indexer.IndexerState.IDLE);
                }
        }
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
