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
import java.sql.NClob;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.WeakHashMap;

/**
 * @author Jafet Rodríguez
 * @author Luis Ruiz
 * This class runs everything and generates the designed render.
 */
public class Raytracer {

    public static void main(String[] args) {
        System.out.println(new Date());
        Scene scene01 = new Scene();

        scene01.setCamera(new Camera(new Vector3D(0, 0, -8), 160, 160, 2000, 2000, 0f, 30f));
        //FIRST RENDER
        /*scene01.addLight(new PointLight(new Vector3D(0f,1.7f,-1.5f), Color.YELLOW, 1.6));
        scene01.addObject(new Polygon(new Vector3D(0f, -2f, 0f), new Triangle[]{new Triangle(new Vector3D(10f,0f,10f), new Vector3D(-10f,0f,10f), new Vector3D(-10f,0f,-10f)),
                new Triangle(new Vector3D(-10f,0f,-10f), new Vector3D(10f,0f,-10f), new Vector3D(10f,0f,10f))}, Color.GRAY, new Material(0.05,5,1,true,false)));
        scene01.addLight(new PointLight(new Vector3D(-2f,-0.5f,-1f), Color.RED, 1.2));
        scene01.addObject(new Sphere(new Vector3D(0f,2f, 2f), 1.3f, Color.PINK, new Material(0.3,0.5,0.8,true,false)));
        scene01.addObject(OBJReader.GetPolygon("SmallTeapot.obj", new Vector3D(0f, -2f, 3f), Color.CYAN,new Material(0.3,0.5,0.4,false,false)));
        scene01.addObject(OBJReader.GetPolygon("SmallTeapot.obj", new Vector3D(-2f, -2f, 0.5f), Color.RED,new Material(0.3,0.5,0.4,false,false)));
        scene01.addLight(new PointLight(new Vector3D(2f,-0.5f,-1f), Color.CYAN, 1.2));
        scene01.addObject(OBJReader.GetPolygon("SmallTeapot.obj", new Vector3D(2f, -2f, 0.5f), new Color(77, 0, 153),new Material(0.3,0.5,0.4,false,false)));
        */
        //SECOND RENDER
        /*scene01.addLight(new PointLight(new Vector3D(0f,1.2f,1f), Color.PINK, 2.6));
        //Wall
        scene01.addObject(new Polygon(new Vector3D(0f, -0f, 10f), new Triangle[]{new Triangle(new Vector3D(60f,60f,0f), new Vector3D(-60f,60f,0f), new Vector3D(-60f,-60f,0f)),
                new Triangle(new Vector3D(-60f,-60f,0f), new Vector3D(60f,-60f,0f), new Vector3D(60f,60f,0f))}, Color.GRAY, new Material(0.05,5,1,false,false)));
        //Floor
        scene01.addObject(new Polygon(new Vector3D(0f, -2f, 0f), new Triangle[]{new Triangle(new Vector3D(10f,0f,10f), new Vector3D(-10f,0f,10f), new Vector3D(-10f,0f,-10f)),
                new Triangle(new Vector3D(-10f,0f,-10f), new Vector3D(10f,0f,-10f), new Vector3D(10f,0f,10f))}, Color.GRAY, new Material(0.05,5,1,true,false)));

        scene01.addObject(OBJReader.GetPolygon("SmallTeapot.obj", new Vector3D(0f, -1.8f, 3f), Color.RED, new Material(0.3,0.5,0.4,false,false)));
        scene01.addObject(OBJReader.GetPolygon("SmallTeapot.obj", new Vector3D(-2f, -0.6f, 3f), Color.PINK, new Material(0.3,0.5,0.4,false,false)));
        scene01.addObject(new Sphere(new Vector3D(1f,-1f, 0.5f),0.45f, Color.ORANGE, new Material(0.3,0.5,0.8,false,true)));
        scene01.addObject(OBJReader.GetPolygon("Cube.obj", new Vector3D(-0.75f, -0.9f, 0.5f), Color.CYAN, new Material(0.3,0.5,0.4,false,true)));
        scene01.addObject(new Sphere(new Vector3D(1f,0f, 3.5f),0.45f, Color.ORANGE, new Material(0.3,0.5,0.8,true,false)));
         */
        //THIRD RENDER

        scene01.addLight(new PointLight(new Vector3D(0f,0f,2f), new Color(255, 127, 0), 2.6));
        scene01.addLight(new PointLight(new Vector3D(0f,0f,1.8f), Color.RED, 0.6));
        scene01.addObject(new Polygon(new Vector3D(0f, -2f, 0f), new Triangle[]{new Triangle(new Vector3D(10f,0f,10f), new Vector3D(-10f,0f,10f), new Vector3D(-10f,0f,-10f)),
                new Triangle(new Vector3D(-10f,0f,-10f), new Vector3D(10f,0f,-10f), new Vector3D(10f,0f,10f))}, Color.ORANGE, new Material(0.05,5,1,true,false)));
        scene01.addObject(OBJReader.GetPolygon("Campfire.obj", new Vector3D(0f, -2.1f, 2.5f), new Color(255, 87, 0), new Material(0.3,0.5,0.4,false,false)));
        scene01.addLight(new PointLight(new Vector3D(-2f,2.8f,4f), Color.YELLOW, -10));
        scene01.addObject(new Sphere(new Vector3D(-4f,2.4f, 6f), 0.4f, Color.PINK, new Material(0.4,25,2,false,false)));
        scene01.addObject(OBJReader.GetPolygon("deer.obj", new Vector3D(1.8, -2f,-1f), new Color(116, 74, 37 ), new Material(0.3,0.5,0.4,false,false)));
        scene01.addObject(OBJReader.GetPolygon("deer2.obj", new Vector3D(-1.8, -2f,-1f), new Color(116, 74, 37 ), new Material(0.3,0.5,0.4,false,false)));

        BufferedImage image = raytrace(scene01);
        File outputImage = new File("image.png");
        try {
            ImageIO.write(image, "png", outputImage);
        } catch (IOException ioe) {
            System.out.println("Something failed");
        }
        System.out.println(new Date());
    }

