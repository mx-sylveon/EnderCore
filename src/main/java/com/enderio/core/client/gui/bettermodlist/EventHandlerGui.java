package com.enderio.core.client.gui.bettermodlist;

import java.lang.reflect.Field;

import com.enderio.core.common.Handlers.Handler;
import com.google.common.base.Throwables;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.client.GuiModList;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Handler
public class EventHandlerGui {
  private static Field _mainMenu, _parentScreen;

  static {
    try {
      _mainMenu = GuiModList.class.getDeclaredField("mainMenu");
      _mainMenu.setAccessible(true);      
    //TODO: 1.8
//      _parentScreen = GuiIngameModOptions.class.getDeclaredField("parentScreen");
//      _parentScreen.setAccessible(true);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @SubscribeEvent
  public void onGuiOpen(GuiOpenEvent event) {
    if (event.gui == null) {
      return;
    }
    try {
      if (event.gui.getClass() == GuiModList.class) {
        event.setCanceled(true);
        Minecraft.getMinecraft().displayGuiScreen(new GuiEnhancedModList((GuiScreen) _mainMenu.get(event.gui)));
      }
      //TODO: 1.8
//      if (event.gui.getClass() == GuiIngameModOptions.class) {
//        event.setCanceled(true);
//        Minecraft.getMinecraft().displayGuiScreen(new GuiEnhancedModList((GuiScreen) _parentScreen.get(event.gui)));
//      }
    } catch (Exception e) {
      Throwables.propagate(e);
    }
  }
}
