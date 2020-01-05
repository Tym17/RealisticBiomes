package com.untamedears.realisticbiomes.growth;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Bamboo;

public class BambooGrower extends RangedColumnPlantGrower {
	protected static final int LEAVES_AMOUNT = 3;
	protected static final int LARGE_LEAVES_START_HEIGHT = 5;

	public BambooGrower() {
		this(12, 4);
	}

	protected BambooGrower(int maxHeight, int maxExtension) {
		super(maxHeight, maxExtension);
	}

	@Override
	protected Block growOnTop(Block block, int howMany) {
		Block highestBlock = super.growOnTop(block, howMany);
		handleProperLeafGrowth(block, highestBlock);

		return highestBlock;
	}

	// according to https://minecraft.gamepedia.com/Bamboo#Appearance
	protected void handleProperLeafGrowth(Block block, Block highestBlock) {
		int actualHeight = super.getExtremitiesDistance(block) + 1;
		int leavesLeft = LEAVES_AMOUNT >= actualHeight ? actualHeight - 1 : LEAVES_AMOUNT;
		Block underBlock = highestBlock;
		Bamboo.Leaves leavesType = actualHeight >= LARGE_LEAVES_START_HEIGHT ?
				Bamboo.Leaves.LARGE : Bamboo.Leaves.SMALL;

		// Makes Bamboo growth O(2n) but prevents forking column based growth into similar yet different code.
		while (underBlock.getType() == highestBlock.getType()) {
			Bamboo data = (Bamboo)underBlock.getBlockData();
			if (leavesLeft != 0) {
				data.setLeaves(leavesType);
				leavesLeft--;
				if (leavesLeft == 1) {
					leavesType = Bamboo.Leaves.SMALL;
				}
			} else {
				data.setLeaves(Bamboo.Leaves.NONE);
			}
			underBlock.setBlockData(data);
			underBlock = underBlock.getRelative(BlockFace.DOWN);
		}
	}
}
