package io.github.bizcub.dropRepair.config;

public interface Config {
    static Config get() {
        return Holder.INSTANCE;
    }

    static void set(final Config config) {
        if (config != null) {
            Holder.INSTANCE = config;
        }
    }

    class Holder {
        private static Config INSTANCE = new Config() { };
    }

    default float repairFraction() {
        return 0.125F;
    }

    default double radius() {
        return 1.5;
    }

    default int checkInterval() {
        return 10;
    }
}
