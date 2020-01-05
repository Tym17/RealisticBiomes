package com.untamedears.realisticbiomes.growth;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Random;

public class RangedColumnPlantGrower extends ColumnPlantGrower {

	protected int maxExtension;

	RangedColumnPlantGrower(int maxHeight, int maxExtension) {
		this(maxHeight, maxExtension, Material.AIR);
	}

	RangedColumnPlantGrower(int maxHeight, int maxExtension, Material environment) {
		super(maxHeight, environment);
		this.maxExtension = maxExtension;
	}

	protected int getExtension(Block block) {
		if (maxExtension == 0) {
			return 0;
		}
		long seed = block.getX() + block.getZ() + block.getType().hashCode();

		// +1 because bound is exclusive
		return new Random(seed).nextInt(maxExtension + 1);
	}

	@Override
	protected int getSupposedHeight(Block block) {
		return maxHeight + getExtension(block);
	}
}
