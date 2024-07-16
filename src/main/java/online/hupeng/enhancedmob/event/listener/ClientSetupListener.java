package online.hupeng.enhancedmob.event.listener;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import online.hupeng.enhancedmob.EnhancedMob;
import online.hupeng.enhancedmob.entitys.EnhancedEntityType;
import online.hupeng.enhancedmob.entitys.renderer.EnhancedZombieRenderer;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = EnhancedMob.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetupListener {

    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(EnhancedEntityType.ENHANCE_ZOMBIE.get(), EnhancedZombieRenderer::new);
        LOGGER.info("Load mod EnhancedMob on the client side");
    }
}
