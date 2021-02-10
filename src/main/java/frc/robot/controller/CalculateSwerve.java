package frc.robot.controller;

import java.lang.Math;

public class CalculateSwerve {
    private double m_trackWidth = 0.0;
    private double m_wheelBase = 0.0;
    private double m_hypotenuse = 0.0;
    private double m_FWD = 0.0;
    private double m_STR = 0.0;
    private double m_RCW = 0.0;

    private double frontLeftWheelPWM = 0.0;
    private double backLeftWheelPWM = 0.0;
    private double frontRightWheelPWM = 0.0;
    private double backRightWheelPWM = 0.0;

    private double frontLeftAnglePWM = 0.0; 
    private double backLeftAnglePWM = 0.0;
    private double frontRightAnglePWM = 0.0;
    private double backRightAnglePWM = 0.0;
    
    public CalculateSwerve(double trackWidth, double wheelBase) {
        m_trackWidth = trackWidth;
        m_wheelBase = wheelBase;
    }

    public double getTrackWidth() {
        return m_trackWidth;
    }

    public double getWheelBase() {
        return m_wheelBase;
    }

    public double getHypotenuse () {
        m_hypotenuse = Math.hypot(m_trackWidth, m_wheelBase);
        return m_hypotenuse;
    }

    public void setJoystick1(double FWD, double STR) {
        m_FWD = FWD;
        m_STR = STR;
    }

    public double getFWD() {
        return m_FWD;
    }

    public double getSTR() {
        return m_STR;
    }

    public void setJoystick2(double RCW) {
        m_RCW = RCW;
    }

    public double getRCW() {
        return m_RCW;
    }

    public void calculate() {
        m_hypotenuse = Math.hypot(m_trackWidth, m_wheelBase);

        double A = (m_STR - m_RCW * (m_wheelBase/m_hypotenuse));
        double B = (m_STR + m_RCW * (m_wheelBase/m_hypotenuse));
        double C = (m_FWD - m_RCW * (m_trackWidth/m_hypotenuse));
        double D = (m_FWD + m_RCW * (m_trackWidth/m_hypotenuse));

        frontLeftWheelPWM = Math.sqrt(Math.pow(B, 2) + Math.pow(D, 2));
        backLeftWheelPWM = Math.sqrt(Math.pow(A, 2) + Math.pow(D, 2));
        frontRightWheelPWM = Math.sqrt(Math.pow(B, 2) + Math.pow(C, 2));
        backRightWheelPWM = Math.sqrt(Math.pow(A, 2) + Math.pow(C, 2));

        frontLeftAnglePWM = (Math.atan2(B, D) * (180/Math.PI));
        backLeftAnglePWM = (Math.atan2(A, D) * (180/Math.PI));
        frontRightAnglePWM = (Math.atan2(B, C) * (180/Math.PI));
        backRightAnglePWM = (Math.atan2(A, C) * (180/Math.PI));
    }

    public double getFrontLeftWheelPWM() {
        return frontLeftWheelPWM;
    }

    public double getBackLeftWheelPWM() {
        return backLeftWheelPWM;
    }
    
    public double getFrontRightWheelPWM() {
        return frontRightWheelPWM;
    }
    
    public double getBackRightWheelPWM() {
        return backRightWheelPWM;
    }

    public double getFrontLeftAnglePWM() {
        return frontLeftAnglePWM;
    }
    
    public double getBackLeftAnglePWM() {
        return backLeftAnglePWM;
    }
    
    public double getFrontRightAnglePWM() {
        return frontRightAnglePWM;
    }
    
    public double getbackRightAnglePWM() {
        return backRightAnglePWM;
    } 
}
