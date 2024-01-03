package frc.robot.subsystems;

import java.lang.reflect.Field;
import java.time.Period;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Tracker extends SubsystemBase{
    private static Tracker instance = new Tracker();

    Field2d field = new Field2d();
    DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(Drive.getInstance().getAngle(), Drive.getInstance().getLeftDistance(), Drive.getInstance().getRightDistance());

    public void update(){
        odometry.update(Drive.getInstance().getAngle(), Drive.getInstance().getLeftDistance(), Drive.getInstance().getRightDistance());
        field.setRobotPose(getPose());
    }

    public static Tracker getInstance(){
        return instance;
    }

    public Pose2d getPose(){
        return odometry.getPoseMeters();

    }
    public void setPose(Pose2d newPose){
        odometry.resetPosition(Drive.getInstance().getAngle(), Drive.getInstance().getLeftDistance(), Drive.getInstance().getRightDistance(), newPose);
    }

    @Override
    public void periodic() {
        update();
        SmartDashboard.putData(field);
    }

}
