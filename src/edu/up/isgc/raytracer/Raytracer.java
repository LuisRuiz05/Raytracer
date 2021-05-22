/**
 * [1968] - [2021] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.raytracer;

import edu.up.isgc.raytracer.lights.DirectionalLight;
import edu.up.isgc.raytracer.lights.Light;
import edu.up.isgc.raytracer.lights.PointLight;
import edu.up.isgc.raytracer.objects.*;
import edu.up.isgc.raytracer.tools.Material;
import edu.up.isgc.raytracer.tools.OBJReader;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Jafet Rodríguez
 * @author Luis Ruiz
 */
public class Raytracer {

    public static void main(String[] args) {
        System.out.println(new Date());
        Scene scene01 = new Scene();
        scene01.setCamera(new Camera(new Vector3D(0, 0, -8), 160, 160, 800, 800, 0f, 30f));
        //scene01.addLight(new DirectionalLight(Vector3D.ZERO(), new Vector3D(0.0, 2.5, -10), Color.WHITE, 4.4));
        scene01.addLight(new PointLight(new Vector3D(0f,0.5f,-1f), Color.WHITE, 1.8));
        //scene01.addObject(new Sphere(new Vector3D(-2.5f, 0.5f, 2f), 0.9f, Color.GREEN));
        scene01.addObject(new Polygon(new Vector3D(0f, -2f, 0f), new Triangle[]{new Triangle(new Vector3D(10f,0f,10f), new Vector3D(-10f,0f,10f), new Vector3D(-10f,0f,-10f)),
                new Triangle(new Vector3D(-10f,0f,-10f), new Vector3D(10f,0f,-10f), new Vector3D(10f,0f,10f))}, Color.GRAY, new Material(0.05,5,1,1,1)));
        scene01.addObject(OBJReader.GetPolygon("SmallTeapot.obj", new Vector3D(0f, -1.7f, 1.5f), Color.RED,new Material(0.3,0.8,0.6,1,1)));
        //scene01.addObject(OBJReader.GetPolygon("Ring.obj", new Vector3D(0f, -1.7f, 1.5f), Color.ORANGE));
        BufferedImage image = raytrace(scene01);
        File outputImage = new File("image.png");
        try {
            ImageIO.write(image, "png", outputImage);
        } catch (IOException ioe) {
            System.out.println("Something failed");
        }
        System.out.println(new Date());
    }

    public static BufferedImage raytrace(Scene scene) {
        Camera mainCamera = scene.getCamera();
        ArrayList<Light> lights = scene.getLights();
        float[] nearFarPlanes = mainCamera.getNearFarPlanes();
        BufferedImage image = new BufferedImage(mainCamera.getResolutionWidth(), mainCamera.getResolutionHeight(), BufferedImage.TYPE_INT_RGB);
        ArrayList<Object3D> objects = scene.getObjects();

        Vector3D[][] positionsToRaytrace = mainCamera.calculatePositionsToRay();
        for (int i = 0; i < positionsToRaytrace.length; i++) {
            for (int j = 0; j < positionsToRaytrace[i].length; j++) {
                double x = positionsToRaytrace[i][j].getX() + mainCamera.getPosition().getX();
                double y = positionsToRaytrace[i][j].getY() + mainCamera.getPosition().getY();
                double z = positionsToRaytrace[i][j].getZ() + mainCamera.getPosition().getZ();

                Ray ray = new Ray(mainCamera.getPosition(), new Vector3D(x, y, z));
                float cameraZ = (float) mainCamera.getPosition().getZ();
                Intersection closestIntersection = raycast(ray, objects, null, new float[]{cameraZ + nearFarPlanes[0], cameraZ + nearFarPlanes[1]});

                //Background color
                Color pixelColor = Color.BLACK;
                if (closestIntersection != null) {
                    pixelColor = Color.BLACK;
                    for (Light light : lights) {
                        Ray shadowRay = new Ray(closestIntersection.getPosition(), Vector3D.substract(light.getPosition(),closestIntersection.getPosition()));
                        Intersection shadow = raycast(shadowRay, objects, closestIntersection.getObject(), new float[]{cameraZ + nearFarPlanes[0], cameraZ + nearFarPlanes[1]});
                        float nDotL = light.getNDotL(closestIntersection);
                        float intensity = (float) light.getIntensity() * nDotL;
                        float lightRange = intensity / (float) Vector3D.magnitude(Vector3D.substract(closestIntersection.getPosition(),light.getPosition()));
                        //float lightRange = intensity / (float) Math.pow(Vector3D.magnitude(Vector3D.substract(closestIntersection.getPosition(),light.getPosition())),2) ;
                        double ambient = closestIntersection.getObject().getMaterial().getAmbient();
                        double specular = specular(closestIntersection, ray, light);
                        Color lightColor = light.getColor();
                        Color objColor = closestIntersection.getObject().getColor();
                        float objDiffuse = (float) closestIntersection.getObject().getMaterial().getDiffuse();
                        float[] lightColors = new float[]{(lightColor.getRed() / 255.0f)*objDiffuse, (lightColor.getGreen() / 255.0f)*objDiffuse, (lightColor.getBlue() / 255.0f)*objDiffuse};
                        float[] objColors = new float[]{objColor.getRed() / 255.0f, objColor.getGreen() / 255.0f, objColor.getBlue() / 255.0f};
                        for (int colorIndex = 0; colorIndex < objColors.length; colorIndex++) {
                            objColors[colorIndex] *= (lightRange+specular+ambient) * lightColors[colorIndex];
                        }
                        Color diffuse = new Color(clamp(objColors[0], 0, 1),clamp(objColors[1], 0, 1),clamp(objColors[2], 0, 1));
                        if (shadow != null){
                            diffuse = Color.BLACK;
                        }
                        pixelColor = addColor(pixelColor, diffuse);
                    }
                }
                image.setRGB(i, j, pixelColor.getRGB());
            }
        }

        return image;
    }

