package com.team687.frc2017.commands;

import com.team687.frc2017.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drive for a specified time
 * 
 * @author tedlin
 *
 */

public class DriveTime extends Command {

    private double m_straightPower;
    private double m_timeout;
    private boolean m_isHighGear;
    private double m_startTime;

    /**
     * @param straightPower
     * @param timeout
     * @param isHighGear
     */
    public DriveTime(double straightPower, double timeout, boolean isHighGear) {
	m_straightPower = straightPower;
	m_timeout = timeout;
	m_isHighGear = isHighGear;

	// subsystem dependencies
	requires(Robot.drive);
    }

    @Override
    protected void initialize() {
	SmartDashboard.putString("Current Command", "DriveTime");
	m_startTime = Timer.getFPGATimestamp();

	if (m_isHighGear) {
	    Robot.drive.shiftUp();
	} else if (!m_isHighGear) {
	    Robot.drive.shiftDown();
	}
    }

    @Override
    protected void execute() {
	Robot.drive.setPower(m_straightPower, -m_straightPower);
    }

    @Override
    protected boolean isFinished() {
	return Timer.getFPGATimestamp() - m_startTime > m_timeout;
    }

    @Override
    protected void end() {
	Robot.drive.stopDrive();
    }

    @Override
    protected void interrupted() {
	end();
    }

}
