package edu.up.isgc.raytracer.tools;

import java.awt.*;

public class Material {
        private double ambient;
        private double shininess;
        private double specular;
        private double diffuse;
        private double reflection;
        private double refraction;

        public Material(double ambient, double shininess, double diffuse, double reflection, double refraction){
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

    public double getReflection() {
        return reflection;
    }

    public void setReflection(double reflection) {
        this.reflection = reflection;
    }

    public double getRefraction() {
        return refraction;
    }

    public void setRefraction(double refraction) {
        this.refraction = refraction;
    }
}
