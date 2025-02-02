package drparanoya.hausehud.addon.hud;

import drparanoya.hausehud.addon.HauseHUD;
import meteordevelopment.meteorclient.renderer.GL;
import meteordevelopment.meteorclient.renderer.Renderer2D;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.hud.HudElement;
import meteordevelopment.meteorclient.systems.hud.HudElementInfo;
import meteordevelopment.meteorclient.systems.hud.HudRenderer;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.settings.ColorSetting;
import meteordevelopment.meteorclient.settings.DoubleSetting;
import meteordevelopment.meteorclient.utils.render.color.Color;
import meteordevelopment.meteorclient.utils.render.color.SettingColor;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class VisionBlocker extends HudElement {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Double> scale = sgGeneral.add(new DoubleSetting.Builder()
        .name("Scale")
        .description("How much of your vision should we block?")
        .defaultValue(1)
        .min(0)
        .sliderRange(0, 10)
        .build()
    );
    private final Setting<SettingColor> imageColor = sgGeneral.add(new ColorSetting.Builder()
        .name("Color")
        .description("Color of the image.")
        .defaultValue(new SettingColor())
        .build()
    );

    private final Identifier image = Identifier.of("hausehud", "image.png");
    public static final HudElementInfo<VisionBlocker> INFO = new HudElementInfo<>(HauseHUD.HUD_GROUP, "vision-blocker", "Renders an image to block your vision.", VisionBlocker::new);

    public VisionBlocker() {
        super(INFO);
    }

    @Override
    public void render(HudRenderer renderer) {
        setSize(scale.get() * 255, scale.get() * 255);
        MatrixStack matrixStack = new MatrixStack();
        GL.bindTexture(image);
        Renderer2D.TEXTURE.begin();
        Renderer2D.TEXTURE.texQuad(this.x, this.y, scale.get() * 255, scale.get() * 255, imageColor.get());
        Renderer2D.TEXTURE.render(matrixStack);
    }
}
