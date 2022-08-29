package net.mirapps.clipboard;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
@RequiredArgsConstructor
public class ClipboardApplication implements ApplicationRunner {

    @Value("${clipboard.config.object-count:1}")
    private int objectCount;

    private final DataDto dataDto;

    public static void main(String[] args) {
        SpringApplication.run(ClipboardApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<Integer, String> objectMap = new LinkedHashMap<>(objectCount);
        for (int i = 0; i < objectCount; i++) {
            objectMap.put(i, "");
        }
        dataDto.setValues(objectMap);
    }
}
