# solar-system
## Overview
3D simulation of the Solar System in Java using the StdDraw3D library.

![alt text](https://github.com/matthewsgordon/solar-system/blob/main/java_FAKxYEtj1e.gif)

## Design
```Java
public class CelestialBody {
    // Name of celestial body
    public String name;
    // Radius of the celestial body
    public double radius;
    // Distance from the Sun
    public double distance;
    // Rate of rotation / Spin
    public double rotation;
    // Speed of orbit relative to Earth
    public double orbit;
    // Current angle of orbit
    public double orbitAngle;
    // Texture of celestial body
    public String imageURL;
}
```
