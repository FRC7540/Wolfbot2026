package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class ClimberSubsystem extends SubsystemBase {

    private final SparkMax climbingMotor;
    private final RelativeEncoder encoder;
    private final DigitalInput limitSwitch;
    double extendRate = -0.5;
    double retractRate = 0.5;

    public ClimberSubsystem(int kClimbingMotorCanId, int limitSwitchPort) {
        climbingMotor = new SparkMax(kClimbingMotorCanId, MotorType.kBrushless);
        encoder = climbingMotor.getEncoder();
        limitSwitch = new DigitalInput(limitSwitchPort);
    }

    public void disable_climber() {
        climbingMotor.set(0);
    }

    public void climber_extend() {
        if (isPeak()) {
            climbingMotor.set(0);
        }
        climbingMotor.set(extendRate);
    }

    public void climber_retract() {
        if (isHome()) {
            climbingMotor.set(0);
            encoder.setPosition(0);
        }
        climbingMotor.set(retractRate);
    }

    public boolean isHome() {
        return limitSwitch.get();
    }

    public boolean isPeak() {
        return encoder.getPosition() >= 170;
    }

}
