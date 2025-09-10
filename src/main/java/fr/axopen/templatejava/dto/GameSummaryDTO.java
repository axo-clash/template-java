package fr.axopen.templatejava.dto;

import fr.axopen.templatejava.enums.GameFailureReasonEnum;
import fr.axopen.templatejava.enums.GameStatusEnum;
import fr.axopen.templatejava.enums.WinnerEnum;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public record GameSummaryDTO(
        Long id,
        List<BotSummaryDTO> bots,
        WinnerEnum winner,
        Integer turns,
        Instant createdAt,
        GameFailureReasonEnum failureReason,
        GameStatusEnum status
) implements Serializable {
}
