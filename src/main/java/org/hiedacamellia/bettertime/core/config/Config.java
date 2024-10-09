package org.hiedacamellia.bettertime.core.config;


import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    public static ModConfigSpec configSpec;
    public static ModConfigSpec.BooleanValue showDateHud, showDayTitle;
    public static ModConfigSpec.ConfigValue<Integer> dateX, dateY, dateColor, hourAdd, minAdd;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("Better Time");

        builder.comment("Time format setting");
        hourAdd = builder.define("The hour addition", 0);
        minAdd = builder.define("The minute addition", 0);

        builder.comment("If true, a date hud will show on your hud");
        showDateHud = builder.define("Show Date Hud", true);
        dateX = builder.define("Date Hud X", 10);
        dateY = builder.define("Date Hud Y", 10);
        dateColor = builder.define("Date Hud Color", -1);

        builder.comment("If true, a day title will show on your screen next days");
        showDayTitle = builder.define("Show day title", true);

        configSpec = builder.build();
    }
}
