package frc.robot.commands.drive;

import java.util.function.DoubleSupplier;

import javax.naming.InitialContext;
import javax.xml.stream.events.EndDocument;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class DriveTank extends CommandBase{
    DoubleSupplier leftAxis;
    DoubleSupplier rightAxis;
    Drive drive;

public DriveTank(DoubleSupplier leftAxis, DoubleSupplier rightAxis, Drive drive){

    this.leftAxis = leftAxis;
    this.rightAxis = rightAxis;
    this.drive = drive;

    

    addRequirements(drive);

}


@Override
public void initialize() {}

@Override
  public void execute() {
    drive.setPercent(leftAxis.getAsDouble(),rightAxis.getAsDouble());

  }

@Override
public void end(boolean interrupted) {
    drive.setPercent(0, 0);
}
}


