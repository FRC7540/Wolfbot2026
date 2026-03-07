package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;


import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class ClimberSubsystem extends SubsystemBase {
    
    private SparkMax climbingMotor;
    private RelativeEncoder climberEncoder;  

    private double climberPosition = climberEncoder.getPosition();

    public ClimberSubsystem(int kClimbingMotorCanId) {
        climbingMotor = new SparkMax(kClimbingMotorCanId, MotorType.kBrushless);
        climberEncoder.getPosition();
        climberEncoder.setPosition(0);
    }

    public void disable_climber() {
        climbingMotor.set(0);
    }

    public void climber_extend() {
        if (climberPosition <= 182) {
            climbingMotor.set(-.5);
        }
        else {
            climbingMotor.set(0);
        }

    }

    public void climber_retract() {
        climbingMotor.set(.5);
        if (climberPosition != 0) {
            climbingMotor.set(0.5);
        }
    }


}
