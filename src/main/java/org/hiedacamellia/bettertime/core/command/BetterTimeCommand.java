package org.hiedacamellia.bettertime.core.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.TimeArgument;


import static org.hiedacamellia.bettertime.Bettertime.*;

public class BetterTimeCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("bettertime").requires((sourceStack) -> sourceStack.hasPermission(2))
                .then(Commands.literal("add").then(Commands.argument("time", TimeArgument.time()).executes(ctx -> {
                    return addTime(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "time"));
                })))

                .then(Commands.literal("set")
                        .then(Commands.argument("time", IntegerArgumentType.integer()).executes(ctx -> {
                            return setTime(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "time"));
                        }).then(Commands.argument("days", IntegerArgumentType.integer()).executes(ctx -> {
                            return setTime(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "time"), IntegerArgumentType.getInteger(ctx, "days"));
                        })).then(Commands.literal("day").executes(ctx -> {
                            return setDay(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "time"));
                        }))).then(Commands.literal("day").executes(ctx -> {
                            return setTime(ctx.getSource(), 1000);
                        }).then(Commands.argument("days", IntegerArgumentType.integer()).executes(ctx -> {
                            return setTime(ctx.getSource(), 1000, IntegerArgumentType.getInteger(ctx, "days"));
                        }))).then(Commands.literal("noon").executes(ctx -> {
                            return setTime(ctx.getSource(), 6000);
                        }).then(Commands.argument("days", IntegerArgumentType.integer()).executes(ctx -> {
                            return setTime(ctx.getSource(), 6000, IntegerArgumentType.getInteger(ctx, "days"));
                        }))).then(Commands.literal("night").executes(ctx -> {
                            return setTime(ctx.getSource(), 13000);
                        }).then(Commands.argument("days", IntegerArgumentType.integer()).executes(ctx -> {
                            return setTime(ctx.getSource(), 13000, IntegerArgumentType.getInteger(ctx, "days"));
                        }))).then(Commands.literal("midnight").executes(ctx -> {
                            return setTime(ctx.getSource(), 18000);
                        }).then(Commands.argument("days", IntegerArgumentType.integer()).executes(ctx -> {
                            return setTime(ctx.getSource(), 18000, IntegerArgumentType.getInteger(ctx, "days"));
                        }))).then(Commands.literal("morning").executes(ctx -> {
                            return setTime(ctx.getSource(), 3000);
                        }).then(Commands.argument("days", IntegerArgumentType.integer()).executes(ctx -> {
                            return setTime(ctx.getSource(), 3000, IntegerArgumentType.getInteger(ctx, "days"));
                        }))).then(Commands.literal("evening").executes(ctx -> {
                            return setTime(ctx.getSource(), 15000);
                        }).then(Commands.argument("days", IntegerArgumentType.integer()).executes(ctx -> {
                            return setTime(ctx.getSource(), 15000, IntegerArgumentType.getInteger(ctx, "days"));
                        }))))

                .then(Commands.literal("query").executes(ctx -> {
                    return queryTime(ctx.getSource());
                }))
        );
    }
}
