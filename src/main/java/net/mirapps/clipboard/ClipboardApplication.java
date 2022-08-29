package net.mirapps.clipboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class ClipboardApplication implements ApplicationRunner {

    @Value("${clipboard.config.object-count:1}")
    private int objectCount;

    @Value("${clipboard.config.expire-seconds:0}")
    private int clipboardDataExpire;

    private final DataDto dataDto;

    public static void main(String[] args) {
        SpringApplication.run(ClipboardApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<Integer, String> objectMap = new TemporaryMap(objectCount);
        for (int i = 0; i < objectCount; i++) {
            objectMap.put(i, Strings.EMPTY);
        }
        dataDto.setValues(objectMap);

        if (clipboardDataExpire > 0) {
            final Set<Integer> keySet = objectMap.keySet();
            new Thread(() -> {
                while (true) {
                    keySet.stream().forEach(key -> {
                        final Long updateTime = TemporaryTimeValueMap.getUpdateTime(key);
                        if (updateTime != null && StringUtils.hasText(objectMap.get(key))){
                            final long diffTime = System.currentTimeMillis() - updateTime;
                            if (diffTime > clipboardDataExpire * 1000) {
                                if (log.isDebugEnabled()) {
                                    log.debug("expired delete Data idx-{} : {}", key,  objectMap.get(key));
                                }
                                objectMap.put(key, Strings.EMPTY);
                            }
                        }
                    });
                    try {
                        if (log.isDebugEnabled()) {
                            log.debug("expire check!!");
                        }
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        log.error("error : ", e);
                    }
                }
            }).start();
        }
    }
}
