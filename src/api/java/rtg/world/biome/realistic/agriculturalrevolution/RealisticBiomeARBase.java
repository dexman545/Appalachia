package rtg.world.biome.realistic.agriculturalrevolution;

import net.minecraft.world.biome.Biome;

import net.minecraftforge.fml.common.Loader;

import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;

public abstract class RealisticBiomeARBase extends RealisticBiomeBase {

    public static RealisticBiomeBase arBambooGrove;
    public static RealisticBiomeBase arCoralReef;
    public static RealisticBiomeBase arDeepReef;
    public static RealisticBiomeBase arKelpForest;
    public static RealisticBiomeBase arOrchard;
    public static RealisticBiomeBase arTropicalHills;

    public RealisticBiomeARBase(Biome b, Biome riverbiome) {

        super(b, riverbiome);

        this.lavaSurfaceLakeChance = 0;
    }

    @Override
    public String modSlug() {
        return "cookingplus";
    }

    public static void addBiomes() {

        if (Loader.isModLoaded("CookingPlus")) {

            for (Biome biome : Biome.REGISTRY) {

                if (biome.getBiomeName().isEmpty()) {
                    Logger.warn("Biome ID %d has no name.", Biome.getIdForBiome(biome));
                    continue;
                }

                String biomeName = biome.getBiomeName();
                String biomeClass = biome.getBiomeClass().getName();

                if (biomeName.equals("Orchard") && biomeClass.equals("CookingPlus.generation.CookingPlusOrchardBiome")) {
                    arOrchard = new RealisticBiomeAROrchard(biome);
                }
                else if (biomeName.equals("Bamboo Grove") && biomeClass.equals("CookingPlus.generation.CookingPlusBambooBiome")) {
                    arBambooGrove = new RealisticBiomeARBambooGrove(biome);
                }
                else if (biomeName.equals("Kelp Forest") && biomeClass.equals("CookingPlus.generation.CookingPlusKelpForestBiome")) {
                    arKelpForest = new RealisticBiomeARKelpForest(biome);
                }
                else if (biomeName.equals("Coral Reef") && biomeClass.equals("CookingPlus.generation.CookingPlusCoralReefBiome")) {
                    arCoralReef = new RealisticBiomeARCoralReef(biome);
                }
                else if (biomeName.equals("Tropical Hills") && biomeClass.equals("CookingPlus.generation.CookingPlusTropicalBiome")) {
                    arTropicalHills = new RealisticBiomeARTropicalHills(biome);
                }
                else if (biomeName.equals("Deep Reef") && biomeClass.equals("CookingPlus.generation.CookingPlusDeepReefBiome")) {
                    arDeepReef = new RealisticBiomeARDeepReef(biome);
                }
            }
        }
    }
}