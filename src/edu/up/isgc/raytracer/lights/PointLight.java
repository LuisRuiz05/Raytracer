/**
 * [1968] - [2021] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.raytracer.lights;

import edu.up.isgc.raytracer.Intersection;
import edu.up.isgc.raytracer.Vector3D;

import java.awt.*;

/**
 * @author Jafet Rodríguez
 * @author Luis Ruiz
 * This class instantiates a light whose direction has already a defined distance.
 */
public class PointLight extends Light {
    /**
     * @param position receives the position where the light will be instantiated.
     * @param color receives an RGB color that will be applied to the light.
     * @param intensity tells how strong will the light flash in scene.
     */
    public PointLight(Vector3D position, Color color, double intensity) {
        super(position, color, intensity);
    }

    public Vector3D getDirection(Intersection intersection) {
        Vector3D direction = Vector3D.substract(getPosition(), intersection.getPosition());
        return direction;
    }

    @Override
    public float getNDotL(Intersection intersection) {
        return (float) Math.max(Vector3D.dotProduct(intersection.getNormal(),
                Vector3D.normalize(Vector3D.substract(getPosition(), intersection.getPosition()))), 0.0);
    }
}
