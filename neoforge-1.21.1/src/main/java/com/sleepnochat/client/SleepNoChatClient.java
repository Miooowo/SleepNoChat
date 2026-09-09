package com.sleepnochat.client;

import com.sleepnochat.SleepNoChat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.InBedChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

@EventBusSubscriber(modid = SleepNoChat.MOD_ID, value = Dist.CLIENT)
public final class SleepNoChatClient {
    private SleepNoChatClient() {
    }

    @SubscribeEvent
    public static void onScreenOpening(ScreenEvent.Opening event) {
        Screen incoming = event.getNewScreen();
        if (incoming instanceof InBedChatScreen && !(incoming instanceof SleepNoChatScreen)) {
            event.setNewScreen(new SleepNoChatScreen());
        }
    }

    @SubscribeEvent
    public static void onRenderGuiLayer(RenderGuiLayerEvent.Pre event) {
        if (!event.getName().equals(VanillaGuiLayers.CHAT)) {
            return;
        }
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null && player.isSleeping()) {
            event.setCanceled(true);
        }
    }
}
