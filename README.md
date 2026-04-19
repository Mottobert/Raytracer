# Raytracer
This project is a basic recursive raytracer implemented in Java using Swing for visualization. It renders a 3D scene by casting rays from a virtual camera into the scene and computing the color of each pixel based on object intersections and a basic point light.

![10_TransparentHollowSphere](renderings/10_TransparentHollowSphere.png)

## Features
- Ray-sphere intersection
- Diffuse and specular shading 
- Shadows 
- Recursive reflections 
- Refraction with transparent materials 
- Simple scene setup with multiple spheres and materials

## How it works
For each pixel, a primary ray is shot out from the camera into the scene. The raytracer:
1.	Finds the closest object intersection
2.	Computes shading
3.	Recursively traces reflection and refraction rays
4.	Combines all contributions into a final color

The recursion depth limits how many times rays are reflected or refracted.

## Running the Project
The project is using Maven as a build system and was created using the IntelliJ IDEA IDE.

1. Run ```mvn package``` in the root directory of the project.
2. A .jar file is created in the target-directory. 
3. Start the .jar file with ```java -jar /target/snapshot.jar``` (filename needs to be replaced)

A window will open and render the scene pixel by pixel.

## Notes
- In the test folder you can find some test classes to verify the methods of the classes are working as intended.

## Test Renderings of the different features

### 1. Visibility
![1_Visibility](renderings/1_Visibility.png)

### 2. Diffuse Lighting
![2_DiffuseLighting](renderings/2_DiffuseLighting.png)

### 3. Diffuse and Specular Lighting
![3_DiffuseAndSpecularLighting](renderings/3_DiffuseAndSpecularLighting.png)

### 4. Checkerboard Texture
![4_CheckerboardTexture](renderings/4_CheckerboardTexture.png)

### 5. Multiple Objects
![5_MultipleObjects](renderings/5_MultipleObjects.png)

### 6. Shadows
![6_Shadows](renderings/6_Shadows.png)

### 7. Recursive Raytracing Depth = 2
![7_RecursiveRaytracingDepth=2](renderings/7_RecursiveRaytracingDepth=2.png)

### 8. Recursive Raytracing Depth = 5
![8_RecursiveRaytracingDepth=5](renderings/8_RecursiveRaytracingDepth=5.png)

### 9. Transparent solid Sphere
![9_TransparentSolidSphere](renderings/9_TransparentSolidSphere.png)

### 10. Transparent hollow Sphere
![10_TransparentHollowSphere](renderings/10_TransparentHollowSphere.png)