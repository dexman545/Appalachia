package appalachia.item.slabs;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

import appalachia.api.AppalachiaBlocks;
import appalachia.block.planks.BlockPlanksOakH180S60;

public class ItemSlabOakH180S60 extends AppalachiaItemSlab {

    public ItemSlabOakH180S60(Block block) {

        super(block);
    }

    @Override
    protected IBlockState getFullBlock() {

        return AppalachiaBlocks.planks_oak_180_60.getDefaultState().withProperty(BlockPlanksOakH180S60.DOUBLE, Boolean.valueOf(true));
    }
}