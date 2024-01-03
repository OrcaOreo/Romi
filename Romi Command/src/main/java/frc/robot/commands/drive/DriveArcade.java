package frc.robot.commands.drive;

import java.util.function.DoubleSupplier;

import javax.naming.InitialContext;
import javax.xml.stream.events.EndDocument;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class DriveArcade extends CommandBase{
    DoubleSupplier speedSup;
    DoubleSupplier turnSup;
    double turnFactor;
    Drive drive;

public DriveArcade(DoubleSupplier speedSup, DoubleSupplier turnSup, double turnFactor, Drive drive){

    this.speedSup = speedSup;
    this.turnSup = turnSup;
    this.turnFactor = turnFactor;
    this.drive = drive;

    addRequirements(drive);

}


@Override
public void initialize() {}

@Override
  public void execute() {
    double leftSpeed = speedSup.getAsDouble() - (turnFactor * turnSup.getAsDouble());
    double rightSpeed = speedSup.getAsDouble() + (turnFactor * turnSup.getAsDouble());

    drive.setPercent(leftSpeed,rightSpeed);

  }

@Override
public void end(boolean interrupted) {
    drive.setPercent(0, 0);
}
}


