package fr.axopen.templatejava.controller;

import fr.axopen.templatejava.dto.BotInfoDTO;
import fr.axopen.templatejava.dto.GameBotDTO;
import fr.axopen.templatejava.dto.PlayResponseDTO;
import fr.axopen.templatejava.service.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bots")
public class BotController {

    private final BotService botService;

    @GetMapping("/info")
    public ResponseEntity<BotInfoDTO> getBotInfo() {
        BotInfoDTO botInfo = botService.getBotInfo();
        return ResponseEntity.ok(botInfo);
    }

    @PostMapping("/play")
    public ResponseEntity<PlayResponseDTO> play(@RequestBody GameBotDTO gameBotDTO) {
        PlayResponseDTO playResponse = botService.play(gameBotDTO);
        return ResponseEntity.ok(playResponse);
    }
}
