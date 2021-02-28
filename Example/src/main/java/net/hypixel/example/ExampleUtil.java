package net.hypixel.example;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.reply.AbstractReply;
import net.hypixel.example.http.ApacheHTTPClient;

import java.util.UUID;
import java.util.function.BiConsumer;

public class ExampleUtil {

    public static final HypixelAPI API;

    static {
        String key = System.getProperty("apiKey", "64bd424e-ccb0-42ed-8b66-6e42a135afb4"); // arbitrary key, replace with your own to test or use the property
        API = new HypixelAPI(UUID.fromString(key), new ApacheHTTPClient());
    }

    public static final UUID HYPIXEL = UUID.fromString("f7c77d99-9f15-4a66-a87d-c4a51ef30d19");

    /**
     * Keep the program alive till we explicitly exit.
     */
    public static void await() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T extends AbstractReply> BiConsumer<T, Throwable> getTestConsumer() {
        return (result, throwable) -> {
            if (throwable != null) {
                throwable.printStackTrace();
                System.exit(0);
                return;
            }

            System.out.println(result);

            System.exit(0);
        };
    }
}
