/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package cn.sh1rocu.futurecherry.util.bop;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

//from Biomes O' Plenty
public interface IBlockPosQuery {
    boolean matches(IWorld world, BlockPos pos);
}