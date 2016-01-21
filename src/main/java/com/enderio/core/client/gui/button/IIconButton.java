package com.enderio.core.client.gui.button;

import org.lwjgl.opengl.GL11;

import com.enderio.core.client.render.RenderUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;

public class IIconButton extends GuiButton {

  public static final int DEFAULT_WIDTH = 24;
  public static final int HWIDTH = DEFAULT_WIDTH / 2;
  public static final int DEFAULT_HEIGHT = 24;
  public static final int HHEIGHT = DEFAULT_HEIGHT / 2;

  protected int hwidth;
  protected int hheight;

  protected TextureAtlasSprite icon;
  protected ResourceLocation texture;

  public IIconButton(FontRenderer fr, int id, int x, int y, TextureAtlasSprite icon, ResourceLocation texture) {
    super(id, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, "");
    hwidth = HWIDTH;
    hheight = HHEIGHT;
    this.icon = icon;
    this.texture = texture;
  }

  public void setSize(int width, int height) {
    this.width = width;
    this.height = height;
    hwidth = width / 2;
    hheight = height / 2;
  }

  public TextureAtlasSprite getIcon() {
    return icon;
  }

  public void setIcon(TextureAtlasSprite icon) {
    this.icon = icon;
  }

  public ResourceLocation getTexture() {
    return texture;
  }

  public void setTexture(ResourceLocation textureName) {
    this.texture = textureName;
  }

  /**
   * Draws this button to the screen.
   */
  @SuppressWarnings("synthetic-access")
  @Override
  public void drawButton(Minecraft par1Minecraft, int mouseX, int mouseY) {
    if (visible) {

      RenderUtil.bindTexture("textures/gui/widgets.png");
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + width && mouseY < this.yPosition + height;
      int hoverState = getHoverState(hovered);

      // x, y, u, v, width, height

      // top half
      drawTexturedModalRect(xPosition, yPosition, 0, 46 + hoverState * 20, hwidth, hheight);
      drawTexturedModalRect(xPosition + hwidth, yPosition, 200 - hwidth, 46 + hoverState * 20, hwidth, hheight);

      // bottom half
      drawTexturedModalRect(xPosition, yPosition + hheight, 0, 66 - hheight + (hoverState * 20), hwidth, hheight);
      drawTexturedModalRect(xPosition + hwidth, yPosition + hheight, 200 - hwidth, 66 - hheight + (hoverState * 20), hwidth, hheight);

      mouseDragged(par1Minecraft, mouseX, mouseY);

      if (icon != null && texture != null) {
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        RenderUtil.bindTexture(texture);
        int xLoc = xPosition + 2;
        int yLoc = yPosition + 2;
        drawTexturedModalRect(xLoc, yLoc, icon, width - 4, height - 4);        
        GL11.glPopAttrib();
      }

    }
  }
}
