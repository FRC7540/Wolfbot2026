package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

    private final SparkMax intakeMotor;
    private final SparkMax storageMotor;

    double intakeRate = 0.5;
    double shootingRate = 0.7;

    public IntakeSubsystem(int kIntakeMotorCanId, int kStorageMotorCanId) {
        intakeMotor = new SparkMax(kIntakeMotorCanId, MotorType.kBrushless);
        storageMotor = new SparkMax(kStorageMotorCanId, MotorType.kBrushless);
    }

    public void stop_intake() {
        intakeMotor.set(0);
        storageMotor.set(0);
    }

    public void run_intake() {
        intakeMotor.set(intakeRate);
        storageMotor.set(intakeRate);
    }

    public void feed_shooter() {
        intakeMotor.set(intakeRate);
        storageMotor.set(-intakeRate);
    }

    public void run_outtake() {
        intakeMotor.set(-intakeRate);
        storageMotor.set(-intakeRate);
    }
}