    /**
     * Here, every ray is tracing, looking for a collision and render according the object's properties.
     * @param scene receives the scene that will be rendered.
     * @return rendered image
     */
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
                    //Reflection
                    if (closestIntersection.getObject().getMaterial().getReflection() == true){
                        int timesReflected = 0;
                        Intersection reflection = reflection(closestIntersection, objects, mainCamera, timesReflected);
                        if (reflection != null){
                            closestIntersection = reflection;
                        }
                    }
                    //Refraction
                    if (closestIntersection != null) {
                        pixelColor = Color.BLACK;
                        //Reflection
                        if (closestIntersection.getObject().getMaterial().getRefraction() == true) {
                            int timesRefracted = 0;
                            Intersection refraction = refraction(closestIntersection, objects, mainCamera, timesRefracted);
                            if (refraction != null) {
                                closestIntersection = refraction;
                            }
                        }
                    }
                    //Check lights
                    for (Light light : lights) {
                        Ray shadowRay = new Ray(closestIntersection.getPosition(), Vector3D.substract(light.getPosition(),closestIntersection.getPosition()));
                        Intersection shadow = raycast(shadowRay, objects, closestIntersection.getObject(), new float[]{cameraZ + nearFarPlanes[0], cameraZ + nearFarPlanes[1]});
                        float nDotL = light.getNDotL(closestIntersection);
                        float intensity = (float) light.getIntensity() * nDotL;
                        float falloff = intensity / (float) Vector3D.magnitude(Vector3D.substract(closestIntersection.getPosition(),light.getPosition()));
                        //float falloff = intensity / (float) Math.pow(Vector3D.magnitude(Vector3D.substract(closestIntersection.getPosition(),light.getPosition())),2) ;
                        double ambient = closestIntersection.getObject().getMaterial().getAmbient();
                        double specular = specular(closestIntersection, ray, light);
                        Color lightColor = light.getColor();
                        Color objColor = closestIntersection.getObject().getColor();
                        float objDiffuse = (float) closestIntersection.getObject().getMaterial().getDiffuse();
                        float[] lightColors = new float[]{(lightColor.getRed() / 255.0f)*objDiffuse, (lightColor.getGreen() / 255.0f)*objDiffuse, (lightColor.getBlue() / 255.0f)*objDiffuse};
                        float[] objColors = new float[]{objColor.getRed() / 255.0f, objColor.getGreen() / 255.0f, objColor.getBlue() / 255.0f};
                        for (int colorIndex = 0; colorIndex < objColors.length; colorIndex++) {
                            objColors[colorIndex] *= (falloff+specular+ambient) * lightColors[colorIndex];
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

    /**
     * This method send the rays.
     * @param ray
     * @param objects
     * @param caster
     * @param clippingPlanes
     * @return closest intersection.
     */
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

    /**
     * @param intersection
     * @param ray
     * @param light
     * @return specular value
     * Adding this specular value to the final color, will achieve a blinn-phong texture.
     */
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

    /**
     * @param intersection
     * @param objects
     * @param camera
     * @param timesReflected
     * @return reflective intersection.
     * @see "https://www.scratchapixel.com/lessons/3d-basic-rendering/introduction-to-shading/reflection-refraction-fresnel"
     */
    public static Intersection reflection(Intersection intersection, ArrayList<Object3D> objects, Camera camera, int timesReflected){
        boolean hasReflection = intersection.getObject().getMaterial().getReflection();
        float cameraZ = (float) camera.getPosition().getZ();
        float[] nearFarPlanes = camera.getNearFarPlanes();
        if (hasReflection == true && timesReflected <= 2) {
            Vector3D normal = intersection.getNormal();
            Vector3D view = Vector3D.substract(intersection.getPosition(), camera.getPosition());
            Vector3D reflectionDirection = Vector3D.add(Vector3D.scalarMultiplication(Vector3D.scalarMultiplication(normal, -2), Vector3D.dotProduct(normal, view)), view);
            Ray reflection = new Ray(intersection.getPosition(), reflectionDirection);

            for (Object3D object : objects){
                if (!object.equals(intersection.getObject())) {
                    Intersection reflectionIntersection = raycast(reflection, objects, intersection.getObject(), new float[]{cameraZ + nearFarPlanes[0], cameraZ + nearFarPlanes[1]});
                    //Recursive reflection
                    if (reflectionIntersection == null) {
                        return intersection;
                    }
                    else {
                        return reflection(reflectionIntersection, objects, camera, timesReflected+1);
                    }
                }
            }
        }
        return intersection;
    }

    /**
     * @param intersection
     * @param objects
     * @param camera
     * @param timesRefracted
     * @return refractive intersection.
     * @see "https://www.scratchapixel.com/lessons/3d-basic-rendering/introduction-to-shading/reflection-refraction-fresnel"
     */
    public static Intersection refraction(Intersection intersection, ArrayList<Object3D> objects, Camera camera, int timesRefracted){
        boolean hasRefraction = intersection.getObject().getMaterial().getRefraction();

        double n1 = 1;
        double n2 = 1.8;
        double n = n1/n2;

        float cameraZ = (float) camera.getPosition().getZ();
        float[] nearFarPlanes = camera.getNearFarPlanes();
        if (hasRefraction == true && timesRefracted <= 2) {
            Vector3D N = intersection.getNormal();
            Vector3D I = Vector3D.normalize(Vector3D.substract(intersection.getPosition(), camera.getPosition()));
            double c1 = Vector3D.dotProduct(N, I);
            double c2 = Math.sqrt(1-Math.pow(n,2)*(1-Math.pow(c1,2)));

            Vector3D T = Vector3D.add(Vector3D.scalarMultiplication(I,n),Vector3D.scalarMultiplication(N,(n*c1)-c2));

            Ray refraction = new Ray(intersection.getPosition(), T);

            for (Object3D object : objects){
                if (!object.equals(intersection.getObject())) {
                    Intersection refractionIntersection = raycast(refraction, objects, intersection.getObject(), new float[]{cameraZ + nearFarPlanes[0], cameraZ + nearFarPlanes[1]});
                    //Recursive refraction
                    if (refractionIntersection == null) {
                        return intersection;
                    }
                    else {
                        return refraction(refractionIntersection, objects, camera, timesRefracted+1);
                    }
                }
            }
        }
        return intersection;
    }
}