    public static float clamp(float value, float min, float max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    public static Color addColor(Color original, Color otherColor){
        float red = clamp((original.getRed() / 255.0f) + (otherColor.getRed() / 255.0f), 0, 1);
        float green = clamp((original.getGreen() / 255.0f) + (otherColor.getGreen() / 255.0f), 0, 1);
        float blue = clamp((original.getBlue() / 255.0f) + (otherColor.getBlue() / 255.0f), 0, 1);
        return new Color(red, green, blue);
    }

    public static Intersection raycast(Ray ray, ArrayList<Object3D> objects, Object3D caster, float[] clippingPlanes) {
        Intersection closestIntersection = null;

        for (int k = 0; k < objects.size(); k++) {
            Object3D currentObj = objects.get(k);
            if (caster == null || !currentObj.equals(caster)) {
                Intersection intersection = currentObj.getIntersection(ray);
                if (intersection != null) {
                    double distance = intersection.getDistance();
                    if (distance >= 0 &&
                            (closestIntersection == null || distance < closestIntersection.getDistance()) &&
                            (clippingPlanes == null || (intersection.getPosition().getZ() >= clippingPlanes[0] &&
                                    intersection.getPosition().getZ() <= clippingPlanes[1]))) {
                        closestIntersection = intersection;
                    }
                }
            }
        }
        return closestIntersection;
    }

    public static double specular(Intersection intersection, Ray ray, Light light){
        Vector3D lightDirection;
        if (light instanceof DirectionalLight) {
            lightDirection = Vector3D.normalize(((DirectionalLight) light).getDirection());
        } else {
            lightDirection = Vector3D.normalize(((PointLight) light).getDirection(intersection));
        }
        Vector3D viewDirection = Vector3D.normalize(ray.getDirection());

        Vector3D lightSourceViewer = Vector3D.normalize(Vector3D.add(viewDirection, lightDirection));
        Vector3D halfVector = Vector3D.normalize(Vector3D.scalarMultiplication(lightSourceViewer,1/Vector3D.magnitude(lightSourceViewer)));

        double nDotH = Vector3D.dotProduct(intersection.getNormal(), halfVector);
        double specular = Math.pow(nDotH, intersection.getObject().getMaterial().getShininess());

        if (nDotH > 0){
            return specular;
        }
            return 0;
    }

    public static Intersection reflection(Intersection intersection, ArrayList<Object3D> objects, Camera camera){
        float reflection = 2;
        if (reflection > 0){
            //refleja();
        }
        return intersection;
    }
}
