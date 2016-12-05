package rtg.world.gen.surface;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.config.BiomeConfig;
import rtg.api.util.CliffCalculator;

public class SurfaceMountainSnow extends SurfaceBase {

    private float min;

    private float sCliff = 1.5f;
    private float sHeight = 60f;
    private float sStrength = 65f;
    private float iCliff = 0.3f;
    private float iHeight = 100f;
    private float iStrength = 50f;
    private float cCliff = 1.5f;

    public SurfaceMountainSnow(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff) {

        super(config, top, fill);
        min = minCliff;
    }

    public SurfaceMountainSnow(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff, float stoneHeight, float stoneStrength, float snowCliff, float snowHeight, float snowStrength, float clayCliff) {

        this(config, top, fill, minCliff);

        sCliff = stoneCliff;
        sHeight = stoneHeight;
        sStrength = stoneStrength;
        iCliff = snowCliff;
        iHeight = snowHeight;
        iStrength = snowStrength;
        cCliff = clayCliff;
    }

    @Override
    public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        Random rand = rtgWorld.rand;
        OpenSimplexNoise simplex = rtgWorld.simplex;
        float c = CliffCalculator.calc(x, z, noise);
        int cliff = 0;

        Block b;
        for (int k = 255; k > -1; k--) {
            b = primer.getBlockState(x, k, z).getBlock();
            if (b == Blocks.AIR) {
                depth = -1;
            }
            else if (b == Blocks.STONE) {
                depth++;

                if (depth == 0) {

                    float p = simplex.noise3(i / 8f, j / 8f, k / 8f) * 0.5f;
                    if (c > min && c > sCliff - ((k - sHeight) / sStrength) + p) {
                        cliff = 1;
                    }
                    if (c > cCliff) {
                        cliff = 2;
                    }
                    if (k > 110 + (p * 4) && c < iCliff + ((k - iHeight) / iStrength) + p) {
                        cliff = 3;
                    }

                    if (cliff == 1) {
                        if (rand.nextInt(3) == 0) {

                            primer.setBlockState(x, k, z, hcCobble(rtgWorld, i, j, x, z, k));
                        }
                        else {

                            primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                        }
                    }
                    else if (cliff == 2) {
                        primer.setBlockState(x, k, z, getShadowStoneBlock(rtgWorld, i, j, x, z, k));
                    }
                    else if (cliff == 3) {
                        primer.setBlockState(x, k, z, Blocks.SNOW.getDefaultState());
                    }
                    else if (k < 63) {
                        if (k < 62) {
                            primer.setBlockState(x, k, z, fillerBlock);
                        }
                        else {
                            primer.setBlockState(x, k, z, topBlock);
                        }
                    }
                    else {
                        primer.setBlockState(x, k, z, Blocks.GRASS.getDefaultState());
                    }
                }
                else if (depth < 6) {
                    if (cliff == 1) {
                        primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                    }
                    else if (cliff == 2) {
                        primer.setBlockState(x, k, z, getShadowStoneBlock(rtgWorld, i, j, x, z, k));
                    }
                    else if (cliff == 3) {
                        primer.setBlockState(x, k, z, Blocks.SNOW.getDefaultState());
                    }
                    else {
                        primer.setBlockState(x, k, z, Blocks.DIRT.getDefaultState());
                    }
                }
            }
        }
    }
}
