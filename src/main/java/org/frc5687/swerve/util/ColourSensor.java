package org.frc5687.swerve.util;

public class ColourSensor {

    private ColorSensorV3 _colorSensor;
    private I2C.Port _port;
    private final ColorMatch _colorMatcher;
    private final Color kBlueTarget = new Color(0.143, 0.427, 0.429);
    private final Color kRedTarget = new Color(0.561, 0.232, 0.114);
    private final Color kGreenTarget = new Color(0.197, 0.561, 0.240);
    private final Color kYellowTarget = new Color(0.361, 0.524, 0.113);
    
    public ColourSensor(){
        
    }
}
