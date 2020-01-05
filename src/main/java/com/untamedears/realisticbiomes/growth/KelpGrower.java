package com.untamedears.realisticbiomes.growth;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class KelpGrower extends RangedColumnPlantGrower {

	public KelpGrower() {
		this(10, 5, Material.WATER);
	}

	protected KelpGrower(int maxHeight, int maxExtension, Material environment) {
		super(maxHeight, maxExtension, environment);
	}

	@Override
	protected Block growOnTop(Block block, int howMany) {
		Block top = super.growOnTop(block, howMany);
		Block secondBlock = top.getRelative(BlockFace.DOWN);

		if (secondBlock.getType() == Material.KELP) {
			secondBlock.setType(Material.KELP_PLANT);
		}

		return top;
	}
}
