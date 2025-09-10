package fr.axopen.templatejava.dto;

import fr.axopen.templatejava.enums.GameEndReasonEnum;
import fr.axopen.templatejava.enums.GameFailureReasonEnum;
import fr.axopen.templatejava.enums.GameStatusEnum;
import fr.axopen.templatejava.enums.WinnerEnum;

import java.io.Serializable;
import java.util.List;

public record GameDTO(
        Long id,
        BotDTO firstBot,
        BotDTO secondBot,
        CaseDTO[][] board,
        int currentTurn,
        List<ActionDTO> actions,
        WinnerEnum winner,
        GameEndReasonEnum endReason,
        GameFailureReasonEnum failureReason,
        GameStatusEnum status
) implements Serializable {
}
