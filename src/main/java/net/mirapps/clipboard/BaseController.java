package net.mirapps.clipboard;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BaseController {

    private final DataDto dataDto;

    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "0") int idx) {
        model.addAttribute("data", dataDto.getValues());
        model.addAttribute("page", idx + 1);
        model.addAttribute("idx", idx);
        model.addAttribute("textObj", dataDto.getValues().get(idx));
        return "index";
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity save(@RequestBody Map<String,String> data) {
        final Map<Integer, String> values = dataDto.getValues();
        values.put(Integer.parseInt(data.get("idx")), data.get("textObj"));
        return ResponseEntity.noContent().build();
    }
}
