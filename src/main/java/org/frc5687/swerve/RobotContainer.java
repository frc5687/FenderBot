/* Team 5687 (C)2021 */
package org.frc5687.swerve;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.frc5687.swerve.commands.Drive;
import org.frc5687.swerve.commands.IdleIndexer;
import org.frc5687.swerve.commands.IdleIntake;
import org.frc5687.swerve.commands.IdleShooter;
import org.frc5687.swerve.commands.OutliersCommand;
import org.frc5687.swerve.commands.Auto.Move;
import org.frc5687.swerve.subsystems.DriveTrain;
import org.frc5687.swerve.subsystems.Indexer;
import org.frc5687.swerve.subsystems.Intake;
import org.frc5687.swerve.subsystems.Maverick;
import org.frc5687.swerve.subsystems.OutliersSubsystem;
import org.frc5687.swerve.subsystems.Shooter;
import org.frc5687.swerve.util.OutliersContainer;

public class RobotContainer extends OutliersContainer {

    private OI _oi;
    private AHRS _imu;

    private Robot _robot;
    private DriveTrain _driveTrain;
    private Indexer _indexer;
    private Shooter _shooter;
    private Intake _intake;

    private Maverick _Maverick;

    public RobotContainer(Robot robot, IdentityMode identityMode) {
        super(identityMode);
        _robot = robot;
    }

    public void init() {
        _oi = new OI();
        _imu = new AHRS(SPI.Port.kMXP, (byte) 200);

        _driveTrain = new DriveTrain(this, _oi, _imu);
        _indexer = new Indexer(this);
        _shooter = new Shooter(this);
        _intake = new Intake(this);
        _Maverick = new Maverick(this, _driveTrain);
        _shooter.Idle();
        _indexer.Idle();
      //  _intake.Idle();

        setDefaultCommand(_driveTrain, new Drive(_driveTrain, _oi));
       // setDefaultCommand(_intake, new IdleIntake(_intake));
        setDefaultCommand(_indexer, new IdleIndexer(_indexer));
        setDefaultCommand(_shooter, new IdleShooter(_shooter));
        _oi.initializeButtons(_driveTrain, _indexer, _shooter, _intake, _Maverick);
        _robot.addPeriodic(this::controllerPeriodic, 0.005, 0.005);
        _imu.reset();
    }

    public void periodic() {}

    public void disabledPeriodic() {}

    @Override
    public void disabledInit() {}

    @Override
    public void teleopInit() {}

    @Override
    public void autonomousInit() {
        _driveTrain.startModules();
        _Maverick.Move();
    }

    public Command getAutonomousCommand(){
        return new Move(_Maverick);
    }

    private void setDefaultCommand(OutliersSubsystem subSystem, OutliersCommand command) {
        if (subSystem == null || command == null) {
            return;
        }
        CommandScheduler s = CommandScheduler.getInstance();
        s.setDefaultCommand(subSystem, command);
    }

    @Override
    public void updateDashboard() {
        _driveTrain.updateDashboard();
        _indexer.updateDashboard();
        _intake.updateDashboard();
        _shooter.updateDashboard();
    }

    public void controllerPeriodic() {
        if (_driveTrain != null) {
            _driveTrain.controllerPeriodic();
        }
    }
}