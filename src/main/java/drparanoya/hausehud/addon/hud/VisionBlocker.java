package drparanoya.hausehud.addon.hud;

import drparanoya.hausehud.addon.HauseHUD;
import meteordevelopment.meteorclient.renderer.GL;
import meteordevelopment.meteorclient.renderer.Renderer2D;
import meteordevelopment.meteorclient.systems.hud.HudElement;
import meteordevelopment.meteorclient.systems.hud.HudElementInfo;
import meteordevelopment.meteorclient.systems.hud.HudRenderer;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.settings.ColorSetting;
import meteordevelopment.meteorclient.settings.DoubleSetting;
import meteordevelopment.meteorclient.utils.render.color.SettingColor;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class VisionBlocker extends HudElement {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Double> scale = sgGeneral.add(new DoubleSetting.Builder()
        .name("Scale")
        .description("Scale of image.png")
        .defaultValue(1)
        .min(0)
        .sliderRange(0, 10)
        .build()
    );
    private final Setting<SettingColor> imageColor = sgGeneral.add(new ColorSetting.Builder()
        .name("Color")
        .description("Color of image.png.")
        .defaultValue(new SettingColor())
        .build()
    );
    private final Setting<Double> width = sgGeneral.add(new DoubleSetting.Builder()
        .name("Width")
        .description("Width of image.png")
        .defaultValue(255)
        .min(0)
        .noSlider()
        .decimalPlaces(0)
        .build()
    );
    private final Setting<Double> height = sgGeneral.add(new DoubleSetting.Builder()
        .name("Height")
        .description("Height of image.png")
        .defaultValue(255)
        .min(0)
        .noSlider()
        .decimalPlaces(0)
        .build()
    );

    private final Identifier image = Identifier.of("hausehud", "image.png");
    public static final HudElementInfo<VisionBlocker> INFO = new HudElementInfo<>(HauseHUD.HUD_GROUP, "vision-blocker", "Renders an image to block your vision.", VisionBlocker::new);

    public VisionBlocker() {
        super(INFO);
    }

    @Override
    public void render(HudRenderer renderer) {
        setSize(width.get() * scale.get(), height.get() * scale.get());
        MatrixStack matrixStack = new MatrixStack();
        GL.bindTexture(image);
        Renderer2D.TEXTURE.begin();
        Renderer2D.TEXTURE.texQuad(this.x, this.y, width.get() * scale.get(), height.get() * scale.get(), imageColor.get());
        Renderer2D.TEXTURE.render(matrixStack);
    }
}
