/**
 * [1968] - [2021] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.raytracer.tools;

import java.awt.*;

/**
 * @author Luis Ruiz
 * This method defines specific object's information for posterior rendering work
 */
public class Material {
        private double ambient;
        private double shininess;
        private double diffuse;
        private boolean reflection;
        private boolean refraction;

    /**
     * @param ambient receives an ambient coefficient.
     * @param shininess receives a small value which will be used for metallic-like renders.
     * @param diffuse receives a diffuse coefficient.
     * @param reflection tells if this is a reflective object.
     * @param refraction tells if this is a refractive object.
     */
        public Material(double ambient, double shininess, double diffuse, boolean reflection, boolean refraction){
            setAmbient(ambient);
            setShininess(shininess);
            setDiffuse(diffuse);
            setReflection(reflection);
            setRefraction(refraction);
        }

    public double getAmbient() {
        return ambient;
    }

    public void setAmbient(double ambient) {
        this.ambient = ambient;
    }

    public double getShininess() {
        return shininess;
    }

    public void setShininess(double shininess) {
        this.shininess = shininess;
    }

    public double getDiffuse() {
        return diffuse;
    }

    public void setDiffuse(double diffuse) {
        this.diffuse = diffuse;
    }

    public boolean getReflection() {
        return reflection;
    }

    public void setReflection(boolean reflection) {
        this.reflection = reflection;
    }

    public boolean getRefraction() {
        return refraction;
    }

        public void setRefraction(boolean refraction) {
        this.refraction = refraction;
    }
}
