package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class OperateBallSubsystem extends SubsystemBase {

    private SparkMax intakeMotor;
    private SparkMax storageMotor;
    private SparkMax shooterMotorUpper;
    private SparkMax shooterMotorLower;

      

    public OperateBallSubsystem(int kIntakeMotorCanId, int kStorageMotorCanId, int kShooterMotorUpperCanId,
            int kShooterMotorLowerCanId) {
        shooterMotorUpper = new SparkMax(kShooterMotorUpperCanId, MotorType.kBrushless);
        shooterMotorLower = new SparkMax(kShooterMotorLowerCanId, MotorType.kBrushless);
        intakeMotor = new SparkMax(kIntakeMotorCanId, MotorType.kBrushless);
        storageMotor = new SparkMax(kStorageMotorCanId, MotorType.kBrushless);
    }

    public void stop_intake() {
        intakeMotor.set(0);
        storageMotor.set(0);
    }

    public void run_intake() {
        intakeMotor.set(0.25);
        storageMotor.set(0.25);
    }

    public void run_outtake(){
        intakeMotor.set(-0.25);
        storageMotor.set(-0.25);
    }

    public void disable_shooter() {
        shooterMotorUpper.set(0);
        shooterMotorLower.set(0);
    }

    public void enable_upper_shooter() {
        shooterMotorUpper.set(.7); 
        shooterMotorLower.set(.7);
    }

    public void enable_lower_shooter(){
        intakeMotor.set(.5);
        storageMotor.set(-.5);
    }

}
