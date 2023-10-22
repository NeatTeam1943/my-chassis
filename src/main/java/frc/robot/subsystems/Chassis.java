// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.ChassisConstants;

public class Chassis extends SubsystemBase {
  private WPI_TalonFX m_leftRear;
  private WPI_TalonFX m_leftFront;
  private WPI_TalonFX m_rightRear;
  private WPI_TalonFX m_rightFront;

  private MotorControllerGroup m_left;
  private MotorControllerGroup m_right;

  private DifferentialDrive m_drive;

  public Chassis() {
    m_leftRear = new WPI_TalonFX(ChassisConstants.kLeftRear);
    m_leftFront = new WPI_TalonFX(ChassisConstants.kLeftFront);
    m_rightRear = new WPI_TalonFX(ChassisConstants.kRightRear);
    m_rightFront = new WPI_TalonFX(ChassisConstants.kRightFront);

    m_left = new MotorControllerGroup(m_leftRear, m_leftFront);
    m_right = new MotorControllerGroup(m_rightRear, m_rightFront);

    m_drive = new DifferentialDrive(m_left, m_right);
  }

  public double getDistance() {
    double leftAvgPos = (m_leftFront.getSelectedSensorPosition() + m_leftRear.getSelectedSensorPosition()) / 2;
    double rightAvgPos = (m_rightFront.getSelectedSensorPosition() + m_rightRear.getSelectedSensorPosition()) / 2;
    double centralAvg = (leftAvgPos + rightAvgPos) / 4;
    double centralMotorAvg = centralAvg / ChassisConstants.kEncoderResolution;
    double centralWheelAvg = centralMotorAvg / ChassisConstants.kMotorToWheelRatio;

    return (-centralWheelAvg * ChassisConstants.kWheelCircumference) / 100;
  }

  public void resetEncoders() {
    m_leftFront.setSelectedSensorPosition(0);
    m_leftRear.setSelectedSensorPosition(0);
    m_rightFront.setSelectedSensorPosition(0);
    m_rightRear.setSelectedSensorPosition(0);
  }

  public void move(CommandXboxController joystick) {
    double mov = -joystick.getLeftY();
    double rot = joystick.getRightX();

    m_drive.arcadeDrive(mov, rot);
  }

  public void move(double movement){
    double mov = movement;
    
    m_drive.arcadeDrive(mov, 0);
  }
}
