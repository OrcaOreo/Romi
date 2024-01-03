package frc.robot.subsystems;


import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.util.RomiGyro;

public class Drive extends SubsystemBase{

    private static Drive instance = new Drive();

    Spark leftMotor = new Spark(DriveConstants.DEVICE_ID_LEFT_MOTOR);
    Spark rightMotor = new Spark(DriveConstants.DEVICE_ID_RIGHT_MOTOR);

    Encoder leftEncoder = new Encoder(DriveConstants.DEVICE_ID_LEFT_ENCODER_A, DriveConstants.DEVICE_ID_LEFT_ENCODER_B);
    Encoder rightEncoder = new Encoder(DriveConstants.DEVICE_ID_RIGHT_ENCODER_A, DriveConstants.DEVICE_ID_RIGHT_ENCODER_B);

    RomiGyro gyro = new RomiGyro();

    public Drive(){
        leftMotor.setInverted (true); 
        rightMotor.setInverted (false);

        leftEncoder.setDistancePerPulse((Math.PI * DriveConstants.WHEEL_DIAMETER)/DriveConstants.ENCODER_CPR);
        rightEncoder.setDistancePerPulse((Math.PI * DriveConstants.WHEEL_DIAMETER) / DriveConstants.ENCODER_CPR);

        leftEncoder.reset();
        rightEncoder.reset();
    }

    @Override
    public void periodic() {
        logData();
    }

    public static Drive getInstance(){
        return instance;
    }

    public void logData(){
        SmartDashboard.putNumber("left distance", Units.metersToFeet(getLeftDistance()));
        SmartDashboard.putNumber("right distance", Units.metersToFeet(getRightDistance()));

        SmartDashboard.putNumber("gryo z-axis", getAngle().getDegrees());

    }

    public Rotation2d getAngle(){
        return Rotation2d.fromDegrees(gyro.getAngleZ());
    }

    

    public void setPercent(double leftSpeed, double rightSpeed){
        leftMotor.set(leftSpeed);
        rightMotor.set(rightSpeed);
    }
    
    public void resetEncoders(){
        leftEncoder.reset();
        rightEncoder.reset();
    }

    public double getLeftDistance(){
        return leftEncoder.getDistance();
    }

    public double getRightDistance(){
        return rightEncoder.getDistance();
    }



}