package fr.axopen.templatejava.service;

import fr.axopen.templatejava.dto.BotInfoDTO;
import fr.axopen.templatejava.dto.GameBotDTO;
import fr.axopen.templatejava.dto.PlayResponseDTO;
import fr.axopen.templatejava.enums.ActionEnum;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class BotService {
    
    private final SecureRandom random = new SecureRandom();
    
    public BotInfoDTO getBotInfo() {
        return new BotInfoDTO("Java Template Bot", "1.0.0");
    }
    
    public PlayResponseDTO play(GameBotDTO gameBotDTO) {
        ActionEnum[] actions = ActionEnum.values();
        ActionEnum randomAction = actions[random.nextInt(actions.length)];
        return new PlayResponseDTO(randomAction);
    }
}
