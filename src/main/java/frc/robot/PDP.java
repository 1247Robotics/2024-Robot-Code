package frc.robot;

import edu.wpi.first.wpilibj.PowerDistribution;

public class PDP {
    PowerDistribution pdp = new PowerDistribution(0, PowerDistribution.ModuleType.kCTRE);

    public double getV() {
        return pdp.getVoltage();
    }

    public boolean isBrownout() {
        return pdp.getVoltage() < 6.5;
    }


    
}
