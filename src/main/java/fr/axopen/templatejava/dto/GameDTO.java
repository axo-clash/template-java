package fr.axopen.templatejava.dto;

import fr.axopen.templatejava.enums.Winner;
import fr.axopen.templatejava.enums.EndReason;
import fr.axopen.templatejava.enums.FailureReason;
import fr.axopen.templatejava.enums.GameStatus;
import java.util.List;

public record GameDTO(
        Long id,
        BotDTO firstBot,
        BotDTO secondBot,
        List<List<CaseDTO>> board,
        Integer currentTurn,
        List<ActionDTO> actions,
        Winner winner,
        EndReason endReason,
        FailureReason failureReason,
        GameStatus status) {
}