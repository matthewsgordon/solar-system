
import java.awt.Color;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author matth
 */
public class SolarSystem {

    public static void main(String[] args) {

        // Full circle in radians
        final double FULL_ORBIT_RADIANS = 6.28318531;
        // Speed of earth orbit, all other bodies are set to a ratio of Earth
        final double EARTH_ORBIT_SPEED = 0.025;
        // Scale of planet to scale up relative to the Sun
        final long PLANET_SCALE = 50;
        // Ratio to reduce relative size of the Solar System to enable easier viewing
        final long DISTANCE_RATIO = 10;
        // Current edge of solar system for camera 
        final double CAMERA_START_RADIUS = 778600000.0;
        
        // Create a array list of celestial bodies
        ArrayList<CelestialBody> celestialBodies = new ArrayList<>();
        // Sun
        CelestialBody sun = new CelestialBody();
        sun.name = "Sun";
        sun.radius = 1391400 / 2;
        sun.distance = 0;
        sun.rotation = 0.1;
        sun.orbit = 0;
        sun.orbitAngle = 0;
        sun.imageURL = ".//src//sun.jpg";
        celestialBodies.add(sun);
        // Mercury
        CelestialBody mercury = new CelestialBody();
        mercury.name = "Mercury";
        mercury.radius = 4879 / 2;
        mercury.distance = 57900000.0;
        mercury.rotation = 0.001083;
        mercury.orbit = 0.241;
        mercury.orbitAngle = 0;
        mercury.imageURL = ".//src/mercury.jpg";
        celestialBodies.add(mercury);
        // Venus
        CelestialBody venus = new CelestialBody();
        venus.name = "Venus";
        venus.radius = 12104 / 2;
        venus.distance = 108200000.0;
        venus.rotation = 0.000652;
        venus.orbit = 0.615;
        venus.orbitAngle = 0;
        venus.imageURL = ".//src//venus.jpg";
        celestialBodies.add(venus);
        // Earth
        CelestialBody earth = new CelestialBody();
        earth.name = "Earth";
        earth.radius = 12756 / 2;
        earth.distance = 149600000.0;
        earth.rotation = 0.1574;
        earth.orbit = 1;
        earth.orbitAngle = 0;
        earth.imageURL = ".//src//earth.jpg";
        celestialBodies.add(earth);
        // Mars
        CelestialBody mars = new CelestialBody();
        mars.name = "Mars";
        mars.radius = 6792 / 2;
        mars.distance = 227900000.0;
        mars.rotation = 0.0866;
        mars.orbit = 1.88;
        mars.orbitAngle = 0;
        mars.imageURL = ".//src//mars.jpg";
        celestialBodies.add(mars);
        // Jupiter
        CelestialBody jupiter = new CelestialBody();
        jupiter.name = "Jupiter";
        jupiter.radius = 142984 / 2;
        jupiter.distance = 778600000.0;
        jupiter.rotation = 4.5583 ;
        jupiter.orbit = 11.9;
        jupiter.orbitAngle = 0;
        jupiter.imageURL = ".//src//jupiter.jpg";
        celestialBodies.add(jupiter);
        // Saturn
        CelestialBody saturn = new CelestialBody();
        saturn.name = "Saturn";
        saturn.radius = 120536 / 2;
        saturn.distance = 1433500000.0;
        saturn.rotation = 3.6840;
        saturn.orbit = 29.4;
        saturn.orbitAngle = 0;
        saturn.imageURL = ".//src//saturn.jpg";
        celestialBodies.add(saturn);
        // Uranus
        CelestialBody uranus = new CelestialBody();
        uranus.name = "Uranus";
        uranus.radius = 51118 / 2;
        uranus.distance = 2872500000.0;
        uranus.rotation = 1.4794;
        uranus.orbit = 87.3;
        uranus.orbitAngle = 0;
        uranus.imageURL = ".//src//uranus.jpg";
        celestialBodies.add(uranus);
        // Neptune
        CelestialBody neptune = new CelestialBody();
        neptune.name = "Neptune";
        neptune.radius = 49528 / 2;
        neptune.distance = 4495100000.0;
        neptune.rotation = 0.9719;
        neptune.orbit = 164;
        neptune.orbitAngle = 0;
        neptune.imageURL = ".//src//neptune.jpg";
        celestialBodies.add(neptune);

        // Set the inital camera position
        StdDraw3D.setScale(-CAMERA_START_RADIUS / DISTANCE_RATIO, CAMERA_START_RADIUS / DISTANCE_RATIO);

        // Create an array of 3D Shapes to draw
        ArrayList<StdDraw3D.Shape> bodyShapes = new ArrayList<>();

        // Draw the celestial bodies
        for (CelestialBody body : celestialBodies) {
            long planetScale = PLANET_SCALE;
            // Scale the Sun less
            if (body.name.equals("Sun")) {
                planetScale = 6;
            }
            // Draw the celestial body
            StdDraw3D.Shape bodyShape = StdDraw3D.sphere(body.distance, 0, 0, body.radius * planetScale, body.imageURL);
            // Add the body to the list of Shapes
            bodyShapes.add(bodyShape);
        }

        while (true) {
            // Loop through each celestial body
            for (int i = 0; i < celestialBodies.size(); i++) {
                // Calculate the new position based on current orbit angle
                double x = Math.cos(celestialBodies.get(i).orbitAngle);
                double y = Math.sin(celestialBodies.get(i).orbitAngle);
                // Set the new position
                bodyShapes.get(i).setPosition(x * celestialBodies.get(i).distance / DISTANCE_RATIO, y * celestialBodies.get(i).distance / DISTANCE_RATIO, 0);
                // Spin the planet
                bodyShapes.get(i).rotateRelative(0, celestialBodies.get(i).rotation, 0);
                // Set the next position of the planet
                celestialBodies.get(i).orbitAngle = celestialBodies.get(i).orbitAngle + EARTH_ORBIT_SPEED / celestialBodies.get(i).orbit;
                // Draw a trace of the orbit in red
                StdDraw3D.setPenColor(Color.red);
                // Calculate the next position to draw a trace
                double nextx = Math.cos(celestialBodies.get(i).orbitAngle);
                double nexty = Math.sin(celestialBodies.get(i).orbitAngle);
                // Draw the trace orbit line from the current position to the next position
                StdDraw3D.line(x * celestialBodies.get(i).distance / DISTANCE_RATIO, y * celestialBodies.get(i).distance / DISTANCE_RATIO, 0, nextx * celestialBodies.get(i).distance / DISTANCE_RATIO, nexty * celestialBodies.get(i).distance / DISTANCE_RATIO, 0);
                // If the current orbit angle is greater than a full circle then set to zero
                if (celestialBodies.get(i).orbitAngle > FULL_ORBIT_RADIANS) {
                    celestialBodies.get(i).orbitAngle = 0;
                }
            }
            StdDraw3D.show(20);
        }
    }
}
