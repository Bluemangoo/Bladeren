package top.leavesmc.Bladeren.mixin.clientcommands.fishCommand;

import net.earthcomputer.clientcommands.Configs;
import net.earthcomputer.clientcommands.MultiVersionCompat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import top.hendrixshen.magiclib.dependency.api.annotation.Dependencies;
import top.hendrixshen.magiclib.dependency.api.annotation.Dependency;
import top.leavesmc.Bladeren.ModInfo;
import top.leavesmc.Bladeren.clientcommands.RandomManager;

@Dependencies(and = @Dependency(ModInfo.CLIENTCOMMANDS_MOD_ID))
@Mixin(Configs.class)
public class MixinConfigs {

    @Redirect(
            method = "conditionLessThan1_20",
            at = @At(value = "INVOKE", target = "Lnet/earthcomputer/clientcommands/MultiVersionCompat;getProtocolVersion()I"),
            remap = false
    )
    private static int getProtocolVersion(MultiVersionCompat instance) {
        if (top.leavesmc.Bladeren.config.Configs.fishCommand && RandomManager.isFishCommand()) {
            return 762;
        } else {
            return instance.getProtocolVersion();
        }
    }
}
