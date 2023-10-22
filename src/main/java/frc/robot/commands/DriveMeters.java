// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ChassisConstants;
import frc.robot.subsystems.Chassis;

public class DriveMeters extends CommandBase {
  private double m_range;
  private Chassis m_chassis;

  public DriveMeters(double range, Chassis chassis) {
    m_range = range;
    m_chassis = chassis;

    addRequirements(m_chassis);
  }

  @Override
  public void initialize() {
    m_chassis.resetEncoders();
  }

  @Override
  public void execute() {
    m_chassis.move(ChassisConstants.kSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("==========Finished Auto==========");
  }

  @Override
  public boolean isFinished() {
    return m_chassis.getDistance() >= m_range;
  }
}
