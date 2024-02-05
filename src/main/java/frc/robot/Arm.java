package frc.robot;

public class Arm {
  double movement;
  boolean settedMovement;
  double target;
  double current;
  public Arm() {
    // This is where the arm will be initialized
  }
  public void setMovement(double amount) {
    movement = amount;
    settedMovement = true;
  }

  public void hold() {
    double x = current;
    x = target - x;
    
    double a = (x+(0.05*x)) / Math.abs(x);
    double b = (((x-1.1)/Math.abs(x-1.1))+1)/2;
    double c = Math.abs((((x+1.1)/Math.abs(x+1.1))-1)/2);
    double d = -(b+c)+1;
    double y = ((0.956*(Math.sqrt(Math.abs(x)*a)*d+-a*(d-1))))/1.047;
    double movement = y;
  }

  public void move() {

  }
    
}
