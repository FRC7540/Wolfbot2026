package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

    private final SparkMax shooterMotorUpper;
    private final SparkMax shooterMotorLower;

    double intakeRate = 0.5;
    double shootingRate = 0.7;

    public ShooterSubsystem(int kShooterMotorUpperCanId,
            int kShooterMotorLowerCanId) {
        shooterMotorUpper = new SparkMax(kShooterMotorUpperCanId, MotorType.kBrushless);
        shooterMotorLower = new SparkMax(kShooterMotorLowerCanId, MotorType.kBrushless);
    }

    public void disable_shooter() {
        shooterMotorUpper.set(0);
        shooterMotorLower.set(0);
    }

    public void enable_upper_shooter() {
        shooterMotorUpper.set(shootingRate);
        shooterMotorLower.set(-shootingRate);
    }
}
