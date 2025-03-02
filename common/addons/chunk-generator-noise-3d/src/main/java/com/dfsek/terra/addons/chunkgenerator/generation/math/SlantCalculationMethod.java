package com.dfsek.terra.addons.chunkgenerator.generation.math;

import com.dfsek.terra.addons.chunkgenerator.generation.math.samplers.Sampler3D;
import com.dfsek.seismic.type.vector.Vector3;


public enum SlantCalculationMethod {
    DotProduct {
        private static final Vector3 DOT_PRODUCT_DIRECTION = Vector3.of(0, 1, 0);

        private static final Vector3[] DOT_PRODUCT_SAMPLE_POINTS = {
            Vector3.of(0, 0, -DERIVATIVE_DIST),
            Vector3.of(0, 0, DERIVATIVE_DIST),
            Vector3.of(0, -DERIVATIVE_DIST, 0),
            Vector3.of(0, DERIVATIVE_DIST, 0),
            Vector3.of(-DERIVATIVE_DIST, 0, 0),
            Vector3.of(DERIVATIVE_DIST, 0, 0)
        };

        @Override
        public double slant(Sampler3D sampler, double x, double y, double z) {
            Vector3.Mutable normalApproximation = Vector3.Mutable.of(0, 0, 0);
            for(Vector3 point : DOT_PRODUCT_SAMPLE_POINTS) {
                var scalar = -sampler.sample(x + point.getX(), y + point.getY(), z + point.getZ());
                normalApproximation.add(point.mutable().mulScalar(scalar));
            }
            return DOT_PRODUCT_DIRECTION.dot(normalApproximation.normalize());
        }

        @Override
        public boolean floorToThreshold() {
            return false;
        }
    },

    Derivative {
        @Override
        public double slant(Sampler3D sampler, double x, double y, double z) {
            double baseSample = sampler.sample(x, y, z);

            double xVal1 = (sampler.sample(x + DERIVATIVE_DIST, y, z) - baseSample) / DERIVATIVE_DIST;
            double xVal2 = (sampler.sample(x - DERIVATIVE_DIST, y, z) - baseSample) / DERIVATIVE_DIST;
            double zVal1 = (sampler.sample(x, y, z + DERIVATIVE_DIST) - baseSample) / DERIVATIVE_DIST;
            double zVal2 = (sampler.sample(x, y, z - DERIVATIVE_DIST) - baseSample) / DERIVATIVE_DIST;
            double yVal1 = (sampler.sample(x, y + DERIVATIVE_DIST, z) - baseSample) / DERIVATIVE_DIST;
            double yVal2 = (sampler.sample(x, y - DERIVATIVE_DIST, z) - baseSample) / DERIVATIVE_DIST;

            return Math.sqrt(
                ((xVal2 - xVal1) * (xVal2 - xVal1)) + ((zVal2 - zVal1) * (zVal2 - zVal1)) + ((yVal2 - yVal1) * (yVal2 - yVal1)));
        }

        @Override
        public boolean floorToThreshold() {
            return true;
        }
    };

    private static final double DERIVATIVE_DIST = 0.55;

    public abstract double slant(Sampler3D sampler, double x, double y, double z);

    /*
     * Controls whether palettes should be applied before or after their respective thresholds.
     *
     * If true, slant values will map to the palette of the next floor threshold, otherwise they
     * will map to the ceiling.
     */
    public abstract boolean floorToThreshold();
}
