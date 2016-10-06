package appalachia.block.planks;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import appalachia.api.AppalachiaBlocks;
import appalachia.block.IAppalachiaBlock;

public class BlockPlanksAutumnCoffee extends AppalachiaBlockPlanks implements IAppalachiaBlock {

    public BlockPlanksAutumnCoffee() {

        super("planks.autumn.coffee");
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {

        return Item.getItemFromBlock(state.getValue(DOUBLE).booleanValue() ? AppalachiaBlocks.slab_autumn_coffee : this);
    }

    @Override
    public String registryName() {

        return super.registryName();
    }
}