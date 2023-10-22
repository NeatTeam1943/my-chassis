// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Chassis;

public class Drive extends CommandBase {
  private Chassis m_chassis;
  private CommandXboxController m_joystick; 

  public Drive(Chassis chassis, CommandXboxController joystick) {
    m_chassis = chassis;
    m_joystick = joystick; 

    addRequirements(m_chassis);
  }

  @Override
  public void execute() {
    m_chassis.move(m_joystick);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
