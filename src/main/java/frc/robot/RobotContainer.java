// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ChassisConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Drive;
import frc.robot.commands.DriveMeters;
import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private final Drive m_drive;
  private final Chassis m_chassis;

  private final DriveMeters m_auto;

  private final CommandXboxController m_driverController;

  public RobotContainer() {
    m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);

    m_chassis = new Chassis();
    m_drive = new Drive(m_chassis, m_driverController);

    m_auto = new DriveMeters(ChassisConstants.kRange, m_chassis);

    m_chassis.setDefaultCommand(m_drive);

    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return m_auto;
  }
}
