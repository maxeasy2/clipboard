package net.mirapps.clipboard;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Getter
@Setter
@ToString
public class DataDto {
    private Map<Integer, String> values;
}
