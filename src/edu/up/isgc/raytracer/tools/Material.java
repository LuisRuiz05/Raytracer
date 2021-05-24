package edu.up.isgc.raytracer.tools;

import java.awt.*;

public class Material {
        private double ambient;
        private double shininess;
        private double diffuse;
        private boolean reflection;
        private boolean refraction;

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
