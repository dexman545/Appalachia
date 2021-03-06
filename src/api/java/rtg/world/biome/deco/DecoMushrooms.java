package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenBush;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SHROOM;

import rtg.api.world.RTGWorld;
import rtg.world.biome.realistic.RealisticBiomeBase;

/**
 * @author WhichOnesPink
 */
public class DecoMushrooms extends DecoBase {

    public float strengthFactor;
    public int maxY;
    public float randomFloat;
    public RandomType randomType;
    public int chance;
    public int loops;

    public DecoMushrooms() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.maxY = 255; // No height limit by default.
        this.strengthFactor = 0f; // The higher the value, the more there will be. Disabled by default.
        this.randomType = RandomType.USE_CHANCE_VALUE;
        this.randomFloat = 1f;
        this.chance = 1;
        this.loops = 1;

        this.addDecoTypes(DecoType.MUSHROOM);
    }

    @Override
    public void generate(RealisticBiomeBase biome, RTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            if (TerrainGen.decorate(rtgWorld.world, rand, new BlockPos(worldX, 0, worldZ), SHROOM)) {

                // Let's figure out what the rand.nextInt() argument should be.
                switch (this.randomType) {
                    case ALWAYS_GENERATE:
                        this.chance = 1;
                        break;

                    case USE_CHANCE_VALUE:
                        break;

                    case X_DIVIDED_BY_STRENGTH:
                        this.chance = (int) (this.randomFloat / strength);
                        break;

                    default:
                        break;
                }

                WorldGenerator worldGeneratorBrownShrooms = new WorldGenBush(Blocks.BROWN_MUSHROOM);
                WorldGenerator worldGeneratorRedShrooms = new WorldGenBush(Blocks.RED_MUSHROOM);

                this.loops = (this.strengthFactor > 0f) ? (int) (this.strengthFactor * strength) : this.loops;
                for (int i = 0; i < this.loops; i++) {
                    if (rand.nextInt(this.chance) == 0) {

                        int intX = worldX + rand.nextInt(16);// + 8;
                        int intY = rand.nextInt(this.maxY);
                        int intZ = worldZ + rand.nextInt(16);// + 8;

                        if (intY <= this.maxY) {

                            if (rand.nextBoolean()) {
                                worldGeneratorBrownShrooms.generate(rtgWorld.world, rand, new BlockPos(intX, intY, intZ));
                            }
                            else {
                                worldGeneratorRedShrooms.generate(rtgWorld.world, rand, new BlockPos(intX, intY, intZ));
                            }
                        }
                    }
                }
            }
        }
    }

    public enum RandomType {
        ALWAYS_GENERATE,
        USE_CHANCE_VALUE,
        X_DIVIDED_BY_STRENGTH
    }
}
