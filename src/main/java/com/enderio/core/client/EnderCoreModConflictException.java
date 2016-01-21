package com.enderio.core.client;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraftforge.fml.client.CustomModLoadingErrorDisplayException;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EnderCoreModConflictException extends CustomModLoadingErrorDisplayException {

  private static final long serialVersionUID = 1L;
  
  private final String[] msgs;

  public EnderCoreModConflictException(String[] msgs) {
    super(msgs[0], new RuntimeException());
    this.msgs = msgs;
  }

  @Override
  public void initGui(GuiErrorScreen errorScreen, FontRenderer fontRenderer) {
  }

  @Override
  public void drawScreen(GuiErrorScreen errorScreen, FontRenderer fontRenderer, int mouseRelX, int mouseRelY, float tickTime) {
    int y = errorScreen.height / 2 - msgs.length * 5;
    for (String msg : msgs) {
      errorScreen.drawCenteredString(fontRenderer, msg, errorScreen.width / 2, y, 0xFFFFFF);
      y += 10;
    }
  }
}
